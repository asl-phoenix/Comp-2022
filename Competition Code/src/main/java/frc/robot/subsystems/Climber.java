// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  /** These are the variables that are created for this subsytem.. */

  public WPI_TalonSRX telescope1, telescope2, secondary1, secondary2;

  public Climber() {
      telescope1 = new WPI_TalonSRX(TELESCOPE_PORT_1);
      telescope2 = new WPI_TalonSRX(TELESCOPE_PORT_2);
      secondary1 = new WPI_TalonSRX(SECONDARY_PORT_1);
      secondary2 = new WPI_TalonSRX(SECONDARY_PORT_2);
  }

  public void extendTelescoping(){
      telescope1.set(ControlMode.PercentOutput, TELESCOPING_SPEED);
      telescope2.set(ControlMode.PercentOutput, TELESCOPING_SPEED);
  }

  public void retractTelescoping(){
      telescope1.set(ControlMode.PercentOutput, -TELESCOPING_SPEED);
      telescope2.set(ControlMode.PercentOutput, -TELESCOPING_SPEED);
  }

  public void extendSecondary(){
      secondary1.set(ControlMode.PercentOutput, SECONDARY_SPEED);
      secondary2.set(ControlMode.PercentOutput, SECONDARY_SPEED);
  }

  public void retractSecondary(){
      secondary1.set(ControlMode.PercentOutput, -SECONDARY_SPEED);
      secondary2.set(ControlMode.PercentOutput, -SECONDARY_SPEED);
  }

  public void offTelescoping(){
      telescope1.set(ControlMode.PercentOutput, 0);
      telescope2.set(ControlMode.PercentOutput, 0);
  }

  public void offSecondary(){
      secondary1.set(ControlMode.PercentOutput, 0);
      secondary2.set(ControlMode.PercentOutput, 0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
