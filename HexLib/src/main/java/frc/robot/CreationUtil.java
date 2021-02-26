// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/** Add your docs here. */
public class CreationUtil {

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
