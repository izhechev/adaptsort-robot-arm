from abc import ABC, abstractmethod

class JointController(ABC):
    @abstractmethod
    def update_position(self, joint_number, rads):
        pass