package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class JAGSubsystem extends SubsystemBase {

    private final TalonFX jagKraken;

    public JAGSubsystem() {
        // PLACEHOLDER, REPLACE WITH DEVICE ID CONSTANT AND CANIVORE NAME
        jagKraken = new TalonFX(0, "canivoreBus");
        var jagConfigurator = jagKraken.getConfigurator();
        var configs = new TalonFXConfiguration();
    }

    @Override
    public void periodic() {
        SmartDashboard.putData("jag kraken", jagKraken);
    }

    public void spinForward(double speed) {
        jagKraken.set(Math.abs(speed));
    }

    public void spinBack(double speed) {
        jagKraken.set(-Math.abs(speed));
    }

    public void stop() {
        jagKraken.stopMotor();
    }
}
