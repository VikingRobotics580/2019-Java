/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

    //public static Joystick leftJoystick = new Joystick(0);
    public static Joystick rightJoystick = new Joystick(0);

    public static int gyroDrive() {
        if (rightJoystick.getRawButtonPressed(4) || rightJoystick.getRawButtonPressed(5) ||
        rightJoystick.getRawButtonPressed(6) || rightJoystick.getRawButtonPressed(7) ||
        rightJoystick.getRawButtonPressed(2)) {
            if (rightJoystick.getRawButtonPressed(4)) {
                return 4;
            } else if (rightJoystick.getRawButtonPressed(5)) {
                return 5;
            } else if (rightJoystick.getRawButtonPressed(6)) {
                return 6;
            } else if (rightJoystick.getRawButtonPressed(7)) {
                return 7;
            } else if (rightJoystick.getRawButtonPressed(2)) {
                return 2;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}

