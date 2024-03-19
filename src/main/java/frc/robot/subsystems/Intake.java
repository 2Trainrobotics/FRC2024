package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private CANSparkMax intakeSwing;
    private CANSparkMax intakeRollers;
    private RelativeEncoder intakeSwingEncoder;
    private boolean enableGuards = false;

    public Intake() {
        intakeSwing = new CANSparkMax(Constants.Intake.kIntakeSwingMotorId, MotorType.kBrushless);
        intakeSwing.setIdleMode(IdleMode.kCoast);
        intakeSwingEncoder = intakeSwing.getEncoder();
        intakeSwingEncoder.setPosition(0);

        intakeRollers = new CANSparkMax(Constants.Intake.kIntakeRollerMotorId, MotorType.kBrushless);
    }

    public void toggleGuard() {
        enableGuards = !enableGuards;
    }

    public void extendIntake() {
        double currentPosition = intakeSwingEncoder.getPosition();
        SmartDashboard.putNumber("encoder position", currentPosition);
        SmartDashboard.putString("intakeop", "extend");
        if (enableGuards) {
            if (currentPosition > -45) {
                intakeSwing.set(-Constants.Intake.kSwingSpeed);
            } else {
                intakeSwing.set(0);
            }
        } else {
            intakeSwing.set(-Constants.Intake.kSwingSpeed);
        }    
        
    }

    public void retractIntake() {
        double currentPosition = intakeSwingEncoder.getPosition();
        SmartDashboard.putNumber("encoder position", currentPosition);
        SmartDashboard.putString("intakeop", "retract");
        if (enableGuards){
            if (currentPosition < -15) {
                intakeSwing.set(Constants.Intake.kSwingSpeed);
            } else {
                intakeSwing.set(0);
            }
        } else {    
            intakeSwing.set(Constants.Intake.kSwingSpeed);
        }
    }

    public void stopIntakeSwing() {
        double currentPosition = intakeSwingEncoder.getPosition();
        SmartDashboard.putNumber("encoder position", currentPosition);
        SmartDashboard.putString("intakeop", "zero");
        intakeSwing.set(0);
    }

    public void intakeRollersIn() {
        intakeRollers.set(-Constants.Intake.kRollerSpeed);
    }

    public void intakeRollersOut() {
        intakeRollers.set(Constants.Intake.kRollerSpeed);
    }

    public void intakeRollersStop() {
        intakeRollers.set(0);
    }
}
