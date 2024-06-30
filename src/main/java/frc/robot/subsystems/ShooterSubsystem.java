package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ShooterSubsystem extends SubsystemBase {
    private static final TalonFX top = new TalonFX(ShooterConstants.TOP_SHOOTER_MOTOR_ID, "canivoreBus");
    private static final TalonFX bottom = new TalonFX(ShooterConstants.BOTTOM_SHOOTER_MOTOR_ID, "canivoreBus");

    //enum that contains all possible Shooter Speed states for the motors
    public enum ShooterSpeed {
        SHUTTLE,
        SPEAKER,
        STOPPED,
        //SPEAKER, //Vision Subsystem?
        AMP,
        TRAP,
        BUMP_FIRE,
        DUMP;
    }

    private ShooterSpeed currentState = ShooterSpeed.STOPPED;

    public ShooterSubsystem() {
        TalonFXConfigurator topConfigurator = top.getConfigurator();
        TalonFXConfigurator bottomConfigurator = bottom.getConfigurator();
        TalonFXConfiguration topConfiguration = new TalonFXConfiguration();
        TalonFXConfiguration bottomConfiguration = new TalonFXConfiguration();

        topConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        topConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        topConfiguration.FutureProofConfigs = true;

        bottomConfiguration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        bottomConfiguration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        bottomConfiguration.FutureProofConfigs = true;

        top.getRotorVelocity().waitForUpdate(0.05);
        top.getRotorPosition().waitForUpdate(0.01);
        topConfigurator.apply(topConfiguration);

        bottom.getRotorVelocity().waitForUpdate(0.05);
        bottom.getRotorPosition().waitForUpdate(0.01);
        bottomConfigurator.apply(bottomConfiguration);
    }
    private void setShooterState(ShooterSpeed shooterState){
        switch(shooterState) {
            case STOPPED -> {
                top.set(ShooterConstants.STOP_SPEED);
                bottom.set(ShooterConstants.STOP_SPEED);
            }
            case AMP -> {
                top.set(ShooterConstants.TOP_AMP_SPEED);
                bottom.set(ShooterConstants.BOTTOM_AMP_SPEED);
            }
            case TRAP -> {
                top.set(ShooterConstants.TOP_TRAP_SPEED);
                bottom.set(ShooterConstants.BOTTOM_TRAP_SPEED);
            }
            case BUMP_FIRE -> {
                top.set(ShooterConstants.TOP_BUMP_SPEED);
                bottom.set(ShooterConstants.BOTTOM_BUMP_SPEED);
            }
            case DUMP -> {
                top.set(ShooterConstants.TOP_DUMP_SPEED);
                bottom.set(ShooterConstants.BOTTOM_DUMP_SPEED);
            }
            case SHUTTLE -> {
                //TODO: Vision calculations for shuttle shot
            }
            case SPEAKER -> {
                //TODO: Vision calculations for speaker shot
            }
        }
    }
    public void stop() {
        setShooterState(ShooterSpeed.STOPPED);
    }

    public void amp(){
        setShooterState(ShooterSpeed.AMP);
    }

    public void trap(){
        setShooterState(ShooterSpeed.TRAP);
    }

    public void bumpfire(){
        setShooterState(ShooterSpeed.BUMP_FIRE);
    }

    public void dump(){
        setShooterState(ShooterSpeed.DUMP);
    }
}


