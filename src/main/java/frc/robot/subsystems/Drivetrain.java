// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

//define your motors & sensors
CANSparkMax left1  = new CANSparkMax(Constants.Drivetrain.kFrontLeftMotorId, MotorType.kBrushed);
CANSparkMax left2  = new CANSparkMax(Constants.Drivetrain.kBackLeftMotorId, MotorType.kBrushed);
CANSparkMax right1 = new CANSparkMax(Constants.Drivetrain.kFrontRightMotorId, MotorType.kBrushed);
CANSparkMax right2 = new CANSparkMax(Constants.Drivetrain.kBackRightMotorId, MotorType.kBrushed);


private DifferentialDrive drive;

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    left2.follow(left1);
    right2.follow(right1);

    drive = new DifferentialDrive(left1, right1);    
  }

  public void drive(double left, double right) {
    drive.tankDrive(-left, right);
  }

  public DifferentialDrive getDrive() {
    return drive;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double returnDouble(double x) {
    return x;
  }
}

