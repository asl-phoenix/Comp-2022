// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Catapult;

public class ReleaseCatapultCommand extends CommandBase {
  // Creates release catapult for shooting command

  private Catapult CATAPULT;
  public double startTime;

  public ReleaseCatapultCommand(Catapult CATAPULT) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(CATAPULT);
    this.CATAPULT = CATAPULT;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // For shooting we need to reverse the piston.
    CATAPULT.pistonReverse();
    // A variable is created for determining when we want to end command.
    startTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // When the command ends, the piston is returned to its original position.
    // Therefore, the mechanism is now ready for lowering the catapult once more.
    CATAPULT.pistonForward();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // This means that 1 second after the command is initialized, the command will end.
    return (System.currentTimeMillis() - startTime) > 2000;
  }
}
