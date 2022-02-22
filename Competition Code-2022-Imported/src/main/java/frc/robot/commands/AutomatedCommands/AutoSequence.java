// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutomatedCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ReleaseCatapultCommand;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoSequence extends SequentialCommandGroup {
  /** Creates a new autonomous command sequence. */
  public Gyro gyroAuto;

  public AutoSequence(
      Drive drive,
      Gyro gyro,
      Catapult catapult,
      Intake intake) {
    // This is the sequential commands within our autonomous sequence
    gyroAuto = gyro;
    // rotators.resetEncoders();
    addCommands(
        new WaitCommand(5.0),
        new IntakeAuto(intake, true),
        new WaitCommand(2.0),
        // new MoveForward(rotators, spinners, 0.5, 1),
        new WaitCommand(2.0),
        new ReleaseCatapultCommand(catapult, intake),
        new WaitCommand(2.0),
        new LowerCatapultAuto(catapult), // make time set option
        // new MoveForward(rotators, spinners, 1.0, 1),
        new WaitCommand(2.0),
        new RunIntakeAuto(intake, true));
  }
}
