package frc.robot.constants;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class IndexerConstants {
    public static final int INDEX_MOTOR_ID = 5;
    public static final int LEFT_INDEX_SENSOR_ID = 0; //Change based on ID
    public static final int RIGHT_INDEX_SENSOR_ID = 1; //Change based on ID

    /* CANBus */
    public static final String INDEX_MOTOR_CANBUS = "rio";

    /* MOTOR SPEED VALUES */
    public static final double INDEX_SPEED = 0.80;
    public static final double FEED_SPEED = 1.00;
    public static final double EJECT_SPEED = -1.00;
    public static final double BREAK_SPEED = 0.00;

    /* MOTOR CONFIG VALUES */
    public static final double PEAK_FORWARD_VOLTAGE = 12.0;
    public static final double PEAK_REVERSE_VOLTAGE = -12.0;
    public static final InvertedValue MOTOR_OUTPUT_INVERTED = InvertedValue.Clockwise_Positive;
    public static final NeutralModeValue MOTOR_NEUTRAL_VALUE = NeutralModeValue.Brake;

}
