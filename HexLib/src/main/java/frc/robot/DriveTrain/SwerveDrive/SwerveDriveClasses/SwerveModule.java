// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


/** 
* @author Yuri Kleyman - FRC 3197 2021
* @version 1.0.0
*/
package frc.robot.DriveTrain.SwerveDrive.SwerveDriveClasses;

/** Creates a Swerve Module Object */

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveBuilderConstants;
import frc.robot.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveModuleConstants;

public class SwerveModule extends SubsystemBase {
  // Measurments are all done in Meters.
  private static SwerveBuilderConstants swerveBuilderConstants;

  private static final double kModuleMaxAngularVelocity = swerveBuilderConstants.getMaxAngleSpeed();
  private static final double kModuleMaxAngularAcceleration = swerveBuilderConstants.getMaxAngleAcceleration();


  private final WPI_TalonFX speed_motor;
  private final WPI_TalonFX angle_motor;

  private SwerveModuleConstants swerveModuleConstants;
  private final CANCoder encoder;
  private PIDController m_drivePIDController;

  private final ProfiledPIDController m_turningPIDController;

  private final SimpleMotorFeedforward m_driveFeedforward;
  private final SimpleMotorFeedforward m_turnFeedforward;

  @SuppressWarnings("static-access")
  public SwerveModule(SwerveModuleConstants swerveModuleConstants, SwerveBuilderConstants swerveBuilderConstants) {
    angle_motor = new WPI_TalonFX(swerveModuleConstants.getAngleID());
    speed_motor = new WPI_TalonFX(swerveModuleConstants.getSpeedID());
    this.swerveModuleConstants = swerveModuleConstants;
    this.swerveBuilderConstants = swerveBuilderConstants;

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

    angle_motor.configOpenloopRamp(swerveBuilderConstants.getRampRate());
    speed_motor.configOpenloopRamp(swerveBuilderConstants.getRampRate());
    angle_motor.setNeutralMode(NeutralMode.Brake);
    speed_motor.setNeutralMode(NeutralMode.Brake);

    angle_motor.setInverted(swerveModuleConstants.getAngleInverted());
    speed_motor.setInverted(swerveModuleConstants.getSpeedInverted());
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
   * @return double
   */
  public double getRPM() {
    return speed_motor.getSelectedSensorVelocity() / Constants.talonEncoderResolution * 10 * 60;
  }

  /**
   * Returns the Encoder Velocity in Meters/Sec
   * @return double
   */
  public double getSpeedEncoderRate() {
    // Pulls the integrated sensor velocity
    double driveUnitsPer100ms = angle_motor.getSelectedSensorVelocity();
    // Converts the encoder rate to meters per second
    double encoderRate = driveUnitsPer100ms / Constants.talonEncoderResolution * 10 * Constants.swerveWheelDiam
        * Constants.swerveDriveMotorGR;
    return encoderRate;
  }

  /**
   * Returns the current position of the angle motor in radians
   * @return double
   */
  public double getAngleRadians() {
    return Units.degreesToRadians(encoder.getAbsolutePosition());
  }
  /**
   * Resets the Encoder of the Drive motor to 0
   */
  public void resetDriveEncoder() {
    speed_motor.setSelectedSensorPosition(0);
  }

  /**
   * Sets the voltage of the Motor
   * @param voltage
   */
  public void setVoltageSpeed(double voltage) {
    speed_motor.setVoltage(voltage);
  }

  /**
   * Returns the Constants of the module
   * @return SwerveModuleConstants
   */
  public SwerveModuleConstants getConstants() {
    return swerveModuleConstants;
  }

  /**
   * Returns the State of the module
   * @return SwerveModuleState
   */
  public SwerveModuleState getState() {
    return new SwerveModuleState(getSpeedEncoderRate(), new Rotation2d(getAngleRadians()));
  }

  /**
   * Returns the optimized SwerveModuleState to make all movements under 90 degrees
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
   * @param desiredState
   */
  public void setDesiredState(SwerveModuleState desiredState) {

    SwerveModuleState state = optimize(desiredState, new Rotation2d(getAngleRadians()));

    // Calculate the drive output from the drive PID controller.
    final double driveOutput = m_drivePIDController.calculate(getSpeedEncoderRate(), state.speedMetersPerSecond);

    final double driveFeedforward = m_driveFeedforward.calculate(state.speedMetersPerSecond);

    // Calculate the turning motor output from the turning PID controller.
    final double turnOutput = m_turningPIDController.calculate(getAngleRadians(), state.angle.getRadians());

    final double turnFeedforward = m_turnFeedforward.calculate(m_turningPIDController.getSetpoint().velocity);

    speed_motor.setVoltage((driveOutput + driveFeedforward) * RobotController.getBatteryVoltage());

    angle_motor.setVoltage((turnOutput + turnFeedforward) * RobotController.getBatteryVoltage());
  }

}
