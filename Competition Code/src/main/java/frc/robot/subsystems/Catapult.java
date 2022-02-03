// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;
// import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
// import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import static frc.robot.Constants.*;

public class Catapult extends SubsystemBase {

  // Motors
  private WPI_TalonSRX catapult_motor_1;

  // Pistons
  private DoubleSolenoid shooter_p;

  // Constructor
  public Catapult() {
    catapult_motor_1 = new WPI_TalonSRX(SHOOTER_MOTOR_PORT_1);
    shooter_p = new DoubleSolenoid(SHOOTER_PISTON_PORT_1, SHOOTER_PISTON_PORT_2);
    limitMotorCurrents();
  }

  // Reduces the current that the TalonSRX draws in order to prevent brownouts
  public void limitMotorCurrents() {
    // catapult_motor_1.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 20, 21,
    // 1));
    // catapult_motor_1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 20, 21,
    // 1));
  }

  /** @param speed [-1.0, 1.0] */

  // This function sets the speed for the winding catapult motor.
  public void setSpeed() {
    catapult_motor_1.set(ControlMode.PercentOutput, -CATAPULT_SPEED);
  }

  // This function stops the winding catapult motor.
  public void motorsOff() {
    catapult_motor_1.set(ControlMode.PercentOutput, 0);
  }

  // This function pushes the catapult piston forward.
  public void pistonForward() {
    shooter_p.set(DoubleSolenoid.Value.kForward);
  }

  // This function pulls the catapult pistion back.
  public void pistonReverse() {
    shooter_p.set(DoubleSolenoid.Value.kReverse);
  }

  // This function turns the catapult piston off.
  public void pistonOff() {
    shooter_p.set(DoubleSolenoid.Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
