// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil;

/** Creates a group of SwerveModuleConstantsObjects */
public class SwerveModuleConstantsGroup {

    private SwerveModuleConstants frontLeftConstants, frontRightConstants, rearLeftConstants, rearRightConstants;

    public SwerveModuleConstantsGroup(SwerveModuleConstants frontLeftConstants, SwerveModuleConstants frontRightConstants, SwerveModuleConstants rearLeftConstants, SwerveModuleConstants rearRightConstants){
        this.frontLeftConstants = frontLeftConstants;
        this.frontRightConstants = frontRightConstants;
        this.rearRightConstants = rearRightConstants;
        this.rearLeftConstants = rearLeftConstants;
    }

    public SwerveModuleConstants getRearLeftConstants(){
        return rearLeftConstants;
    }
    public SwerveModuleConstants getFrontLeftConstants(){
        return frontLeftConstants;
    }
    public SwerveModuleConstants getRearRightConstants(){
        return rearRightConstants;
    }
    public SwerveModuleConstants getFrontRightConstants(){
        return frontRightConstants;
    }
     

}
