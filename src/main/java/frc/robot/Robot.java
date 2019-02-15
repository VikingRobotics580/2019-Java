// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Robot base program

package frc.robot;

// General:
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {

    public static OI oi;
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

    @Override
    public void robotInit() {
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
  
    // Reset Gyro:
    public void resetGyro() {
        //gyro.reset();
    }

    // Base Periodic Code for Teleop and Autonomous
    public void periodic() {
        Scheduler.getInstance().run();
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
