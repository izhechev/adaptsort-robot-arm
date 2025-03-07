
import UARTCommunicator
import JointController

# SC232 class, responsible for controlling individual motor controls
# 

class SSC232(JointController):
    def __init__(self, UARTCommunicator=UARTCommunicator):
        self.communicator = UARTCommunicator

    def send_pwm_steer_command(self):
        pass

    def send_version_command(self):
        self.communicator.send_data("VER\r")
    
    def steer_motor(self, motor_number:int, radians:float):
        pass
        # 1. convert radians to PWM (0 rad = 1500us, 1/2pi rad = 2500us, within range)
        # 2. Send the steer command
        # Use the following
        # # <ch> P <pw> S <spd> ... # <ch> P <pw> S <spd> T <time> <cr> for the command

    def update_position(self, joint_number, rads):
        print(f"Updating joint {joint_number} to {rads} radians.")
        # Implementation here to actually command the motor

        pass