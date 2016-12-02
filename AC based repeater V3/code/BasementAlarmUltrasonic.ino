/*
  Ultrasonic Sensor - based on a MySensors example (actuator)

  modified 30 august 2016
  version 1.2
  by Gert Sanders

  Uses 2.0.0-dev version of the library

*/

// Enable debug prints to serial monitor

//#define MY_DEBUG
//#define MY_DEBUG_VERBOSE

//#define MY_NODE_ID 123
#define MY_NODE_ID 32

// Enable and select radio type attached
#define MY_RADIO_NRF24
//#define MY_RADIO_RFM69

// possible values: RF24_PA_LOW (is default on gateway), RF24_PA_MED, RF24_PA_HIGH, RF24_PA_MAX (is default on nodes)
#define MY_RF24_PA_LEVEL RF24_PA_MAX

// RF channel for the sensor net, 0-127

#define MY_RF24_CHANNEL     80  // just my choice


//RF24_250KBPS for 250kbs, RF24_1MBPS for 1Mbps, or RF24_2MBPS for 2Mbps
#define MY_RF24_DATARATE      RF24_250KBPS


// defines for sensors
#define PUMP          1
#define WATERLEVEL    2


// Enable repeater functionality for this node
//#define MY_REPEATER_FEATURE


// All includes
#include <MySensor.h>
#include <NewPing.h>

// Pin definitions and other defines
#define TRIGGER_PIN  5  // Arduino pin tied to trigger pin on the ultrasonic sensor.
#define ECHO_PIN     6  // Arduino pin tied to echo pin on the ultrasonic sensor.
#define RELAYPIN     7
#define LEDPIN       8

#define PumpOFFLimit    2
#define PumpONLimit     12

#define MAX_DISTANCE 255 // Maximum distance we want to ping for (in centimeters). Maximum sensor distance is rated at 400-500cm.

#define NUMBEROFSAMPLES 100


// constants


// variables
boolean  PumpStatus = false;
boolean  oldPumpStatus = false;

unsigned int uS = 0;
byte Distance = 0;
byte LastMeasuredDistance = 0;
byte MaxMeasuredDistance = 0;



// Instantiate objects
NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE); // NewPing setup of pins and maximum distance.
MyMessage PumpMsg(PUMP, V_LIGHT);
MyMessage DistanceMsg(WATERLEVEL, V_DISTANCE);



void presentation()
{
  // Send the sketch version information to the gateway and Controller
  sendSketchInfo("BasementAlarmUltrasonic", "1.1");
  wait(500); // delay needed by Raspi/domotics
  present(PUMP, S_LIGHT);
  wait(500); // delay needed by Raspi/domotics
  present(WATERLEVEL, S_DISTANCE);
  wait(500); // delay needed by Raspi/domotics
}





// supporting functions.
void blipled()
{
  digitalWrite(LEDPIN, !PumpStatus);   // turn the LED on (HIGH is the voltage level)
  wait(50);              // wait for a bit
  digitalWrite(LEDPIN, PumpStatus);    // turn the LED off by making the voltage HIGH
  wait(150);              // wait for a bit
  digitalWrite(LEDPIN, !PumpStatus);   // turn the LED on (HIGH is the voltage level)
  wait(50);              // wait for a bit
  digitalWrite(LEDPIN, PumpStatus);    // turn the LED off by making the voltage HIGH
}






void receive(const MyMessage & message)
{


#ifdef MY_DEBUG
  // Write some debug info
  Serial.print(F("Incoming change for sensor:"));
  Serial.print(message.sensor);
#endif


  if (message.type == V_LIGHT)
  {
    // Change relay state
    oldPumpStatus = PumpStatus;
    PumpStatus = message.getBool();
    digitalWrite(RELAYPIN, PumpStatus ? HIGH : LOW);
    // Store state in eeprom
    saveState(PUMP, PumpStatus);

#ifdef MY_DEBUG
    Serial.print(F("Changing PUMP status to : "));
    Serial.println(PumpStatus);
#endif

  }


}




/////////////////////////////////////////////////////////////////////////
// the setup function runs once when you press reset or power the board
/////////////////////////////////////////////////////////////////////////
void setup()
{
  PumpStatus = false;
  oldPumpStatus = false;

  // initialize digital LEDpin as an output.
  pinMode(LEDPIN, OUTPUT);
  digitalWrite(LEDPIN, HIGH);    // turn the LED on by making the voltage HIGH

  // initialize digital Pumppin as an output.
  pinMode(RELAYPIN, OUTPUT);
  digitalWrite(RELAYPIN, LOW);    // turn the LED off by making the voltage LOW

  PumpStatus = loadState(PUMP);
  digitalWrite(RELAYPIN, PumpStatus);
  send(PumpMsg.set(PumpStatus == true ? 1 : 0));
  wait(500);

  MaxMeasuredDistance = loadState(WATERLEVEL);

  digitalWrite(LEDPIN, LOW);                   // turn the LED off by making the voltage LOW

}










///////////////////////////////////////////////////////
// the loop function runs over and over again forever
///////////////////////////////////////////////////////

void loop()
{
  blipled();

  uS = sonar.ping_median(NUMBEROFSAMPLES); // Send ping, get ping time in microseconds (uS).

  Distance = uS / US_ROUNDTRIP_CM;

  if (Distance >= MaxMeasuredDistance)
  {
    MaxMeasuredDistance = Distance;
    if (Distance >= loadState(WATERLEVEL))
    {
      saveState(WATERLEVEL, Distance);
    }
  }

  if (Distance != LastMeasuredDistance)
  {
    if (send(DistanceMsg.set((MaxMeasuredDistance - Distance), 0)))
    {
      LastMeasuredDistance = Distance;
      wait(500);
    }
  }

#ifdef MY_DEBUG
  Serial.print("Ping: ");
  Serial.print(Distance); // Convert ping time to distance in cm and print result (0 = outside set distance range)
  Serial.print("cm");
  Serial.print("    Max: ");
  Serial.print(MaxMeasuredDistance); // Convert ping time to distance in cm and print result (0 = outside set distance range)
  Serial.println("cm");
#endif


  if ((MaxMeasuredDistance - Distance) >= PumpONLimit)
  {
    SetPumpOn();
  }

  if ((MaxMeasuredDistance - Distance) <= PumpOFFLimit)
  {
    SetPumpOff();
  }

  if (oldPumpStatus != PumpStatus)
  {

    if (send(PumpMsg.set(PumpStatus == true ? 1 : 0)))
    {
      oldPumpStatus = PumpStatus;
      wait(500); // delay needed by Raspi/domotics
    }
  }


}




void SetPumpOn()
{

  digitalWrite(RELAYPIN, HIGH);
  oldPumpStatus = PumpStatus;
  PumpStatus = true;
  saveState(PUMP, PumpStatus);

}



void SetPumpOff()
{

  digitalWrite(RELAYPIN, LOW);
  oldPumpStatus = PumpStatus;
  PumpStatus = false;
  saveState(PUMP, PumpStatus);

}



