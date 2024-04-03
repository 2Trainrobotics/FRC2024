// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int kDriverControllerPort = 1;

    public static class DrivetrainConstants {
        public static int kFrontLeftMotorId = 2;
        public static int kBackLeftMotorId = 5;
        public static int kFrontRightMotorId = 8;
        public static int kBackRightMotorId = 4;
    }
    
    public static class ShooterConstants {
        public static int kLeftShooterMotorId = 9;
        public static int kRightShooterMotorId = 3;

        public static double kShooterSpeed = 1.0;
        public static double kIntakeSpeed = 0.5;
    }

    public static class IntakeConstants {
        public static int kIntakeSwingMotorId = 7;
        public static int kIntakeRollerMotorId = 6;

        public static double kSwingSpeed = 0.3;
        public static double kRollerSpeed = 0.5;

        public static double extendMax = -45;
        public static double retractMax = -15;
    }


    public static class ClimberConstants {
        public static int kLeftClimberMotorId = -1;
        public static int kRightClimberMotorId = -1;
        //value should have some sort of buffer
        public static double climbTop = 0;
        public static double climbBottom = 0;
    }
}