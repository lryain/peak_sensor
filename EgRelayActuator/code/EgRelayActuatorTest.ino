/* 
 * "EgRelayActuatorTest", "1.0 20160311"
Testsketch till MinAnslutPlug1.0...
by m26872

*/
#include <avr/pgmspace.h>
#include <MySensor.h>
#include <SPI.h>

//#define DEBUG

#define NODE_ID 170
#define CHECK_INTERVAL 3000  // (ms) Interval for internal status check.
#define REPORT_INTERVAL 60  // CHECK_INTERVAL*REPORT_INTERVAL = Status report interval of status parameters (temps, voltages and error codes) to the controller. E.g. 3s*20*60= 1 per 60min

#define ERR_CODE_VOLT12_L1 11
//#define ERR_CODE_VOLT12_L2 2  // Not yet used. Used to detect 12v relay fuse blown.
#define ERR_CODE_VOLT12_H1 13
#define ERR_CODE_VOLT33_L1 32
#define ERR_CODE_VOLT33_H1 34
#define ERR_CODE_AVRTEMP_L1 5
#define ERR_CODE_AVRTEMP_H1 60
//#define ERR_CODE_AVRTEMP_H2
#define ERR_CODE_THERM1_L1 6
#define ERR_CODE_THERM1_H1 45
//#define ERR_CODE_THERM1_H2
#define ERR_CODE_THERM2_L1 7
#define ERR_CODE_THERM2_H1 46
//#define ERR_CODE_THERM2_H2

#define VOLT12_L1 11.0
//#define VOLT12_L2 2  
#define VOLT12_H1 13.0
#define VOLT33_L1 3200
#define VOLT33_H1 3400
#define AVRTEMP_L1 5.0
#define AVRTEMP_H1 60.0
//#define AVRTEMP_H2 xx
#define THERM1_L1 5.0
#define THERM1_H1 70.0
//#define THERM1_H2 xx
#define THERM2_L1 5.0
#define THERM2_H1 70.0
//#define THERM2_H2 xx

#define OFF 0
#define ON 1
#define ALIVE 1
#define SLOW 2
#define FAST 3
#define LED_ON_ALIVE 50
#define LED_OFF_ALIVE 10000
#define LED_ON_FAST 100
#define LED_OFF_FAST 100
#define LED_ON_SLOW 700
#define LED_OFF_SLOW 1000
#define RELAY_ON 1
#define RELAY_OFF 0
#define VOLTS_PER_BIT 0.02395332875772

#ifdef DEBUG
#define DEBUG_SERIAL(x) Serial.begin(x)
#define DEBUG_PRINT(x) Serial.print(x)
#define DEBUG_PRINTLN(x) Serial.println(x)
#else
#define DEBUG_SERIAL(x)
#define DEBUG_PRINT(x) 
#define DEBUG_PRINTLN(x) 
#endif

#define RELAY_PIN 6
#define LED_BLUE_PIN 7  // Diode "D3"
#define LED_RED_PIN 8   // Diode "D4"
uint8_t VOLTAGE_12V_PIN = A0; 

#define THERM_FIXED_RESISTANCE 100000.0
#define THERM_COEFF_A 0.000415118840177787
#define THERM_COEFF_B 0.000256202949760582
uint8_t THERMISTOR_1_SENS_PIN = A1;
uint8_t THERMISTOR_2_SENS_PIN = A2;

unsigned long presentMillis, previousMillis, lastTimeLedBlue, lastTimeLedRed, ledBlueInterval, ledRedInterval = 0UL;
int reportCounter = 1190; // To make a first report after 30s
bool ledBlueState, ledRedState, nodeWarning, nodeError = LOW;
uint8_t ledBlueBlinkSpeed, ledRedBlinkSpeed = OFF;
const unsigned long onOffTimesTable[4][2] = {{0,0},{LED_OFF_ALIVE,LED_ON_ALIVE},{LED_OFF_SLOW,LED_ON_SLOW},{LED_OFF_FAST,LED_ON_FAST}};
bool state = RELAY_OFF;

MySensor gw;
MyMessage msg1(1,V_STATUS);
MyMessage msg2(2,V_TEMP);
MyMessage msg3(3,V_TEMP);
MyMessage msg4(4,V_VOLTAGE);
MyMessage msg5(5,V_VAR1);
MyMessage msg6(6,V_VOLTAGE);
MyMessage msg7(7,V_TEMP);


