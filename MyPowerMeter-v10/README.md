 The idea of this board was  that  in my house I`ve got a lot  electrical home appliances like electric heater,Dishwashers, Electric Cooktop, Water Heater.. etc and if I conect some of the machine altogether  afther some minutes  the electrical company turns off   the electircal line and i have to turn off all the machine and after   some secons I have electricity again. In Spain you pay  to the electrical company for 2 reason the energy you've consumed and the power you 've  contracted ... and a lot of Tax!.
  The purpose of this is made a board that  i could monitor the energy and when i am spending more electicity that i've contrated  the buzzer plays and I will turn off some home appliances before the electrical company turns off .
 
**The board had been desinged for use with arduino Nano and with the following features:**
- 4 Curent sensor
- LCD 84x48
- Rotary Encoder Module
- NRF24L01
- Buzzer
- 2 inputs for general propuse
- I2c  port (with +v, gnd, SDA , SCL)


The pinout of the arduino boar is :
![enter image description here](https://www.openhardware.io//uploads/56e5d6455905c64977a5e158/image/Pinout.png "enter image title here")

  The board has 4 channel for Current Sensor (based of https://openenergymonitor.org/emon/modules/emonTxV3) where you can conect a non-invasive Energy Monitor Sensor(SCT-013-000  or SCT-013-030 ) 

![enter image description here](https://www.openhardware.io//uploads/56e5d6455905c64977a5e158/image/current sensor copia.jpg "enter image title here")

The difference between SCT-013-000  and SCT-013-030  is that SCT-013-000 has a current output and SCT-013-030 has a voltage output . Such arduino only has analog  pin  that can read voltage if we use current output type we have put a burder resistor.
Depending on the type sensor you use , yo should  put on the board R17,R14,R22,(burder resistor) R26 or not.

In the other hand  you can conect  a Nokia LCD where you can show the information that you need,  In my case i show a grafic of some of the current sensor. If we conect   rotary encoder module we can do some menu  for change parameter

Also it has 2 input where depending of the sensors we can put a pull up resistor (R15, R16)  or a Capacitor (C5, C6) as a filter.  If for example we can conected the light of the background of the screen we only have 1 input.

 For the powering we have two regulator the first if we conecter with  voltage  above 5v. And the seconds is for giving 3.3v to the NRF24L01 with some of capacitor.


 





