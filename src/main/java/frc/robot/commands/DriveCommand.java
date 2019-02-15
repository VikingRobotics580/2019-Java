package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveCommand extends Command {

	public DriveCommand() {
		requires(Robot.drive);
	}

	protected void initialize()
	{
	}

	protected void execute()
	{
		Robot.drive.Drive();;
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

	protected void end()
	{
	}

	protected void interrupted()
	{
		System.out.println("DriveCommand Interupted!???");
	}
}