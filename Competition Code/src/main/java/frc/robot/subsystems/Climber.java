// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.DemandType;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
// import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  /** These are the variables that are created for this subsytem.. */
  public WPI_TalonSRX telescope1, telescope2, secondary1, secondary2;

  // This is the constructor for the Climber.
  public Climber() {
    telescope1 = new WPI_TalonSRX(TELESCOPE_PORT_1);
    telescope2 = new WPI_TalonSRX(TELESCOPE_PORT_2);
    secondary1 = new WPI_TalonSRX(SECONDARY_PORT_1);
    secondary2 = new WPI_TalonSRX(SECONDARY_PORT_2);
    offTelescoping();
    offSecondary();
  }

  // This method supplies a certain output to the telescoping motors.
  public void supplyTelescoping(double power) {
    telescope1.set(ControlMode.PercentOutput, power);
    telescope2.set(ControlMode.PercentOutput, power);
  }

  // This method supplies a certain percent output to the secondary motors.
  public void supplySecondary(double power) {
    secondary1.set(ControlMode.PercentOutput, power);
    secondary2.set(ControlMode.PercentOutput, power);
  }

  // This method turns off the telescoping arms' motors.
  public void offTelescoping() {
    telescope1.set(ControlMode.PercentOutput, 0);
    telescope2.set(ControlMode.PercentOutput, 0);
  }

  // This method turns off the secondary arms' motors.
  public void offSecondary() {
    secondary1.set(ControlMode.PercentOutput, 0);
    secondary2.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