void setup()  
{
  DEBUG_SERIAL(115200);    // <<<<<<<<<<<<<<<<<<<<<<<<<< Note BAUD_RATE in MySensors.h (v1.4) or MyConfig.h (v1.5-)
  DEBUG_PRINTLN(F("Serial started!"));
  
  analogReference(INTERNAL);
  
  delay(500); // Allow time for power-up and radio if power used as reset 
  gw.begin(incomingMessage, NODE_ID);   // Initialize library and add callback for incoming messages
  gw.sendSketchInfo("EgRelayActuatorTest", "1.0 20160311");
  
  DEBUG_PRINT(F("Presenting children..."));
  gw.present(1,S_LIGHT);
  gw.present(2,S_TEMP);
  gw.present(3,S_TEMP);
  gw.present(4,S_MULTIMETER);
  gw.present(5,S_CUSTOM);
  gw.present(6,S_MULTIMETER);
  gw.present(7,S_TEMP);  
  DEBUG_PRINTLN(F("...done!"));
  DEBUG_PRINTLN("");
  
  DEBUG_PRINT(F("Setup inputs and outputs..."));
  pinMode(RELAY_PIN, OUTPUT);
  pinMode(LED_BLUE_PIN, OUTPUT);
  pinMode(LED_RED_PIN, OUTPUT);
  digitalWrite(RELAY_PIN,LOW);  // always start with relay off (for safety reason, change this later to use eeprom status)
  digitalWrite(LED_BLUE_PIN,LOW);
  digitalWrite(LED_RED_PIN,LOW);

  pinMode(VOLTAGE_12V_PIN, INPUT);
  pinMode(THERMISTOR_1_SENS_PIN, INPUT);
  pinMode(THERMISTOR_2_SENS_PIN, INPUT);
  digitalWrite(VOLTAGE_12V_PIN,LOW);          // ensure disabled pull-ups
  digitalWrite(THERMISTOR_1_SENS_PIN,LOW);
  digitalWrite(THERMISTOR_2_SENS_PIN,LOW);
  DEBUG_PRINTLN(F("...done!"));
  DEBUG_PRINTLN("");

  unsigned long t = millis()+2000;
  
  // Perform blink tests
  DEBUG_PRINT(F("LEDs blink test..."));
  while (millis()<t)
	blinkBlueLed(FAST);
  t+=1000;
  while (millis()<t)
	blinkBlueLed(SLOW);
  digitalWrite(LED_BLUE_PIN,LOW);
  t+=1000;
  while (millis()<t)
	blinkRedLed(FAST);
  t+=1000;
  while (millis()<t)
	blinkRedLed(SLOW);
  digitalWrite(LED_RED_PIN,LOW);
  DEBUG_PRINTLN(F("...done!"));
  DEBUG_PRINTLN("");
  
  DEBUG_PRINTLN(F("Check volts..."));
  check12volt();
  check33volt();
  DEBUG_PRINTLN("");
  DEBUG_PRINTLN(F("Check temperatures..."));
  checkInternalTemp();
  checkThermistor1Temp();
  checkThermistor2Temp();
  DEBUG_PRINTLN("");
  gw.wait(200);
  DEBUG_PRINTLN(F("Check 12V again..."));
  check12volt();
  DEBUG_PRINTLN("");
  

 if (nodeWarning) {
	DEBUG_PRINTLN(F("Warning detected, please press any key to continue...!"));
	Serial.flush();
	while ((!Serial.available())) {
		blinkRedLed(SLOW);
	}
  }
  else if (nodeError) {
	DEBUG_PRINTLN(F("Error detected, start-up aborted...!"));
	while (1) {
		blinkRedLed(FAST);
	}
  }
  else   
	DEBUG_PRINTLN(F("Testing volts and temps passed OK !"));
  DEBUG_PRINTLN("");
	
  DEBUG_PRINTLN(F("Starting relay test..."));
  digitalWrite(LED_BLUE_PIN,HIGH);
  digitalWrite(LED_RED_PIN,HIGH);
  gw.wait(1000);
  for (uint8_t i=0; i<2; i++) {
	digitalWrite(RELAY_PIN,HIGH);
	gw.wait(500);
	digitalWrite(RELAY_PIN,LOW);
	gw.wait(1000);
  }
  digitalWrite(LED_BLUE_PIN,LOW);
  digitalWrite(LED_RED_PIN,LOW);  
  DEBUG_PRINTLN(F("Relay test done!"));
  DEBUG_PRINTLN("");
  
  DEBUG_PRINTLN(F("Setup finished!"));
  DEBUG_PRINTLN("");
  
  DEBUG_PRINTLN(F("Entering loop in 3s!"));
  DEBUG_PRINTLN("");
  gw.wait(3000);
  
}

