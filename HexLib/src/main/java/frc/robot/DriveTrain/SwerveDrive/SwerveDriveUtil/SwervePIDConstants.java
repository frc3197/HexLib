// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil;

/** A class created to store PID Constants */
public class SwervePIDConstants {

    private double P, I, D, kS, kV;

    public SwervePIDConstants(double P, double I, double D, double kS, double kV) {
        this.P = P;
        this.I = I;
        this.D = D;

        this.kS = kS;
        this.kV = kV;
    }

    /**
     * Gets the P value
     * @return double
     */
    public double getPID_P() {
        return P;
    }

    /**
     * Gets the I Value
     * @return double
     */
    public double getPID_I() {
        return I;
    }

    /**
     * Gets the D Value
     * @return double
     */
    public double getPID_D() {
        return D;
    }

    /**
     * Gets the kS Value
     * @return double
     */
    public double getPID_kS() {
        return kS;
    }

    /**
     * Gets the kV Value
     * @return double
     */
    public double getPID_kV() {
        return kV;
    }

}
