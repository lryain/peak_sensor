/*

 MyPowerMeter
  This is a simple sketch where you can read de energy from 4 ac sensor  and  the temperatura an dhumidity
  and save in a struct, After that   you can see the data in the screen or automatily sent  the  struct  thought
   the NRF comunicacition
   
   Autor: Peteorito
   Date:22-3-2015
   
   It is a beta program only  for test some funtions
   


*/

#include <nRF24L01.h>
#include <RF24.h>
#include <RF24_config.h>


#include <SPI.h>
#include <Adafruit_GFX.h>
#include <Adafruit_PCD8544.h>

#include "DHT.h"
#include "EmonLib.h"                   // Include Emon Library



// Adafruit_PCD8544 display = Adafruit_PCD8544(D/C, CS, RST);
 Adafruit_PCD8544 display = Adafruit_PCD8544(8, 9, 10);
 
////RF24 radio(CE,CSN);
RF24 radio(6,7);
 
//---PINOUT DEFINITION
//i2C -> SCL=A5 and SDA=A4 
//SPI-> MOSI=11, MISO=12, CLK=13

const int BUZZER=5;
#define SENS1 A0 // GENERAL SENSOR 1
#define SENS2_LCD A1  // GENERAL SENSOR 1 OR LIGHT FOR LCD
const int CTS1= A2;        // analog input pin  current sensor 1
const int CTS2= A3;        // analog input pin  current sensor 2
const int CTS3= A6;        // analog input pin  current sensor 3
const int CTS4= A7;        // analog input pin  current sensor 4


const int RT_CLK= 2;
const int RT_DT = 3;
const int RT_SW = 4;



//--- CONST & TYPES
const uint64_t pipe = 0xE8E8F0F0E1LL;// CANAL
const  float ACVoltaje= 0.23;
unsigned long  d_read_sensor = 2000;//   retardo para el proximo refresco
unsigned long  d_LightLcd = 6000;//   retardo para el proximo refresco





typedef struct {
         byte Temp, Hum;
         float IrmsCTS[4];
         
}TData;


#define DHTTYPE DHT11   
TData  MyData;
DHT dht(SENS1, DHTTYPE);

EnergyMonitor emon1,emon2,emon3,emon4;  // Create an instance




//----------VAR---------------------
unsigned long clock = 0; // guarda el tiempo de milisegundo que lleva encendido
unsigned long  t_read_sensor = 0;  //  variable que guarda el tiempo para el proximo refresco
unsigned long  t_LightLcd = 0;  //  variable que guarda el tiempo para el proximo refresco
byte aux_data[82]; //vector aux for drawing last data

boolean flag_maxi=0;
boolean lcdState=HIGH;
boolean bt_okA;
boolean bt_ok;


volatile int lastEncoded = 0;
volatile int encoderValue = 0;
volatile int LastencoderValue=0;
bool change_menu=true;










void setup() {
  pinMode(BUZZER, OUTPUT);  
  pinMode(SENS2_LCD, OUTPUT);
  pinMode(RT_CLK, INPUT); // digitalWrite(RT_CLK, HIGH); //turn pullup resistor on
  pinMode(RT_DT, INPUT);//  digitalWrite(RT_DT, HIGH); //turn pullup resistor on
  pinMode(RT_SW, INPUT);//  digitalWrite(RT_SW, HIGH); //turn pullup resistor on
  
   attachInterrupt(0, updateEncoder, CHANGE); 
  attachInterrupt(1, updateEncoder, CHANGE);
 
   Serial.begin(9600);
   
  radio.begin();
  radio.openReadingPipe(1,pipe);
  radio.startListening();
   
  display.begin();  
  display.setContrast(60);
  display.clearDisplay();
  display.display();// si no pongo esto sale Logo
  display.setTextColor(WHITE, BLACK);
  display.print(".MyPowerMeter."); 
  display.setTextColor( BLACK,WHITE);
  display.setCursor(0,12); display.print(" SCT-013-030"); 
  display.setCursor(0,24); display.print(" SCT-013-000"); 
  display.setCursor(0,36); display.print("DHT11 Temp&Hum");
  display.display(); 
// digitalWrite(SENS2_LCD, HIGH);
    
  //http://openenergymonitor.org/emon/buildingblocks/calibration
  emon1.current(CTS1, 65);             // Current: input pin, calibration.//65 because   SCT-013-100
  emon2.current(CTS2, 30);             // Current: input pin, calibration.//30 because   SCT-013-030
  emon3.current(CTS3, 30);             // Current: input pin, calibration.//30 because   SCT-013-030
  emon4.current(CTS4, 30);             // Current: input pin, calibration.//30 because   SCT-013-030
  
 dht.begin();  
  delay(1000);
  digitalWrite(SENS2_LCD, LOW);


}

