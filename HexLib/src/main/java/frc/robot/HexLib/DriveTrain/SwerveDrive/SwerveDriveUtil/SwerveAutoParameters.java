// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;

/** Add your docs here. */
public class SwerveAutoParameters {
    Pose2d initialPos;
    PIDController XPID, YPID;
    ProfiledPIDController rotPID;
    double stateSD, localMeasurementSD, visionMeasurmentSD;

    public SwerveAutoParameters(Pose2d initialPos, PIDController XPID, PIDController YPID, ProfiledPIDController rotPID,
            double stateSD, double localMeasurementSD, double visionMeasurmentSD) {
        this.initialPos = initialPos;
        this.XPID = XPID;
        this.YPID = YPID;
        this.rotPID = rotPID;
        this.stateSD = stateSD;
        this.localMeasurementSD = localMeasurementSD;
        this.visionMeasurmentSD = visionMeasurmentSD;
    }

    public Pose2d getInitialPos() {
        return initialPos;
    }

    public PIDController getXPID() {
        return XPID;
    }

    public PIDController getYPID() {
        return YPID;
    }

    public ProfiledPIDController getRotPID() {
        return rotPID;
    }

    public double getStateSD(){
        return stateSD;
    }
    public double getLocalMeasurementSD(){
        return localMeasurementSD;
    }
    public double getVisionMeasurementSD(){
        return visionMeasurmentSD;
    }

}
