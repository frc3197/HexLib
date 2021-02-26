// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil;


/** Add your docs here. */
// Takes in a SwerveModuleConstantsGroup, Gyro Type, Length (Meters), Width(Meters).
public class SwerveDriveBuilder {
SwerveModuleConstantsGroup swerveModuleConstantsGroup;
String gyroType;
double length; 
double width;

public SwerveDriveBuilder(SwerveModuleConstantsGroup swerveModuleConstantsGroup, String gyroType, double length, double width){
this.swerveModuleConstantsGroup = swerveModuleConstantsGroup;
this.gyroType = gyroType;
this.length = length;
this.width = width;
}

// Match String Type and Create Gyro
// navX, ADXRS450_Gyro, Pigeon

}
