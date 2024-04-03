package frc.robot.subsystems;

import javax.management.InstanceAlreadyExistsException;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    private CANSparkMax left, right;
    private AbsoluteEncoder leftEncoder;

    public Shooter() {
        left = new CANSparkMax(Constants.ShooterConstants.kLeftShooterMotorId, MotorType.kBrushless);
        right = new CANSparkMax(Constants.ShooterConstants.kRightShooterMotorId, MotorType.kBrushless);
        left.setIdleMode(IdleMode.kCoast);
        right.setIdleMode(IdleMode.kCoast);
        right.setInverted(true);
        // right.burnFlash();

        leftEncoder = left.getAbsoluteEncoder();
    }

    private void setSpeeds(double speed) {
         left.set(speed);
         right.set(speed);
    }

    public void shoot() {
       setSpeeds(-Constants.ShooterConstants.kShooterSpeed);
    }

    public void stopWheels() {
        setSpeeds(0);
    }

    public void enableIntake() {
        setSpeeds(Constants.ShooterConstants.kIntakeSpeed);
    }

    public double velocity() {
        return leftEncoder.getVelocity();
    }

}
