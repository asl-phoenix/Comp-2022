// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.SwerveRotaters;
import frc.robot.subsystems.SwerveSpinners;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoMoveCommand extends SequentialCommandGroup {
  /** Creates a new AutoMoveCommand. */
  public AutoMoveCommand(SwerveRotaters rotators, SwerveSpinners spinners, Gyro gyro, double distance, double angle) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetWheelDirectionsWithGyro(rotators, gyro, angle, angle, angle, angle),
      new MoveForward(spinners, distance)
    );
  }
}
