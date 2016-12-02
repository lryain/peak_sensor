# ACcapableSensor
_A sensor node pcb with various powering options._

**Powering modes**

This board can be powered by the following:

1. Two AAA batteries.
2. An external DC power supply with a maximum of 3v3 directly connected to the Vcc power line. A jumper ("BAT") needs to be shorted to connect the 3V3 power line to the Vcc power line.
3. An external DC power supply with a minimum of 3V and a maximum of 5V directly connected to the Vcc power line. Requires adding the AMS1117 to supply the 3V3 power line when the external supply gives more then 3V6.
4. An external DC power supply with a minimum of 6V and a maximum of 24V using the DC-DC step-down converter SG053-SZ. If the DC-DC step-down converter SG053-SZ is used at 4V output, then a 1N400x diode needs to be placed to supply the 3V3 line. If the DC-DC step-down converter SG053-SZ output is higher (e.g. 5V), then the AMS1117 needs to supply the 3V3 power line.
5. an external AC power supply of 110-220V AC using the HLK-PM01 for the 5V on Vcc power line and AMS1117 for the 3V3 line.


**Using batteries**

The batteries are connected to the Vcc power line. The NRF24 is connected to the 3V3 power line. There is no interconnection, unless you short the jumper "BAT". The jumper is located on the BOTTOM side next to the location for the AMS1117.

**Using external DC directly**

You can use a 5V source directly on the board. You will then need to connect it to the same terminals as the battery holders V+ and V-. Again, there is no direct connection between VCC and 3V3 power lines, so either you leave it open (and the 3V3 is NOT powered), or you add the AMS1117 regulator to step 5V down to 3V3.

If the external DC source is maximum 4V, then you could connect the DC source to the + OUT pin which is normally used for the DC-DC converter output or to the battery input pins +VO (=VCC) and -VO (=GND). To drop the 4V down to an acceptable voltage for the radio, you can add the 1N4001 (or any 1N400x) diode which sits between VCC and 3V3 power lines. Do check the direction (from high to low voltage).

**Using the DC-DC step down converter module SG053-SZ**

The board provides a place to add the SG053-SZ DC-DC step down module.

To connect the external DC source, there is a 2-pin header provided where you could place a JST connector or standard 2-pin male header. It is marked with +VDC and GND, right next to the SG053-SZ module on the board edge.

This module allows any DC input from 4V5 up to 28V and can give you anything between 0V8 and 20V. We use it to power the SIM800L as this needs 4V and around 2A peaks when transmitting (the module can give up to 3A peaks, so the external DC power source needs to have sufficient power, I use a 12V DC source of minimum 15W).

When using the DC-DC module with 4V output, I also place the 1N4004 diode so that the 4V is dropped to around 3V5 (at minimum load, a bit less when under load). The NRF24 can handle 3V6 so we are within limits then.

**Using AC**

The board has place for the AC to DC module HLK-PM01 and a slow blow fuse (250mA). In this revision there is no thermal fuse or varistor. My experience so far is that the module never warms up. Adding a Temperature sensor module would allow you to sense the internal temperature if this board is built into a box, and alarms then need to be set up in the controller (I use Domoticz for this). A TMP39 would be a nice sensor to add.

The HK-PM01 takes 110-220V AC and provides the board with a clean 5V DC. For the NRF24 you will need to add the 3V3 emulator (AMS1117-3V3).


**Options**

_I2C based flash memory_

You can add two I2C based flash memory chips (smd versions). They will have consecutive addresses (e.g. 0x50 and 0x51).
The AT24C's are optional, you can add one or two. The second place (AT24C-2) has a +1 address versus the first location (AT24C-1). If you do plan to use them, you will also need to place the I2C pull resistors and you will need to short the jumpers RSCL and RSDA. These two jumpers connect the pull-up resistors to the SCL and SDA lines.
If you use a sensor connected to the pins SCL/SDA of the extension connector you sometimes have no need for pull-ups as the sensor itself has them, hence no need to pull up on the board itself (the plugged in sensor takes of it then).

_Switching the location of SDA and SCL on the extension connector_

The jumpers SCL-SDA and SDA-SCL are there to allow switching the signals of the SCL/SDA pins on the extension connector. The jumper closest to the board edge is for the pin on the very edge of the connector, the jumper close to the ATSHA is for the second pin. Without shorting these two jumpers, the pins on the extension connector have no function.
Some sensor-boards have a SDA-SCL-GND-3V3 connector, others have a SCL-SDA-GND-3V3 pattern. The jumpers are there to be able to use both types.

_Placing the NRF24_

There two places to add the NRF24L01+. The one closest to the board edge is meant for the LPA-PO version with external SMA antenna, so that the antenna can go through a box wall. The SMA stick out from the board edge.

The other location is moved slightly away from the board edge so that the NRF24L01+ with PCB can be added. This version has a connector which is offset from it middle, so the connector on the board needed to be moved inward to allow the edge of the NRF24L01+ to fall in line with this board's edge. The "INT" jumper is located between this second connector placement and the board edge.

_Connecting the INT pin of the NRF24L01+ to D2_

If you want to use the INT pin on the NRF24L01+ connector to allow the radio to interrupt the processor, you need to short the jumper "INT". It is next to the second NRF24 connector placement (the one used for versions with their antenna on the PCB).
Close the jumper with solder to make the connection.

**The expansion connector**

The expansion connector "JP2" has pins for sensors and switches and also has the FTDI connector embedded. Looking at this board from the top, with the expansion connector on the lower left: pin 2 is the pin closest to the board edge and corner, pin 1 is closest to the RESET jumper.

```
"Green"
RESET  1    2  SDA/SCL
TX     3F   4  SCL/SDA
RX     5T   6  GND
VCC    7D   8  3V3
GND    9I  10  A3
GND   11   12  VCC
"Black"
VCC   13   14  A2
D2    15   16  3V3
GND   17   18  A1
D3    19   20  VCC
VCC   21   22  A0
```

Pins 1-3-5-7-9-11 form the FTDI connector.

![enter image description here](https://www.openhardware.io//uploads/5690e3ac674652236a8e73ae/image/Screen Shot 2016-01-17 at 16.26.14.png "Expansion connector, pin 1 is in the image below closest to RESET jumper, bottom right hand pin.")




This board is available via DirtyPCB:
http://dirtypcbs.com/view.php?share=12146&accesskey=c3a217d7f31434a5565ecf9bdf3f7dc6