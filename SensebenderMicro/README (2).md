<img src="/uploads/568d7feb0779a3581858ac2a/image/sensebender.png" class="ir" style="width:300px; max-width:35%">
The MySensors Sensebender Micro is a tiny Arduino compatible board stackable with a Nordic NRF24L01+ transceiver module. The Micro has been designed for extended battery life; e.g. it operates at 1Mhz without the need for power regulators for low power consumption.
<img src="/uploads/568d7feb0779a3581858ac2a/image/microLarge.png" class="ir" style="width:300px; max-width:35%">
Even in its small form factor it's packed with great features such as temperature and humidity sensor, 64k extra flash memory and a hardware module for secure message signing and verification.

Need more? Expand the functionality by attaching additional breakout sensors to the five digital GPIOs, two analog inputs or the I2C interface.

The board has been preloaded with a sketch reporting temperature and humidity levels every minute.

## Technical Specification

* Very small footprint, 30 mm x 17.5 mm (1.18in x 0.69 in). Making it almost same size as the NRF24 module.
* Arduino IDE 1.5.x & 1.6.x compatible.
* Si7021 integrated humidity / temperature sensor.
* 64kb SPI flash / e2prom onboard. Useful for over-the-air firmware updates. Also possible to use memory for a local sensor data cache.
* ATSHA204A on board (connected to A3). Used for HMAC-SHA256 message authentication support.
* Runs directly off 2x1.5V batteries (AA or AAA), without power converters.
* Socket<sup>*</sup> for connecting the radio module on top.
* Pin-headers* for D3-D7 on one side, and SDA/SCL + A0/A1 and power on the other side.
* FTDI header similar to Arduino Pro Mini.
* Standard Atmel programming ISCP header.
* Pads for mounting a 32Khz crystal (when timing is crucial).
* Status LED (on A2).

<small>*) You have to solder the radio socket, and pin-headers yourself if you need them mounted.
Note: The actual NRF24L01+ module is sold separately.</small>

## Available pins
- Powersupply (1.8 -> 3.5V)
- SDA / SCL (I2C bus)
- A0 / A1
- D3 / D4 / D5 / D6 / D7
- 6 pin ISP port
- 8 pin standard NRF24L01+ connector layout
- FTDI header for serial


## Wiring Things Up

**NOTE!** Maximum allowed supply voltage is 3.5V, anything above this WILL fry both
Si7021, flash and NRF24 radio module. There is NO voltage regulator on board (To keep
supply current down to a minimum)

<div class="row">
    <div class="col-sm-6">
        <div class="instruction">
            <h4 style="text-align:center">Available Pins</h4>
            <img src="/uploads/568d7feb0779a3581858ac2a/image/micro_pins.png" style="max-width:100%">
            <p>
                This picture shows all the available pins on the Micro board. Click on the image to enlarge it. The ISP connector exposes MISO, MOSI, SCK and RST.
            </p>
        </div>
        <br>
    </div>
    <div class="col-sm-6">
        <div class="instruction">
            <h4 style="text-align:center">Connecting Battery</h4>
            <img src="/uploads/568d7feb0779a3581858ac2a/image/micro_battery.png" style="width:100%">
            <p>
                The board has been designed to operate at maximum 3V (2x1.5V batteries). Make sure not to overfeed it or reverse the polarity. This would permanently kill it. Connect:
                <br>
                <b>GND</b> to minus (-) often marked with a black wire.
                <br>
                <b>VCC</b> to plus (+) often marked with a red wire.
                <br>
            </p>
        </div>
        <br>
    </div>
</div>
<div class="clearfix"></div>
<div class="row">
    <div class="col-sm-6">
        <div class="instruction">
            <h4 style="text-align:center">Connecting FTDI Programmer</h4>
            <img src="/uploads/568d7feb0779a3581858ac2a/image/micro_ftdi.png" style="width:100%">
            <p>
                To upload a new sketch to your Micro board use a FTDI programmer. Remember to set it a 3v3 output using the jumper setting on the programmer.
            </p>
            <a href="https://github.com/mysensors/ArduinoBoards">Setting up Arduino IDE for Sensebender</a>
        </div>
        <br>
    </div>
    <div class="col-sm-6">
        <div class="instruction">
            <h4 style="text-align:center">Connecting ISP Programmer</h4>
            <img src="/uploads/568d7feb0779a3581858ac2a/image/micro_isp.png" style="max-width:100%">
            <p>
                Make sure the red-dotted ISP wire goes into ground on the board. Disable power feeding on 5V ISP programmers (remove jumper) and power up the board on VCC/GND from a 3V power source.
            </p>
        </div>
        <br>
    </div>
</div>
<div class="clearfix"></div>
<div class="row">
    <div class="col-sm-6">
        <div class="instruction">
            <h4 style="text-align:center">Mounting Radio</h4>
            <img src="/uploads/568d7feb0779a3581858ac2a/image/micro_radio.png" style="width:100%">
            <p>
                The radio can be soldered directly onto the board or in a socket.
            </p>
        </div>
    </div>
</div>


## Example Sketch

This example reports humidity and temperature levels to the controller every minute. To save battery it sleeps MCU and radio between sends.

If you connect **A0 -> GND** while during power-up it will go into a self-test mode (reports status on serial output).

``https://github.com/mysensors/SensebenderMicro/blob/master/Arduino/SensebenderMicro/SensebenderMicro.ino``
