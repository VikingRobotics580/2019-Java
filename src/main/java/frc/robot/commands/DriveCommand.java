package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import static frc.robot.OI.*;

public class DriveCommand extends Command {

	private int t = 0;

	public DriveCommand() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		t = OI.gyroDrive();
		if (t != -1) {
			if (t == 4) {
				Robot.drive.rotate90Left();
			} else if (t == 5) {
				Robot.drive.rotate90Right();
			} else if (t == 6) {
				Robot.drive.goto0();

			} else if (t == 7) {
				Robot.drive.goto180();
			} else if (t == 2) {
				Robot.drive.printAngle();
			}
		} else {
			Robot.drive.Driver();
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