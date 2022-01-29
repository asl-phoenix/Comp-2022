// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSpinners;

public class MoveForwardBetter extends CommandBase {
  SwerveSpinners swerve;
  double d;
  long time;

  /** Creates a new moveTo. */
  public MoveForwardBetter(SwerveSpinners swerve, double d) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);
    this.swerve = swerve;
    this.d = swerve.cmToPulses(d);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    swerve.turnOn();
    time = System.currentTimeMillis();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    time += System.currentTimeMillis(); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //swerve.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (time > 5000);
  }
}
