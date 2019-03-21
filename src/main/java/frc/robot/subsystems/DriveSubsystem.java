package frc.robot.subsystems;

import frc.robot.Robot;

import static frc.robot.OI.*;
import static frc.robot.RobotMap.*;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.commands.DriveCommand;
//import com.analog.adis16448.frc.ADIS16448_IMU;
import frc.robot.OI;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class DriveSubsystem extends Subsystem {
    //public static final ADIS16448_IMU imu = new ADIS16448_IMU();
    ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    double left;
    double right;
    double position;
    private Talon MotorZero;
    private Talon MotorOne;
    private Talon MotorTwo;
    private Talon MotorThree;
    private int g = 0;

    private DifferentialDrive DriveTrain;
    private SpeedControllerGroup LeftDrive;
    private SpeedControllerGroup RightDrive;

    private double jx;
    private double jy;

    public DriveSubsystem() {
        MotorZero = new Talon(TALON_ZERO);
        MotorOne = new Talon(TALON_ONE);
        MotorTwo = new Talon(TALON_TWO);
        MotorThree = new Talon(TALON_THREE);

        LeftDrive = new SpeedControllerGroup(MotorZero,MotorOne);
        RightDrive = new SpeedControllerGroup(MotorTwo,MotorThree);

        DriveTrain = new DifferentialDrive(LeftDrive,RightDrive);
        DriveTrain.setSafetyEnabled(false);
    }

    public void Driver() {
        jx = rightJoystick.getX();
        jy = rightJoystick.getY();
        right = (-jy) - (jx);
        left = (-jy) + (jx);
        position = java.lang.Math.abs(left);

        if (position < java.lang.Math.abs(right)) {
        position = java.lang.Math.abs(right);
        }

        if (position > 1) {
        left = left/position;
        right = right/position;
        }
        DriveTrain.tankDrive(left, right);


		/*if (OI.rightJoystick.getRawButtonPressed(6)) {
            goto0();
            System.out.println("lol");
		}
		if (OI.rightJoystick.getRawButtonPressed(2)) {
			printAngle();
        }
        if (OI.rightJoystick.getRawButtonPressed(12)) {
            System.out.println(left + ", " + right);
        }
        if (OI.rightJoystick.getRawButtonPressed(5)) {
            testDrive();
        }*/

        if (OI.rightJoystick.getRawButtonPressed(7)) {
            g = 1;
        }
        if (OI.rightJoystick.getRawButtonPressed(8)) {
            g = 0;
        }
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }

    // Rotate robot 90 deg to the left
    public void rotate90Left() {
        double to = gyro.getAngle() - 90;
        if (gyro.isConnected()) {
            while (gyro.getAngle() < to) {
                DriveTrain.tankDrive(-0.2,-0.2);
            }
        } else {
            System.out.println("Gyro not connected!");
        }
    }

    // Rotate robot 90 deg to the right
    public void rotate90Right() {
        double to = gyro.getAngle() + 90;
        if (gyro.isConnected()) {
            while (gyro.getAngle() < to) {
                DriveTrain.tankDrive(0.2,0.2);
            }
        } else {
            System.out.println("Gyro not connected!");
        }
    }

    // Go back to 0
    public void goto0() {
        if (gyro.isConnected()) {
            if (gyro.getAngle() < 0) {
                while (gyro.getAngle() > -3 ) {
                    DriveTrain.tankDrive(0.6,-0.6);
                }
            } else if (gyro.getAngle() > 0) {
                while (gyro.getAngle() < 3) {
                    DriveTrain.tankDrive(-0.6,0.6);
                }
            }
        } else {
            System.out.println("Gyro not connected!");
        }
    }

    public void testDrive() {
        DriveTrain.tankDrive(1, -1);
    }

    // Go to 180 degree position
    public void goto180() {
        if (gyro.isConnected()) {
            while (gyro.getAngle() > 179 && gyro.getAngle() < 181) {
                DriveTrain.tankDrive(-0.2,-0.2);
            }
        } else {
            System.out.println("Gyro not connected!");
        }
    }

    public void printAngle() {
        System.out.println("Gyro: " + gyro.getAngle());
    }

    /*public double angle() {
        if (g = 1) {
            return imu.getAngle();
        } else { 
            return gyro.getAngle();
        }
    }*/

}