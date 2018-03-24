package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.DelayedCommand;
import org.usfirst.frc.team188.robot.controlcommands.HighGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.MoveElevator;
import org.usfirst.frc.team188.robot.controlcommands.MoveIntake;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;
import org.usfirst.frc.team188.robot.controlcommands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftSideLeftScaleAuto extends CommandGroup {

    public LeftSideLeftScaleAuto() {
    	addSequential(new ShiftGears('h'));
    	addParallel(new DelayedCommand(2.0, new MoveElevator(3)));
	    addSequential(new HighGearGyroDrive(245000, 0)); //270000
	    addSequential(new ShiftGears('l'));
	    addSequential(new Turn(30));
	    addSequential(new MoveIntake(0.5, 0.6));
	    addSequential(new Turn(150));
	    addParallel(new MoveElevator(0));
	    addSequential(new WaitCommand(1.0));
	    addParallel(new LowGearGyroDrive(33000, 150));
	    addSequential(new MoveIntake(-1.0, 1.5));
	    addSequential(new Turn(7));
	    addSequential(new LowGearGyroDrive(25000, 7));
	    addSequential(new Turn(140));
	    addSequential(new LowGearGyroDrive(34000, 140));
    }
}
