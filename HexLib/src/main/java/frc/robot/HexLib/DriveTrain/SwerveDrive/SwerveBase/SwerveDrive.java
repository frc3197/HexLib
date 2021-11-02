// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveBase;

import edu.wpi.first.wpilibj.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveAutoParameters;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveBuilderConstants;

/** Represents a swerve drive style drivetrain. */
@SuppressWarnings("unused")
public class SwerveDrive implements Subsystem {
    private static SwerveBuilderConstants swerveBuilderConstants;
    private static double maxSpeed;

    private double x;
    private double y;
    // Translation from the center of the bot, distance of the wheels to the center.
    private final Translation2d m_frontLeftLocation;
    private final Translation2d m_frontRightLocation;
    private final Translation2d m_rearLeftLocation;
    private final Translation2d m_rearRightLocation;

    // Creates 4 SwerveModule Objects
    private final SwerveModule m_frontRight;
    private final SwerveModule m_frontLeft;
    private final SwerveModule m_rearRight;
    private final SwerveModule m_rearLeft;

    // Creates a Generic Gyro object
    private static Gyro m_gyro;

    // Creates a SwerveDriveKinematics object
    private final SwerveDriveKinematics m_kinematics;

    // Creates a SwerveDriveOdometry
    private final SwerveDriveOdometry m_odometry;

    private final SwerveDrivePoseEstimator m_poseEstimator;

    @SuppressWarnings("static-access")
    public SwerveDrive(SwerveModule rearRight, SwerveModule rearLeft, SwerveModule frontRight, SwerveModule frontLeft,
            SwerveBuilderConstants swerveBuilderConstants, SwerveAutoParameters swerveAutoParameters, Gyro gyro) {
        m_frontRight = frontRight;
        m_frontLeft = frontLeft;
        m_rearRight = rearRight;
        m_rearLeft = rearLeft;

        m_gyro = gyro;

        this.swerveBuilderConstants = swerveBuilderConstants;
        maxSpeed = swerveBuilderConstants.getMaxDriveSpeed();

        x = Units.inchesToMeters(swerveBuilderConstants.getWidth() / 2);
        y = Units.inchesToMeters(swerveBuilderConstants.getLength() / 2);

        m_frontLeftLocation = new Translation2d(x, y);
        m_frontRightLocation = new Translation2d(x, -y);
        m_rearLeftLocation = new Translation2d(-x, y);
        m_rearRightLocation = new Translation2d(-x, -y);
        
        m_kinematics = new SwerveDriveKinematics(m_frontLeftLocation,
            m_frontRightLocation, m_rearLeftLocation, m_rearRightLocation);

        m_odometry = new SwerveDriveOdometry(m_kinematics, m_gyro.getRotation2d());

        m_poseEstimator = new SwerveDrivePoseEstimator(m_gyro.getRotation2d(), swerveAutoParameters.getInitialPos(), m_kinematics, swerveAutoParameters.getStateSD(), swerveAutoParameters.getLocalMeasurementSD(), swerveAutoParameters.getVisionMeasurementSD());
    
        m_gyro.reset();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        updateOdometry();
    }

    /**
     * Function used to drive the bot
     * 
     * @param xSpeed
     * @param ySpeed
     * @param rot
     * @param fieldRelative
     */
    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    // Set up the modules
    if (fieldRelative) {
        setModuleStates(ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, m_gyro.getRotation2d()));
      }
      else {
        setModuleStates(new ChassisSpeeds(xSpeed, ySpeed, rot));
      }
    }

    
    /** 
     * @param chassisSpeeds
     * @param centerOfRotation
     */
    private void setModuleStates(ChassisSpeeds chassisSpeeds, Translation2d centerOfRotation) {

        // Get the module states
        SwerveModuleState[] swerveModuleStates = m_kinematics.toSwerveModuleStates(chassisSpeeds, centerOfRotation);
    
        // Setup the max speed of each module
        SwerveDriveKinematics.normalizeWheelSpeeds(swerveModuleStates,maxSpeed);
    
        // Set each of the modules to their optimized state
        m_frontLeft.setDesiredState(swerveModuleStates[0]);
        m_frontRight.setDesiredState(swerveModuleStates[1]);
        m_rearLeft.setDesiredState(swerveModuleStates[2]);
        m_rearRight.setDesiredState(swerveModuleStates[3]);
    
        // Update the robot's odometry
        updateOdometry();
      }
    
    
      /**
       * Sets all of the states of the modules and updates the odometry of the robot
       * @param chassisSpeeds The desired velocities of the movement of the entire drivetrain
       */
      private void setModuleStates(ChassisSpeeds chassisSpeeds) {
    
        // Get the module states
        SwerveModuleState[] swerveModuleStates = m_kinematics.toSwerveModuleStates(chassisSpeeds);
    
        // Setup the max speed of each module
        SwerveDriveKinematics.normalizeWheelSpeeds(swerveModuleStates, maxSpeed);
    
        // Set each of the modules to their optimized state
        m_frontLeft.setDesiredState(swerveModuleStates[0]);
        m_frontRight.setDesiredState(swerveModuleStates[1]);
        m_rearLeft.setDesiredState(swerveModuleStates[2]);
        m_rearRight.setDesiredState(swerveModuleStates[3]);
    
        // Update the robot's odometry
        updateOdometry();
      }

    /**
     * Updates the state and position of Robot
     */
    public void updateOdometry() {
        m_odometry.update(m_gyro.getRotation2d(), m_frontLeft.getState(), m_frontRight.getState(), m_rearLeft.getState(),
                m_rearRight.getState());
    }

    /**
     * Resets the Gyro
     */
    public void resetGyro() {
        m_gyro.reset();
    }

    /**
     * Resets the Encoders
     */
    public void resetEncoders() {
        m_frontLeft.resetDriveEncoder();
        m_frontRight.resetDriveEncoder();
        m_rearLeft.resetDriveEncoder();
        m_rearRight.resetDriveEncoder();
    }

    /**
     * Sets the Voltage of All the Motors
     * 
     * @param speed
     */
    public void setVoltageAllMotors(double speed) {
        m_frontLeft.setVoltageSpeed(speed);
        m_frontRight.setVoltageSpeed(speed);
        m_rearLeft.setVoltageSpeed(speed);
        m_rearRight.setVoltageSpeed(speed);

    }

}