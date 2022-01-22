// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import frc.robot.Constants;

public class Catapult extends SubsystemBase {
  /** Creates a new Shooter. */

  //Motors
  private WPI_TalonSRX catapult_motor_1, catapult_motor_2

  //P
  private DoubleSolenoid shooter_p;

  //Constant Variables


  public Catapult(){
    catapult_motor_1 = new WPI_TalonSRX(Constants.SHOOTER_MOTOR_PORT_1);
    catapult_motor_2 = new WPI_TalonSRX(Constants.SHOOTER_MOTOR_PORT_2);
    shooter_p= new DoubleSolenoid(Constants.SHOOTER_PISTON_PORT_1, Constants.SHOOTER_PISTON_PORT_2);
    limitMotorCurrents();
  }
  //MOTOR

  //Reduces the current that the TalonSRX draws in order to prevent brownouts
  public void limitMotorCurrents(){
    shooter_motor_1.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 20, 21, 1));
    shooter_motor_1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 20, 21, 1));

    shooter_motor_2.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 20, 21, 1));
    shooter_motor_2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 20, 21, 1));
  }

  /**
   *  @param speed [-1.0, 1.0]
   */
  public void setSpeed(double speed){
    shooter_motor_1.set(ControlMode.PercentOutput, speed);
    shooter_motor_2.set(ControlMode.PercentOutput, speed);
  }
  //PUMP

  public void blockCatapult(){
    shooter_p.set(DoubleSolenoid.Value.kForward);
  }

  public void releaseCatapult(){
    shooter_p.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
