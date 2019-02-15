/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Robot base program

//boat

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Robot extends TimedRobot {

    private Joystick joystick;
    private DifferentialDrive DriveTrain;
    private SpeedControllerGroup LeftDrive;
    private SpeedControllerGroup RightDrive;

    private Talon MotorZero;
    private Talon MotorOne;
    private Talon MotorTwo;
    private Talon MotorThree;
    private Talon Elevator;
    private Talon BallMotor;
    private Talon BallShooter;

    private Timer m_timer = new Timer();

    private double left;
    private double right;
    private double position;
    private boolean pressed;

    private String status = "";

    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    M_I2C i2c = new M_I2C();
    Arduino arduino = i2c.getArduino();
    Solenoid solenoid;

    //Gyroscope gyro = new Gyroscope();

    @Override
    public void robotInit() {

        MotorZero = new Talon(0);
        MotorOne = new Talon(1);
        MotorTwo = new Talon(2);
        MotorThree = new Talon(3);
        Elevator = new Talon(4);
        BallMotor = new Talon(5);
        BallShooter = new Talon(6);
    
        LeftDrive = new SpeedControllerGroup(MotorZero, MotorOne);
        RightDrive = new SpeedControllerGroup(MotorTwo, MotorThree);
    

        DriveTrain = new DifferentialDrive(LeftDrive, RightDrive);
        joystick = new Joystick(0);
        solenoid = new Solenoid(joystick);
        
        pressed = false;

        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(320, 240);

        SmartDashboard.putString("Robot", "initialized");

        System.out.println(arduino.area);
        System.out.println(arduino.x);
        System.out.println(arduino.y);

    }

    public void visionCheck(){ //Checking if it's okay to shoot ballshooter
        //pixy values: (x = 0.70-0.85) (y = 0.45 - 0.65) (a = 0.04-0.07)
        arduino = i2c.getArduino();
        if(arduino.x != -1){//if data is exist
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

    public void visionCheckNoUltra(){ //Checking if it's okay to shoot ballshooter if ultrasonic is defective
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
    
    //boat oat wrote moat afloat coat goat float bloat scapegoat throat haha
    //shooters = windshield
    //pulleys = talons

    public void drive() {
        left = (-joystick.getY()) - (joystick.getX());
        right = (-joystick.getY()) + (joystick.getX());
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

    public void rotate90Left() {
        double to = gyro.getAngle() + 90;
        if (gyro.isConnected()) {
            while (gyro.getAngle() < to) {
                DriveTrain.tankDrive(1,1);
            }
        }
    }

    public void rotate90Right() {
        double to = gyro.getAngle() - 90;
        if (gyro.isConnected()) {
            while (gyro.getAngle() < to) {
                DriveTrain.tankDrive(-1,-1);
            }
        }
    }

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

    public void goto180() {
        if (gyro.isConnected()) {
            while (gyro.getAngle() > 178 && gyro.getAngle() < 182) {
                DriveTrain.tankDrive(-1,-1);
            }
        }
    }

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

    public void periodic() {
        //If 2 is pressed, debug message
        if (joystick.getRawButtonReleased(2)) {
            checkConnections();
        }

        //If 5 is pressed, rotate 90 to left
<<<<<<< HEAD
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
=======
        if (joystick.getRawButton(5) && !pressed) {
            rotate90Left();
            pressed = true;
        } else if (!joystick.getRawButton(5)) {
            pressed = false;
>>>>>>> parent of 580ba0c... Subsystems, Stylizing, EVERYTHING.
        }

        //If 6 is pressed, rotate 90 to right
        if (joystick.getRawButton(6) && !pressed) {
            rotate90Right();
            pressed = true;
        } else if (!joystick.getRawButton(6)) {
            pressed = false;
        }

        //If 3 is pressed, go back to 0
        if (joystick.getRawButton(3) && !pressed) {
            goto0();
            pressed = true;
        } else if (!joystick.getRawButton(3)) {
            pressed = false;
        }

        //If 4 is pressed, go to 180
<<<<<<< HEAD
        if (rightJoystick.getRawButtonReleased(4)) {
            //goto180();
>>>>>>> 0fee9883f3518736ece6171a0f62088ea3980e9a
=======
        if (joystick.getRawButton(4) && !pressed) {
            goto180();
            pressed = true;
        } else if (!joystick.getRawButton(4)) {
            pressed = false;
>>>>>>> parent of 580ba0c... Subsystems, Stylizing, EVERYTHING.
        }

        //Solenoid Checks, buttons 12-7
        if (joystick.getRawButton(12)) {
            solenoid.hatchSolenoidForward();
        } else if (joystick.getRawButton(11)) {
            solenoid.hatchSolenoidBackward();
<<<<<<< HEAD
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
=======
        } else if (joystick.getRawButton(10)) {
>>>>>>> parent of 580ba0c... Subsystems, Stylizing, EVERYTHING.
            solenoid.habSolenoidFrontForward();
        } else if (joystick.getRawButton(9)) {
            solenoid.habSolenoidFrontBackward();
        } else if (joystick.getRawButton(8)) {
            solenoid.habSolenoidBackForward();
        } else if (joystick.getRawButton(7)) {
            solenoid.habSolenoidBackBackward();
>>>>>>> 0fee9883f3518736ece6171a0f62088ea3980e9a
        }

        drive();
       
    }

    @Override
    public void teleopPeriodic() {
        periodic();
    }

    public void autonomousPeriodic() {
        periodic();
    }
}

