
import DHController

class ObjectPickingActivity:

    def __init__(self, DHController:DHController, desired_cartesian_point):
        # Need DH controller
        # Would need desired setpoint to move towards, would be cartesian xyz list
        self.controller=DHController
        self.setpoint=desired_cartesian_point

    def _run_activity(self):
        # 1. Check if there are 5 joints
        pass

    def start(self):
        pass

    def _goto_default_position(self): # Step 1
        # Apply hardcoded correct rotations
        # TODO how would one know this is done? Callback function of self-context?
        pass

    def _rotate_base_joint(self): # Step 2
        pass