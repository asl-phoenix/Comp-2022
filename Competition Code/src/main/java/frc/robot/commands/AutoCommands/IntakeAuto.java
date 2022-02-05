package frc.robot.commands.AutoCommands;


import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeAuto extends CommandBase {
    boolean lower;
    Intake auto;

    public IntakeAuto(Intake auto, boolean toLower){
        this.auto = auto;
        this.lower = toLower;

        addRequirements(auto);
    }
    
    public void initialize(){
        if (lower){
            auto.pistonForward();
            auto.intake();
        } else if (!lower){
            auto.pistonReverse();
            auto.off();
        }
    }

    public void execute(){}

    public void end(boolean interrupted){
        
    }

    public boolean isFinished(){
        return false;
    }
}
