// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.CreationUtil;
import frc.robot.DriveTrain.SwerveDrive.SwerveDriveClasses.SwerveDrive;
import frc.robot.DriveTrain.SwerveDrive.SwerveDriveClasses.SwerveModule;

/** Add your docs here. */
// Takes in a SwerveModuleConstantsGroup, Gyro Type, Length (Meters),
// Width(Meters).
public class SwerveDriveBuilder {
    SwerveModuleConstantsGroup swerveModuleConstantsGroup;
    SwerveBuilderConstants swerveBuilderConstants;

    String gyroType;
    String motorType;

    double length;
    double width;

    public SwerveDriveBuilder(SwerveModuleConstantsGroup swerveModuleConstantsGroup,
            SwerveBuilderConstants swerveBuilderConstants, String gyroType, String motorType, double length,
            double width) {
        this.swerveModuleConstantsGroup = swerveModuleConstantsGroup;
        this.swerveBuilderConstants = swerveBuilderConstants;

        this.gyroType = gyroType;
        this.motorType = motorType;

        this.length = length;
        this.width = width;
    }

    public static SwerveDrive buildSwerve(SwerveDriveBuilder swerveDriveBuilder) {

        SwerveModuleConstants frontLeftConstants = swerveDriveBuilder.swerveModuleConstantsGroup
                .getFrontLeftConstants();
        SwerveModuleConstants frontRightConstants = swerveDriveBuilder.swerveModuleConstantsGroup
                .getFrontRightConstants();
        SwerveModuleConstants rearLeftConstants = swerveDriveBuilder.swerveModuleConstantsGroup.getRearLeftConstants();
        SwerveModuleConstants rearRightConstants = swerveDriveBuilder.swerveModuleConstantsGroup
                .getRearRightConstants();

        SwerveBuilderConstants builderConstants = swerveDriveBuilder.swerveBuilderConstants;

        Gyro gyro = CreationUtil.createGyro(swerveDriveBuilder.gyroType);

        SwerveModule frontLeft = new SwerveModule(frontLeftConstants, builderConstants);
        SwerveModule frontRight = new SwerveModule(frontRightConstants, builderConstants);
        SwerveModule rearLeft = new SwerveModule(rearLeftConstants, builderConstants);
        SwerveModule rearRight = new SwerveModule(rearRightConstants, builderConstants);
        SwerveDrive swerveDrive = new SwerveDrive(rearRight, rearLeft, frontRight, frontLeft, builderConstants, gyro);

        return swerveDrive;
    }

}