void loop() 
{
  gw.process();
  blinkBlueLed(ALIVE);
  if (nodeError) blinkRedLed(FAST);
  if (nodeWarning) blinkRedLed(SLOW);
    
  presentMillis = millis();
  if (presentMillis - previousMillis > CHECK_INTERVAL) {     // Check status every 3s.
    previousMillis = presentMillis;
	//DEBUG_PRINT("Tid: "); DEBUG_PRINT(presentMillis); DEBUG_PRINTLN("ms");
	nodeError = LOW;
        nodeWarning = LOW;
        digitalWrite(LED_RED_PIN,LOW);
        reportCounter++;
	check12volt();
	if (reportCounter >= REPORT_INTERVAL) {
		gw.send(msg6.set((analogRead(VOLTAGE_12V_PIN)*VOLTS_PER_BIT),1));    // Great care must be used when/where to place the 12V reading because of the problem to switch back to analagReference(INTERNAL) after analogReference(DEFAULT)
        }
        check33volt();
	checkInternalTemp();
	if (reportCounter >= REPORT_INTERVAL) {
		gw.send(msg7.set(getInternalTemp(),1));
		gw.send(msg4.set(readVcc()));
                analogReference(DEFAULT);
                gw.send(msg2.set(thermistorTemp(analogRead(THERMISTOR_1_SENS_PIN)),1));
		gw.send(msg3.set(thermistorTemp(analogRead(THERMISTOR_2_SENS_PIN)),1));
		reportCounter = 0;
	}
        checkThermistor1Temp();  // Need to be last to safe switch back to analogReference(INTERNAL)
	checkThermistor2Temp();
  }
}

void incomingMessage(const MyMessage &message) {
  if (message.type == V_STATUS) {
     state = message.getBool();
     digitalWrite(RELAY_PIN, state?RELAY_ON:RELAY_OFF); 
     DEBUG_PRINT(F("Incoming relay change request for sensor:"));
     DEBUG_PRINT(message.sensor);
     DEBUG_PRINT(F(", New status: "));
     DEBUG_PRINTLN(message.getBool());
     DEBUG_PRINTLN("");
   }
   else  {
     DEBUG_PRINTLN(F("Wrong message type."));
   } 
}

uint8_t check12volt(void) {
  DEBUG_PRINT(F("12V voltage: "));
  uint8_t errorCode; 
  analogReference(INTERNAL);
  analogRead(VOLTAGE_12V_PIN);
  float volt12 = analogRead(VOLTAGE_12V_PIN)*VOLTS_PER_BIT;
  DEBUG_PRINT(volt12); 
  DEBUG_PRINT(F(" V"));
  if (volt12 < VOLT12_L1) {
	nodeError = HIGH;
	errorCode = ERR_CODE_VOLT12_L1;
	DEBUG_PRINTLN(F("...error: too low 12V !"));
	gw.send(msg5.set(errorCode));
	return errorCode;
  }
  else if (volt12 > VOLT12_H1) {
	nodeError = HIGH;
	errorCode = ERR_CODE_VOLT12_H1;
	DEBUG_PRINTLN(F("...error: too high 12V !"));
	gw.send(msg5.set(errorCode));
	return errorCode;
  }
  else {
	DEBUG_PRINTLN(F("  ...OK!"));
	return 0;
  }
}

