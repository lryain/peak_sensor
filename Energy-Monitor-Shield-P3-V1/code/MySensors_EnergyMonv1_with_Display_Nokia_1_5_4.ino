/*
This sketch is for a Energy Monitor v 1.0
http://www.seeedstudio.com/depot/Energy-Monitor-Shield-Monitoring-System-with-Nokia-LCD-Screen-p-1775.html
and MySensors 1.5

 modified
 1 December 2015
 by greengo
 */

#include <SPI.h>
#include <LCD5110_Graph_SPI.h>
#include <MySensor.h>  
#include "EmonLib.h"

#define WINDOW 15

// Define a static node address, remove if you want auto address assignment
#define NODE_ADDRESS   40

#define NODE_REPEAT true
#define RF24_PA_LEVEL RF24_PA_MAX

// How many milli seconds between each measurement
#define MEASURE_INTERVAL    1000 //for Debug 1 sec

// FORCE_TRANSMIT_INTERVAL, this number of times of wakeup, the sensor is forced to report all values to the controller
#define FORCE_TRANSMIT_INTERVAL 300 //every 5 min

unsigned long SLEEP_TIME = 1000; // Sleep time between reads (in milliseconds)

int TEMP_TRANSMIT_THRESHOLD = 0; //35 Watt

#define CHILD_ID_POWER 0
#define CHILD_ID_FLOATING_CUR_SENSOR 1 //floating sensor

LCD5110 myGLCD(5,6,3);
extern unsigned char SmallFont[];

EnergyMonitor emon;

MyTransportNRF24 transport(7, 8, RF24_PA_LEVEL); //for EMv1
MySensor gw(transport);

unsigned long CHECK_TIME = millis();

MyMessage IrmsMsg(CHILD_ID_POWER, V_WATT);
MyMessage kWhMsg(CHILD_ID_POWER, V_KWH);
MyMessage FloatingMsg(CHILD_ID_FLOATING_CUR_SENSOR, V_VAR1);

// Global settings
int measureCount = 0;
boolean transmission_occured = false;

// Storage of old measurements
float realWatt = 0;
float realkWt = 0;
double Irms;
float lastIrms = 0;
int Floating;
float PMin = 99;
float PMax = 0;

int stateFloating = 0;

void setup()  
{ 
  myGLCD.InitLCD();
  myGLCD.setFont(SmallFont);
  myGLCD.update();

 double cIrms = 0;  
  //  emon.current(0, 111.1);       // Current: input pin, calibration.
//  emon.current(0, 66.5); 
    emon.current(0, 71.2); //72.6 
    //72.8 = 103.9 
    //71.8 = 100.8

 double Irms[WINDOW];   
         Irms[0] = emon.calcIrms(1480); // the first value in the measurement clearly "crooked"
      //Serial.println("calculate delta");
      for (int i=0; i<WINDOW; i++) {
        Irms[i] = emon.calcIrms(1480);
     cIrms = cIrms + Irms[i];
        delay(100);      
      } 
      
#ifdef NODE_ADDRESS
   gw.begin(incomingMessage, NODE_ADDRESS, NODE_REPEAT);
#else
  gw.begin(incomingMessage, AUTO, NODE_REPEAT);
#endif    
  gw.sendSketchInfo("Energy Monitor v1.0", "1.5.4");
    
  // Register all sensors to gw (they will be created as child devices)

  gw.present(CHILD_ID_POWER, S_POWER); 
  gw.present(CHILD_ID_FLOATING_CUR_SENSOR, S_CUSTOM);
// gw.wait(1000);
  sendPowerMeasurements(false);

}

void loop()      
{      
   gw.process(); 
   
   displayUpdate();
   
  unsigned long NOW_TIME = millis();
  
  if(NOW_TIME - CHECK_TIME >= SLEEP_TIME)
  { 
  measureCount ++;
  bool forceTransmit = false;
  transmission_occured = false;
 
  
  if (measureCount > FORCE_TRANSMIT_INTERVAL) { // force a transmission
    forceTransmit = true; 
    measureCount = 0;
  }
     sendPowerMeasurements(forceTransmit);
   
  Serial.print(" measureCount: ");
  Serial.println(measureCount); 
  Serial.print(" ");
  
       CHECK_TIME = NOW_TIME;
     } 
  }

