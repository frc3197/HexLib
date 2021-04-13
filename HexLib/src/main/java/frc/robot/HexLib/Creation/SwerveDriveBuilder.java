// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.HexLib.Creation;

import edu.wpi.first.wpilibj.interfaces.Gyro;

//import frc.robot.Creation.CreationUtil;

import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveClasses.SwerveDrive;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveClasses.SwerveModule;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveBuilderConstants;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveModuleConstants;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveModuleConstantsGroup;

/** Class using all of the other SwerveUtilities to build a SwerveDrive Obj */
public class SwerveDriveBuilder {
        private SwerveModuleConstantsGroup swerveModuleConstantsGroup;
        private SwerveBuilderConstants swerveBuilderConstants;

        private String gyroType;
        private String motorType;

        private double length;
        private double width;

        public SwerveDriveBuilder(SwerveModuleConstantsGroup swerveModuleConstantsGroup,
                        SwerveBuilderConstants swerveBuilderConstants, String gyroType
        // , String motorType
        ) {
                this.swerveModuleConstantsGroup = swerveModuleConstantsGroup;
                this.swerveBuilderConstants = swerveBuilderConstants;

                this.gyroType = gyroType;
                // this.motorType = motorType;

                this.length = swerveBuilderConstants.getLength();
                this.width = swerveBuilderConstants.getWidth();
        }

        /**
         * Builds a SwerveDrive Object using all of the other Utils and Constants
         * 
         * @param swerveDriveBuilder
         * @return SwerveDrive
         */
        public static SwerveDrive buildSwerve(SwerveDriveBuilder swerveDriveBuilder) {

                SwerveModuleConstants frontLeftConstants = swerveDriveBuilder.swerveModuleConstantsGroup
                                .getFrontLeftConstants();
                SwerveModuleConstants frontRightConstants = swerveDriveBuilder.swerveModuleConstantsGroup
                                .getFrontRightConstants();
                SwerveModuleConstants rearLeftConstants = swerveDriveBuilder.swerveModuleConstantsGroup
                                .getRearLeftConstants();
                SwerveModuleConstants rearRightConstants = swerveDriveBuilder.swerveModuleConstantsGroup
                                .getRearRightConstants();

                SwerveBuilderConstants builderConstants = swerveDriveBuilder.swerveBuilderConstants;

                Gyro gyro = CreationUtil.createGyro(swerveDriveBuilder.gyroType);

                SwerveModule frontLeft = new SwerveModule(frontLeftConstants, builderConstants);
                SwerveModule frontRight = new SwerveModule(frontRightConstants, builderConstants);
                SwerveModule rearLeft = new SwerveModule(rearLeftConstants, builderConstants);
                SwerveModule rearRight = new SwerveModule(rearRightConstants, builderConstants);
                SwerveDrive swerveDrive = new SwerveDrive(rearRight, rearLeft, frontRight, frontLeft, builderConstants,
                                gyro);

                return swerveDrive;
        }

        /**
         * Get the length of the SwerveDrive
         * 
         * @return double
         */
        public double getLength() {
                return length;
        }

        /**
         * Get the width of the SwerveDrive
         * 
         * @return double
         */
        public double getWidth() {
                return width;

        }

        /**
         * Get the String containing the GyroType
         * 
         * @return String
         */
        public String getGyroType() {
                return gyroType;
        }

        /**
         * Get the String containing the MotorType
         * 
         * @return String
         */
        public String getMotorType() {
                return motorType;
        }

        /**
         * Get the swerveBuilderConstants Object
         * 
         * @return SwerveBuilderConstants
         */
        public SwerveBuilderConstants getSwerveBuilderConstants() {
                return swerveBuilderConstants;
        }

        /**
         * Get the swerveModuleConstantsGroup Object
         * 
         * @return SwerveModuleConstantsGroup
         */
        public SwerveModuleConstantsGroup getSwerveModuleConstantsGroup() {
                return swerveModuleConstantsGroup;
        }

}