uint8_t check33volt(void) {
  DEBUG_PRINT(F("ATmega328p voltage: "));
  uint8_t errorCode;
  long avrVcc = readVcc();
  DEBUG_PRINT(avrVcc); 
  DEBUG_PRINT(F(" mV"));
  if (avrVcc < VOLT33_L1) {
	nodeError = HIGH;
	errorCode = ERR_CODE_VOLT33_L1;
	DEBUG_PRINTLN(F("...error: too low 3.3V!"));
	gw.send(msg5.set(errorCode));
	return errorCode;
  }
  else if (avrVcc > VOLT33_H1) {
	nodeWarning = HIGH;
	errorCode = ERR_CODE_VOLT33_H1;
	DEBUG_PRINTLN(F("...warning: too high 3.3V!"));
	gw.send(msg5.set(errorCode));
	return errorCode;
  }
  else {
	DEBUG_PRINTLN(F("  ...OK!"));
	return 0;
  }
}

uint8_t checkInternalTemp(void) {
  DEBUG_PRINT(F("ATmega328p internal temp: "));
  uint8_t errorCode;
  float intTemp = getInternalTemp();
  DEBUG_PRINT(intTemp); 
  DEBUG_PRINT(F(" C"));
  if (intTemp < AVRTEMP_L1) {
	nodeWarning = HIGH;
	errorCode = ERR_CODE_AVRTEMP_L1;
	DEBUG_PRINTLN(F("...warning: low ATmega internal temperature !"));
	gw.send(msg5.set(errorCode));
	return errorCode;
  }
  else if (intTemp > AVRTEMP_H1) {
	nodeError = HIGH;
	errorCode = ERR_CODE_AVRTEMP_H1;
	DEBUG_PRINTLN(F("...error: high ATmega internal temperature !"));
	gw.send(msg5.set(errorCode));	
	return errorCode;
  }
  else {
	DEBUG_PRINTLN(F("  ...OK!"));	
	return 0;
  }	
}  
  
uint8_t checkThermistor1Temp(void) {  
  DEBUG_PRINT(F("Thermistor 1 temp: "));
  analogReference(DEFAULT);  
  uint8_t errorCode;
  int rawRead = analogRead(THERMISTOR_1_SENS_PIN);
  analogReference(INTERNAL);
  analogRead(THERMISTOR_1_SENS_PIN);  
  DEBUG_PRINT(rawRead);
  DEBUG_PRINT(F(" bits, "));
  float thermTemp1 = thermistorTemp(rawRead);
  DEBUG_PRINT(thermTemp1); 
  DEBUG_PRINT(F(" C"));
  if (thermTemp1 < THERM1_L1) {
	nodeWarning = HIGH;
	errorCode = ERR_CODE_THERM1_L1;
	DEBUG_PRINTLN(F("...warning: low thermistor_1 temperature !"));
	gw.send(msg5.set(errorCode));		
	return errorCode;
  }
  else if (thermTemp1 > THERM1_H1) {
	nodeError = HIGH;
	errorCode = ERR_CODE_THERM1_H1;	
	DEBUG_PRINTLN(F("...error: high thermistor_1 temperature !"));
	gw.send(msg5.set(errorCode));		
	return errorCode;
  }
  else {
	DEBUG_PRINTLN(F("  ...OK!"));
    return 0;
  }
}
	
uint8_t checkThermistor2Temp(void) {	
  DEBUG_PRINT(F("Thermistor 2 temp: "));
  uint8_t errorCode;
  analogReference(DEFAULT);  
  float thermTemp2 = thermistorTemp(analogRead(THERMISTOR_2_SENS_PIN));
  analogReference(INTERNAL);
  analogRead(THERMISTOR_1_SENS_PIN);   
  DEBUG_PRINT(thermTemp2); 
  DEBUG_PRINT(F(" C"));
  if (thermTemp2 < THERM2_L1) {
	nodeWarning = HIGH;
	errorCode = ERR_CODE_THERM2_L1;
	DEBUG_PRINTLN(F("...warning: low thermistor_2 temperature !"));
	gw.send(msg5.set(errorCode));
	return errorCode;
  }
  else if (thermTemp2 > THERM2_H1) {
	nodeError = HIGH;
	errorCode = ERR_CODE_THERM2_H1;
	DEBUG_PRINTLN(F("...error: high thermistor_2 temperature !"));
	gw.send(msg5.set(errorCode));
	return errorCode;
  }
  else {
	DEBUG_PRINTLN(F("  ...OK!"));
	return 0;
  }	
}

