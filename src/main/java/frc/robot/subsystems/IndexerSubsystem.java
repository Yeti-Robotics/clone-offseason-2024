package frc.robot.subsystems;


import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IndexerConstants;

public class IndexerSubsystem extends SubsystemBase {
    private final TalonFX indexMotor = new TalonFX(IndexerConstants.INDEX_MOTOR_ID, IndexerConstants.INDEX_MOTOR_CANBUS);
    private final DigitalInput leftIndexSensor = new DigitalInput(IndexerConstants.LEFT_INDEX_SENSOR_ID);
    private final DigitalInput rightIndexSensor = new DigitalInput(IndexerConstants.RIGHT_INDEX_SENSOR_ID);

    public enum IndexerState {
        EJECT,
        BREAK,
        FEED,
        INDEX
    }

    private IndexerState currentState = IndexerState.BREAK;

    public IndexerSubsystem() {
        var indexConfiguration = new TalonFXConfiguration();
        indexConfiguration.MotorOutput.NeutralMode = IndexerConstants.MOTOR_NEUTRAL_VALUE;
        indexConfiguration.MotorOutput.Inverted = IndexerConstants.MOTOR_OUTPUT_INVERTED;
        indexConfiguration.Voltage.PeakForwardVoltage = IndexerConstants.PEAK_FORWARD_VOLTAGE;
        indexConfiguration.Voltage.PeakReverseVoltage = IndexerConstants.PEAK_REVERSE_VOLTAGE;
        indexMotor.getConfigurator().apply(indexConfiguration);
    }

    public void stageNote() {
        indexMotor.set(0.5);
        SmartDashboard.putBoolean("indexer/Left sensor enabled", true);
        SmartDashboard.putBoolean("indexer/Right sensor enabled", true);
    }

    public boolean haveNote() {
        return leftIndexSensor.get() && rightIndexSensor.get();
    }

    public void feed() {
        setState(IndexerState.FEED);
    }

    public void index() {
        setState(IndexerState.INDEX);
    }

    public void stop() {
        setState(IndexerState.BREAK);
    }

    public void eject() {
        setState(IndexerState.EJECT);
    }

    public IndexerState getCurrentState() {
        return currentState;
    }

    private void setState(IndexerState indexerState) {
        switch (indexerState) {
            case FEED -> indexMotor.set(IndexerConstants.FEED_SPEED);
            case INDEX -> indexMotor.set(IndexerConstants.INDEX_SPEED);
            case EJECT -> indexMotor.set(IndexerConstants.EJECT_SPEED);
            case BREAK -> indexMotor.set(IndexerConstants.BREAK_SPEED);
        }
        currentState = indexerState;
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("indexer/Have note", haveNote());
        SmartDashboard.putBoolean("indexer/Left sensor", leftIndexSensor.get());
        SmartDashboard.putBoolean("indexer/Right sensor", rightIndexSensor.get());
    }
}
