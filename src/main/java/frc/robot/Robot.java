// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Robot base program

package frc.robot;

// General:
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

// Controls: 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

// Sensors:
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

// Subsystems:

import frc.robot.subsystems.Solenoid;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.M_I2C;
import frc.robot.subsystems.Arduino;

public class Robot extends TimedRobot {

    // Controls:
    private Joystick leftJoystick;
    private Joystick rightJoystick;

    // Drive:
    private DifferentialDrive DriveTrain;
    private SpeedControllerGroup LeftDrive;
    private SpeedControllerGroup RightDrive;

    // Talons:
    private Talon MotorZero;
    private Talon MotorOne;
    private Talon MotorTwo;
    private Talon MotorThree;
    private Talon Elevator;
    private Talon BallMotor;
    private Talon BallShooter;

    // General: 
    private Timer m_timer = new Timer();
    private double left;
    private double right;
    private double position;
    private boolean pressed;
    private String status = "";

    // Sensors:
    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    M_I2C i2c = new M_I2C();
    Arduino arduino = i2c.getArduino();

    Solenoid solenoid;

    // Initialization:
    @Override
    public void robotInit() {
        // Talons:
        MotorZero = new Talon(0);
        MotorOne = new Talon(1);
        MotorTwo = new Talon(2);
        MotorThree = new Talon(3);
        Elevator = new Talon(4);
        BallMotor = new Talon(5);
        BallShooter = new Talon(6);

        // Drive: 
        LeftDrive = new SpeedControllerGroup(MotorZero, MotorOne);
        RightDrive = new SpeedControllerGroup(MotorTwo, MotorThree);
        DriveTrain = new DifferentialDrive(LeftDrive, RightDrive);

        // Controls:

        leftJoystick = new Joystick(0);
        rightJoystick = new Joystick(1);
        solenoid = new Solenoid(rightJoystick);
        pressed = false;

        // Sensors:
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(320, 240);
        SmartDashboard.putString("Robot", "initialized");
        System.out.println(arduino.area);
        System.out.println(arduino.x);
        System.out.println(arduino.y);

    }

    // Vision Tester, "Is the position good to load or shoot ball?"
    public void visionCheck(){
        //pixy values: (x = 0.70-0.85) (y = 0.45 - 0.65) (a = 0.04-0.07)
        arduino = i2c.getArduino();
        if (arduino.x != -1) {
            SmartDashboard.putString("Pixy", "set up");
            status = "good";
            if((arduino.x >= 0.70 && arduino.x <= 0.85) && (arduino.y >= 0.45 && arduino.y <= 0.65) && (arduino.area >= 0.4 && arduino.area <= 0.7) && (arduino.distance > 8)) {
                System.out.println("Go for it");
            }
        }
        if (!status.equals("bad")) {
            System.out.println(arduino.area);
            System.out.println(arduino.x);
            System.out.println(arduino.y);
            SmartDashboard.putString("Pixy", "not set up");
            status = "bad";
        }
    }  

    public void visionCheckNoUltrasonic(){ //Checking if it's okay to shoot ballshooter if ultrasonic is defective
        //pixy values: (x = 0.70-0.85) (y = 0.45 - 0.65) (a = 0.04-0.07)
        arduino = i2c.getArduino();
        if(arduino.x != -1){//if data is exist
            SmartDashboard.putString("Pixy", "set up");
            status = "good";
            if((arduino.x >= 0.70 && arduino.x <= 0.85) && (arduino.y >= 0.45 && arduino.y <= 0.65) && (arduino.area >= 0.4 && arduino.area <= 0.7)) {
                System.out.println("Go for it");
            }
        }
        if (!status.equals("bad")) {
            System.out.println(arduino.area);
            System.out.println(arduino.x);
            System.out.println(arduino.y);
            SmartDashboard.putString("Pixy", "not set up");
            status = "bad";
        }
    }  

    /*
    boat oat wrote moat afloat coat goat float bloat scapegoat throat haha
    //shooters = windshield
    //pulleys = talons
    */

    // Main Drive Code:
    public void drive() {
        left = (-rightJoystick.getY()) - (rightJoystick.getX());
        right = (-rightJoystick.getY()) + (rightJoystick.getX());
        position = java.lang.Math.abs(left);

        if (position < java.lang.Math.abs(right)) {
          position = java.lang.Math.abs(right);
        }

        if (position > 1) {
          left = left/position;
          right = right/position;
        }
        DriveTrain.tankDrive(left, right);
    }

    // Rotate robot 90 deg to the left
    public void rotate90Left() {
        double to = gyro.getAngle() + 90;
        if (gyro.isConnected()) {
            while (gyro.getAngle() < to) {
                DriveTrain.tankDrive(1,1);
            }
        }
    }