void loop() {
   clock = millis();      
   bt_ok=digitalRead(RT_SW);
   
  if( clock > t_read_sensor + d_read_sensor) {  
    	t_read_sensor = clock;
         ReadSensors() ;
        flag_maxi=!flag_maxi;        
        change_menu=true;
   
      if (LastencoderValue==encoderValue){
           if(encoderValue==0) {
              ShiftDataVector(MyData.Temp);
           }else{
              ShiftDataVector(MyData.IrmsCTS[encoderValue-1]);           
           }
   
      }else{
        ResetDataVector();
        LastencoderValue=encoderValue;
 
      }
   radio.write( &MyData, sizeof(MyData) );
 }

 
  
 ////// 
  if (change_menu==true){
               display.clearDisplay();
      ////  Temperature & Humidity
        if(encoderValue==0){
            WriteLCDTempHum();
            DrawData(aux_data);       
            DrawAxis();
           
       }
      ////  CTS1
      if(encoderValue==1){
           WriteLCDIrms(1);    
           DrawData(aux_data);
           DrawAxis();
                
           
       }
          ////  CTS2
      if(encoderValue==2){
           WriteLCDIrms(2);
           DrawData(aux_data);        
           DrawAxis();
            
       }
       
      ////  CTS3
      if(encoderValue==3){
            WriteLCDIrms(3);
            DrawData(aux_data);
            DrawAxis();
                  
            
       }
          ////  CTS4
      if(encoderValue==4){
           WriteLCDIrms(4);    
           DrawData(aux_data);       
           DrawAxis();
         
            
       }
     
       
       change_menu==false;  
  }
  
  
  
       
        
        
  
   
   

}
//************************************************************************************
//************************************************************************************
//************************************************************************************



////////////////////////////////////////////////////////////////////////////////////////////
///================  F U N C I O N E S   Y   P R O C E D I M I E N T O S ====================
////////////////////////////////////////////////////////////////////////////////////////////



//---------------------------------------------------------------------------------------------
//Draws a Axis
//---------------------------------------------------------------------------------------------
void DrawAxis(){
  
  int x,y;
 
  for(y=16;y<48;y++){
    display.drawPixel(1,y, BLACK);
  }
  
  for(x=0;x<84;x++){
    display.drawPixel(x,46, BLACK);
  }
  
  display.drawPixel(0,41, BLACK);//5
  display.drawPixel(0,36, BLACK);//10
  display.drawPixel(0,31, BLACK);//15
  display.drawPixel(0,26, BLACK);//20
  display.drawPixel(0,21, BLACK);//25
  display.drawPixel(0,16, BLACK);//30
  display.display();
}
//*******************************************************************************************




//---------------------------------------------------------------------------------------------
///Draws a vector in the screen
//---------------------------------------------------------------------------------------------
void DrawData(byte array[]){
  byte y;
  for(y=0;y<81;y++){   
   display.drawLine(y+2,46-array[y],y+3,46-array[y+1] ,BLACK);
  }
 // display.display();
}
//*******************************************************************************************

//---------------------------------------------------------------------------------------------
///Shift de data one position  in the vector
//---------------------------------------------------------------------------------------------
void ShiftDataVector(byte last){
  int i;
  for(i=0;i<81;i++){
      aux_data[i]=aux_data[i+1];
    }
    aux_data[81]=last;

}   
//*******************************************************************************************

    

//---------------------------------------------------------------------------------------------
///Reset all the position of one vector 
//---------------------------------------------------------------------------------------------
void ResetDataVector(){
  int i;
  for(i=0;i<=81;i++){
      aux_data[i]=0;
    }
  
}   
//*******************************************************************************************


//---------------------------------------------------------------------------------------------
//  Shows  Temperature and Humidity in the display
//---------------------------------------------------------------------------------------------
void  WriteLCDTempHum(){
     display.setTextColor(BLACK);
     display.setCursor(0,0);
     display.print("Temp "); display.print(MyData.Temp);display.print((char)223);display.println("C");
     display.print("Hume "); display.print( MyData.Hum);display.println("%");
    // display.display();
 }
 //*******************************************************************************************


//---------------------------------------------------------------------------------------------
//  Shows  Temperature and Humidity in the display
//---------------------------------------------------------------------------------------------
void WriteLCDIrms(byte n_sensor){
     display.setTextColor(BLACK);
     display.setCursor(0,0);
     display.print("CT"); display.print(byte(n_sensor));
     display.print(" I[A]:"); display.println(MyData.IrmsCTS[n_sensor-1],1);
     display.print("P[Kw]:"); display.print(MyData.IrmsCTS[n_sensor-1]*ACVoltaje,1);
    //display.display();

 }
 //*******************************************************************************************








//---------------------------------------------------------------------------------------------
//   Read all sensor and save in vars
//---------------------------------------------------------------------------------------------
void ReadSensors(){

      MyData.IrmsCTS[0]=emon1.calcIrms(1480);  // Calculate Irms only  
      MyData.IrmsCTS[1]=emon2.calcIrms(1480);  // Calculate Irms only  
      MyData.IrmsCTS[2]=emon3.calcIrms(1480);  // Calculate Irms only  
      MyData.IrmsCTS[3]=emon4.calcIrms(1480);  // Calculate Irms only  
      MyData.Hum=dht.readHumidity();
      MyData.Temp=dht.readTemperature();


}
//************************************************************************************************



//---------------------------------------------------------------------------------------------
//   Encoder Funcion
//---------------------------------------------------------------------------------------------
void updateEncoder()
{
  int MSB = digitalRead(RT_CLK); //MSB = bit mas significativo
  int LSB = digitalRead(RT_DT); //LSB = bit menos significativo

  int encoded = (MSB << 2) |LSB; //convierte el pin 2 en un solo valor
  int sum  = (lastEncoded << 2) | encoded; //añadiendolo al valor anterior

  if(sum == 0b1101 || sum == 0b0100 || sum == 0b0010 || sum == 0b1011) {
     if(encoderValue <5)
     { encoderValue ++;}
   
  }
     
     
  if(sum == 0b1110 || sum == 0b0111 || sum == 0b0001 || sum == 0b1000) {
    
   if(encoderValue >0){
     encoderValue --;

   }
 }

  lastEncoded = encoded; //guarda el valor para la proxima vez
  change_menu=true;
  Serial.println(encoderValue );
  change_menu=true;
}

