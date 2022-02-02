package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static frc.robot.Constants.*;

public class Intake extends SubsystemBase {
  // These are the variables that need to be created for Intake.
  private DoubleSolenoid intakePiston;
  private WPI_TalonSRX rollerMotor;

  // This is the constructor.
  public Intake() {
    rollerMotor = new WPI_TalonSRX(INTAKE_MOTOR_PORT);
    intakePiston = new DoubleSolenoid(INTAKE_PISTON_PORT_1, INTAKE_PISTON_PORT_2);
  }
  /**
   * Sets the speed of the motor
   *
   * @param speed [-1.0, 1.0]
   */

  // This method sets the intake motor's speed to a value.
  public void intake() {
    rollerMotor.set(INTAKE_SPEED);
  }

  // This method pushes the Intake piston forward.
  public void pistonForward() {
    intakePiston.set(DoubleSolenoid.Value.kForward);
  }

  // This method pulls the Intake piston back.
  public void pistonReverse() {
    intakePiston.set(DoubleSolenoid.Value.kReverse);
  }

  // This method runs the intake motor at negative a value in order to outtake.
  public void outtake() {
    rollerMotor.set(-INTAKE_SPEED);
  }

  // This method turns off the intake motor.
  public void off() {
    rollerMotor.set(0);
  }
}
