// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Robot base program

package frc.robot;

// General:
import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

    public static final DriveSubsystem drive = new DriveSubsystem();
    public static final Vision vision = new Vision();
    public static final M_I2C m_i2c = new M_I2C();
    //public static final Solenoid solenoid = new Solenoid();
    //public static BallShooter ballShooter = new BallShooter();
    //public static Elevator elevator = new Elevator();
    //private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    //M_I2C i2c = new M_I2C();

    @Override
    public void robotInit() {
        SmartDashboard.putString("Robot", "initialized");
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