void blinkBlueLed(uint8_t blinkSpeed) {
	unsigned long currentMillis = millis();	
	  if (currentMillis - lastTimeLedBlue > ledBlueInterval) {
		lastTimeLedBlue = currentMillis;
		if (ledBlueState == LOW) {
		  ledBlueState = HIGH;
		  ledBlueInterval = onOffTimesTable[blinkSpeed][ON];
		} 
		else {
		  ledBlueState = LOW;
		  ledBlueInterval = onOffTimesTable[blinkSpeed][OFF];
		}  
		digitalWrite(LED_BLUE_PIN, ledBlueState); 
	  }
}

void blinkRedLed(uint8_t blinkSpeed) {
	unsigned long currentMillis = millis();	
	  if (currentMillis - lastTimeLedRed > ledRedInterval) {
		lastTimeLedRed = currentMillis;
		if (ledRedState == LOW) {
		  ledRedState = HIGH;
		  ledRedInterval = onOffTimesTable[blinkSpeed][ON];
		} 
		else {
		  ledRedState = LOW;
		  ledRedInterval = onOffTimesTable[blinkSpeed][OFF];
		}  
		digitalWrite(LED_RED_PIN, ledRedState); 
	  }
}


// function for reading Vcc by reading 1.1V reference against AVcc. Based from http://provideyourown.com/2012/secret-arduino-voltmeter-measure-battery-voltage/
// To calibrate reading replace 1125300L with scale_constant = internal1.1Ref * 1023 * 1000, where internal1.1Ref = 1.1 * Vcc1 (per voltmeter) / Vcc2 (per readVcc() function) 
long readVcc() {
  // set the reference to Vcc and the measurement to the internal 1.1V reference
  ADMUX = _BV(REFS0) | _BV(MUX3) | _BV(MUX2) | _BV(MUX1);
  delay(2); // Wait for Vref to settle
  ADCSRA |= _BV(ADSC); // Start conversion
  while (bit_is_set(ADCSRA,ADSC)); // measuring
  uint8_t low  = ADCL; // must read ADCL first - it then locks ADCH  
  uint8_t high = ADCH; // unlocks both
  long result = (high<<8) | low;
  result = 1125300L / result; // Calculate Vcc (in mV); 1125300 = 1.1*1023*1000
  return result; // Vcc in millivolts
}

/* function for reading thermistor temp. From http://playground.arduino.cc/ComponentLib/Thermistor2
Inputs ADC Value from Thermistor and outputs Temperature in Celsius. 
Utilizes the simplified Steinhart-Hart Thermistor Equation: Temperature (in Kelvin) = 1 / {A + B[ln(R)]} 
 where thermistor resistance R_th = R_fix / (1023/RawADC - 1) when connection is (Gnd)--(R_th)--(ADCinput)--(R_fix)--(Vcc) 
(Values of R_fix, A and B have been calibrated by exercising followed by off line least squares curve fitting.)  
*/
float thermistorTemp(int RawADC) {
  float thermResistance = THERM_FIXED_RESISTANCE/((1023.0/RawADC) - 1);
  float thermTemp = (1 / (THERM_COEFF_A + (THERM_COEFF_B * log(thermResistance)))) - 273.15;
  return thermTemp;
}

// function for reading internal temp. From http://playground.arduino.cc/Main/InternalTemperatureSensor 
double getInternalTemp(void) {  // (Both double and float are 4 byte in arduino implementation)
  unsigned int wADC;
  double t;
  // The internal temperature has to be used with the internal reference of 1.1V. Channel 8 can not be selected with the analogRead function yet.
  ADMUX = (_BV(REFS1) | _BV(REFS0) | _BV(MUX3));   // Set the internal reference and mux.
  ADCSRA |= _BV(ADEN);  // enable the ADC
  delay(20);            // wait for voltages to become stable.
  ADCSRA |= _BV(ADSC);  // Start the ADC
  while (bit_is_set(ADCSRA,ADSC));   // Detect end-of-conversion
  wADC = ADCW;   // Reading register "ADCW" takes care of how to read ADCL and ADCH.
  t = (wADC - 333.0 ) / 1.1;   // The offset of 324.31 could be wrong. It is just an indication.
  return (t);   // The returned temperature in degrees Celcius.
}




