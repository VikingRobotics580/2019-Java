/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    private static Joystick leftJoystick = new Joystick(0);
    private static Joystick rightJoystick = new Joystick(1);

    public static boolean hatchJoystickForward() {
        return(rightJoystick.getRawButtonPressed(12) && !rightJoystick.getRawButtonPressed(11));
    }    

    public static boolean hatchJoystickRelease() {
        return(rightJoystick.getRawButtonPressed(11) && !rightJoystick.getRawButtonPressed(12));
    }    

    public static boolean habFrontJoystickForward() {
        return(rightJoystick.getRawButtonPressed(10) && !rightJoystick.getRawButtonPressed(9));
    }    

    public static boolean habFrontJoystickRelease() {
        return(rightJoystick.getRawButtonPressed(9) && !rightJoystick.getRawButtonPressed(10));
    }    

    public static boolean habBackJoystickForward() {
        return(rightJoystick.getRawButtonPressed(8) && !rightJoystick.getRawButtonPressed(7));
    }    

    public static boolean habBackJoystickRelease() {
        return(rightJoystick.getRawButtonPressed(7) && !rightJoystick.getRawButtonPressed(8));
    }    

    public static double rightJjoystickX() {
        return(rightJoystick.getX());
    }

    public static double rightJoystickY() {
        return(rightJoystick.getY());
    }

    public static boolean rj2() {
        return(rightJoystick.getRawButtonReleased(2));
    }

    public static boolean rj3() {
        return(rightJoystick.getRawButtonReleased(3));
    }

    public static boolean rj4() {
        return(rightJoystick.getRawButtonReleased(4));
    }

    public static boolean rj5() {
        return(rightJoystick.getRawButtonReleased(5));
    }

    public static boolean rj6() {
        return(rightJoystick.getRawButtonReleased(6));
    }

    public static boolean lj2() {
        return(leftJoystick.getRawButtonReleased(2));
    }

    public static boolean lj3() {
        return(leftJoystick.getRawButtonReleased(3));
    }

    public static boolean lj4() {
        return(leftJoystick.getRawButtonReleased(4));
    }

    public static boolean lj5() {
        return(leftJoystick.getRawButtonReleased(5));
    }

    public static boolean lj6() {
        return(leftJoystick.getRawButtonReleased(6));
    }

}

