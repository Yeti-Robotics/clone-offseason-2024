package frc.robot.constants;

public class DrivetrainConstants {

    /* CAN BUS */
    public static final String DRIVETRAIN_CANBUS = "canivoreBus";

    /* SPEEDS */
    public static final double STOP_SPEED = 0.0;
    // Rest of speeds need to be tuned
    public static final double TOP_AMP_SPEED = 0.0;
    public static final double BOTTOM_AMP_SPEED = 0.0;
    public static final double TOP_TRAP_SPEED = 0.0;
    public static final double BOTTOM_TRAP_SPEED = 0.0;
    public static final double TOP_BUMP_SPEED = 0.0;
    public static final double BOTTOM_BUMP_SPEED = 0.0;
    public static final double TOP_DUMP_SPEED = 0.0;
    public static final double BOTTOM_DUMP_SPEED = 0.0;

    /* CANCODERS */
    public static final int BACK_LEFT_CANCODER_ID = 2;
    public static final int FRONT_LEFT_CANCODER_ID = 3;
    public static final int BACK_RIGHT_CANCODER_ID = 4;
    public static final int FRONT_RIGHT_CANCODER_ID = 5;

    // PIGEON
    public static final int PIGEON_ID = 1;

    /* MOTORS */
    // BACK LEFT
    public static final int BACK_LEFT_AZIMUTH_ID = 6;
    public static final int BACK_LEFT_DRIVE_ID = 7;

    // BACK RIGHT
    public static final int BACK_RIGHT_AZIMUTH_ID = 8;
    public static final int BACK_RIGHT_DRIVE_ID = 11;

    // FRONT LEFT
    public static final int FRONT_LEFT_AZIMUTH_ID = 12;
    public static final int FRONT_LEFT_DRIVE_ID = 9;

    // FRONT RIGHT
    public static final int FRONT_RIGHT_AZIMUTH_ID = 10;
    public static final int FRONT_RIGHT_DRIVE_ID = 13;

}
