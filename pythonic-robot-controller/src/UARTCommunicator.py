import serial # == pyserial
import threading

class UARTCommunicator:
    def __init__(self, port='/dev/serial0', baudrate=2400, timeout=1):
        self.ser = serial.Serial(
            port=port,
            baudrate=baudrate,
            parity=serial.PARITY_NONE,
            stopbits=serial.STOPBITS_ONE,
            bytesize=serial.EIGHTBITS,
            timeout=timeout
        )
        self.read_thread = threading.Thread(target=self.read_data)
        self.running = False
        self.callback = None

    def start_reading(self, callback=None):
        if callback:
            self.callback = callback
        else:
            self.callback = print # default callback function
        self.running = True
        self.read_thread.start()

    def stop_reading(self):
        self.running = False
        self.read_thread.join()

    def read_data(self):
        while self.running:
            if self.ser.in_waiting > 0:
                data = self.ser.readline().decode('utf-8').rstrip()
                self.callback(data)

    def send_data(self, data):
        self.ser.write(data.encode())

    def close(self):
        self.stop_reading()
        self.ser.close()