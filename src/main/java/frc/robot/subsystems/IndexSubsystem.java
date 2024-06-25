package frc.robot.subsystems;


import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {
    private final static IndexSubsystem INSTANCE = null;
    private final TalonFX indexMotor = new TalonFX(FeederConstants.indexMotorID, FeederConstants.indexMotorCanBus);
    private final DigitalInput leftIndexSensor = new DigitalInput(FeederConstants.leftIndexSensorID);
    private final DigitalInput rightIndexSensor = new DigitalInput(FeederConstants.rightIndexSensorID);
    private boolean haveNote = false;

    public enum IndexerState {
        EJECT,
        BREAK,
        SOFTFEED,
        FEED,
        INDEX
    }

    private IndexerState currentState = IndexerState.BREAK;

    public IndexSubsystem() {

        var indexConfiguration = new TalonFXConfiguration();
        indexConfiguration.MotorOutput.NeutralMode = FeederConstants.motorNeutralValue;
        indexConfiguration.MotorOutput.Inverted = FeederConstants.motorOutputInverted;
        indexConfiguration.Voltage.PeakForwardVoltage = FeederConstants.peakForwardVoltage;
        indexConfiguration.Voltage.PeakReverseVoltage = FeederConstants.peakReverseVoltage;
        indexMotor.getConfigurator().apply(indexConfiguration);

    }

    public void stageNote() {
        indexMotor.set(0.5);
        SmartDashboard.putBoolean("indexer/Left sensor enabled", true);
        SmartDashboard.putBoolean("indexer/Right sensor enabled", true);

    }

    public boolean haveNote() {
        return (SmartDashboard.getBoolean("indexer/Left sensor enabled", true) && !leftIndexSensor.get()) ||
        (SmartDashboard.getBoolean("indexer/Right sensor enabled", true) && !rightIndexSensor.get());

    }


    public void feed() {
        setState(IndexerState.FEED);
    }
    public void index() {
        setState(IndexerState.INDEX);
    }
    public void softfeed() {
        setState(IndexerState.SOFTFEED);
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
            case FEED -> indexMotor.set(FeederConstants.FEEDSPEED);
            case INDEX -> indexMotor.set(FeederConstants.INDEXSPEED);
            case EJECT -> indexMotor.set(FeederConstants.EJECTSPEED);
            case SOFTFEED -> indexMotor.set(FeederConstants.SOFTFEEDSPEED);
            case BREAK -> indexMotor.set(FeederConstants.BREAKSPEED);
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