void sendPowerMeasurements(bool force)
{
  bool tx = force;

   Irms = emon.calcIrms(1480);  // Calculate Irms only
   realWatt  = (emon.Irms * 220);  // Extract Real Power into variable
//   realkWt   = (emon.Irms * 220)/1000;
   realkWt   = (Irms * 0.220);
   
         // fixing minimum and maximum values
  if (realkWt>PMax) PMax = realkWt;
  if (realkWt<PMin && realkWt>0.05) PMin = realkWt;       
 
  
// Set relay to last known state (using eeprom storage) 
  TEMP_TRANSMIT_THRESHOLD = gw.loadState(CHILD_ID_FLOATING_CUR_SENSOR);
  Floating = TEMP_TRANSMIT_THRESHOLD;
  
  Serial.print("TEMP_TRANSMIT_THRESHOLD: ");Serial.println(TEMP_TRANSMIT_THRESHOLD);
 
  float diffIrms = abs(lastIrms - Irms);

  Serial.print(F("IrmsDiff :"));Serial.println(diffIrms);

  if (diffIrms > TEMP_TRANSMIT_THRESHOLD) tx = true;

  if (tx) {
    measureCount = 0;
     
    Serial.print("Watt: ");Serial.println(realWatt);
    Serial.print("kWt : ");Serial.println(realkWt);

  gw.send(IrmsMsg.set(realWatt, 1));
  gw.send(kWhMsg.set(realkWt, 2));
  

    lastIrms = Irms;
    transmission_occured = true;
  }
  
  Serial.print("Irms : ");Serial.println(Irms);
  
}  
  // LCD Nokia 
 void displayUpdate()
 {
 char tbuf[8];
 char sbuf[12];
    
    dtostrf(realWatt,5,2,tbuf);
    sprintf(sbuf, " %s kWt", tbuf);
    myGLCD.print(sbuf, 20, 0);  
    myGLCD.print("PWR:", 0, 0);   
      
    dtostrf(Irms,5,2,tbuf);
    sprintf(sbuf, " %s Amp", tbuf);
    myGLCD.print(sbuf, 20, 10);  
    myGLCD.print("IRM:", 0, 10);  
    
    dtostrf(PMin,5,2,tbuf);
    sprintf(sbuf, " %s kWt", tbuf);
    myGLCD.print(sbuf, 20, 20);   
    myGLCD.print("MiN:", 0, 20);   
    
    dtostrf(PMax,5,2,tbuf);
    sprintf(sbuf, " %s kWt", tbuf);
    myGLCD.print(sbuf, 20, 30);  
    myGLCD.print("MaX:", 0, 30);  
    
    dtostrf(Floating,5,2,tbuf);
    sprintf(sbuf, " %s Watt", tbuf);
    myGLCD.print(sbuf, 20, 40);   
    myGLCD.print("flt:", 0, 40);  
    
    myGLCD.update(); 
    
}
//*********************************************
 void incomingMessage(const MyMessage &message) {
  // We only expect one type of message from controller. But we better check anyway.
  if (message.isAck()) {
     Serial.println("This is an ack from gateway");
  }

  if (message.type == V_VAR1) {
     // Change state
     stateFloating = message.getInt();
 
     // Store state in eeprom
     gw.saveState(CHILD_ID_FLOATING_CUR_SENSOR, stateFloating);
    
     // Write some debug info
     Serial.print("Incoming change for sensor:");
     Serial.print(message.sensor);
     Serial.print(", Delta =: ");
     Serial.println(message.getInt());
   //  Serial.print(", New status: ");
   //  Serial.println(message.getBool());
   } 
 }   




