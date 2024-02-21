// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

//define your motors & sensors
CANSparkMax left1=new CANSparkMax(Constants.LEFT1_PORT, MotorType.kBrushed);
CANSparkMax left2=new CANSparkMax(Constants.LEFT2_PORT, MotorType.kBrushed);
CANSparkMax right1=new CANSparkMax(Constants.RIGHT1_PORT,MotorType.kBrushed); 
CANSparkMax right2=new CANSparkMax(Constants.RIGHT2_PORT,MotorType.kBrushed); 




  /** Creates a new Drivetrain. */
  public Drivetrain() {}



  public void drive( double leftSpeed, double rightSpeed ){

      left1.set(leftSpeed);
      left2.set(leftSpeed); 
      right1.set(-rightSpeed); 
      right2.set(-rightSpeed); 


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
