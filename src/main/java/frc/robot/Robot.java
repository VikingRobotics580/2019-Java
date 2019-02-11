/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshbeg, Gavin Sanchez
// Robot base program

//boat

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
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
    PixyData pkt = i2c.getPixy();
    Solenoid solenoid;

    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button button11;
    Button button12;

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

        //UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture();
        //cam1.setResolution(320, 240);

        System.out.println("bitcht");

        System.out.println(pkt.area);
        System.out.println(pkt.x);
        System.out.println(pkt.y);

        button3 = new JoystickButton(joystick, 3);
        button4 = new JoystickButton(joystick, 4);
        button5 = new JoystickButton(joystick, 5);
        button6 = new JoystickButton(joystick, 6);
        button6 = new JoystickButton(joystick, 7);
        button9 = new JoystickButton(joystick, 8);
        button9 = new JoystickButton(joystick, 9);
        button10 = new JoystickButton(joystick, 10);
        button11 = new JoystickButton(joystick, 11);
        button12 = new JoystickButton(joystick, 12);

        //UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture();
        //cam1.setResolution(320, 240);

    }

    public void centerOnObject(){
        //pixy values: (x = 0.70-0.85) (y = 0.45 - 0.65) (a = 0.04-0.07)
        pkt = i2c.getPixy();
        if(pkt.x != -1){//if data is exist
            System.out.println("Pixy is set up");
            status = "good";
            if((pkt.x >= 0.70 && pkt.x <= 0.85) && (pkt.y >= 0.45 && pkt.y <= 0.65) && (pkt.area >= 0.4 && pkt.area <= 0.7)) {
                System.out.println("Go for it");
            }
        }
        if (!status.equals("bad")) {
            System.out.println(pkt.area);
            System.out.println(pkt.x);
            System.out.println(pkt.y);
            System.out.println("Camera data no data");
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

    public void checkConnections() {
        if (i2c.getPixy().x != -1) {
            System.out.println("Pixy Status: Working");
            System.out.println("Pixy Status: Error");
        } 
        Timer.delay(1);
    }

    @Override
    public void teleopPeriodic() {
        /**if (joystick.getRawButton(12) && !pressed) {
            checkConnections();
            pressed = true;
        } else if (!joystick.getRawButton(12)) {
            pressed = false;
        }*/

        /*if (joystick.getRawButton(11) && !pressed) {
            System.out.println(gyro.getAngle());
            pressed = true;
        } else if (!joystick.getRawButton(11)) {
            pressed = false;
        }
        */

        if (joystick.getRawButton(12)) {
            solenoid.hatchSolenoidForward();
        } else if (joystick.getRawButton(11)) {
            solenoid.hatchSolenoidBackward();
        } else if (joystick.getRawButton(10)) {
            solenoid.habSolenoidFrontForward();
        } else if (joystick.getRawButton(9)) {
            solenoid.habSolenoidFrontBackward();
        } else if (joystick.getRawButton(8)) {
            solenoid.habSolenoidBackForward();
        } else if (joystick.getRawButton(7)) {
            solenoid.habSolenoidBackBackward();
        }

        drive();
        //solenoid.habSolenoidBack();
        //solenoid.habSolenoidFront();

        /*if (joystick.getRawButton(10) && !pressed) {
            System.out.println(i2c.getDistance());
            pressed = true;
        } else if (!joystick.getRawButton(10)) {
            pressed = false;
        }
        */
    }
}
