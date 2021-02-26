// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.Creation;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/** Utilities for creating an object based on a String given */
public class CreationUtil {

    /**
     * Creates a Gyro based on the String given
     * Options:
     * "navX" for the navX gyro 
     * "ADXRS450_Gyro" for the ADXRS450_Gyro 
     * @param gyroType
     * @return Gyro
     */
    public static Gyro createGyro(String gyroType) {
        switch (gyroType) {
            case "navX":
                return new AHRS(edu.wpi.first.wpilibj.SerialPort.Port.kUSB);

            case "ADXRS450_Gyro":
                return new ADXRS450_Gyro(edu.wpi.first.wpilibj.SPI.Port.kMXP);
            default:
                return null;
        }

    }

    /**
     * Creates a motor based on the String given
     * Options: 
     * "TalonFX" for the TalonFX motorController used on the Falcon 500s
     * "CANSparkMax" for the CANSparkMax motorController used on the Neo, Neo 550, etc.
     * @param motorType
     * @param CANID
     * @return Object
     */
    public static Object createMotor(String motorType, int CANID) {
        switch (motorType) {
            case "TalonFX":
                return new WPI_TalonFX(CANID);

            case "CANSparkMax":
                return new CANSparkMax(CANID, MotorType.kBrushless);

            default:
                return null;
        }
    }

}
