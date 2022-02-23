// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.cscore.UsbCamera;

public class CameraSwitch extends CommandBase {
  private VideoSink server;
  private UsbCamera camera1, camera2;
  private Boolean camera1Selected;

  public CameraSwitch(
      VideoSink server, UsbCamera camera1, UsbCamera camera2, Boolean camera1Selected) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.server = server;
    this.camera1 = camera1;
    this.camera2 = camera2;
    this.camera1Selected = camera1Selected;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    server.setSource(camera1Selected ? camera2 : camera1);
    frc.robot.Robot.camera1Selected = !frc.robot.Robot.camera1Selected;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
