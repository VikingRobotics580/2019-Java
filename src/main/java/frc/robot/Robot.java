/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//boat

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
=======
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.AnalogGyro;
>>>>>>> 3712f918eee305565a997551c821e84d9da06801

public class Robot extends TimedRobot {

    NetworkTable table;

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

    private Timer m_timer = new Timer();

    private double left;
    private double right;
    private double position;

    private String status = "";

    M_I2C i2c = new M_I2C();
    PixyData pkt = i2c.getPixy();

    ADXRS450_Gyro gyro;
=======
    Compressor compressor;
    DoubleSolenoid hatch;
    DoubleSolenoid habFront;
    DoubleSolenoid habBack;

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

    private AnalogGyro gyro;
>>>>>>> 3712f918eee305565a997551c821e84d9da06801

    public Robot() {
        //table = NetworkTable.getTable(raspberr);
        compressor = new Compressor();	//null parameter
        hatch = new DoubleSolenoid(0, 1);	
        habFront = new DoubleSolenoid(2, 3);	
        habBack = new DoubleSolenoid(4, 5);	
    }

    @Override
    public void robotInit() {
        MotorZero = new Talon(0);
        MotorOne = new Talon(1);
        MotorTwo = new Talon(2);
        MotorThree = new Talon(3);
        Elevator = new Talon(4);
        Elevator = new Talon(5);
    
        LeftDrive = new SpeedControllerGroup(MotorTwo, MotorThree);
        RightDrive = new SpeedControllerGroup(MotorZero, MotorOne);
    
        DriveTrain = new DifferentialDrive(LeftDrive, RightDrive);
        joystick = new Joystick(0);
    
<<<<<<< HEAD
        //UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture();
        //cam1.setResolution(320, 240);

        System.out.println("bitcht");

        System.out.println(pkt.area);
        System.out.println(pkt.x);
        System.out.println(pkt.y);
    }

    public void centerOnObject(){
        pkt = i2c.getPixy();
        if(pkt.x != -1){//if data is exist
            System.out.println("Pixy is set up");
            status = "good";
            if(pkt.x < .48 || pkt.x > .52){
                //and the 'object', whatever it may be is not in the center
                //the code on the arduino decides what object to send
                while(pkt.x < .48 || pkt.x > .52){//while it is not center
                    
                    if(pkt.x < .48){//if its on the left side of robot, turn left
                        System.out.println("Would go left");
                        /*
                        drive.setLDrive(-0.2);//this is our left side of tank drive
                        drive.setRDrive(0.2);//you drive code might differ
                        */
                    }
                    if(pkt.x > .52){//if its on the right side of robot, turn right
                        System.out.println("Would go right");
                        /*
                        drive.setLDrive(0.2);
                        drive.setRDrive(-0.2);
                        */
                    }
                    if(pkt.y == -1) {//Restart if ball lost during turn
                        System.out.println("Restart ball");
                        break;
                    }
                    pkt = i2c.getPixy();//refresh the data
                    System.out.println("XPos: " + pkt.x);//print the data just to see
                }
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
    
=======
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

        gyro = new AnalogGyro(1); 
        gyro.reset();

        UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture();
        cam1.setResolution(320, 240);

    } //boat oat wrote moat afloat coat goat float bloat scapegoat throat haha

    //shooters = windshield
    //pulleys = talons

    public void elevator(){

        if(joystick.getRawButton(10) == true && joystick.getRawButton(9) != true)
  		{
  			Elevator.set(0.5); //Up when 10 is pressed
  		}
  		else if(joystick.getRawButton(9) == true && joystick.getRawButton(10) != true)
  		{
  			Elevator.set(-0.5); //Down when 9 is pressed
  		}
  		else
  		{
  			Elevator.set(0.0); //Nothing while nothing is pressed
  		}

    }

    public void hatchSolenoid()  
  	{
        //Reads imput from joystick and controls double solenoid based on input
  		compressor.start();
  		compressor.setClosedLoopControl(true);
  		
  		if(joystick.getRawButton(12) == true && joystick.getRawButton(11) != true)
  		{
  			hatch.set(DoubleSolenoid.Value.kForward); //Forward when 12 is pressed
  		}
  		else if(joystick.getRawButton(11) == true && joystick.getRawButton(12) != true)
  		{
  			hatch.set(DoubleSolenoid.Value.kReverse); //Reverse when 11 is pressed
  		}
  		else
  		{
  			hatch.set(DoubleSolenoid.Value.kOff); //Nothing while nothing is pressed
  		}
  	}

    public void habSolenoidFront()  
  	{
        //Reads input from joystick and controls double solenoid based on input
  		compressor.start();
  		compressor.setClosedLoopControl(true);
  		
  		if(joystick.getRawButton(6) == true && joystick.getRawButton(4) != true)
  		{
  			habFront.set(DoubleSolenoid.Value.kForward); //Forward when 12 is pressed
  		}
  		else if(joystick.getRawButton(4) == true && joystick.getRawButton(6) != true)
  		{
  			habFront.set(DoubleSolenoid.Value.kReverse); //Reverse when 11 is pressed
  		}
  		else
  		{
  			habFront.set(DoubleSolenoid.Value.kOff); //Nothing while nothing is pressed
  		}
  	}

    public void habSolenoidBack()  
  	{
        //Reads imput from joystick and controls double solenoid based on input
  		compressor.start();
  		compressor.setClosedLoopControl(true);
  		
  		if(joystick.getRawButton(5) == true && joystick.getRawButton(3) != true)
  		{
  			habBack.set(DoubleSolenoid.Value.kForward); //Forward when 12 is pressed
  		}
  		else if(joystick.getRawButton(3) == true && joystick.getRawButton(5) != true)
  		{
  			habBack.set(DoubleSolenoid.Value.kReverse); //Reverse when 11 is pressed
  		}
  		else
  		{
  			habBack.set(DoubleSolenoid.Value.kOff); //Nothing while nothing is pressed
  		}
    }
    
    public void ballMotor(){

      if(joystick.getRawButton(8) == true && joystick.getRawButton(7) != true)
    {
      BallMotor.set(0.2); //Up when 10 is pressed
      m_timer.delay(1); 
      BallMotor.set(0.0); 
    }
    else if(joystick.getRawButton(7) == true && joystick.getRawButton(8) != true)
    {
      BallMotor.set(-0.2);//Down when 9 is pressed
      m_timer.delay(1); 
      BallMotor.set(0.0); 
    }
    else
    {
      BallMotor.set(0.0); //Nothing while nothing is pressed
    }

  }

>>>>>>> 3712f918eee305565a997551c821e84d9da06801
    @Override
    public void teleopPeriodic() {
        centerOnObject();
        // Use the joystick X axis for lateral movement, Y axis for forward

        //left = (-joystick.getY()) + (joystick.getX());
       // right = (-joystick.getY()) - (joystick.getX());
        //position = java.lang.Math.abs(left);

        //if (position < java.lang.Math.abs(right)) {
       //   position = java.lang.Math.abs(right);
       // }

        //if (position > 1) {
        //  left = left/position;
       //   right = right/position;
      //  }

       // DriveTrain.tankDrive(left, right);
   
    }
}
