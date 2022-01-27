package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

import static frc.robot.Constants.*;

public class Intake extends SubsystemBase{
    //Need to edit rollerIntake as we are not using flywheel design anymore
    private DoubleSolenoid intakePiston;
    private VictorSP rollerMotor;

    public Intake(){
        //intakePiston = new DoubleSolenoid(INTAKE_PISTON_PORT_1, INTAKE_PISTON_PORT_2);
        rollerMotor = new VictorSP(INTAKE_PORT);
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

    public void outtake(){
        rollerMotor.set(-INTAKE_SPEED);
    }

    public void off(){
        rollerMotor.set(0);
    }
}
