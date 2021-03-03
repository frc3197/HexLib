// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil;

/** This Class stores Constants for the SwerveDriveBuilder */
public class SwerveBuilderConstants {
    /**
     * For all units conversion use the static methods from the Units class to do
     * any conversions Length: Meters Width: Meters maxAngleSpeed: Radians Per
     * Second maxAngleAcceleration : Radians Per Second maxSpeed: Meters Per Second
     * rampRate: Seconds
     */
    private double length, width, maxAngleSpeed, maxAngleAcceleration, maxDriveSpeed, rampRate, swerveWheelDiam,
            swerveDriveMotorGearRatio;

    public SwerveBuilderConstants(double length, double width, double maxAngleSpeed, double maxAngleAcceleration,
            double maxDriveSpeed, double rampRate, double swerveWheelDiam, double swerveDriveMotorGearRatio) {
        this.length = length;
        this.width = width;
        this.maxAngleSpeed = maxAngleSpeed;
        this.maxAngleAcceleration = maxAngleAcceleration;
        this.maxDriveSpeed = maxDriveSpeed;
        this.rampRate = rampRate;
        this.swerveDriveMotorGearRatio = swerveDriveMotorGearRatio;
        this.swerveWheelDiam = swerveWheelDiam;
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
     * Get the maxAngleSpeed of the SwerveDrive
     * 
     * @return double
     */
    public double getMaxAngleSpeed() {
        return maxAngleSpeed;
    }

    /**
     * Get the maxAngleAcceleration of the SwerveDrive
     * 
     * @return double
     */
    public double getMaxAngleAcceleration() {
        return maxAngleAcceleration;
    }

    /**
     * Get the maxDriveSpeed of the SwerveDrive
     * 
     * @return double
     */
    public double getMaxDriveSpeed() {
        return maxDriveSpeed;
    }

    /**
     * Get the rampRate of the SwerveDrive
     * 
     * @return double
     */
    public double getRampRate() {
        return rampRate;
    }

    /**
     * Get the WheelDiam of the SwerveDrive
     * 
     * @return double
     */
    public double getWheelDiam() {
        return swerveWheelDiam;
    }

    /**
     * Get the DriveMotor's Gear Ratio of the SwerveDrive
     * 
     * @return double
     */
    public double getDriveGearRatio() {
        return swerveDriveMotorGearRatio;
    }

}
