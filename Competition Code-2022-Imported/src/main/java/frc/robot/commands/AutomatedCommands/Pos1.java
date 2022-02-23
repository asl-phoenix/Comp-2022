// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutomatedCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ReleaseCatapultCommand;
import frc.robot.subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Pos1 extends SequentialCommandGroup {
  /** Creates a new autonomous command sequence. */
  /*
  move, shoot, move, intake, move shoot, */
  public Gyro gyroAuto;

  public Pos1(
      SwerveRotaters rotators,
      SwerveSpinners spinners,
      Gyro gyro,
      Catapult catapult,
      Intake intake) {
    // This is the sequential commands within our autonomous sequence
    gyroAuto = gyro;
    addCommands(
        new SequentialCommandGroup(
            new WaitCommand(3.0),
            new IntakeAuto(intake, true),
            new MoveForward(rotators, spinners, 0.6, 1, 0.4),
            new WaitCommand(0.5),
            new ReleaseCatapultCommand(catapult),
            new WaitCommand(0.5),
            new LowerCatapultAuto(catapult), // make time set option
            new Rotate(rotators, spinners, gyro, 15),
            new ParallelCommandGroup(
                new MoveForward(rotators, spinners, 0.8, 1, 0.4),
                new RunIntakeAuto(intake, true, 3.0)),
            new WaitCommand(2.0),
            new MoveForward(rotators, spinners, 0.4, -1, 0.4),
            new WaitCommand(0.5),
            new Rotate(rotators, spinners, gyro, 9),
            new WaitCommand(0.5),
            new ReleaseCatapultCommand(catapult),
            new Rotate(rotators, spinners, gyro, 120),
            new MoveForward(rotators, spinners, 0.4, 1, 0.2),
            new SetRotatorsfortELEOP(rotators),
            new MoveForward(rotators, spinners, 1.0, 1, 0.7),
            new GyroReset(gyro)));
    /*
    new ParallelCommandGroup(
      new RunIntakeAuto(intake, true),
      new MoveForward(rotators, spinners, 1.0, -1))
      */

  }
}

// move forward, shoot, rotate 15 degrees anti conclic, move direction with 5
