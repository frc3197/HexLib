// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil;

/** Add your docs here. */
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
     * @return double
     */
    public double getPID_P() {
        return P;
    }

    /**
     * @return double
     */
    public double getPID_I() {
        return I;
    }

    /**
     * @return double
     */
    public double getPID_D() {
        return D;
    }

    /**
     * @return double
     */
    public double getPID_kS() {
        return kS;
    }

    /**
     * @return double
     */
    public double getPID_kV() {
        return kV;
    }

}
