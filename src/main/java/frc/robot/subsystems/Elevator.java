// VikingRobotics 2019 FRC Robotics
// Programming Team: Bhada Yun, Finn Cawley, Kate Hirshberg
// Elevator Code
// Lead: Finn Cawley

package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ElevatorCommand;

public class Elevator extends Subsystem {

    private Talon LeftElevator = new Talon(4);
    private Talon RightElevator = new Talon(5);

    public void elevator(){

        if(OI.rightJoystick.getRawButton(10) == true && OI.rightJoystick.getRawButton(9) != true) {
            LeftElevator.set(0.1); //Up when 10 is pressed
            RightElevator.set(-0.1); //Up when 10 is pressed
            System.out.println("10");
        }
        else if(OI.rightJoystick.getRawButton(9) == true && OI.rightJoystick.getRawButton(10) != true) {
            LeftElevator.set(-0.1); //Down when 9 is pressed
            RightElevator.set(0.1); //Down when 9 is pressed
            System.out.println("9");
        }
        else {
            LeftElevator.set(0.0); //Nothing while nothing is pressed
            RightElevator.set(0.0); //Nothing while nothing is pressed
        }
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ElevatorCommand());
    }

}