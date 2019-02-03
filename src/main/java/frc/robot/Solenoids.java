package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Solenoids extends Robot {
     private Joystick DriverStick;
     private DoubleSolenoid s1;                             //defines solenoids

     public Airsystem() {
          DriverStick = new Joystick(1);             // USB port
          airCompressor = new Compressor(1,1);  //Digtial I/O,Relay
          airCompressor.start();                        // Start the air compressor

          s1 = new DoubleSolenoid(1, 2);                        // Solenoid port
     public void autonomous() {
     }
     public void operatorControl() {
          if(DriverStick.getRawButton(1) == true)
          {
                s1.set(DoubleSolenoid.value.kForward);
           }
           if(DriverStick.getRawButton(2) == true)
           {
                s1.set(DoubleSolenoid.value.kReverse);
            }
        }
    }
}