// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg, Gavin Sanchez
// Solenoid Code

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Solenoid {

    DoubleSolenoid hatch;
    DoubleSolenoid habFront;
    DoubleSolenoid habBack;
    Joystick joystick;

    public Solenoid(Joystick joystick) {
        hatch = new DoubleSolenoid(0, 1);	
        habFront = new DoubleSolenoid(2, 3);	
        habBack = new DoubleSolenoid(4, 5);	
        this.joystick = joystick;
    }

    public void hatchSolenoidForward() {
        if (joystick.getRawButton(12) == true && joystick.getRawButton(11) != true) {
            hatch.set(DoubleSolenoid.Value.kForward); 
        } else {
            hatch.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void hatchSolenoidBackward() {
        if (joystick.getRawButton(11) == true && joystick.getRawButton(12) != true) {
            hatch.set(DoubleSolenoid.Value.kReverse); 
        } else {
            hatch.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void habSolenoidFrontForward() {
        if(joystick.getRawButton(10) == true && joystick.getRawButton(9) != true) {
            habFront.set(DoubleSolenoid.Value.kForward); 
        } else {
            habFront.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void habSolenoidFrontBackward() {
        if(joystick.getRawButton(9) == true && joystick.getRawButton(10) != true) {
            habFront.set(DoubleSolenoid.Value.kReverse);
        } else {
            habFront.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void habSolenoidBackForward() {
        if(joystick.getRawButton(8) == true && joystick.getRawButton(7) != true) {
            habBack.set(DoubleSolenoid.Value.kForward); 
        } else {
            habFront.set(DoubleSolenoid.Value.kOff); 
        }
    }

    public void habSolenoidBackBackward() {
        if(joystick.getRawButton(7) == true && joystick.getRawButton(8) != true) {
            habBack.set(DoubleSolenoid.Value.kReverse); 
        } else {
            habFront.set(DoubleSolenoid.Value.kOff); 
        }
    }

}

/*
Repressed Test Code: 
    All compressor instantiations
    compressor.start();
    compressor.setClosedLoopControl(true);
*/
