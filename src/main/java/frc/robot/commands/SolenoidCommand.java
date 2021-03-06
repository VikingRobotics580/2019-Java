package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import static frc.robot.OI.*;


//Non-recyclable, we likely won't use solenoids again ;(

public class SolenoidCommand extends Command {

	public SolenoidCommand() {
		requires(Robot.solenoid);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (Robot.solenoid.isWorking()) {
			Robot.solenoid.runSolenoid();
		}
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