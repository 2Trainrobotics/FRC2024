package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimberConstants;

public class Climber extends SubsystemBase {
    private CANSparkMax left, right;
    private AbsoluteEncoder leftEncoder;
    //continues until setpoint, then stops
    private BangBangController climbController = new BangBangController();
    
    public Climber() {
        left = new CANSparkMax(Constants.ClimberConstants.kLeftClimberMotorId, MotorType.kBrushless);
        right = new CANSparkMax(Constants.ClimberConstants.kRightClimberMotorId, MotorType.kBrushless);
        right.setIdleMode(IdleMode.kCoast);
        left.setIdleMode(IdleMode.kCoast);        

        right.follow(left);

        leftEncoder = left.getAbsoluteEncoder();
    }

    public void climbUp() {
        left.set(climbController.calculate(leftEncoder.getPosition(), ClimberConstants.climbTop));
    }

    public void climbDown() {
        left.set(climbController.calculate(leftEncoder.getPosition(), ClimberConstants.climbBottom));
    }
}