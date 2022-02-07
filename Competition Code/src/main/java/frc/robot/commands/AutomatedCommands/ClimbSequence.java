// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutomatedCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.PowerSecondaryCommand;
import frc.robot.commands.PowerTelescopingCommand;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ClimbSequence extends SequentialCommandGroup {
  /** Creates a new autonomous command sequence. */
  public Climber climber;
  private double T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19;

  /**
   * 
   * This is good enough for now
   * 
   * @param climber
   * @param rotators
   * @param spinners
   */


  public ClimbSequence(
      Climber climber, SwerveRotaters rotators, SwerveSpinners spinners) {
    // This is the sequential commands for our climbing sequence
    this.climber = climber;
    T1 = 1;
    T2 = 1;
    T3 = 1;
    T4 = 1;
    T5 = 1;
    T6 = 1;
    T7 = 1;
    T8 = 1;
    T9 = 1;
    T10 = 1;
    T11 = 1;
    T12 = 1;
    T13 = 1;
    T14 = 1;
    T15 = 1;
    T16 = 1;
    T17 = 1;
    T18 = 1;
    T19 = 1;

    addCommands(
        // The telescoping is initialized
        new PowerTelescopingCommand(climber, TELESCOPING_INITIALIZE),
        new WaitCommand(T1),
        // The secondaries fo back
        new PowerSecondaryCommand(climber, SECONDARY_BACKWARD_POSITION),
        new WaitCommand(T2),
        // The telescopings are let to max height
        new PowerTelescopingCommand(climber, 0),
        new WaitCommand(T3),
        //  The robot moves into position
        new MoveForward(rotators, spinners, T4),
        new WaitCommand(T5),
        // telescopings latch onto bar
        new PowerTelescopingCommand(climber, LOWPOWER),
        new WaitCommand(T6),
        // more power to telescoping to lift robot
        new PowerTelescopingCommand(climber, HIGHPOWER),
        new WaitCommand(T7),
        // power set to keep the robot where it is
        new PowerTelescopingCommand(climber, MIDPOWER),
        new WaitCommand(T8),
        // secondaries are in position to latch
        new PowerSecondaryCommand(climber, SECONDARY_MID_POSITION),
        new WaitCommand(T9),
        // robot starts going down
        new PowerTelescopingCommand(climber, LOWPOWER),
        new WaitCommand(T10),
        // after secondary latches, telescoping is kept at traversing height
        new PowerTelescopingCommand(climber, LOWESTPOWER),
        new WaitCommand(T11),
        // secondaries go forward to tilt the robot
        new PowerSecondaryCommand(climber, SECONDARY_FORWARD_POSITION),
        new WaitCommand(T12),
        // telescoping to maximum height
        new PowerTelescopingCommand(climber, 0),
        new WaitCommand(T13),
        // Secondaries tilt the telescoping into latching position
        new PowerSecondaryCommand(climber, SECONDARY_BACKWARD_POSITION),
        new WaitCommand(T14),
        // telescoping go down
        new PowerTelescopingCommand(climber, LOWPOWER),
        new WaitCommand(T15),
        // telescoping latches and starts lifting robot
        new PowerTelescopingCommand(climber, HIGHPOWER),
        new WaitCommand(T16),
        // secondaries will unlatch and start moving backward, when that happens we drop
        new PowerTelescopingCommand(climber, LOWPOWER),
        new WaitCommand(T17),
        // secondaries should be fully back, we stop
        new PowerTelescopingCommand(climber, MIDPOWER),
        // TRAVERSE 1 COMPLETE
        
        //TRAVERSE 2
        // More power to telescopings to lift

        new PowerTelescopingCommand(climber, HIGHPOWER),
        new WaitCommand(T18),
        // power set to keep the robot where it is
        //  == At this pointwe start repeating == //
        new PowerTelescopingCommand(climber, MIDPOWER),
        new WaitCommand(T8),
        // secondaries are in position to latch
        new PowerSecondaryCommand(climber, SECONDARY_MID_POSITION),
        new WaitCommand(T9),
        // robot starts going down
        new PowerTelescopingCommand(climber, LOWPOWER),
        new WaitCommand(T10),
        // after secondary latches, telescoping is kept at traversing height
        new PowerTelescopingCommand(climber, LOWESTPOWER),
        new WaitCommand(T11),
        // secondaries go forward to tilt the robot
        new PowerSecondaryCommand(climber, SECONDARY_FORWARD_POSITION),
        new WaitCommand(T12),
        // telescoping to maximum height
        new PowerTelescopingCommand(climber, 0),
        new WaitCommand(T13),
        // Secondaries tilt the telescoping into latching position
        new PowerSecondaryCommand(climber, SECONDARY_BACKWARD_POSITION),
        new WaitCommand(T14),
        // telescoping go down
        new PowerTelescopingCommand(climber, LOWPOWER),
        new WaitCommand(T15),
        // telescoping latches and starts lifting robot
        new PowerTelescopingCommand(climber, HIGHPOWER),
        new WaitCommand(T16),
        // secondaries will unlatch and start moving backward, when that happens we drop
        new PowerTelescopingCommand(climber, LOWPOWER),
        new WaitCommand(T17),
        // == repeat stops == //
        // secondaries should be fully back, we now let the robot drop more
        new WaitCommand(T19),
        // finally telescoping power to 0
        new PowerTelescopingCommand(climber, 0)
    );
  }
}
