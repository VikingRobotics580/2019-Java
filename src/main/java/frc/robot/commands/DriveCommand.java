package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import static frc.robot.OI.*;

public class DriveCommand extends Command {

	public DriveCommand() {
		requires(Robot.drive);
	}

	protected void init() {
	}

	protected void execute() {
		Robot.drive.Drive(rightJjoystickX(),rightJoystickY());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted()
	{
		System.out.println("DriveCommand Interupted!???");
	}
}