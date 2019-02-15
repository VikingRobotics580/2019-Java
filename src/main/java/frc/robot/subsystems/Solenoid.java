// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg, Gavin Sanchez
// Solenoid Code
// Lead: Finn Cawley & Bhada Yun

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import static frc.robot.RobotMap.*;

public class Solenoid extends Subsystem {
    DoubleSolenoid hatch = new DoubleSolenoid(HATCH_FORWARD,HATCH_REVERSE);
    DoubleSolenoid habFront = new DoubleSolenoid(HAB_FRONT_FORWARD,HAB_FRONT_REVERSE);
    DoubleSolenoid habBack = new DoubleSolenoid(HAB_BACK_FORWARD,HAB_BACK_REVERSE);

    public void initDefaultCommand() {
    }

    public void hatchSolenoidForward() {
        if (OI.hatchJoystickForward()) {
            hatch.set(DoubleSolenoid.Value.kForward); 
        } else {
            hatch.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void hatchSolenoidBackward() {
        if (OI.hatchJoystickRelease()) {
            hatch.set(DoubleSolenoid.Value.kReverse); 
        } else {
            hatch.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void habSolenoidFrontForward() {
        if (OI.habFrontJoystickForward()) {
            habFront.set(DoubleSolenoid.Value.kForward); 
        } else {
            habFront.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void habSolenoidFrontBackward() {
        if(OI.habFrontJoystickRelease()) {
            habFront.set(DoubleSolenoid.Value.kReverse);
        } else {
            habFront.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void habSolenoidBackForward() {
        if(OI.habBackJoystickForward()) {
            habBack.set(DoubleSolenoid.Value.kForward); 
        } else {
            habFront.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void habSolenoidBackBackward() {
        if(OI.habBackJoystickRelease()) {
            habBack.set(DoubleSolenoid.Value.kReverse); 
        } else {
            habFront.set(DoubleSolenoid.Value.kOff); 
        }

    }

}

