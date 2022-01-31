// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSpinners;

public class MoveForward extends CommandBase {
  SwerveSpinners spinners;
  double pulsesDistance;

  /** Creates a new moveTo. */
  public MoveForward(SwerveSpinners spinners, double moveDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(spinners);
    this.spinners = spinners;
    this.pulsesDistance = spinners.cmToPulses(moveDistance);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    spinners.resetEncoders();
    spinners.driveDistance(pulsesDistance);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    spinners.stop();
    System.out.println("Finished");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return spinners.reachedPosition(pulsesDistance);
  }
}
