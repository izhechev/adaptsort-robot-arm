## Pythonic robot controller

### Setting up UART

for communicating to the robot, the GPIO UART pins on the raspberry pi will be used. These have the respective pins:

- 14: TXD
- 15: RXD

Please consult [the pinout sheet](https://pinout.xyz/pinout/uart) for more information. Remember to mirror the RX and TX pins with the RS-232 to ensure proper communication transmission!

To setup UART on the Raspberry pi, navigate to `/boot/config.txt` and add the following:

```bash
enable_uart=1
dtoverlay=disable-bt
```

For using UART on the raspberry pi through Python, `pyserial` is used. Install it using `pip install pyserial` (or use `sudo apt-get` when using the package manager). Setup the serial objects using the following parameters:

```python

ser = serial.Serial(
    port='/dev/serial0',  # Port for GPIO 14 and 14
    baudrate=BAUDRATE,    # Adjust as needed
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS,
    timeout=1             # Set read timeout
)
```

### Setting up 