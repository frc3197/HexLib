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

    
    /** 
     * @return int
     */
    // Get angleMotorID
    public int getAngleID() {
        return angleMotorID;
    }

    
    /** 
     * @return int
     */
    // Get speedMotorID
    public int getSpeedID() {
        return speedMotorID;
    }

    
    /** 
     * @return int
     */
    // Get encoderCANID
    public int getEncoderID() {
        return encoderCANID;
    }

    
    /** 
     * @return boolean
     */
    // Get angleMotorInverted
    public boolean getAngleInverted() {
        return angleMotorInverted;
    }

    
    /** 
     * @return boolean
     */
    // Get speedMotorInverted
    public boolean getSpeedInverted() {
        return speedMotorInverted;
    }

    
    /** 
     * @return double
     */
    // Get P
    public double getPID_P_Angle() {
        return swervePID_Angle.getPID_P();
    }

    
    /** 
     * @return double
     */
    // Get I
    public double getPID_I_Angle() {
        return swervePID_Angle.getPID_I();
    }

    
    /** 
     * @return double
     */
    // Get D
    public double getPID_D_Angle() {
        return swervePID_Angle.getPID_D();
    }

    
    /** 
     * @return double
     */
    // Get kS
    public double getFF_kS_Angle() {
        return swervePID_Angle.getPID_kS();
    }

    
    /** 
     * @return double
     */
    // Get kV
    public double getFF_kV_Angle() {
        return swervePID_Angle.getPID_kV();
    }
    
    /** 
     * @return double
     */
    // Get P
    public double getPID_P_Drive() {
        return swervePID_Drive.getPID_P();
    }

    
    /** 
     * @return double
     */
    // Get I
    public double getPID_I_Drive() {
        return swervePID_Drive.getPID_I();
    }

    
    /** 
     * @return double
     */
    // Get D
    public double getPID_D_Drive() {
        return swervePID_Drive.getPID_D();
    }

    
    /** 
     * @return double
     */
    // Get kS
    public double getFF_kS_Drive() {
        return swervePID_Drive.getPID_kS();
    }

    
    /** 
     * @return double
     */
    // Get kV
    public double getFF_kV_Drive() {
        return swervePID_Drive.getPID_kV();
    }

    
    /** 
     * @param isInverted
     */
    // Sets SpeedMotorInverted
    public void setSpeedInverted(boolean isInverted){
        speedMotorInverted = isInverted;
    }
    
    /** 
     * @param isInverted
     */
    // Sets AngleMotorInverted
    public void setAngleInverted(boolean isInverted){
        angleMotorInverted = isInverted;
    }

}
