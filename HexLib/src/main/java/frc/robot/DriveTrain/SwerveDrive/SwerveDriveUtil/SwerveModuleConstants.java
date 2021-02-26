// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil;

/** Add your docs here. */
public class SwerveModuleConstants {

    private int angleMotorID;
    private int speedMotorID;
    private int encoderCANID;

    private boolean angleMotorInverted, speedMotorInverted;

    private SwervePIDConstants swervePID_Drive;
    private SwervePIDConstants swervePID_Angle;

    // Used to enumerate all constants for swerve
    public SwerveModuleConstants(int angleMotorID, int speedMotorID, int encoderCANID, boolean angleMotorInverted,
            boolean speedMotorInverted, SwervePIDConstants swervePID_Drive, SwervePIDConstants swervePID_Angle) {
        this.angleMotorID = angleMotorID;
        this.speedMotorID = speedMotorID;
        this.encoderCANID = encoderCANID;

        this.angleMotorInverted = angleMotorInverted;
        this.speedMotorInverted = speedMotorInverted;

        this.swervePID_Angle = swervePID_Angle;
        this.swervePID_Drive = swervePID_Drive;

    }

    // Get angleMotorID
    public int getAngleID() {
        return angleMotorID;
    }

    // Get speedMotorID
    public int getSpeedID() {
        return speedMotorID;
    }

    // Get encoderCANID
    public int getEncoderID() {
        return encoderCANID;
    }

    // Get angleMotorInverted
    public boolean getAngleInverted() {
        return angleMotorInverted;
    }

    // Get speedMotorInverted
    public boolean getSpeedInverted() {
        return speedMotorInverted;
    }

    // Get P
    public double getPID_P_Angle() {
        return swervePID_Angle.getPID_P();
    }

    // Get I
    public double getPID_I_Angle() {
        return swervePID_Angle.getPID_I();
    }

    // Get D
    public double getPID_D_Angle() {
        return swervePID_Angle.getPID_D();
    }

    // Get kS
    public double getFF_kS_Angle() {
        return swervePID_Angle.getPID_kS();
    }

    // Get kV
    public double getFF_kV_Angle() {
        return swervePID_Angle.getPID_kV();
    }
    // Get P
    public double getPID_P_Drive() {
        return swervePID_Drive.getPID_P();
    }

    // Get I
    public double getPID_I_Drive() {
        return swervePID_Drive.getPID_I();
    }

    // Get D
    public double getPID_D_Drive() {
        return swervePID_Drive.getPID_D();
    }

    // Get kS
    public double getFF_kS_Drive() {
        return swervePID_Drive.getPID_kS();
    }

    // Get kV
    public double getFF_kV_Drive() {
        return swervePID_Drive.getPID_kV();
    }

    // Sets SpeedMotorInverted
    public void setSpeedInverted(boolean isInverted){
        speedMotorInverted = isInverted;
    }
    // Sets AngleMotorInverted
    public void setAngleInverted(boolean isInverted){
        angleMotorInverted = isInverted;
    }

}
