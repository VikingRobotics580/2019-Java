// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshbeg, Gavin Sanchez
// Ball Shooter code

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class BallShooter {

    private Talon BallMotor;
    private Joystick joystick;
    private Timer m_timer = new Timer();

    public void BallMotor(){

        if(joystick.getRawButton(8) == true && joystick.getRawButton(7) != true) {
            BallMotor.set(0.2); //Up when 10 is pressed
            m_timer.delay(1); 
            BallMotor.set(0.0); 
        }
        else if(joystick.getRawButton(7) == true && joystick.getRawButton(8) != true) {
            BallMotor.set(-0.2);//Down when 9 is pressed
            m_timer.delay(1); 
            BallMotor.set(0.0); 
        }
        else {
            BallMotor.set(0.0); //Nothing while nothing is pressed
        }

    }

}