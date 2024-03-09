package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    private CANSparkMax left, right;
    private AbsoluteEncoder leftEncoder;
    
    public Climber() {
        left = new CANSparkMax(Constants.Climber.LEFT_CLIMBER, MotorType.kBrushless);
        right = new CANSparkMax(Constants.Climber.RIGHT_CLIMBER, MotorType.kBrushless);

        right.follow(left);

        leftEncoder = left.getAbsoluteEncoder();
    }



}