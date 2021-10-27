// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.HexLib.Creation.SwerveDriveBuilder;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveBase.SwerveDrive;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveBuilderConstants;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveModuleConstants;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwerveModuleConstantsGroup;
import frc.robot.HexLib.DriveTrain.SwerveDrive.SwerveDriveUtil.SwervePIDConstants;
import frc.robot.HexLib.Util.FilteredController;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  SwervePIDConstants rearLeftPIDDrive = new SwervePIDConstants(0, 0, 0, 0, 0);
  SwervePIDConstants rearLeftPIDAngle = new SwervePIDConstants(0, 0, 0, 0, 0);

  SwervePIDConstants rearRightPIDDrive = new SwervePIDConstants(0, 0, 0, 0, 0);
  SwervePIDConstants rearRightPIDAngle = new SwervePIDConstants(0, 0, 0, 0, 0);

  SwervePIDConstants frontLeftPIDDrive = new SwervePIDConstants(0, 0, 0, 0, 0);
  SwervePIDConstants frontLeftPIDAngle = new SwervePIDConstants(0, 0, 0, 0, 0);

  SwervePIDConstants frontRightPIDDrive = new SwervePIDConstants(0, 0, 0, 0, 0);
  SwervePIDConstants frontRightPIDAngle = new SwervePIDConstants(0, 0, 0, 0, 0);

  SwerveModuleConstants frontLeftConstants = new SwerveModuleConstants(4, 5, 2, false, false, frontLeftPIDDrive,
      frontLeftPIDAngle);
  SwerveModuleConstants rearLeftConstants = new SwerveModuleConstants(2, 3, 1, false, false, rearLeftPIDDrive,
      rearLeftPIDAngle);
  SwerveModuleConstants frontRightConstants = new SwerveModuleConstants(6, 7, 3, false, false, frontRightPIDDrive,
      frontRightPIDAngle);
  SwerveModuleConstants rearRightConstants = new SwerveModuleConstants(0, 1, 0, false, false, rearRightPIDDrive,
      rearRightPIDAngle);

  SwerveModuleConstantsGroup swerveModuleConstantsGroup = new SwerveModuleConstantsGroup(frontLeftConstants,
      frontRightConstants, rearLeftConstants, rearRightConstants);

  SwerveBuilderConstants swerveBuilderConstants = new SwerveBuilderConstants(26.125, 22, 6*Math.PI, 22.5*Math.PI, Units.feetToMeters(32.5), .035, Units.inchesToMeters(4), 1/6.86, NeutralMode.Brake, NeutralMode.Brake,new WPI_TalonFX(20), new WPI_TalonFX(20));

  SwerveDriveBuilder swerveDriveBuilder = new SwerveDriveBuilder(swerveModuleConstantsGroup, swerveBuilderConstants,
      "navX");

  SwerveDrive swerveDrive = SwerveDriveBuilder.buildSwerve(swerveDriveBuilder);

  FilteredController controller = new FilteredController(new XboxController(0));

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    swerveDrive.drive(controller.getX(Hand.kLeft, .1), controller.getY(Hand.kLeft, .1),
        controller.getX(Hand.kRight, .1), true);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
