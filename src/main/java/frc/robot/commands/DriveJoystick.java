// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class DriveJoystick extends Command {

  private Supplier<Double> leftSpeed;
  private Supplier<Double> rightSpeed;
  private Drivetrain drivetrain;

  /** Creates a new DriveJoystick. */
  public DriveJoystick( Supplier<Double> leftSpeed, Supplier<Double> rightSpeed) {

    this.leftSpeed=leftSpeed;
    this.rightSpeed=rightSpeed; 

    drivetrain=RobotContainer.drivetrain;
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    drivetrain.drive(0.0,0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    drivetrain.drive(leftSpeed.get(),rightSpeed.get());
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    drivetrain.drive(0.0,0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
