package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static frc.robot.Constants.*;

public class Intake extends SubsystemBase{
    //Need to edit rollerIntake as we are not using flywheel design anymore
    private DoubleSolenoid intakePiston;
    private WPI_TalonSRX rollerMotor;

    public Intake(){
        rollerMotor = new WPI_TalonSRX(INTAKE_MOTOR_PORT);
        intakePiston = new DoubleSolenoid(INTAKE_PISTON_PORT_1, INTAKE_PISTON_PORT_2);
    }
    //MOTORS
    /**
     * Sets the speed of the motor
     * 
     * @param speed [-1.0, 1.0]
     */

    public void intake(){
        rollerMotor.set(INTAKE_SPEED);
    }

    public void lowerIntake(){
        intakePiston.set(DoubleSolenoid.Value.kForward);
    }

    public void raiseIntake(){
        intakePiston.set(DoubleSolenoid.Value.kReverse);
    }

    public void outtake(){
        rollerMotor.set(-INTAKE_SPEED);
    }

    public void off(){
        rollerMotor.set(0);
    }
}