    // Rotate robot 90 deg to the right
    public void rotate90Right() {
        double to = gyro.getAngle() - 90;
        if (gyro.isConnected()) {
            while (gyro.getAngle() < to) {
                DriveTrain.tankDrive(-1,-1);
            }
        }
    }

    // Go back to 0
    public void goto0() {
        if (gyro.isConnected()) {
            if (gyro.getAngle() > 180) {
                while (gyro.getAngle() > -3 && gyro.getAngle() < 3) {
                    DriveTrain.tankDrive(1,1);
                }
            } else {
                while (gyro.getAngle() > -3 && gyro.getAngle() < 3) {
                    DriveTrain.tankDrive(-1,-1);
                }
            }
        }
    }

    // Go to 180 degree position
    public void goto180() {
        if (gyro.isConnected()) {
            while (gyro.getAngle() > 178 && gyro.getAngle() < 182) {
                DriveTrain.tankDrive(-1,-1);
            }
        }
    }

    // Reset Gyro:
    public void resetGyro() {
        gyro.reset();
    }

    // Debug Connections:
    public void checkConnections() {
        if (i2c.getArduino().x != -1) {
            SmartDashboard.putString("Pixy", "set up");
            //SmartDashboard.putNumber("Angle", gyro.getAngle());
            SmartDashboard.putNumber("Distance", i2c.getDistance());
            SmartDashboard.putData("Gyro", gyro);
        } 
        System.out.println("Gyro: " + Math.abs(gyro.getAngle()));
        SmartDashboard.putData("Gyro", gyro);
    }

    // Base Periodic Code for Teleop and Autonomous
    public void periodic() {
        //If 2 is pressed, debug message
        if (leftJoystick.getRawButtonReleased(2) || rightJoystick.getRawButtonReleased(2)) {
            //checkConnections();
        }

        //If 5 is pressed, rotate 90 to left
<<<<<<< HEAD
        if (joystick.getRawButton(5) && !pressed) {
            //rotate90Left();
            pressed = true;
        } else if (!joystick.getRawButton(5)) {
            pressed = false;
        }

        //If 6 is pressed, rotate 90 to right
        if (joystick.getRawButton(6) && !pressed) {
            //rotate90Right();
            pressed = true;
        } else if (!joystick.getRawButton(6)) {
            pressed = false;
        }

        //If 3 is pressed, go back to 0
        if (joystick.getRawButton(3) && !pressed) {
            //goto0();
            pressed = true;
        } else if (!joystick.getRawButton(3)) {
            pressed = false;
        }

        //If 4 is pressed, go to 180
        if (joystick.getRawButton(4) && !pressed) {
            //goto180();
            pressed = true;
        } else if (!joystick.getRawButton(4)) {
            pressed = false;
=======
        if (rightJoystick.getRawButtonReleased(5)) {
            //rotate90Left();
        }

        //If 6 is pressed, rotate 90 to right
        if (rightJoystick.getRawButtonReleased(6)) {
            //rotate90Right();
        }

        //If 3 is pressed, go back to 0
        if (rightJoystick.getRawButtonReleased(3)) {
            //goto0();
        }

        //If 4 is pressed, go to 180
        if (rightJoystick.getRawButtonReleased(4)) {
            //goto180();
>>>>>>> 0fee9883f3518736ece6171a0f62088ea3980e9a
        }

        //Solenoid Checks, buttons 12-7
        if (rightJoystick.getRawButton(12)) {
            solenoid.hatchSolenoidForward();
        } else if (rightJoystick.getRawButton(11)) {
            solenoid.hatchSolenoidBackward();
<<<<<<< HEAD
        } else if (joystick.getRawButton(10)) {
            //solenoid.habSolenoidFrontForward();
        } else if (joystick.getRawButton(9)) {
            //solenoid.habSolenoidFrontBackward();
        } else if (joystick.getRawButton(8)) {
            //solenoid.habSolenoidBackForward();
        } else if (joystick.getRawButton(7)) {
            //solenoid.habSolenoidBackBackward();
=======
        } else if (rightJoystick.getRawButton(10)) {
            solenoid.habSolenoidFrontForward();
        } else if (rightJoystick.getRawButton(9)) {
            solenoid.habSolenoidFrontBackward();
        } else if (rightJoystick.getRawButton(8)) {
            solenoid.habSolenoidBackForward();
        } else if (rightJoystick.getRawButton(7)) {
            solenoid.habSolenoidBackBackward();
>>>>>>> 0fee9883f3518736ece6171a0f62088ea3980e9a
        }

        drive();
       
    }

    // Teleop
    @Override
    public void teleopPeriodic() {
        periodic();
    }

    // Autonomous
    @Override
    public void autonomousPeriodic() {
        periodic();
    }

}
