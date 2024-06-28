package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

public class ShooterSubsystem extends SubsystemBase {
    private static TalonFX top;
    private static TalonFX bottom;
    private final VoltageOut voltageOut = new VoltageOut(0).withEnableFOC(true);
    private final VelocityVoltage topControl = new VelocityVoltage(0).withEnableFOC(true);
    private final VelocityVoltage bottomControl = new VelocityVoltage(0).withEnableFOC(true);

    //enum that contains all possible Shooter Speed states for the motors
    public enum ShooterSpeed {
        STOPPED(ShooterConstants.STOP_SPEED, ShooterConstants.STOP_SPEED),
        //SPEAKER, //Vision Subsystem?
        AMP(ShooterConstants.TOP_AMP_SPEED, ShooterConstants.BOTTOM_AMP_SPEED),
        TRAP(ShooterConstants.TOP_TRAP_SPEED, ShooterConstants.BOTTOM_TRAP_SPEED),
        SHUTTLE(0, 0) {
            @Override
            public void setShooterSpeed(double topShooterSpeed, double bottomShooterSpeed) {
                this.topShooterSpeed = topShooterSpeed;
                this.bottomShooterSpeed = bottomShooterSpeed;
            }
        }, // Vision based values
        SPEAKER(0, 0)  {
            @Override
            public void setShooterSpeed(double topShooterSpeed, double bottomShooterSpeed) {
                this.topShooterSpeed = topShooterSpeed;
                this.bottomShooterSpeed = bottomShooterSpeed;
            }
        }, // Vision based values
        BUMP_FIRE(ShooterConstants.TOP_BUMP_SPEED, ShooterConstants.BOTTOM_BUMP_SPEED),
        DUMP(ShooterConstants.TOP_DUMP_SPEED, ShooterConstants.BOTTOM_DUMP_SPEED);

        //initialize topShooterSpeed and bottomShooterSpeed to set motor speeds later
        protected double topShooterSpeed;
        protected double bottomShooterSpeed;

        ShooterSpeed(double topShooterSpeed, double bottomShooterSpeed) {
            this.topShooterSpeed = topShooterSpeed;
            this.bottomShooterSpeed = bottomShooterSpeed;

        }

        public void setShooterSpeed(double topSpeed, double bottomSpeed) {
            top.set(topSpeed);
            bottom.set(bottomSpeed);
        }
    }
}


