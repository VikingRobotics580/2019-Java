// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Robot base program

package frc.robot;

// General:
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

// Controls 
/*import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;*/

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

    public static OI oi;

    // Controls
  //  public Joystick leftJoystick;
  //  public Joystick rightJoystick;

    // Drive
    //private SpeedControllerGroup LeftDrive;
    //private SpeedControllerGroup RightDrive;

    // Talons
    /*private Talon MotorZero;
    private Talon MotorOne;
    private Talon MotorTwo;
    private Talon MotorThree;
    private Talon Elevator;
    private Talon BallMotor;
    private Talon BallShooter;*/

    // General: 
    private Timer m_timer = new Timer();
    //private double left;
    //private double right;
    //private double position;
   // private boolean pressed;
    //private String status = "";
    boolean pressed;
    String status;
    // Sensors
    public static Drive drive = new Drive();
    //private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    //M_I2C i2c = new M_I2C();
    //Arduino arduino = i2c.getArduino();

    //Solenoid solenoid;

    // Initialization:
    @Override
    public void robotInit() {
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

        // Controls:

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

    // Vision Tester, "Is the position good to load or shoot ball?"
    /*public void visionCheck(){
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
    }  */
  
    // Reset Gyro:
    public void resetGyro() {
        //gyro.reset();
    }
/*
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
    }*/

    // Base Periodic Code for Teleop and Autonomous
    public void periodic() {
       /* //If 2 is pressed, debug message
        if (leftJoystick.getRawButtonReleased(2) || rightJoystick.getRawButtonReleased(2)) {
            //checkConnections();
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
        if (rightJoystick.getRawButton(12)) {
            solenoid.hatchSolenoidForward();
        } else if (rightJoystick.getRawButton(11)) {
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
