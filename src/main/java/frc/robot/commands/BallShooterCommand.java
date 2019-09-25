package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import static frc.robot.OI.*;


//Non-recyclable, we won't use a ballshooter again
public class BallShooterCommand extends Command {

	public BallShooterCommand() {
		//requires(Robot.ballShooter);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		//Robot.ballShooter.BallMotor();;
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override

	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}