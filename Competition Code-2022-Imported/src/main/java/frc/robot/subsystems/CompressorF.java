// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.*;

public class CompressorF extends SubsystemBase {

  private Compressor pcmCompressor;
  /** Creates a new Compressor. */
  public CompressorF() {
    pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);


  }
  public void getSetCompressorStatus() {
    pcmCompressor.enableDigital();
    System.out.println(pcmCompressor.enabled());
    System.out.println("\n" + pcmCompressor.getPressure());

  }
  public void setCompressorStatus(){
    pcmCompressor.enableAnalog(0, 120);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
