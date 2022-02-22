package frc.robot.commands.AutomatedCommands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntakeAuto extends CommandBase {
  boolean run;
  Intake intake;

  public RunIntakeAuto(Intake intake, boolean run) {
    this.intake = intake;
    this.run = run;

    addRequirements(intake);
  }

  public void initialize() {
    if (run) {
      intake.pistonForward();
      intake.intake();
    } else if (!run) {
      intake.pistonReverse();
      intake.off();
    }
  }

  public void execute() {}

  public void end(boolean interrupted) {
    System.out.println("Intake done");
  }

  public boolean isFinished() {
    return ((intake.getPistonState() == true));
  }
}
