// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg, Gavin Sanchez
// Solenoid Code
// Lead: Finn Cawley & Bhada Yun

package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.OI.*;
import frc.robot.commands.SolenoidCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

public class Solenoid extends Subsystem {

    DoubleSolenoid hatch;
    DoubleSolenoid habFront;
    DoubleSolenoid habBack;
    Joystick joystick;

    public Solenoid() {
        hatch = new DoubleSolenoid(0, 1);	
        //habFront = new DoubleSolenoid(2, 3);	
        //habBack = new DoubleSolenoid(4, 5);	
        joystick = OI.rightJoystick;
    }

    public boolean isWorking() {
        return true;
    }

    public void runSolenoid() {

        if (joystick.getRawButton(12) == true && joystick.getRawButton(11) != true) {
            hatch.set(DoubleSolenoid.Value.kForward); 
        } else {
            hatch.set(DoubleSolenoid.Value.kOff); 
        }
    

        if (joystick.getRawButton(11) == true && joystick.getRawButton(12) != true) {
            hatch.set(DoubleSolenoid.Value.kReverse); 
        } else {
            hatch.set(DoubleSolenoid.Value.kOff); 
        }
    

        if(joystick.getRawButton(10) == true && joystick.getRawButton(9) != true) {
            //habFront.set(DoubleSolenoid.Value.kForward); 
        } else {
            //habFront.set(DoubleSolenoid.Value.kOff); 
        }
    

        if(joystick.getRawButton(9) == true && joystick.getRawButton(10) != true) {
            //habFront.set(DoubleSolenoid.Value.kReverse);
        } else {
            //habFront.set(DoubleSolenoid.Value.kOff); 
        }
    

        if(joystick.getRawButton(8) == true && joystick.getRawButton(7) != true) {
            //habBack.set(DoubleSolenoid.Value.kForward); 
        } else {
            //habFront.set(DoubleSolenoid.Value.kOff); 
        }
    

        if(joystick.getRawButton(7) == true && joystick.getRawButton(8) != true) {
            //habBack.set(DoubleSolenoid.Value.kReverse); 
        } else {
            //habFront.set(DoubleSolenoid.Value.kOff); 
        }
    
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new SolenoidCommand());
    }

}
