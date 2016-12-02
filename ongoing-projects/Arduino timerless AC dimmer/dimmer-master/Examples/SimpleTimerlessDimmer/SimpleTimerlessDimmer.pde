#include <dimmer.h>

void setup(){
  Dimmer::init(1,0); // 1 dimmers, 0 interrupt (2 pin)
  Dimmer::add(3);// pin 3
  Serial.begin(38400);
  Serial.println("Input dimmer value:");
}

void loop(){
  Dimmer::loop(); //Do not delay
  
  if (Serial.available()) {
    byte inByte = Serial.parseInt();
    Serial.println(inByte);
    Dimmer::set(0,inByte);
  }
}