package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.DelayedCommand;
import org.usfirst.frc.team188.robot.commands.ResetEnc;
import org.usfirst.frc.team188.robot.controlcommands.HighGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.MoveElevator;
import org.usfirst.frc.team188.robot.controlcommands.MoveIntake;
import org.usfirst.frc.team188.robot.controlcommands.PivotIntake;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;
import org.usfirst.frc.team188.robot.controlcommands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftSideLeftScaleAuto extends CommandGroup {

    public LeftSideLeftScaleAuto() {
    	addParallel(new MoveIntake(0, 0));
    	addSequential(new ShiftGears('l'));
    	addParallel(new MoveElevator(3));
    	addParallel(new PivotIntake('d'));
	    addSequential(new LowGearGyroDrive(245000, 0)); //245000, high gear
	    addSequential(new ShiftGears('l'));
	    addSequential(new Turn(50));
	    addSequential(new MoveIntake(0.3, 0.6));
	    addSequential(new ResetEnc());
	    addSequential(new LowGearGyroDrive(-25000, 50, 0.5));
	    addParallel(new MoveElevator(0));
	    addSequential(new WaitCommand(0.5));
	    addSequential(new Turn(148));
	    addSequential(new ResetEnc());
	    addParallel(new MoveIntake(-1.0, 1.5));
	    addSequential(new LowGearGyroDrive(100000, 155)); //33000
	    addParallel(new MoveElevator(1));
	    addSequential(new Turn(7));
	    addSequential(new ResetEnc());
	    addParallel(new MoveElevator(3));
	    addSequential(new LowGearGyroDrive(25000, 7));
	    addSequential(new MoveIntake(0.5, 0.6));
	    addSequential(new ResetEnc());
	    addSequential(new LowGearGyroDrive(-15000, 7, 0.5));
	    addParallel(new MoveElevator(1));
	    addSequential(new WaitCommand(0.5));
	    addSequential(new Turn(140));
	    addParallel(new MoveElevator(0));
	    addSequential(new ResetEnc());
	    addSequential(new LowGearGyroDrive(34000, 140));
	    addSequential(new MoveIntake(-1.0, 1.5));
    }
}
