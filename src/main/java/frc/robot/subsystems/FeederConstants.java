package frc.robot.subsystems;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class FeederConstants {
    public static final int indexMotorID = 13; //Change based on ID
    public static final int leftIndexSensorID = 0; //Change based on ID
    public static final int rightIndexSensorID = 1; //Change based on ID
    /* CANBus */
    public static final String indexMotorCanBus = "rio";
    /* Motor Speed Values */
    public static final double INDEXSPEED = 0.80;
    public static final double FEEDSPEED = 1.00;
    public static final double SOFTFEEDSPEED = 0.25;
    public static final double EJECTSPEED = -1.00;
    public static final double BREAKSPEED = 0.00;
    /* Timer Values */
    public static final double PAUSETOSHOOTSPEED = 0.75;
    /* Motor Config Values */
    public static final double peakForwardVoltage = 12.0;
    public static final double peakReverseVoltage = -12.0;

    public static final InvertedValue motorOutputInverted = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue motorNeutralValue = NeutralModeValue.Brake;

}
