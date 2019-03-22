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
        compressor.start();
        compressor.setClosedLoopControl(true);	
    }

    public void hatchSolenoid() {
    //Reads imput from joystick and controls double solenoid based on input
        //compressor.setClosedLoopControl(false);
        
        if(joystick.getRawButton(12) == true && joystick.getRawButton(11) != true)
        {
            System.out.println("12");
            hatch.set(DoubleSolenoid.Value.kForward); //Forward when 12 is pressed
        }
        else if(joystick.getRawButton(11) == true && joystick.getRawButton(12) != true)
        {
            System.out.println("11");
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
        
        if(joystick.getRawButton(10) == true && joystick.getRawButton(9) != true)
        {
            System.out.println("10");
            habFront.set(DoubleSolenoid.Value.kForward); //Forward when 12 is pressed
        }
        else if(joystick.getRawButton(9) == true && joystick.getRawButton(10) != true)
        {
            System.out.println("9");
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
        compressor.setClosedLoopControl(false);
        
        if(joystick.getRawButton(7) == true && joystick.getRawButton(8) != true)
        {
            System.out.println("7");
            habBack.set(DoubleSolenoid.Value.kForward); //Forward when 12 is pressed
        }
        else if(joystick.getRawButton(8) == true && joystick.getRawButton(7) != true)
        {
            System.out.println("8");
            habBack.set(DoubleSolenoid.Value.kReverse); //Reverse when 11 is pressed
        }
        else
        {
            habBack.set(DoubleSolenoid.Value.kOff); //Nothing while nothing is pressed
        }
    }
}