## Specs:

- User defined switches/scenes.
- User can define how many switches/scenes to use.
- One value is fetched from controller (like outside temp) every 15min.
- Dims if user is not present
- Clock
- Automatically selects switch/scene depending on time (user setting).
- Nice logo with your name on top.
- Powered from 240v or 5v

## BOX/LCD enclosure with buttons
The enclosure is from a floor heating package from the company Ebeco. I got this over when we remodeled our bathroom. Ebeco seems to be a Swedish company, but i think there should be other good enclosures available since the LCD screen size (1,8" / 36x28.7mm) is quite common. Here is a [link ](http://www.smarthem.se/19976/p/termostater-till-elektrisk-golvvarme/front-cover-till-termostat-ebeco-eb-therm-355/)to a swedish store.

This fits inside a standard Schneider wall frame. The frame and Ebeco enclosure was glued to a metal frame which can be attached to the in wall box with screws.

## Mounting the LCD and buttons
Images say more than 1000 words:

![enter image description here](https://www.openhardware.io//uploads/56a89613e61a0aa50496dcdb/image/buildinstructions.jpg "enter image title here")

## Wiring
![enter image description here](https://www.openhardware.io//uploads/56a89613e61a0aa50496dcdb/image/Fritzing.jpg "enter image title here")

## Power
For this project i use my 240 to 5v node. It holds both power, MCU and nrf24 radio.
LCD, buttons and LED are connected to the MYSX gpio on this node.
Please read more here: https://www.openhardware.io/view/13/In-Wall-ACDC-Pcb-for-MySensors

![enter image description here](https://www.openhardware.io//uploads/56a89613e61a0aa50496dcdb/image/20160124_213417.jpg "enter image title here")

If you have 5v in another way you can do this by wiring an Arduino Pro mini and Nrf24 radio as usual.

As always - if you decides to use this know it is high voltage power = do it on your own risk - I cant guarantee your safety.

## Inspiration (Build images and other stuff)
http://1drv.ms/1SfkEtm