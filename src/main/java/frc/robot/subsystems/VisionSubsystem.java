

package frc.robot.subsystems;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.List;
import java.util.Optional;



import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;



public class VisionSubsystem extends SubsystemBase {
    //Output variables:
    Pose3d currentPose;
    double range;

    //Initializing Photonvision camera:
    PhotonCamera camera = new PhotonCamera("photonvision");
    AprilTagFieldLayout aprilTagFieldLayout = AprilTagFields.k2024Crescendo.loadAprilTagLayoutField();
        Transform3d robotToCam = 
        new Transform3d(new Translation3d(Constants.VisionConstants.cameraX, Constants.VisionConstants.cameraY, Constants.VisionConstants.cameraZ), 
        new Rotation3d(Constants.VisionConstants.cameraRoll,Constants.VisionConstants.cameraPitch,Constants.VisionConstants.cameraYaw)); 
        //Cam mounted facing forward, half a meter forward of center, half a meter up from center.

    // Construct PhotonPoseEstimator
    PhotonPoseEstimator photonPoseEstimator = new PhotonPoseEstimator(aprilTagFieldLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, camera, robotToCam);


    public Pose3d getCurrentPose()
    {
        return currentPose;
    }

    public double getDistanceFromSpeaker()
    {
        return range;
    }

    @Override
    public void periodic() {
        //Finding current pose:
        Optional<EstimatedRobotPose> estimatedPoseOpt =  photonPoseEstimator.update();//Update the current pose.

        if (estimatedPoseOpt.isPresent()) //If the update worked send it to 'currentPose'.
        {
            currentPose = estimatedPoseOpt.get().estimatedPose;
        }

        //Finding speaker distance:
        PhotonPipelineResult result = camera.getLatestResult();


        if(result.hasTargets())
        {

            for (PhotonTrackedTarget currentTarget : result.getTargets())
            {
                if(currentTarget.getFiducialId() == Constants.VisionConstants.speakerTargetID)
                {
                    range = PhotonUtils.calculateDistanceToTargetMeters(
                            Constants.VisionConstants.cameraHeight,
                            Constants.VisionConstants.targetHeight,
                            Constants.VisionConstants.cameraPitch,
                            Math.toRadians(currentTarget.getPitch())
                    );
                }
            }
        }
}



}
