// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil;

/** Add your docs here. */
public class SwerveBuilderConstants {
    private double length, width, maxAngleSpeed, maxAngleAcceleration, maxDriveSpeed, rampRate;

    public SwerveBuilderConstants(double length, double width, double maxAngleSpeed, double maxAngleAcceleration,
            double maxDriveSpeed, double rampRate) {
        this.length = length;
        this.width = width;
        this.maxAngleSpeed = maxAngleSpeed;
        this.maxAngleAcceleration = maxAngleAcceleration;
        this.maxDriveSpeed = maxDriveSpeed;
        this.rampRate = rampRate;
    }

    /**
     * @return double
     */
    public double getLength() {
        return length;
    }

    /**
     * @return double
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return double
     */
    public double getMaxAngleSpeed() {
        return maxAngleSpeed;
    }

    /**
     * @return double
     */
    public double getMaxAngleAcceleration() {
        return maxAngleAcceleration;
    }

    /**
     * @return double
     */
    public double getMaxDriveSpeed() {
        return maxDriveSpeed;
    }

    /**
     * @return double
     */
    public double getRampRate() {
        return rampRate;
    }

}
