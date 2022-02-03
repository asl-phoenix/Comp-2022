/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;
import frc.robot.commands.AutoCommands.*;

public class RobotContainer {

  // == JOYSTICKS =//
  public final Joystick shopper = new Joystick(DRIVER_CONTROLLER);
  public final Joystick operator = new Joystick(OPERATOR_CONTROLLER);

  // == SUBSYSTEMS == //

  // Drivetrain Subs
  public final SwerveSpinners SWERVESPINNERS = new SwerveSpinners();
  public final SwerveRotaters SWERVEROTATERS = new SwerveRotaters();
  public final Gyro GYRO = new Gyro();

  // Mechanism Subs
  public final Catapult CATAPULT = new Catapult();
  public final Intake INTAKE = new Intake();
  public final Climber CLIMBER = new Climber();

  // Auto Subs Access
  public SwerveSpinners getSpinners() {
    return SWERVESPINNERS;
  }

  public SwerveRotaters getRotaters() {
    return SWERVEROTATERS;
  }

  public Gyro getGyro() {
    return GYRO;
  }

  public Intake getIntake() {
    return INTAKE;
  }

  public Climber getClimber() {
    return CLIMBER;
  }

  public Catapult getCatapult() {
    return CATAPULT;
  }

  // == BUTTONS == //

  // Intake
  public final JoystickButton intakeButton = new JoystickButton(shopper, INTAKE_BUTTON);
  public final JoystickButton outtakeButton = new JoystickButton(shopper, OUTTAKE_BUTTON);
  public final JoystickButton raiseIntakeButton = new JoystickButton(operator, RAISE_INTAKE_BUTTON);
  public final JoystickButton lowerIntakeButton = new JoystickButton(operator, LOWER_INTAKE_BUTTON);

  // Catapult
  public final JoystickButton lowerCatapultButton =
      new JoystickButton(operator, LOWERCATAPULT_BUTTON);
  public final JoystickButton releaseCatapultButton =
      new JoystickButton(operator, RELEASECATAPULT_BUTTON);

  // Climber
  public final JoystickButton extendTelescopingButton =
      new JoystickButton(operator, EXTEND_TELESCOPING_BUTTON);
  public final JoystickButton retractTelescopingButton =
      new JoystickButton(operator, RETRACT_TELESCOPING_BUTTON);
  public final JoystickButton extendSecondaryButton =
      new JoystickButton(operator, EXTEND_SECONDARY_BUTTON);
  public final JoystickButton retractSecondaryButton =
      new JoystickButton(operator, RETRACT_SECONDARY_BUTTON);

  // == COMMANDS == //

  // Intake Commands
  public final Command intakeCommand = new IntakeCommand(INTAKE);
  public final Command outtakeCommand = new OuttakeCommand(INTAKE);
  public final Command raiseIntakeCommand = new RaiseIntakeCommand(INTAKE);
  public final Command lowerIntakeCommand = new LowerIntakeCommand(INTAKE);

  // Catapult Commands
  public final Command releaseCatapultCommand = new ReleaseCatapultCommand(CATAPULT);
  public final Command lowerCatapultCommand = new LowerCatapultCommand(CATAPULT);

  // Climber Commands
  public final Command extendTelescopingCommand = new ExtendTelescopingCommand(CLIMBER);
  public final Command retractTelescopingCommand = new RetractTelescopingCommand(CLIMBER);
  public final Command extendSecondaryCommand = new ExtendSecondaryCommand(CLIMBER);
  public final Command retractSecondaryCommand = new RetractSecondaryCommand(CLIMBER);

  // This constructs the robot container class.
  public RobotContainer() {
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // == BUTTON BINDINGS == //

    // Swervedrive.exe
    SWERVEROTATERS.setDefaultCommand(
        new RunCommand(
            () ->
                SWERVEROTATERS.rotateMotors(
                    shopper.getRawAxis(TRANSLATIONAL_HORIZONTAL_AXIS),
                    shopper.getRawAxis(TRANSLATIONAL_VERTICAL_AXIS),
                    shopper.getRawAxis(ROTATIONAL_HORIZONTAL_AXIS),
                    GYRO.getYaw()),
            SWERVEROTATERS));
    SWERVESPINNERS.setDefaultCommand(
        new RunCommand(
            () ->
                SWERVESPINNERS.spinMotors(
                    shopper.getRawAxis(TRANSLATIONAL_HORIZONTAL_AXIS),
                    shopper.getRawAxis(TRANSLATIONAL_VERTICAL_AXIS),
                    shopper.getRawAxis(ROTATIONAL_HORIZONTAL_AXIS),
                    SWERVEROTATERS.getAngle(
                        shopper.getRawAxis(TRANSLATIONAL_HORIZONTAL_AXIS),
                        shopper.getRawAxis(TRANSLATIONAL_VERTICAL_AXIS),
                        GYRO.getYaw())),
            SWERVESPINNERS));
    GYRO.setDefaultCommand(new RunCommand(() -> GYRO.getState(), GYRO));

    // Catapult
    lowerCatapultButton.whenHeld(lowerCatapultCommand);
    releaseCatapultButton.whenHeld(releaseCatapultCommand);

    // Intake
    intakeButton.whileHeld(intakeCommand);
    outtakeButton.whileHeld(outtakeCommand);
    raiseIntakeButton.whenPressed(raiseIntakeCommand);
    lowerIntakeButton.whenPressed(lowerIntakeCommand);

    // Climber
    extendTelescopingButton.whenHeld(extendTelescopingCommand);
    retractTelescopingButton.whenHeld(retractTelescopingCommand);
    extendSecondaryButton.whenHeld(extendSecondaryCommand);
    retractSecondaryButton.whenHeld(retractSecondaryCommand);
  }
}
