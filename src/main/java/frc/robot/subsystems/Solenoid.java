// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Solenoid Code
// Lead: Finn Cawley & Bhada Yun

package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.OI.*;
import frc.robot.commands.SolenoidCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;

public class Solenoid extends Subsystem {

    DoubleSolenoid hatch;
    DoubleSolenoid habFront;
    DoubleSolenoid habBack;
    Joystick joystick;

    public Solenoid() {
        hatch = new DoubleSolenoid(0, 1);	
        habFront = new DoubleSolenoid(2, 3);	
        habBack = new DoubleSolenoid(4, 5);
    }

    public boolean isWorking() {
        return true;
    }

    public void runSolenoid() {

        if (OI.rightJoystick.getRawButtonPressed(12) == true && OI.rightJoystick.getRawButtonPressed(11) != true) {
            hatch.set(DoubleSolenoid.Value.kReverse); 
        /*} else {
            hatch.set(DoubleSolenoid.Value.kOff);*/
        }
    
        if (OI.rightJoystick.getRawButtonPressed(11) == true && OI.rightJoystick.getRawButtonPressed(12) != true) {
            hatch.set(DoubleSolenoid.Value.kForward); 
        /*} else {
            hatch.set(DoubleSolenoid.Value.kOff);*/
        }

        if(OI.rightJoystick.getRawButtonPressed(10) == true && OI.rightJoystick.getRawButtonPressed(9) != true) {
            habFront.set(DoubleSolenoid.Value.kForward); 
        /*} else {
            habFront.set(DoubleSolenoid.Value.kOff);*/
        }
    

        if(OI.rightJoystick.getRawButtonPressed(9) == true && OI.rightJoystick.getRawButtonPressed(10) != true) {
            habFront.set(DoubleSolenoid.Value.kReverse);
        /*} else {
            habFront.set(DoubleSolenoid.Value.kOff);*/
        }
    

        if(OI.rightJoystick.getRawButtonPressed(8) == true && OI.rightJoystick.getRawButtonPressed(7) != true) {
            habBack.set(DoubleSolenoid.Value.kForward); 
        /*} else {
            habFront.set(DoubleSolenoid.Value.kOff);*/ 
        }
    

        if(OI.rightJoystick.getRawButtonPressed(7) == true && OI.rightJoystick.getRawButtonPressed(8) != true) {
            habBack.set(DoubleSolenoid.Value.kReverse); 
        /*} else {
            habFront.set(DoubleSolenoid.Value.kOff);*/
        }
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new SolenoidCommand());
    }

}
