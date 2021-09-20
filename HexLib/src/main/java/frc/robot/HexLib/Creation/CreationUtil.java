// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.HexLib.Creation;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/** Utilities for creating an object based on a String given */
public class CreationUtil {

    /**
     * Creates a Gyro based on the String given Options: "navX" for the navX gyro
     * "ADXRS450_Gyro" for the ADXRS450_Gyro
     * 
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
                System.out.println("Error: Failed Creation - Invalid String Gyro Type");
                return null;
        }

    }

    /**
     * Creates a motor based on the String given Options: "TalonFX" for the TalonFX
     * motorController used on the Falcon 500s "CANSparkMax" for the CANSparkMax
     * motorController used on the Neo, Neo 550, etc.
     * 
     * @param motorType
     * @param CANID
     * @return SpeedController
     */
    public static SpeedController createMotor(String motorType, int CANID) {
        switch (motorType) {
            case "TalonFX":
                return new WPI_TalonFX(CANID);

            case "CANSparkMax":
                return new CANSparkMax(CANID, MotorType.kBrushless);

            default:
                System.out.println("Error: Failed Creation - Invalid String Motor Type");
                return null;
        }
    }

    /**
     * Creates a motor based on the SpeedControllerObject type given Supported
     * Options: TalonFX, CANSparkMax
     * 
     * @param motorType
     * @param CANID
     * @return SpeedController
     */
    public static SpeedController createMotor(SpeedController speedController, int CANID) {
        if (speedController instanceof WPI_TalonFX) {
            // ((WPI_TalonFX)speedController).free();
            return new WPI_TalonFX(CANID);
        } else if (speedController instanceof CANSparkMax) {

            return new CANSparkMax(CANID, MotorType.kBrushless);
        } else {
            System.out.println("Error: Failed Creation - Invalid Object Motor Type");
            return null;
        }

    }

    /**
     * Creates a motor based on the SpeedControllerObject type given and sets up
     * motor parameters Supported Options: TalonFX, CANSparkMax
     * 
     * @param speedController
     * @param CANID
     * @param brakeMode
     * @param rampRate
     * @param inverted
     * @return SpeedController
     */

    @SuppressWarnings({ "resource" })
    public static SpeedController createMotor(SpeedController speedController, int CANID, Object brakeMode,
            double rampRate, boolean inverted) {
        if (speedController instanceof WPI_TalonFX) {

            return setupWPI_TalonFX(new WPI_TalonFX(CANID), (NeutralMode) brakeMode, rampRate, inverted);
        } else if (speedController instanceof CANSparkMax) {
            return setupCANSparkMax(new CANSparkMax(CANID, MotorType.kBrushless), (IdleMode) brakeMode, rampRate,
                    inverted);
        } else {
            System.out.println("Error: Failed Creation - Invalid Type");
            return null;
        }
    }

    /**
     * The purpose of the function is to run the setup methods for the WPI_TalonFX
     * motor and return it
     * 
     * @param motor
     * @param brakeMode
     * @param rampRate
     * @param inverted
     * @return WPI_TalonFX
     */
    private static WPI_TalonFX setupWPI_TalonFX(WPI_TalonFX motor, NeutralMode brakeMode, double rampRate,
            boolean inverted) {
        motor.setNeutralMode(brakeMode);
        motor.configOpenloopRamp(rampRate);
        motor.setInverted(inverted);
        return motor;
    }

    /**
     * The purpose of the function is to run the setup methods for the CANSparkMax
     * motor and return it
     * 
     * @param motor
     * @param brakeMode
     * @param rampRate
     * @param inverted
     * @return CANSparkMax
     */
    private static CANSparkMax setupCANSparkMax(CANSparkMax motor, IdleMode brakeMode, double rampRate,
            boolean inverted) {
        motor.setIdleMode(brakeMode);
        motor.setOpenLoopRampRate(rampRate);
        motor.setInverted(inverted);
        return motor;
    }
}
