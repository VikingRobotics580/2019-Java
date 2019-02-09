package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Solenoid {

    Compressor compressor;
    DoubleSolenoid hatch;
    DoubleSolenoid habFront;
    DoubleSolenoid habBack;
    private Joystick joystick;

    public Solenoid() {
        compressor = new Compressor();
        hatch = new DoubleSolenoid(0, 1);	
        habFront = new DoubleSolenoid(2, 3);	
        habBack = new DoubleSolenoid(4, 5);	

    }

    public void hatchSolenoid() {
    //Reads imput from joystick and controls double solenoid based on input
        compressor.start();
        compressor.setClosedLoopControl(true);
        
        if(joystick.getRawButton(12) == true && joystick.getRawButton(11) != true)
        {
            hatch.set(DoubleSolenoid.Value.kForward); //Forward when 12 is pressed
        }
        else if(joystick.getRawButton(11) == true && joystick.getRawButton(12) != true)
        {
            hatch.set(DoubleSolenoid.Value.kReverse); //Reverse when 11 is pressed
        }
        else
        {
            hatch.set(DoubleSolenoid.Value.kOff); //Nothing while nothing is pressed
        }
    }

    public void habSolenoidFront()  
    {
    //Reads input from joystick and controls double solenoid based on input
        compressor.start();
        compressor.setClosedLoopControl(true);
        
        if(joystick.getRawButton(6) == true && joystick.getRawButton(4) != true)
        {
            habFront.set(DoubleSolenoid.Value.kForward); //Forward when 12 is pressed
        }
        else if(joystick.getRawButton(4) == true && joystick.getRawButton(6) != true)
        {
            habFront.set(DoubleSolenoid.Value.kReverse); //Reverse when 11 is pressed
        }
        else
        {
            habFront.set(DoubleSolenoid.Value.kOff); //Nothing while nothing is pressed
        }
    }

    public void habSolenoidBack()  
    {
    //Reads imput from joystick and controls double solenoid based on input
        compressor.start();
        compressor.setClosedLoopControl(true);
        
        if(joystick.getRawButton(5) == true && joystick.getRawButton(3) != true)
        {
            habBack.set(DoubleSolenoid.Value.kForward); //Forward when 12 is pressed
        }
        else if(joystick.getRawButton(3) == true && joystick.getRawButton(5) != true)
        {
            habBack.set(DoubleSolenoid.Value.kReverse); //Reverse when 11 is pressed
        }
        else
        {
            habBack.set(DoubleSolenoid.Value.kOff); //Nothing while nothing is pressed
        }
    }
}