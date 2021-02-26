// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DriveTrain.SwerveDrive.SwerveDriveClasses;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveBuilderConstants;

/** Represents a swerve drive style drivetrain. */
public class SwerveDrive implements Subsystem {
    public static SwerveBuilderConstants swerveBuilderConstants;
    public static double maxSpeed = Units.feetToMeters(swerveBuilderConstants.getMaxDriveSpeed());
    public static double maxAngleSpeed = swerveBuilderConstants.getMaxAngleSpeed();
    
    private double x = Units.inchesToMeters(swerveBuilderConstants.getWidth() / 2);
    private double y = Units.inchesToMeters(swerveBuilderConstants.getLength() / 2);
    // Translation from the center of the bot, distance of the wheels to the center.
    private final Translation2d m_frontLeftLocation = new Translation2d(x,y);
    private final Translation2d m_frontRightLocation = new Translation2d(x,-y);
    private final Translation2d m_rearLeftLocation = new Translation2d(-x,y);
    private final Translation2d m_rearRightLocation = new Translation2d(-x,-y);

    // Creates 4 SwerveModule Objects
    public final SwerveModule m_frontRight;
    public final SwerveModule m_frontLeft;
    public final SwerveModule m_rearRight;
    public final SwerveModule m_rearLeft;

    public static Gyro gyro;

    private final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(m_frontLeftLocation,
            m_frontRightLocation, m_rearLeftLocation, m_rearRightLocation);

    private final SwerveDriveOdometry m_odometry = new SwerveDriveOdometry(m_kinematics, gyro.getRotation2d());

    @SuppressWarnings("static-access")
    public SwerveDrive(SwerveModule rearRight,SwerveModule rearLeft,SwerveModule frontRight,SwerveModule frontLeft, SwerveBuilderConstants swerveBuilderConstants, Gyro gyro) {
    m_frontRight = frontRight;
    m_frontLeft = frontLeft;
    m_rearRight = rearRight;
    m_rearLeft = rearLeft;

    this.gyro = gyro;

    this.swerveBuilderConstants = swerveBuilderConstants;

    gyro.reset();
}
@Override
public void periodic() {
  // This method will be called once per scheduler run
  updateOdometry();
}

    //drive command
    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        var swerveModuleStates =
            m_kinematics.toSwerveModuleStates(
                fieldRelative
                    ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, gyro.getRotation2d())
                    : new ChassisSpeeds(xSpeed, ySpeed, rot));
        SwerveDriveKinematics.normalizeWheelSpeeds(swerveModuleStates, maxSpeed);
        m_frontLeft.setDesiredState(swerveModuleStates[0]);
        m_frontRight.setDesiredState(swerveModuleStates[1]);
        m_rearLeft.setDesiredState(swerveModuleStates[2]);
        m_rearRight.setDesiredState(swerveModuleStates[3]);
      }
    
//do things again 
    public void updateOdometry() {
        m_odometry.update(
            gyro.getRotation2d(),
            m_frontLeft.getState(),
            m_frontRight.getState(),
            m_rearLeft.getState(),
            m_rearRight.getState());
      }

    public void resetGyro(){
        gyro.reset();
    }

    public void resetEncoders(){
        m_frontLeft.resetDriveEncoder();
        m_frontRight.resetDriveEncoder();
        m_rearLeft.resetDriveEncoder();
        m_rearRight.resetDriveEncoder();
    }

    public void setVoltageAllMotors(double speed){
m_frontLeft.setVoltageSpeed(speed);        
m_frontRight.setVoltageSpeed(speed);
m_rearLeft.setVoltageSpeed(speed);
m_rearRight.setVoltageSpeed(speed);

    }

}