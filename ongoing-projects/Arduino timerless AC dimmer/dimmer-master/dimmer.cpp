#include <dimmer.h>

void Dimmer::init(byte cnt, byte intr){
	dimmers = (_dimmer**)malloc(cnt*sizeof(struct _dimmer *));
	interrupt = intr;
	attachInterrupt(interrupt, &down, FALLING);
}

void Dimmer::add(byte pin){
	dimmers[counter]->pin = pin;
	dimmers[counter]->value = 0;
	pinMode(dimmers[counter]->pin, OUTPUT);
	digitalWrite(dimmers[counter]->pin, LOW);
	counter++;
}

void Dimmer::up(){
	detachInterrupt(interrupt);
	for(byte i = 0; i < counter; i++) {
		if (dimmers[i]->value < 0xff) { 
			digitalWrite(dimmers[i]->pin, LOW); //every dimed off
		}
	}
	attachInterrupt(interrupt, &down, FALLING );
}
	
void Dimmer::down(){
	detachInterrupt(interrupt);
	unsigned long nh = micros() - timer;
	if(nh > 9000 and nh < 12000)
	halfPeriod = nh;
	timer = micros();
	attachInterrupt(interrupt, &up, RISING );
}

void Dimmer::loop(){
	noInterrupts();
	unsigned long offset = micros()-timer;
	byte phase = 0xff - min(0xff, 0xff*offset/halfPeriod);
	for(byte i = 0; i < counter; i++) {
		if (dimmers[i]->value > phase) { 
			digitalWrite(dimmers[i]->pin, HIGH); //every dimed off
		}	
	}
	interrupts();
}

void Dimmer::set(byte i, byte val){
	dimmers[i]->value = val;
}

_dimmer** Dimmer::dimmers = (_dimmer**)0;
byte Dimmer::counter = 0;
byte Dimmer::interrupt =0;
volatile  unsigned long Dimmer::timer = 0;
volatile  unsigned long Dimmer::halfPeriod = 9000;