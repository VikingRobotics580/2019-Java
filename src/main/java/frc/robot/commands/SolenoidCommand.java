package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import static frc.robot.OI.*;

public class SolenoidCommand extends Command {

	public SolenoidCommand() {
		//requires(Robot.solenoid);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		//if (Robot.solenoid.isWorking()) {
		System.out.println("hi");
		//Robot.solenoid.runSolenoid();
		//}
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