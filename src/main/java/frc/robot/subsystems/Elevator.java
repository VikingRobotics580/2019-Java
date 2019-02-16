// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg, Gavin Sanchez
// Elevator Code
// Lead: Finn Cawley

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ElevatorCommand;

public class Elevator extends Subsystem {

    private Talon Elevator;
    private Joystick joystick;

    public void elevator(){

        if(joystick.getRawButton(10) == true && joystick.getRawButton(9) != true)
        {
            Elevator.set(0.5); //Up when 10 is pressed
        }
        else if(joystick.getRawButton(9) == true && joystick.getRawButton(10) != true)
        {
            Elevator.set(-0.5); //Down when 9 is pressed
        }
        else
        {
            Elevator.set(0.0); //Nothing while nothing is pressed
        }

    }

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorCommand());
    }

}