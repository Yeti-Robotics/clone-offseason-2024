package frc.robot.constants;

import frc.robot.subsystems.LEDCANdleSubsystem.LEDColor;

public class LEDCANdleConstants {


    // TODO: update CANdle-specific constants
    public static final int CANDLE_DEVICE_ID = 0;
    public static final String CANDLE_CAN_CHAIN = "rio";

    public static final class Colors {
        public static final LEDColor RED = new LEDColor(255, 0, 0);
        public static final LEDColor YETI_BLUE = new LEDColor(84, 182, 229);
        public static final LEDColor OFF = new LEDColor(0, 0, 0);
    }

}
