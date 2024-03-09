package frc.robot.subsystems;

import javax.management.InstanceAlreadyExistsException;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    private CANSparkMax left, right;

    public Shooter() {
        left = new CANSparkMax(Constants.Shooter.LEFT_SHOOTER, MotorType.kBrushless);
        right = new CANSparkMax(Constants.Shooter.RIGHT_SHOOTER, MotorType.kBrushless);
        right.setInverted(false);
        right.burnFlash();
    }

    private void setSpeeds(double speed) {
        left.set(speed);
        right.set(-speed);
    }

    public Command shoot() {
       return runOnce(() -> setSpeeds(-Constants.Shooter.SHOOTER_SPEED));
    }

    public Command stopWheels() {
        return runOnce(() -> setSpeeds(0));
    }

    public Command enableIntake() {
        return runOnce(() -> setSpeeds(Constants.Shooter.INTAKE_SPEED));
    }
}
