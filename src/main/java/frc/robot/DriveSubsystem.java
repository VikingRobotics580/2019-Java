package frc.robot.subsystems;

import frc.robot.Robot;

import static frc.robot.OI.*;
import static frc.robot.RobotMap.*;
import frc.robot.commands.DriveCommand;


import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class DriveSubsystem extends Subsystem {

    ADXRS450_Gyro gyro;
    double left;
    double right;
    double position;

    private Talon MotorZero;
    private Talon MotorOne;
    private Talon MotorTwo;
    private Talon MotorThree;

    private DifferentialDrive DriveTrain;
    private SpeedControllerGroup LeftDrive;
    private SpeedControllerGroup RightDrive;
    

    public DriveSubsystem() {
        MotorZero = new Talon(DRIVE_ZERO);
        MotorOne = new Talon(DRIVE_ONE);
        MotorTwo = new Talon(DRIVE_TWO);
        MotorThree = new Talon(DRIVE_THREE);

        LeftDrive = new SpeedControllerGroup(MotorZero,MotorOne);
        RightDrive = new SpeedControllerGroup(MotorTwo,MotorThree);

        DriveTrain = new DifferentialDrive(LeftDrive,RightDrive);
    }

    public void Driver(double jx, double jy) {
        left = (-jy) - (jx);
        right = (-jy) + (jx);
        position = java.lang.Math.abs(left);

        if (position < java.lang.Math.abs(right)) {
        position = java.lang.Math.abs(right);
        }

        if (position > 1) {
        left = left/position;
        right = right/position;
        }
        DriveTrain.tankDrive(left, right);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }

}

    /*
    // Rotate robot 90 deg to the left
    public void rotate90Left() {
        if (lj3()) {
            double to = gyro.getAngle() + 90;
            if (gyro.isConnected()) {
                while (gyro.getAngle() < to) {
                    DriveTrain.tankDrive(1,1);
                }
            }
        }
    }

    // Rotate robot 90 deg to the right
    public void rotate90Right() {
        if (lj4()) {
            double to = gyro.getAngle() - 90;
            if (gyro.isConnected()) {
                while (gyro.getAngle() < to) {
                    DriveTrain.tankDrive(-1,-1);
                }
            }
        }
    }

    // Go back to 0
    public void goto0() {
        if (lj5()) {
            if (gyro.isConnected()) {
                if (gyro.getAngle() > 180) {
                    while (gyro.getAngle() > -3 && gyro.getAngle() < 3) {
                        DriveTrain.tankDrive(1,1);
                    }
                } else {
                    while (gyro.getAngle() > -3 && gyro.getAngle() < 3) {
                        DriveTrain.tankDrive(-1,-1);
                    }
                }
            }
        }
    }

    // Go to 180 degree position
    public void goto180() {
        if (lj6()) {
            if (gyro.isConnected()) {
                while (gyro.getAngle() > 179 && gyro.getAngle() < 181) {
                    DriveTrain.tankDrive(-1,-1);
                }
            }
        }
    }
    */