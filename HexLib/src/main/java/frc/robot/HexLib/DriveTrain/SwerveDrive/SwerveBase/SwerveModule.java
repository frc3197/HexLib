// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveBase;

/** Creates a Swerve Module Object */

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HexLib.Creation.*;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveBuilderConstants;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveModuleConstants;

public class SwerveModule extends SubsystemBase {
  // Measurments are all done in Meters.
  private SwerveBuilderConstants swerveBuilderConstants;

  private static double kModuleMaxAngularVelocity;
  private static double kModuleMaxAngularAcceleration;

  private final SpeedController speed_motor;
  private final SpeedController angle_motor;

  private SwerveModuleConstants swerveModuleConstants;
  private final CANCoder encoder;
  private PIDController m_drivePIDController;

  private final ProfiledPIDController m_turningPIDController;

  private final SimpleMotorFeedforward m_driveFeedforward;
  private final SimpleMotorFeedforward m_turnFeedforward;

  public SwerveModule(SwerveModuleConstants swerveModuleConstants, SwerveBuilderConstants swerveBuilderConstants) {
    this.swerveModuleConstants = swerveModuleConstants;
    this.swerveBuilderConstants = swerveBuilderConstants;

    angle_motor = CreationUtil.createMotor(swerveBuilderConstants.getAngleMotorType(),
        swerveModuleConstants.getAngleID(), swerveBuilderConstants.getAngleBrakeMode(),
        swerveBuilderConstants.getRampRate(), swerveModuleConstants.getAngleInverted());
    speed_motor = CreationUtil.createMotor(swerveBuilderConstants.getDriveMotorType(),
        swerveModuleConstants.getSpeedID(), swerveBuilderConstants.getDriveBrakeMode(),
        swerveBuilderConstants.getRampRate(), swerveModuleConstants.getSpeedInverted());

    kModuleMaxAngularVelocity = swerveBuilderConstants.getMaxAngleSpeed();
    kModuleMaxAngularAcceleration = swerveBuilderConstants.getMaxAngleAcceleration();

    m_turningPIDController = new ProfiledPIDController(swerveModuleConstants.getPID_P_Angle(),
        swerveModuleConstants.getPID_I_Angle(), swerveModuleConstants.getPID_D_Angle(),
        new TrapezoidProfile.Constraints(kModuleMaxAngularVelocity, kModuleMaxAngularAcceleration));

    m_drivePIDController = new PIDController(swerveModuleConstants.getPID_P_Drive(),
        swerveModuleConstants.getPID_I_Drive(), swerveModuleConstants.getPID_D_Drive());

    m_driveFeedforward = new SimpleMotorFeedforward(swerveModuleConstants.getFF_kS_Drive(),
        swerveModuleConstants.getFF_kV_Drive());
    m_turnFeedforward = new SimpleMotorFeedforward(swerveModuleConstants.getFF_kS_Angle(),
        swerveModuleConstants.getFF_kS_Angle());
    encoder = new CANCoder(swerveModuleConstants.getEncoderID());

    m_turningPIDController.setTolerance(0);
    m_drivePIDController.setTolerance(3);
    m_turningPIDController.enableContinuousInput(-Math.PI, Math.PI);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  /**
   * Returns the RPM of the SpeedMotor
   * 
   * @return double
   */
  public double getRPM() {
    if (speed_motor instanceof WPI_TalonFX) {
      // By default the WPI_TalonFX returns 2048 ticks per 100 ms
      return ((WPI_TalonFX) speed_motor).getSelectedSensorVelocity() / Constants.getTalonEncoderResolution() * 10 * 60;
    } else {
      // By default the CANSparkMax returns RPM by default
      return ((CANSparkMax) speed_motor).getEncoder().getVelocity();

    }
  }

  /**
   * Returns the Encoder Velocity in Meters/Sec
   * 
   * @return double
   */
  public double getSpeedEncoderRate() {
    double driveUnitsPer100ms, encoderRate, driveRPM;
    if (angle_motor instanceof WPI_TalonFX) {
      // Pulls the integrated sensor velocity
      driveUnitsPer100ms = ((WPI_TalonFX) angle_motor).getSelectedSensorVelocity();
      // Converts the encoder rate to meters per second
      encoderRate = driveUnitsPer100ms / Constants.getTalonEncoderResolution() * 10
          * (swerveBuilderConstants.getWheelDiam()*Math.PI) * swerveBuilderConstants.getDriveGearRatio();
    } else {
      // Pulls the integrated sensor velocity
      driveRPM = ((CANSparkMax) angle_motor).getEncoder().getVelocity();
      // Converts the encoder rate to meters per second
      encoderRate = driveRPM / 60 * (swerveBuilderConstants.getWheelDiam() * Math.PI) * swerveBuilderConstants.getDriveGearRatio();
    }
    
    return encoderRate;
  }

  /**
   * Returns the current position of the angle motor in radians
   * 
   * @return double
   */
  public double getAngleRadians() {
    return Units.degreesToRadians(encoder.getAbsolutePosition());
  }

  /**
   * Resets the Encoder of the Drive motor to 0
   */
  public void resetDriveEncoder() {
    if (speed_motor instanceof WPI_TalonFX) {
      ((WPI_TalonFX) speed_motor).setSelectedSensorPosition(0);
    } else {
      ((CANSparkMax) speed_motor).getEncoder().setPosition(0);
    }

  }

  /**
   * Sets the voltage of the Motor
   * 
   * @param voltage
   */
  public void setVoltageSpeed(double voltage) {
    speed_motor.setVoltage(voltage);
  }

  /**
   * Returns the Constants of the module
   * 
   * @return SwerveModuleConstants
   */
  public SwerveModuleConstants getConstants() {
    return swerveModuleConstants;
  }

  /**
   * Returns the State of the module
   * 
   * @return SwerveModuleState
   */
  public SwerveModuleState getState() {
    return new SwerveModuleState(-getSpeedEncoderRate(), new Rotation2d(getAngleRadians()));
  }

  /**
   * Returns the optimized SwerveModuleState to make all movements under 90
   * degrees
   * 
   * @param desiredState
   * @param currentAngle
   * @return SwerveModuleState
   */
  public SwerveModuleState optimize(SwerveModuleState desiredState, Rotation2d currentAngle) {
    var delta = desiredState.angle.minus(currentAngle);
    if (Math.abs(delta.getDegrees()) > 90.0) {
      return new SwerveModuleState(-desiredState.speedMetersPerSecond,
          desiredState.angle.rotateBy(Rotation2d.fromDegrees(180.0)));
    } else {
      return new SwerveModuleState(desiredState.speedMetersPerSecond, desiredState.angle);
    }
  }

  /**
   * Sets the desired state of the SwerveModule
   * 
   * @param desiredState
   */
  public void setDesiredState(SwerveModuleState desiredState) {

    SwerveModuleState state = optimize(desiredState, new Rotation2d(getAngleRadians()));

    // Calculate the drive output from the drive PID controller.
    final double driveOutput = m_drivePIDController.calculate(getSpeedEncoderRate(), state.speedMetersPerSecond);

    final double driveFeedforward = m_driveFeedforward.calculate(state.speedMetersPerSecond);
    SmartDashboard.putNumber("driveOSpeed",state.speedMetersPerSecond);
    // Calculate the turning motor output from the turning PID controller.
    final double turnOutput = m_turningPIDController.calculate(getAngleRadians(), state.angle.getRadians());
      SmartDashboard.putNumber("turngetRadians", state.angle.getRadians());
    final double turnFeedforward = m_turnFeedforward.calculate(m_turningPIDController.getSetpoint().velocity);
      SmartDashboard.putNumber("TurnFeedForward", turnFeedforward);
    speed_motor.setVoltage((driveOutput + driveFeedforward) * RobotController.getBatteryVoltage());
      SmartDashboard.putNumber("DriveOutput", driveOutput);
    angle_motor.setVoltage((turnOutput + turnFeedforward) * RobotController.getBatteryVoltage());
      SmartDashboard.putNumber("DriveFeedForward", driveFeedforward);
  }

}
