// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  enum ControllerScheme {
    ORIGINAL_FLAVOR,
    ONE_CONTROLLER,
    TWO_CONTROLLERS
  }
  private ControllerScheme controllerScheme = ControllerScheme.ORIGINAL_FLAVOR;
  // private RobotContainer m_robotContainer;

  public XboxController driverController = new XboxController(0);
  public XboxController operatorController = new XboxController(1);

  Drivetrain drivetrain = new Drivetrain();
  Shooter shooter = new Shooter();
  Intake intake = new Intake();

  private Command autonomousCommand;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    // m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // // schedule the autonomous command (example)
    // if (autonomousCommand != null) {
    //   autonomousCommand.schedule();
    // }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    drivetrain.drive(0.25, 0.25);
  }

  @Override
  public void teleopInit() {

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  public void teleopOriginalControls() {
    drivetrain.drive(driverController.getLeftY(), driverController.getRightY());

    if (driverController.getYButton()) {
      intake.extendIntake();
    } else if (driverController.getXButton()) {
      intake.retractIntake();
    } else {
      intake.stopIntakeSwing();
    }

    if (driverController.getAButton()) {
      intake.intakeRollersIn();
    } else if (driverController.getBButton()) {
      intake.intakeRollersOut();
    } else {
      intake.intakeRollersStop();
    }

    if (driverController.getLeftBumper() || driverController.getRightBumper()) {
      shooter.shoot();
    } else {
      shooter.stopWheels();
    }
  }

  public void teleopOneController() {
    drivetrain.drive(driverController.getLeftY(), driverController.getRightY());

    if (driverController.getYButton()) {
      intake.extendIntake();
    } else if (driverController.getXButton()) {
      intake.retractIntake();
    } else {
      intake.stopIntakeSwing();
    }

    if (driverController.getLeftBumper() || driverController.getRightBumper()) {
      intake.intakeRollersIn();
      shooter.stopWheels();
    } else {
      if (driverController.getLeftTriggerAxis() > 0.3) {
        intake.intakeRollersOut();
      } else {
        intake.intakeRollersStop();
      }
      if (driverController.getRightTriggerAxis() > 0.3) {
        shooter.shoot();
      } else {
        shooter.stopWheels();
      }
    }
  }

  public void teleopTwoControllers() {
    drivetrain.drive(driverController.getLeftY(), driverController.getRightY());

    if (driverController.getYButton() || operatorController.getYButton()) {
      intake.extendIntake();
    } else if (driverController.getXButton() || operatorController.getXButton()) {
      intake.retractIntake();
    } else {
      intake.stopIntakeSwing();
    }

    if (operatorController.getLeftBumper() || operatorController.getRightBumper()) {
      intake.intakeRollersIn();
      shooter.stopWheels();
    } else {
      if (operatorController.getLeftTriggerAxis() > 0.3) {
        intake.intakeRollersOut();
      } else {
        intake.intakeRollersStop();
      }
      if (operatorController.getRightTriggerAxis() > 0.3) {
        shooter.shoot();
      } else {
        shooter.stopWheels();
      }
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    switch (controllerScheme) {
      case ONE_CONTROLLER:
        teleopOneController();
        break;
      case TWO_CONTROLLERS:
        teleopTwoControllers();
        break;
      case ORIGINAL_FLAVOR:
      default:
        teleopOriginalControls();
        break;
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
