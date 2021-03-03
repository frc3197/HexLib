// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil;

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

    
    /** 
     * Gets the CAN ID of the AngleMotor
     * @return int
     */
    public int getAngleID() {
        return angleMotorID;
    }

    
    /** 
     * Gets the CAN ID of the SpeedMotor
     * @return int
     */
    public int getSpeedID() {
        return speedMotorID;
    }

    
    /** 
     * Gets the CAN ID of the Encoder
     * @return int
     */

    public int getEncoderID() {
        return encoderCANID;
    }

    
    /** 
     * Gets the boolean representing whether the angleMotor is inverted or not
     * @return boolean
     */
    public boolean getAngleInverted() {
        return angleMotorInverted;
    }

    
    /** 
     * Gets the boolean representing whether the speedMotor is inverted or not
     * @return boolean
     */
    // Get speedMotorInverted
    public boolean getSpeedInverted() {
        return speedMotorInverted;
    }

    
    /** 
     * Gets the P of the AngleMotor
     * @return double
     */
    public double getPID_P_Angle() {
        return swervePID_Angle.getPID_P();
    }

    
    /** 
     * Gets the I of the AngleMotor
     * @return double
     */
    public double getPID_I_Angle() {
        return swervePID_Angle.getPID_I();
    }

    
    /** 
     * Gets the D of the AngleMotor
     * @return double
     */
    public double getPID_D_Angle() {
        return swervePID_Angle.getPID_D();
    }

    
    /** 
     * Gets the kS of the AngleMotor
     * @return double
     */
    public double getFF_kS_Angle() {
        return swervePID_Angle.getPID_kS();
    }

    
    /** 
     * Gets the kV of the AngleMotor
     * @return double
     */
    public double getFF_kV_Angle() {
        return swervePID_Angle.getPID_kV();
    }
    
    /** 
     * Gets the P of the DriveMotor
     * @return double
     */
    public double getPID_P_Drive() {
        return swervePID_Drive.getPID_P();
    }

    
    /** 
     * Gets the I of the DriveMotor
     * @return double
     */
    public double getPID_I_Drive() {
        return swervePID_Drive.getPID_I();
    }

    
    /** 
     * Gets the D of the DriveMotor
     * @return double
     */
    public double getPID_D_Drive() {
        return swervePID_Drive.getPID_D();
    }

    
    /** 
     * Gets the kS of the DriveMotor
     * @return double
     */
    public double getFF_kS_Drive() {
        return swervePID_Drive.getPID_kS();
    }

    
    /** 
     * Gets the kV of the DriveMotor
     * @return double
     */
    public double getFF_kV_Drive() {
        return swervePID_Drive.getPID_kV();
    }

    
    /** 
     * Sets the boolean representing whether the SpeedMotor is inverted or not
     * @param isInverted
     */
    public void setSpeedInverted(boolean isInverted){
        speedMotorInverted = isInverted;
    }
    
    /** 
     * Sets the boolean representing whether the AngleMotor is inverted or not
     * @param isInverted
     */
    public void setAngleInverted(boolean isInverted){
        angleMotorInverted = isInverted;
    }

}
