// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil;

/** Add your docs here. */
public class SwerveBuilderConstants {
    private double length, width, maxAngleSpeed, maxAngleAcceleration, maxDriveSpeed, rampRate;

    public SwerveBuilderConstants(double length, double width, double maxAngleSpeed, double maxAngleAcceleration, double maxDriveSpeed, double rampRate){
        this.length = length;
        this.width = width;
        this.maxAngleSpeed = maxAngleSpeed;
        this.maxAngleAcceleration = maxAngleAcceleration;
        this.maxDriveSpeed = maxDriveSpeed;
        this.rampRate = rampRate;
    }

    public double getLength(){
        return length;
    }
    public double getWidth(){
        return width;
    }
    public double getMaxAngleSpeed(){
        return maxAngleSpeed;
    }
    public double getMaxAngleAcceleration(){
        return maxAngleAcceleration;
    }
    public double getMaxDriveSpeed(){
        return maxDriveSpeed;
    }
    public double getRampRate(){
        return rampRate;
    }




}
