// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Ball Shooter code
// Lead: Bhada Yun & Finn Cawley



//

package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.commands.BallShooterCommand;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.Timer;

public class BallShooter extends Subsystem {

    private Talon BallMotor;
    private Joystick joystick;
    private double startPos = 0;
    private double startNeg = 0;
    private Timer m_timer = new Timer();

    public void BallMotor(){

        if(joystick.getRawButton(8) == true && joystick.getRawButton(7) != true) {
            if (startPos == 0) {
                BallMotor.set(0.2); //Up when 10 is pressed
                startPos = Timer.getFPGATimestamp();
            }
            if ((Timer.getFPGATimestamp() - 2 > startPos) && (!OI.rightJoystick.getRawButtonPressed(2))) {
                BallMotor.set(0.0);
                startPos = 0;
            }
        }
        else if(joystick.getRawButton(7) == true && joystick.getRawButton(8) != true) {
            if (startNeg == 0) {
                BallMotor.set(-0.2); //Up when 10 is pressed
                startNeg = Timer.getFPGATimestamp();
            }
            if ((Timer.getFPGATimestamp() - 2 > startNeg) && (!OI.rightJoystick.getRawButtonPressed(2))) {
                BallMotor.set(0.0); 
                startNeg = 0;
            }
        }
        else {
            BallMotor.set(0.0); //Nothing while nothing is pressed
        }

    }

    public void initDefaultCommand() {
        setDefaultCommand(new BallShooterCommand());
    }

}