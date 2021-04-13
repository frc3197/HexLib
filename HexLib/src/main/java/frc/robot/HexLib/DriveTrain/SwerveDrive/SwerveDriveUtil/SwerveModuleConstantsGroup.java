// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
* The purpose of this class is to store multiple SwerveModuleConstants objects in order to condense the amount of constants needed for SwerveDriveBuilder creation.
*/

package frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil;

/** Creates a group of SwerveModuleConstantsObjects */
public class SwerveModuleConstantsGroup {

    private SwerveModuleConstants frontLeftConstants, frontRightConstants, rearLeftConstants, rearRightConstants;

    /**
     * Constructor for a SwerveModuleConstantsGroup object
     * 
     * @param frontLeftConstants
     * @param frontRightConstants
     * @param rearLeftConstants
     * @param rearRightConstants
     */
    public SwerveModuleConstantsGroup(SwerveModuleConstants frontLeftConstants,
            SwerveModuleConstants frontRightConstants, SwerveModuleConstants rearLeftConstants,
            SwerveModuleConstants rearRightConstants) {
        this.frontLeftConstants = frontLeftConstants;
        this.frontRightConstants = frontRightConstants;
        this.rearRightConstants = rearRightConstants;
        this.rearLeftConstants = rearLeftConstants;
    }

    /**
     * Gets the SwerveModuleConstants object for the rearLeftModule
     * 
     * @return rearLeftConstants
     */

    public SwerveModuleConstants getRearLeftConstants() {
        return rearLeftConstants;
    }

    /**
     * Gets the SwerveModuleConstants object for the frontLeftModule
     * 
     * @return frontLeftConstants
     */

    public SwerveModuleConstants getFrontLeftConstants() {
        return frontLeftConstants;
    }

    /**
     * Gets the SwerveModuleConstants object for the rearRightModule
     * 
     * @return rearRightConstants
     */
    public SwerveModuleConstants getRearRightConstants() {
        return rearRightConstants;
    }

    /**
     * Gets the SwerveModuleConstants object for the frontRightModule
     * 
     * @return frontRightConstants
     */
    public SwerveModuleConstants getFrontRightConstants() {
        return frontRightConstants;
    }

}
