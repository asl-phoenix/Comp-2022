// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import frc.robot.subsystems.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveRotaters;

public class SetWheelDirectionsWithGyro extends CommandBase {

  SwerveRotaters rotators;
  Gyro gyro;
  double fR, fL, bR, bL;

  /** Creates a new SetWheelDirections. */
  public SetWheelDirectionsWithGyro(SwerveRotaters rotators, Gyro gyro, double fR, double fL, double bR, double bL) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.rotators = rotators;
    this.gyro = gyro;
    this.fR = rotators.angleToPulse(fR, gyro.getYaw());
    this.fL = rotators.angleToPulse(fL, gyro.getYaw());
    this.bR = rotators.angleToPulse(bR, gyro.getYaw());
    this.bL = rotators.angleToPulse(bL, gyro.getYaw());
    addRequirements(rotators);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rotators.setWheelDirection(fR, fL, bR, bL);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //rotators.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return rotators.reachedPosition(fR, fL, bR, bL);
  }
}
