package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final TalonFX intakeMotor = new TalonFX(IntakeConstants.INTAKE_MOTOR_ID, "canivoreBus");
   // private final DigitalInput beamBreak = new DigitalInput(IntakeConstants.INTAKE_BEAMBREAK_CHANNEL);

    public enum IntakeState {
        INTAKE,
        OUTTAKE,
        STOPPED
    }

    private IntakeState currentState = IntakeState.STOPPED;

    public IntakeSubsystem() {
        TalonFXConfigurator configurator = intakeMotor.getConfigurator();
        TalonFXConfiguration configuration = new TalonFXConfiguration();

        configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        configuration.FutureProofConfigs = true;
        intakeMotor.getRotorVelocity().waitForUpdate(0.05);
        intakeMotor.getRotorPosition().waitForUpdate(0.01);
        configurator.apply(configuration);
    }

    public void intake() {
        setState(IntakeState.INTAKE);
    }

    public void outtake() {
        setState(IntakeState.OUTTAKE);
    }

    public void stop() {
        setState(IntakeState.STOPPED);
    }

    public IntakeState getCurrentState() {
        return currentState;
    }

    private void setState(IntakeState intakeState) {
        switch (intakeState) {
            case INTAKE -> intakeMotor.set(IntakeConstants.INTAKE_MOTOR_INTAKE_SPEED);
            case OUTTAKE -> intakeMotor.set(IntakeConstants.INTAKE_MOTOR_EJECT_SPEED);
            case STOPPED -> intakeMotor.set(IntakeConstants.INTAKE_MOTOR_STOP_SPEED);
        }

        currentState = intakeState;
    }
}
