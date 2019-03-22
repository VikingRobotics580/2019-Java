// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Robot base program

package frc.robot;

// General:
import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import com.analog.adis16448.frc.ADIS16448_IMU;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Robot extends TimedRobot {
    //public static final ADIS16448_IMU imu = new ADIS16448_IMU();
    public static final DriveSubsystem drive = new DriveSubsystem();
    public static final M_I2C m_i2c = new M_I2C();
    public static final Solenoid solenoid = new Solenoid();
    //public static BallShooter ballShooter = new BallShooter();
    public static Elevator elevator = new Elevator();
    //M_I2C i2c = new M_I2C();

    @Override
    public void robotInit() {
        SmartDashboard.putString("Robot", "initialized");
        new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(320, 240);
            camera.setFPS(30);
                        
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Front Camera", 320, 240);
                        
            Mat frame = new Mat();
            Point pt1 = new Point(155, 120);
            Point pt2 = new Point(165, 120);
            Point pt3 = new Point(160, 115);
            Point pt4 = new Point(160, 125);
            Scalar color = new Scalar(255, 255, 255);
                        
            while(!Thread.interrupted()) {
              if (cvSink.grabFrame(frame) == 0) {
                continue;
              }
              Imgproc.line(frame, pt1, pt2, color, 1);
              Imgproc.line(frame, pt3, pt4, color, 1);
              outputStream.putFrame(frame);
            }
        }).start();
    }

    // Base Periodic Code for Teleop and Autonomous
    public void periodic() {
        Scheduler.getInstance().run();
        /*SmartDashboard.putNumber("Gyro-X", imu.getAngleX());
        SmartDashboard.putNumber("Gyro-Y", imu.getAngleY());
        SmartDashboard.putNumber("Gyro-Z", imu.getAngleZ());
        
        SmartDashboard.putNumber("Accel-X", imu.getAccelX());
        SmartDashboard.putNumber("Accel-Y", imu.getAccelY());
        SmartDashboard.putNumber("Accel-Z", imu.getAccelZ());
        
        SmartDashboard.putNumber("Pitch", imu.getPitch());
        SmartDashboard.putNumber("Roll", imu.getRoll());
        SmartDashboard.putNumber("Yaw", imu.getYaw());
        
        SmartDashboard.putNumber("Pressure: ", imu.getBarometricPressure());
        SmartDashboard.putNumber("Temperature: ", imu.getTemperature());*/
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

//chooser = new SendableChooser();
//chooser = new SendableChooser();
//drv.setDefaultOption("Drive", new DriveCommand());
//sol.setDefaultOption("Solenoid", new SolenoidCommand());
//m_chooser.setDefaultOption("M_I2C", new ArduinoCommand());
//drv.setDefaultOption("Drive", new DriveCommand());
//sol.setDefaultOption("Solenoid", new SolenoidCommand());
//m_chooser.setDefaultOption("M_I2C", new ArduinoCommand());
//SendableChooser chooser;
//SmartDashboard.putData("Chooser",chooser);
