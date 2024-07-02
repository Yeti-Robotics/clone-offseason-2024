package frc.robot.subsystems;


import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {
    private final static IndexSubsystem INSTANCE = null;
    private final TalonFX indexMotor = new TalonFX(IndexerConstants.indexMotorID, IndexerConstants.indexMotorCanBus);
    private final DigitalInput leftIndexSensor = new DigitalInput(IndexerConstants.leftIndexSensorID);
    private final DigitalInput rightIndexSensor = new DigitalInput(IndexerConstants.rightIndexSensorID);
    private boolean haveNote = false;

    public enum IndexerState {
        EJECT,
        BREAK,
        FEED,
        INDEX
    }

    private IndexerState currentState = IndexerState.BREAK;

    public IndexSubsystem() {

        var indexConfiguration = new TalonFXConfiguration();
        indexConfiguration.MotorOutput.NeutralMode = IndexerConstants.motorNeutralValue;
        indexConfiguration.MotorOutput.Inverted = IndexerConstants.motorOutputInverted;
        indexConfiguration.Voltage.PeakForwardVoltage = IndexerConstants.peakForwardVoltage;
        indexConfiguration.Voltage.PeakReverseVoltage = IndexerConstants.peakReverseVoltage;
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
            case FEED -> indexMotor.set(IndexerConstants.FEEDSPEED);
            case INDEX -> indexMotor.set(IndexerConstants.INDEXSPEED);
            case EJECT -> indexMotor.set(IndexerConstants.EJECTSPEED);
            case BREAK -> indexMotor.set(IndexerConstants.BREAKSPEED);
        }
        currentState = indexerState;
    }



    @Override
    public void periodic() {
        SmartDashboard.putBoolean("indexer/Have note", haveNote);
        SmartDashboard.putBoolean("indexer/Left sensor", leftIndexSensor.get());
        SmartDashboard.putBoolean("indexer/Right sensor", rightIndexSensor.get());
    }

    }




