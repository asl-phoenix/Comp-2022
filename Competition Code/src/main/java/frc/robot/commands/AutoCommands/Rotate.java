// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import frc.robot.subsystems.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;

public class Rotate extends CommandBase {

  SwerveRotaters rotators;
  SwerveSpinners spinners;
  Gyro gyro;
  double targetAngle, fR, fL, bR, bL;

  // This command sets the position of the motors to a preset configuration.
  // Then the command rotates the robot untill it reaches a certain angle. 
  public Rotate(SwerveRotaters rotators, SwerveSpinners spinners, Gyro gyro, double targetAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.rotators = rotators;
    this.gyro = gyro;
    this.fR = rotators.angleToPulse(45);
    this.fL = rotators.angleToPulse(135);
    this.bR = rotators.angleToPulse(225);
    this.bL = rotators.angleToPulse(315);
    addRequirements(rotators, spinners);
    this.targetAngle = targetAngle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rotators.setWheelDirection(fR, fL, bR, bL);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rotators.setWheelDirection(fR, fL, bR, bL);
    if(rotators.reachedPosition(fR, fL, bR, bL)){
      spinners.autoRunSpinners(AUTO_ROTATE_SPEED);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    rotators.stop();
    spinners.stop();
    System.out.println("Finished");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ((((targetAngle-ANGLE_ERROR_TOLERANCE)%360)<=gyro.getYaw())&&(gyro.getYaw()<=((targetAngle+ANGLE_ERROR_TOLERANCE)%360)));
  }
}
