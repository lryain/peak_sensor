### Decsription
Wall Socket Insertable Node is a board which you can insert into basically every 220 -110 V wall socket to control and monitor it.

### Features 
- Very slim (only 10.2mm top layer components and <13mm overall height).
- Embedded custom AC/DC switching power supply with very low profile transformer
- 10A relay
- RFM69 transceiver
- Onboard ATSHA204A(optional)
- AC current sensor ACS712 (optional)
- Hardware window-watchdog (optional). Watchdog is very important but with MySensors we can't use atmega's watchdog (it is used in deep sleep mode in MySensors library). So I suggest using hardware watchdog to improve stability.
- Smart RGB led (SK6812mini)
- Standard UART header

### Estimated prices
BASIC (ac/dc, atmega, rfm69, 10a relay) - **9.5$**<br>
ADVANCED (ac/dc, atmega, rfm69, 10a relay, current sensor, rgb led) - **10.8$**<br>
MAX (ac/dc, atmega, rfm69, 10a relay, currrent sensor, atsha204, watchdog, rgb led) - **12.2$**


### PCB requirements
- 7mil/7mil width/clearance
- 2oz copper

### Project status
<strike>Waiting for custom low profile transformer to arrive, last checking of schematic and pcb... Ready to start pcb sample testing!</strike>
Sample is ready. Device is under tests now.