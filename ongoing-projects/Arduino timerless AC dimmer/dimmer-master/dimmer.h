#if ARDUINO >= 100
#include <Arduino.h>
#else
#include <WProgram.h> 
#endif

struct _dimmer {
  byte pin;
  byte value;
};

 class Dimmer {
	public:
		static void  init(byte cnt, byte intr);
		static void  set(byte i, byte val);
		static void  loop();
		static void  add(byte pin);
	private:
		static void  down();
		static void  up();
		static byte interrupt;
		static _dimmer **dimmers;
		static byte counter;		
		static volatile  unsigned long timer;
		static volatile  unsigned long halfPeriod;		
};