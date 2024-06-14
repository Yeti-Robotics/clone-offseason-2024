package frc.robot.subsystems;


import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.LEDCANdleConstants;

/**
 * DO NOT USE - CURRENTLY DO NOT HAVE PLANS TO ACQUIRE HARDWARE
 * TODO: refactor into new class that uses Addressable LED
 */
public class LEDCANdleSubsystem extends SubsystemBase {

    public record LEDColor(int R, int G, int B) {}

    public enum LEDSolidState {
        DISABLED(LEDCANdleConstants.Colors.RED),
        READY(LEDCANdleConstants.Colors.YETI_BLUE);

        private final LEDColor ledColor;

        LEDSolidState(LEDColor ledColor) {
            this.ledColor = ledColor;
        }

        /**
         * Sets CANdle to color associated with the state
         * @param candle
         */
        private void activateState(CANdle candle) {
            candle.setLEDs(ledColor.R, ledColor.G, ledColor.B);
        }
    }

    public enum LEDBlinkState {
        LOCKED(LEDCANdleConstants.Colors.YETI_BLUE),
        INTAKE(LEDCANdleConstants.Colors.YETI_BLUE),
        OUTTAKE(LEDCANdleConstants.Colors.YETI_BLUE);

        private final LEDColor ledColor;

        LEDBlinkState(LEDColor ledColor) {
            this.ledColor = ledColor;
        }

        /**
         * Sets specified CANdle to color associated with the state
         * @param candle - candle to set
         */
        private void blinkOn(CANdle candle) {
            candle.setLEDs(ledColor.R, ledColor.G, ledColor.B);
        }

        /**
         * Turns off specified CANdle
         * @param candle - candle to turn LEDs off on
         */
        private static void blinkOff(CANdle candle) {
            candle.setLEDs(LEDCANdleConstants.Colors.OFF.R, LEDCANdleConstants.Colors.OFF.G, LEDCANdleConstants.Colors.OFF.B);
        }
    }

    private final CANdle candle;
    private LEDSolidState prevSolidState = null;
    private LEDSolidState currSolidState = null;
    private LEDBlinkState blinkState = null;

    public LEDCANdleSubsystem() {
        candle = new CANdle(LEDCANdleConstants.CANDLE_DEVICE_ID, LEDCANdleConstants.CANDLE_CAN_CHAIN);
        // TODO: tune brightness scalar
        candle.configBrightnessScalar(0.5);
        // TODO: change to LED type we're using,
        candle.configLEDType(LEDStripType.GRB);
        // TODO: update to defaults we'd want to use--these were what Lynk used
        candle.configV5Enabled(true);
        candle.configLOSBehavior(false);

        // Set state to ready on successful init
        setSolidState(LEDSolidState.READY);
    }

    /**
     * Sets the solid state to the specified state
     * @param solidState - new state to be set
     */
    public void setSolidState(LEDSolidState solidState) {
        currSolidState = solidState;
    }

    @Override
    public void periodic() {
        // Need to implement something similar to how Lynk does their blink, but for solid
        // state this can work.
        if (currSolidState != prevSolidState) {
            currSolidState.activateState(candle);
            prevSolidState = currSolidState;
        }
    }
}
