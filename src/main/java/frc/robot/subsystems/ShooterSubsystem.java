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
        SHUTTLE(0,0), // Vision based values
        SPEAKER(0,0), // Vision based values
        BUMP_FIRE(ShooterConstants.TOP_BUMP_SPEED, ShooterConstants.BOTTOM_BUMP_SPEED),
        DUMP(ShooterConstants.TOP_DUMP_SPEED, ShooterConstants.BOTTOM_DUMP_SPEED);

        //initialize topShooterSpeed and bottomShooterSpeed to set motor speeds later
        private final double topShooterSpeed;
        private final double bottomShooterSpeed;

        ShooterSpeed(double topShooterSpeed, double bottomShooterSpeed) {
            this.topShooterSpeed = topShooterSpeed;
            this.bottomShooterSpeed = bottomShooterSpeed;

        }
        //set speed for dynamic states
        void setShooterSpeed() {
            if (this == SPEAKER || this == SHUTTLE) {
                throw new Error("Can't set constant speed for vision-based shots.");
            }
            top.set(topShooterSpeed);
            bottom.set(bottomShooterSpeed);
        }

        //set speed non-dynamic states
        void setShooterSpeed(double topSpeed, double bottomSpeed){
            if (this != SPEAKER && this != SHUTTLE) {
                throw new Error("Can't set dynamic speed for non-vision shots.");
            }
            top.set(topSpeed);
            bottom.set(bottomSpeed);
        }
    }

    //set motor speed
    public void setSpeed(ShooterSpeed speedState) {
        if (speedState == ShooterSpeed.SPEAKER || speedState == ShooterSpeed.SHUTTLE) {
            speedState.setShooterSpeed(0, 0);
            //TODO: get vision values
        } else {
            speedState.setShooterSpeed();
        }
    }
}

