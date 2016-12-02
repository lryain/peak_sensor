This is a PCB that uses AC power to run a MySensors node and a relay (SSR) inside a wall socket. The main goal is to have a MySensors node with I/O to be able to build your own node with your own sensors inside a normal (European) wall socket. It comes with PCB and a 3d box for protection and safety. 

This is a development of my first [In-Wall-ACDC-Pcb-for-MySensors](https://www.openhardware.io/view/13/In-Wall-ACDC-Pcb-for-MySensors) (Not SMD components, only axial)

_Note: This PCB are using SMD components!_

### Current Status and revision

Working on rev 7.1 including MysX 2.?, security improvements
Sent to PCB house 16sept, testing next.

#### Rev 7.0

- Changes in resistors and NPN transistor for relay to work better.
- Designed 3dboxes for safety.
- Increased AC-DC separation (Rev 7 = 5mm)
- AC clearance 2.5mm minimum, added millings.
- Redesigned outer dimension/border.
- Safety recommendations: This PCB meets the criteria of pollution level II (2,5mm (Basic) AC seperation and 5mm (Reinforced) AC-DC separation ) and can be used "where only non-conductive contamination occurs. However, occasional temporary conductivity must be expected as a result of moisture condensation." To be on the safe side - use a sealed case!

_For rev history, see Changes.txt_

_Note when buying through openhardware.io may say another rev. This is only revision for manufacturer, not current PCB revision. I always update PCB gerber files for sale first on openhardware.io_

### **Why??**

I designed this project for the main purpose of safely powering a node from a AC source. Battery operations and 5v phone chargers in all its glory but I needed a node small enough to put inside a wall-socket, adaptable/dynamic and AC powered. 

We have AC mains running everywhere in our houses today and this project aims for converting AC to 5vDC with safety as priority #1. 

#### Aim of this project

- Convert 240v/120v AC to 5v DC (using HLK-PM01)

- Make it safe!

- Fit inside standard [appliance box](http://www.elko.se/getfile.php/produkter/img/3/3/4944/Foto4944.jpg) / in wall

- Run MySensors node (Arduino Pro Mini, NRF24L01+, MysX Gpio for sensors)

- Have a on board relay (SSR).

- Make it as small as possible using SMD components.

### **How??**

By using this you have everything you need for a AC powered MySensors node. With MysX connector you have plenty of options both digital and analog input/outputs for you sensors. For the AC power there are both a temperature fuse, a normal fuse, and a varistor to protect your node.

I wanted the node to be dynamic (no sensor specific) - and therefore the MysX connector. Now you can add any sensor you like to the connector. Also, while at it - why not add a relay. Since Im going to use this around AC i might also be able to switch it in the same time.

Follow this thread for latest info: https://forum.mysensors.org/topic/4187/in-wall-pcb-with-relay-for-mysensors-smd

Build instructions coming soon, see BOM for more info.

### **What??**

**Power **- Using the HLK-PM01 converting AC 240/120v to 5v dc and running the MySensors node. The input is protected with a fuse, temp fuse and varistor ([read this discussion](http://forum.mysensors.org/topic/1607/safe-in-wall-ac-to-dc-transformers)).

**MySensors **- A Atmega328p-au (normal schematics) and a Nrf24l01+ SMD Radio with all the normal components.

**I/O** - MysX (Raw input not possible) + some extra (A4+A5), ICSP**

**Relay(SSR)** - A Omron G3MC-202PL protected with a fuse of your choise (axial)

**3d-box** - A part of safety, attached is a .stl file with a case for 3d printing. This can be printed in your 3d printer or sent to a 3d-hub.

### Safety

Safety is one of the most important aspects of this project! Se more here: https://forum.mysensors.org/topic/4175/clearance-creepage-and-other-safety-aspects-in-mysensors-pcbs. It is highly recommended to use the latest revision for best safety.

Text below is written for the latest posted revision. For older revisions, please see changes.txt

##### AC-DC Separation
My thoughts has been to separate AC (primary) and DC (secondary) sides completely. At this point (Rev 7.0) this separation is 6.5mm so below minimum for use outdoor (in pollution level 3 = 8.0mm) but it should be OK if you dont use in in worst case pollution. In upcoming revisions I may add millings to increase creepage. This revision i recommend using it indoors and with the 3d case (or any case!) If you put it in a completely sealed case it should be ok to use outdoor.

The HLK-PM01 has been discussed in many threads here and are transformer based so it should be safe. More about it [here ](http://lygte-info.dk/review/Power%20Mains%20to%205V%200.6A%20Hi-Link%20HLK-PM01%20UK.html)and [here](https://skippy.org.uk/quick-look-at-the-hlk-pm01/).  

Note: there has recently (Sept 16) been some [fake/pirated HLK components](https://forum.mysensors.org/topic/1607/safe-in-wall-ac-to-dc-transformers/356)! Warning!

##### AC high voltage trace creepage

As for the separation within the AC traces I have tried to use 3mm creepage and clearance but this turned out to be to hard. This PCB  (Rev 7.0) has a creepage distance on the AC side of 2.5mm. This means if you are using 240v you can put it in pollution level II Sames as above, dont use it outdoors and put it in a case.

### Disclaimer

If you order or use this you agree to and understand that the author is not liable for any injury or damage howsoever caused and that you use this PCB at your own risk. This is DIY and the author has not made any professional test. This product has not been marked with any certification marks and cant be guaranteed to compliance with any standards. 

### **Cost**?
Total cost for one node is about US$14 where the relay and HLK-pm01 is about US$10(See BOM).

### **Bonus**

You can actually cut/split the PCB between AC and DC side and get a normal 5v MySensors node where you can attach sensors to MysX connector.

##### Links

- http://forum.mysensors.org/topic/1607/safe-in-wall-ac-to-dc-transformers

- http://forum.mysensors.org/topic/1540/110v-230v-ac-to-mysensors-pcb-board

- [Performance test and review of mains to 5V 0.6A Hi-Link HLK-PM01](http://lygte-info.dk/review/Power%20Mains%20to%205V%200.6A%20Hi-Link%20HLK-PM01%20UK.html)

- https://skippy.org.uk/quick-look-at-the-hlk-pm01/

- https://www.ieee.li/pdf/essay/safety_considerations_in_power_supply_design.pdf

### Questions needing answers

- Reliability, when will it fail? (compared to a commercial product?)
- How will it fail? What is it weakest point?
- Will my safety components be enough and prevail damage... ?
- What will happen when it fails?