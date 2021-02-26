// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class SwerveModuleConstants {

    private int angleMotorID;
    private int speedMotorID;
    private int encoderCANID;

    private boolean angleMotorInverted, speedMotorInverted;

    private double P, I, D, kS, kV;

    // Used to enumerate all constants for swerve
    public SwerveModuleConstants(int angleMotorID, int speedMotorID, int encoderCANID, boolean angleMotorInverted,
            boolean speedMotorInverted, double P, double I, double D, double kS, double kV) {
        this.angleMotorID = angleMotorID;
        this.speedMotorID = speedMotorID;
        this.encoderCANID = encoderCANID;

        this.angleMotorInverted = angleMotorInverted;
        this.speedMotorInverted = speedMotorInverted;

        this.P = P;
        this.I = I;
        this.D = D;

        this.kS = kS;
        this.kV = kV;

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
    public double getPID_P() {
        return P;
    }

    // Get I
    public double getPID_I() {
        return I;
    }

    // Get D
    public double getPID_D() {
        return D;
    }

    // Get kS
    public double getFF_kS() {
        return kS;
    }

    // Get kV
    public double getFF_kV() {
        return kV;
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
