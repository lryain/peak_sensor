### Decsription
Wall Switch Insertable Node is a board which you can insert into basically every wall switch. 

### Features 
- Small size - only 31x49mm with 8.5mm height! See drawing.
- CR2450 battery holder. I haven't completed all tests but hope this board will work about 5 years from battery.
- Very clever, cheap and micropower switch hardware controller. You can use up to 4 switches and buttons (both tacts and ordinary ON/OFF switches). Every channel has hardware debouncer (so you do not need software debouncing). Every state change of every switch triggers interrupt 1 (pin D3). Switch controller circuit has very low power consumption (less than 1uA). See schematic.
- RFM69 transceiver
- Onboard divider to measure battery level.
- Onboard ATSHA204A (optional)
- Standard UART header
- JST (Japan Solder less Terminals) to connect switches. 

### Estimated prices
BASIC (atmega, rfm69, buttons controller, JST wires) - **6.5$**<br>
MAX (atmega, rfm69, buttons controller, JST wires, atsha204) - **7.5$**

### PCB requirements
- 5mil/5mil width/clearance

### Project status
<strike>Starting pcb sample production!</strike>
Device is under tests now :)