
import DHJoint

class DHController:
    
    def __init__(self):
        self.jointlist = list()

    def add_joint(self, joint_num:int, a, d, alpha_deg, theta_deg):
        self.jointlist.append(DHJoint(joint_num, a, d, alpha_deg, theta_deg))
    
    def remove_joint(self, joint_number):
        for joint in self.jointlist:
            if joint.get_joint_number() is joint_number:
                self.jointlist.remove(joint)

    def apply_all_joint_rotations(self):
        for joint in self.jointlist:
            joint.apply_rotation()

    def get_joint_information(self):
        for joint in self.jointlist:
            print(joint.get_info())
    
    def set_joint_rotation(self):
        # needs joint number
        # needs joint rotation (in deg)
        pass # set theta 

    def set_hypothetical_joint_rotation(self):
        # needs joint number
        # needs hypothetical joint notation (in deg)
        pass # set hypothetical theta

    def _calculate_cartesian_endpoint(self):
        # 1. Get all instances of matrices T from 0 to i
        DH_matrix_list = [joint.get_transformation_matrix() for joint in self.jointlist] # is this correct?
        # 2. Calculate, using multiplication, the end matrix
        # 3. Isolate the XYZ values from the right upper side of the matrix
        # 4. return the XYZ values of the computed matrix
        

    def _calculate_hypothetical_cartesian_endpoint(self):
        # Here, instead of the regular matrices, get the hypothetical alpha matrix using get_transformation_matrix_with_hypothetical_theta
        pass