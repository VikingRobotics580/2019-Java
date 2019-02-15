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

import frc.*;

import edu.wpi.first.wpilibj.TimedRobot;

//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {

<<<<<<< HEAD
    private Joystick joystick;
    private DifferentialDrive DriveTrain;
    private SpeedControllerGroup LeftDrive;
    private SpeedControllerGroup RightDrive;

    private Talon MotorZero;
=======
    public static OI oi;
<<<<<<< HEAD
=======

    // Controls
  //  public Joystick leftJoystick;
  //  public Joystick rightJoystick;

    // Drive
    //private SpeedControllerGroup LeftDrive;
    //private SpeedControllerGroup RightDrive;

    // Talons
    /*private Talon MotorZero;
>>>>>>> 1a6b8ffbb4488fdd5eb4fa2cffa5c0ecb4ac9199
    private Talon MotorOne;
    private Talon MotorTwo;
    private Talon MotorThree;
    private Talon Elevator;
    private Talon BallMotor;
    private Talon BallShooter;*/

    private Timer m_timer = new Timer();
<<<<<<< HEAD

    private double left;
    private double right;
    private double position;
    private boolean pressed; //I'm like groceries

    private String status = "";

    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    M_I2C i2c = new M_I2C();
    Arduino arduino = i2c.getArduino();
    Solenoid solenoid;
=======
    //private double left;
    //private double right;
    //private double position;
   // private boolean pressed;
    //private String status = "";
>>>>>>> a2f0a02b1fc17c1d1fc8292867bbd949083fb3ef
    boolean pressed;
    String status;
    // Sensors
    public static DriveSubsystem drive = new DriveSubsystem();
    public static M_I2C m_i2c = new M_I2C();
    public static Solenoid solenoid = new Solenoid();
    SendableChooser<Command> m_chooser = new SendableChooser<>();
    //private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    //M_I2C i2c = new M_I2C();
    //Arduino arduino = i2c.getArduino();

    //Solenoid solenoid;
>>>>>>> 1a6b8ffbb4488fdd5eb4fa2cffa5c0ecb4ac9199

    //Gyroscope gyro = new Gyroscope();

    @Override
    public void robotInit() {
<<<<<<< HEAD
=======
<<<<<<< HEAD

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
=======
        // Talons:
        //MotorZero = new Talon(0);
        //MotorOne = new Talon(1);
        //MotorTwo = new Talon(2);
        //MotorThree = new Talon(3);
        //Elevator = new Talon(4);
        //BallMotor = new Talon(5);
        //BallShooter = new Talon(6);

        // Drive: 
        //LeftDrive = new SpeedControllerGroup(MotorZero, MotorOne);
        //RightDrive = new SpeedControllerGroup(MotorTwo, MotorThree);

>>>>>>> a2f0a02b1fc17c1d1fc8292867bbd949083fb3ef
        // Controls:
        //m_chooser.setDefaultOption("Drive", new DriveCommand());
        //m_chooser.setDefaultOption("M_I2C", new ArduinoCommand());
        SmartDashboard.putData("Chooser",m_chooser);
        //leftJoystick = new Joystick(0);
        //rightJoystick = new Joystick(1);
        //solenoid = new Solenoid();
        pressed = false;

        // Sensors:
        ///UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        //camera.setResolution(320, 240);
        SmartDashboard.putString("Robot", "initialized");
       // System.out.println(arduino.area);
       // System.out.println(arduino.x);
        //System.out.println(arduino.y);

    }
<<<<<<< HEAD
=======

    // Vision Tester, "Is the position good to load or shoot ball?"
    /*public void visionCheck(){
>>>>>>> 1a6b8ffbb4488fdd5eb4fa2cffa5c0ecb4ac9199
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
<<<<<<< HEAD
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

=======
    }  */
>>>>>>> a2f0a02b1fc17c1d1fc8292867bbd949083fb3ef
  
    // Reset Gyro:
    public void resetGyro() {
        //gyro.reset();
    }
<<<<<<< HEAD
=======
/*
    // Debug Connections:
>>>>>>> 1a6b8ffbb4488fdd5eb4fa2cffa5c0ecb4ac9199
    public void checkConnections() {
        if (i2c.getArduino().x != -1) {
            SmartDashboard.putString("Pixy", "set up");
            //SmartDashboard.putNumber("Angle", gyro.getAngle());
            SmartDashboard.putNumber("Distance", i2c.getDistance());
            SmartDashboard.putData("Gyro", gyro);
        } 
        System.out.println("Gyro: " + Math.abs(gyro.getAngle()));
        SmartDashboard.putData("Gyro", gyro);
    }*/
>>>>>>> a2f0a02b1fc17c1d1fc8292867bbd949083fb3ef

    public void periodic() {
<<<<<<< HEAD
        Scheduler.getInstance().run();
=======
<<<<<<< HEAD
        //If 2 is pressed, debug message
        if (joystick.getRawButtonReleased(2)) {
            checkConnections();
=======
       /* //If 2 is pressed, debug message
        if (leftJoystick.getRawButtonReleased(2) || rightJoystick.getRawButtonReleased(2)) {
            //checkConnections();
>>>>>>> 1a6b8ffbb4488fdd5eb4fa2cffa5c0ecb4ac9199
        }

        //If 5 is pressed, rotate 90 to left
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
        }

        //Solenoid Checks, buttons 12-7
        if (joystick.getRawButton(12)) {
            solenoid.hatchSolenoidForward();
        } else if (joystick.getRawButton(11)) {
            solenoid.hatchSolenoidBackward();
        } else if (joystick.getRawButton(10)) {
            //solenoid.habSolenoidFrontForward();
        } else if (joystick.getRawButton(9)) {
            //solenoid.habSolenoidFrontBackward();
        } else if (joystick.getRawButton(8)) {
            //solenoid.habSolenoidBackForward();
        } else if (joystick.getRawButton(7)) {
            //solenoid.habSolenoidBackBackward();
        }
        */
>>>>>>> a2f0a02b1fc17c1d1fc8292867bbd949083fb3ef
    }

    @Override
    public void teleopPeriodic() {
        periodic();

    }

    public void autonomousPeriodic() {
        periodic();
    }
}

