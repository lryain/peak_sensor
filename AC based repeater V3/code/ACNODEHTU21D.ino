

//#define MYDEBUG
//#define MY_DEBUG
//#define MY_DEBUG_VERBOSE

//#define WITH_LEDS_BLINKING 0

//#define MY_NODE_ID 15
//#define MY_PARENT_ID 0

//#define MY_BAUD_RATE 57600

#define MY_RADIO_NRF24
//#define MY_RADIO_RFM69

// Enable serial gateway
//#define MY_GATEWAY_SERIAL

// Enabled repeater feature for this node
//#define MY_REPEATER_FEATURE

// Flash leds on rx/tx/err
//#define MY_LEDS_BLINKING_FEATURE
// Set blinking period
//#define MY_DEFAULT_LED_BLINK_PERIOD 300

// Enable inclusion mode
//#define MY_INCLUSION_MODE_FEATURE
// Enable Inclusion mode button on gateway
//#define MY_INCLUSION_BUTTON_FEATURE
// Set inclusion mode duration (in seconds)
//#define MY_INCLUSION_MODE_DURATION 60
// Digital pin used for inclusion mode button
//#define MY_INCLUSION_MODE_BUTTON_PIN  3

//#define MY_DEFAULT_ERR_LED_PIN 5  // Error led pin
//#define MY_DEFAULT_RX_LED_PIN  6  // Receive led pin
//#define MY_DEFAULT_TX_LED_PIN  7  // the PCB, on board LED

// possible values: RF24_PA_LOW (is default on gateway), RF24_PA_MED, RF24_PA_HIGH, RF24_PA_MAX (is default on nodes)
//#define MY_RF24_PA_LEVEL RF24_PA_HIGH

// RF channel for the sensor net, 0-127
#define MY_RF24_CHANNEL     76

//RF24_250KBPS for 250kbs, RF24_1MBPS for 1Mbps, or RF24_2MBPS for 2Mbps
#define MY_RF24_DATARATE      RF24_250KBPS

//#include <Vcc.h>
#include <SPI.h>
#include <MySensor.h>
#include <Wire.h>
#include <SparkFunHTU21D.h>

//#define DEBUG 0 // Debug enables Serial.print 1 = Yes 0 = No
//#define WITH_LEDS_BLINKING 0

// define childID's of sensors

//#define BATTERYLEVEL  0
#define TEMPERATURE   1
#define HUMIDITY      2
#define MOTION        3
#define RELAY1        4
#define RELAY2        5

// define pins
#define motionpin  4
#define relay1pin  5
#define relay2pin  6
#define ledpin     8


#define NormalOnTime 5
#define NormalOffTime 75
#define MESSAGEREPEAT 500
#define MESSAGEWAIT  500

#define RELAY_ON  0  // GPIO value to write to turn on attached relay
#define RELAY_OFF 1 // GPIO value to write to turn off attached relay

unsigned long CHECKTIME =    300000; // every 5 minutes

unsigned long LastTempCheck = 0;
boolean LastMotionState = LOW;
boolean LastRelay1State = LOW;
boolean LastRelay2State = LOW;


float Humidity = 0.0;
float Temperature = 0.0;


// instantiate classes
MyMessage HumMsg(HUMIDITY, V_HUM);
MyMessage TempMsg(TEMPERATURE, V_TEMP);
MyMessage MotionMsg(MOTION, V_TRIPPED);
MyMessage Relay1Msg(RELAY1, V_STATUS);
MyMessage Relay2Msg(RELAY2, V_STATUS);

HTU21D myTempHum;


void presentation()
{
  sendSketchInfo("HTU21D/PIR/2RELAYS", "1.0");
  present(TEMPERATURE, S_TEMP);
  wait(MESSAGEWAIT);
  present(HUMIDITY, S_HUM);
  wait(MESSAGEWAIT);
  present(MOTION, S_MOTION);
  wait(MESSAGEWAIT);
  present(RELAY1, S_LIGHT);
  wait(MESSAGEWAIT);
  present(RELAY2, S_LIGHT);
  wait(MESSAGEWAIT);
}


void receive(const MyMessage &message)
{
  // We only expect one type of message from controller. But we better check anyway.
  if (message.type == V_LIGHT)
  {
    // Change relay state
    digitalWrite(message.sensor - 4 + relay1pin, message.getBool() ? RELAY_ON : RELAY_OFF);
    // Store state in eeprom
    saveState(message.sensor, message.getBool());
    // Write some debug info
    Serial.print("Incoming change for sensor:");
    Serial.print(message.sensor);
    Serial.print(", New status: ");
    Serial.println(message.getBool());
  }
}

// the setup function runs once when you press reset or power the board
void setup()
{
  // initialize digital LEDpin as an output.
  pinMode(ledpin, OUTPUT);
  pinMode(motionpin, INPUT);

  pinMode(relay1pin, OUTPUT);
  digitalWrite(relay1pin, loadState(RELAY1) ? RELAY_ON : RELAY_OFF);
  LastRelay1State = loadState(RELAY1);
  while (!send(Relay1Msg.set(LastRelay1State ? "1" : "0")))
  {
    wait(MESSAGEREPEAT);
  }
  wait(MESSAGEWAIT);

  pinMode(relay2pin, OUTPUT);
  digitalWrite(relay2pin, loadState(RELAY2) ? RELAY_ON : RELAY_OFF);
  LastRelay2State = loadState(RELAY2);
  while (!send(Relay2Msg.set(LastRelay2State ? "1" : "0")))
  {
    wait(MESSAGEREPEAT);
  }
  wait(MESSAGEWAIT);

  myTempHum.begin();
  LastTempCheck = millis();

} ////////////////////////////////////////// END SETUP /////////////////



// the loop function runs over and over again forever
void loop()
{

  if ((millis() - LastTempCheck) >= CHECKTIME)
  {
    Temperature = myTempHum.readTemperature();
    Humidity = myTempHum.readHumidity();

    while (!send(HumMsg.set(Humidity, 1)))
    {
      wait(MESSAGEREPEAT);
    }
    wait(MESSAGEWAIT);

    while (!send(TempMsg.set(Temperature, 1)))
    {
      wait(MESSAGEREPEAT);
    }
    wait(MESSAGEWAIT);
    LastTempCheck = millis();
  }


  boolean tripped = digitalRead(motionpin) == HIGH;

  if (tripped != LastMotionState)
  {
    while (!send(MotionMsg.set(tripped ? "1" : "0")))
    {
      wait(MESSAGEREPEAT);
    }
    LastMotionState = tripped;
  }
  wait(MESSAGEWAIT);

  tripped = digitalRead(relay1pin) == RELAY_ON;

  if (tripped != LastRelay1State)
  {
    while (!send(Relay1Msg.set(tripped ? "1" : "0")))
    {
      wait(MESSAGEREPEAT);
    }
    LastRelay1State = tripped;
  }
  wait(MESSAGEWAIT);


  tripped = digitalRead(relay2pin) == RELAY_ON;

  if (tripped != LastRelay2State)
  {
    while (!send(Relay2Msg.set(tripped ? "1" : "0")))
    {
      wait(MESSAGEREPEAT);
    }
    LastRelay2State = tripped;
  }
  wait(MESSAGEWAIT);

  BlinkLed();


} /////////////////////// END LOOP ///////////////////




////////////////////////////////////////////////////////
//////////// SUPPORTING FUNCTIONS //////////////////////
////////////////////////////////////////////////////////


////////////////////////////////////////////////////////
void BlinkLed()
{
  digitalWrite(ledpin, HIGH);
  wait(NormalOnTime);
  digitalWrite(ledpin, LOW);

}



