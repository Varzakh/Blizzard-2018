package org.usfirst.frc.team188.automodes;

import org.usfirst.frc.team188.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.controlcommands.MoveElevator;
import org.usfirst.frc.team188.controlcommands.MoveIntake;
import org.usfirst.frc.team188.controlcommands.PivotIntake;
import org.usfirst.frc.team188.controlcommands.ShiftGears;
import org.usfirst.frc.team188.controlcommands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchAuto extends CommandGroup {

    public RightSwitchAuto() {
    	addSequential(new MoveElevator(0));
	    addSequential(new ShiftGears('l'));
		addSequential(new LowGearGyroDrive(18500, 0));
		addSequential(new Turn(22));
		addSequential(new LowGearGyroDrive(105000, 22));
		addSequential(new MoveIntake(1.0, 0.6));
		addSequential(new LowGearGyroDrive(-105000, 22));
		addSequential(new Turn(0));
		addSequential(new PivotIntake('d'));
		addParallel(new LowGearGyroDrive(73000, 0));
		addSequential(new MoveIntake(-1.0, 1.0));
		addParallel(new MoveIntake(-1.0, 1.0));
		addSequential(new LowGearGyroDrive(-63000, 0));
		addParallel(new MoveElevator(1));
		addSequential(new Turn(22));
		addSequential(new LowGearGyroDrive(105000, 22));
		addSequential(new MoveIntake(0.5, 0.6));
    }
}
