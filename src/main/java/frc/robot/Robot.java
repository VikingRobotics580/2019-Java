// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Robot base program

package frc.robot;

//Pretty good, though some parts may need to be removed if they aren't used in our robot

// General:
import edu.wpi.first.wpilibj.TimedRobot;

import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
    public static final DriveSubsystem drive = new DriveSubsystem();

    @Override
    public void robotInit() {
        
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