#
# Todo:
# - Add the matrix functionality for the DH parameters
# - Add the step-by-step pickup algorithm, with the individual steps as individual commands
# - Add a function where one can add a joint to the list of joints
#
# - Step 1: Base position
# - Step 2: Rotate base (using cosine law)
# - Step 3: XY correction (with Z adjustment)
# - Step 4: Z correction to a certain height (with XY adjustment)
# - Step 5: Grabbing

import DHController
from enum import Enum, auto

class RobotControllerMainState(Enum):
    IDLE = auto()
    RECEIVING_REQUEST = auto()
    PICKING_UP_OBJECT = auto()

class RobotController:

    def __init__(self):
        self.main_state = RobotControllerMainState.IDLE
        # TODO DH controller could in the future be abstracted away as a control interface
        self.dh_controller = DHController()

    # TODO Call using CV API, to which the state would change
    # to picking up an object whenever possible
    def receive_pickup_request():
        pass