/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AutomatedCommands.*;
import frc.robot.commands.ReleaseCatapultCommand;

import frc.robot.commands.*;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import static frc.robot.Constants.*;

import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private Command m_autonomousCommand;

  private final TalonFX BRR = new TalonFX(8);

  private RobotContainer rCon;
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();


  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    CameraServer.startAutomaticCapture();
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.


    var BRR = new TalonFX(BACK_RIGHT_MODULE_STEER_MOTOR);
    var BLR = new TalonFX(BACK_LEFT_MODULE_STEER_MOTOR);
    var FRR = new TalonFX(FRONT_RIGHT_MODULE_STEER_MOTOR);
    var FLR = new TalonFX(FRONT_LEFT_MODULE_STEER_MOTOR);

    BRR.setSelectedSensorPosition(0, 0, 20);
    BLR.setSelectedSensorPosition(0, 0, 20);
    FRR.setSelectedSensorPosition(0, 0, 20);
    FLR.setSelectedSensorPosition(0, 0, 20);

    rCon = new RobotContainer();
    // SmartDashboard.putData("Auto choices", autoChooser);
    // SmartDashboard.putNumber("Auto Wait Time", 0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  /*
   * public void getAutoCommand() {
   * m_autonomousCommand = autoChooser.getSelected();
   * }
   */

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    System.out.println(BRR.getSelectedSensorPosition());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
  
  public void initailizeAutoChooser() {
    // TODO figure out what extention WPI lib likes
    // climbChild = this.rCon.getDrive().autoPath("Blue1p1.wpilib.json");s

    autoChooser.addOption("Red Hangar", getAuto(AutoName.RED_HANGAR));
    autoChooser.addOption("Red Human", getAuto(AutoName.RED_HUMAN));
    autoChooser.addOption("Red Middle", getAuto(AutoName.RED_MIDDLE));
    autoChooser.addOption("Blue Hangar", getAuto(AutoName.BLUE_HANGAR));
    autoChooser.addOption("Blue Human", getAuto(AutoName.BLUE_HUMAN));
    autoChooser.addOption("Blue Middle", getAuto(AutoName.BLUE_MIDDLE));
    autoChooser.addOption("Nothing", getAuto(AutoName.NOTHING));
    autoChooser.addOption("Exit", getAuto(AutoName.EXIT));
    autoChooser.addOption("Threeball Red", getAuto(AutoName.THREEBALL_RED));
    autoChooser.addOption("Threeball Blue", getAuto(AutoName.THREEBALL_BLUE));
  }
  
  enum AutoName {
    RED_HANGAR, RED_HUMAN, RED_MIDDLE, BLUE_HANGAR, BLUE_HUMAN, BLUE_MIDDLE ,NOTHING, EXIT, THREEBALL_RED, THREEBALL_BLUE
  }

  SequentialCommandGroup twoBallAuto(String path) {
    var intake = rCon.getIntake();
    var catapult = rCon.getCatapult();
    var drive = rCon.getDrive();

    var fireCatapult = new ReleaseCatapultCommand(catapult, intake);
    var intakeOn = new IntakeAuto(intake, true);
    var lowerCatapult = new LowerCatapultAuto(catapult);
    var wait = new WaitCommand(0.5);

   return new SequentialCommandGroup(
      intakeOn,wait, fireCatapult,wait, lowerCatapult, drive.autoPath(path + "1.path"), wait, drive.autoPath(path + "2.path"), fireCatapult, drive.autoPath(path + "3.path")
    ); 
  }

  Command getAuto(AutoName pos) {

    var intake = rCon.getIntake();
    var catapult = rCon.getCatapult();
    var drive = rCon.getDrive();

    var fireCatapult = new ReleaseCatapultCommand(catapult, intake);
    var intakeOn = new IntakeAuto(intake, true);
    var lowerCatapult = new LowerCatapultAuto(catapult);
    var wait = new WaitCommand(0.5);
    

    switch (pos) {

      case BLUE_HANGAR:
        return twoBallAuto("pathplanner/BlueHangar");
      case BLUE_HUMAN:
        return twoBallAuto("pathplanner/BlueHuman");
      case BLUE_MIDDLE:
        return twoBallAuto("pathplanner/BlueMiddle");
      case RED_HANGAR:
        return twoBallAuto("pathplanner/RedHangar");
      case RED_HUMAN:
        return twoBallAuto("pathplanner/RedHuman");
      case RED_MIDDLE:
        return twoBallAuto("pathplanner/RedMiddle");
      case THREEBALL_BLUE:
        return new SequentialCommandGroup(intakeOn, wait, fireCatapult, wait, lowerCatapult, drive.autoPath("BlueThreeBall1.path"), wait, drive.autoPath("BlueThreeBall2.path"), fireCatapult, wait, lowerCatapult, drive.autoPath("BlueThreeBall3.path"), wait, drive.autoPath("BlueThreeBall4.path"),fireCatapult,wait,lowerCatapult,drive.autoPath("BlueThreeBall5.path"));
      case THREEBALL_RED:
        return new SequentialCommandGroup(intakeOn, wait, fireCatapult, wait, lowerCatapult, drive.autoPath("RedThreeBall1.path"), wait, drive.autoPath("RedThreeBall2.path"), fireCatapult, wait, lowerCatapult, drive.autoPath("RedThreeBall3.path"), wait, drive.autoPath("RedThreeBall4.path"),fireCatapult,wait,lowerCatapult,drive.autoPath("RedThreeBall5.path"));
      // case EXIT:
      //   break;
      case NOTHING:
        return new WaitCommand(15);
      default:
        return new WaitCommand(15);

        
    }
  }
}
