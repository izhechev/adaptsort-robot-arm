
import numpy as np
import JointController

class DHJoint:
    # TODO pass a child JointController instance through DI (i.e., get interface)
    def __init__(self, joint_num:int, a, d, alpha_deg, theta_deg):
        self.joint_num=joint_num
        self.a = a
        self.d = d
        self.alpha = np.radians(alpha_deg)
        self.theta = np.radians(theta_deg)
        self.hypothetical_theta = np.radians(0)

    def get_joint_number(self):
        return self.joint_num
    
    def get_info(self):
        return f"Joint {self.joint_num}:\ta={self.a}\td={self.d},alpha={self.alpha},theta={self.theta}, hypothetical theta={self.h_theta}"

    def update_theta(self, new_theta_deg):
        self.theta = np.radians(new_theta_deg)

    def update_hypothetical_theta(self, new_hypothetical_theta_deg):
        self.hypothetical_theta = np.radians(new_hypothetical_theta_deg)

    def apply_rotation(self):
        pass # TODO will invoke interface here

    def get_transformation_matrix(self):
        return self._compute_transformation_matrix(self.theta)

    def get_transformation_matrix_with_hypothetical_theta(self):
        return self._compute_transformation_matrix(self.hypothetical_theta)

    def _compute_transformation_matrix(self, theta_rad):
        T = np.array([
            [np.cos(theta_rad), -np.sin(theta_rad), 0, self.a],
            [np.sin(theta_rad) * np.cos(self.alpha), np.cos(theta_rad) * np.cos(self.alpha), -np.sin(self.alpha), -self.d * np.sin(self.alpha)],
            [np.sin(theta_rad) * np.sin(self.alpha), np.cos(theta_rad) * np.sin(self.alpha), np.cos(self.alpha), self.d * np.cos(self.alpha)],
            [0, 0, 0, 1]
        ])
        return T