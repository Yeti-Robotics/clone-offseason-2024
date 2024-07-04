

package frc.robot.subsystems;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;
import java.util.Optional;



import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;



public class VisionSubsystem extends SubsystemBase {

    Pose3d currentPose;

    double range;

    PhotonCamera camera = new PhotonCamera("photonvision");
    AprilTagFieldLayout aprilTagFieldLayout = AprilTagFields.k2024Crescendo.loadAprilTagLayoutField();
    Transform3d robotToCam = new Transform3d(new Translation3d(0.5, 0.0, 0.5), new Rotation3d(0,0,0)); //Cam mounted facing forward, half a meter forward of center, half a meter up from center.
    // Construct PhotonPoseEstimator
    PhotonPoseEstimator photonPoseEstimator = new PhotonPoseEstimator(aprilTagFieldLayout, PoseStrategy.CLOSEST_TO_REFERENCE_POSE, camera, robotToCam);
    public Pose3d getCurrentPose()
    {
       
        return currentPose;
    }

    public double CheckDistanceFromSpeaker()
    {
       
        return range;
        
        
    }

    @Override
    public void periodic() {
       Optional<EstimatedRobotPose> estimatedPoseOpt =  photonPoseEstimator.update();//update

        if (estimatedPoseOpt.isPresent()) {//if update worked new current pose
            currentPose = estimatedPoseOpt.get().estimatedPose;
        }


        var result = camera.getLatestResult();
        boolean hasTargets = result.hasTargets();

        if(hasTargets == true){
            
        
        List<PhotonTrackedTarget> targets = result.getTargets();
        int target_size = targets.size();
        int targetID;
        PhotonTrackedTarget speakertarget = null;
        for (int i = 0; i < target_size; i++) {
           PhotonTrackedTarget current_target = targets.get(i);
            targetID = current_target.getFiducialId();
            if(targetID == 11){
                speakertarget = targets.get(i);
            }
        }
        double range =
                        PhotonUtils.calculateDistanceToTargetMeters(
                                1,
                                2,
                                1,
                                Units.degreesToRadians(speakertarget.getPitch()));

      }
    }



}
