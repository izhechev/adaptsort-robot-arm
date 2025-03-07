robot = rigidBodyTree('DataFormat','column','MaxNumBodies',5);

% Base link
baseLink = rigidBody('baseLink');
joint1 = rigidBodyJoint('joint1', 'revolute');
joint1.JointAxis = [0 0 1];
setFixedTransform(joint1,trvec2tform([0 0 0.047]));
baseLink.Joint = joint1;
addBody(robot, baseLink, 'base'); % Attach to base

% Adding the physical base cylinder
baseradius = 0.1;   % Radius 10cm
baseheight = 0.047; % Height 4.7cm
baseCylinder = collisionCylinder(baseradius, baseheight);
cyloffset = trvec2tform([0, 0, baseheight/2]); % move upwards to 0,0,0
baseCylinder.Pose = cyloffset;
addCollision(robot.Base, baseCylinder);

% Second link and joint (servo on top of base)
link1 = rigidBody('link1');
joint2 = rigidBodyJoint('joint2', 'revolute');
joint2.JointAxis = [0 1 0];
setFixedTransform(joint2,trvec2tform([0 0.015 0.02]));
link1.Joint = joint2;
addBody(robot, link1, 'baseLink'); % Attach link1 to baseLink

% Adding the second arm attached to the servo
secondArm = collisionBox(0.065,0.12,0.025);
secondArm.Pose = trvec2tform([0 0.06 0]);
addCollision(robot.Bodies{2}, secondArm);

% Correct third link and joint definition
link2 = rigidBody('link2');
joint3 = rigidBodyJoint('joint3', 'revolute');
joint3.JointAxis = [0 1 0];
setFixedTransform(joint3,trvec2tform([0 0.12 0]));
link2.Joint = joint3;
addBody(robot, link2, 'link1');

show(robot,Collisions="on",Visuals="off");