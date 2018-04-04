package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.DelayedCommand;
import org.usfirst.frc.team188.robot.commands.ResetEnc;
import org.usfirst.frc.team188.robot.commands.TuneBaseGyroPID;
import org.usfirst.frc.team188.robot.controlcommands.HighGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroArc;
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
    	addSequential(new ShiftGears('h'));
    	addParallel(new MoveElevator(2));
    	addParallel(new PivotIntake('d'));
	    addSequential(new HighGearGyroDrive(258000, 0)); //215000, high gear
	    addSequential(new ShiftGears('l'));
	    addSequential(new Turn(43));
	    addParallel(new MoveElevator(3));
	    addSequential(new WaitCommand(0.5));
	    addSequential(new MoveIntake(0.7, 0.6));
	    addParallel(new MoveElevator(0));
	    addParallel(new PivotIntake('u'));
	    addSequential(new WaitCommand(0.5));
	    addParallel(new PivotIntake('d'));
	    addSequential(new Turn(147));
	    addSequential(new ResetEnc());
	    addParallel(new MoveIntake(-1.0, 4));
	    addSequential(new LowGearGyroArc(68000, 147)); //33000
	    addSequential(new Turn(157));
	    addSequential(new Turn(137));
	    addSequential(new Turn(147));
	    addParallel(new MoveElevator(1));
	    addSequential(new Turn(0));
	    addSequential(new ResetEnc());
	    addParallel(new MoveElevator(3));
	    addSequential(new LowGearGyroArc(46000, 0));
	    addSequential(new MoveIntake(0.15, 0.6));
	    addSequential(new LowGearGyroArc(0, 0));
	    addSequential(new WaitCommand(0.8));
	    addParallel(new PivotIntake('u'));
	    addParallel(new MoveElevator(0));
	    addSequential(new WaitCommand(0.5));
	    addParallel(new PivotIntake('d'));
	    addSequential(new Turn(140));
//	    addParallel(new MoveElevator(0));
//	    addSequential(new ResetEnc());
//	    addParallel(new MoveIntake(-1.0, 1.5));
//	    addSequential(new LowGearGyroArc(28000, 140));
//	    addSequential(new MoveIntake(-1.0, 1.5));
//	    addParallel(new MoveElevator(1));
//	    addSequential(new Turn(-10));
//	    addParallel(new MoveElevator(3));
//	    addSequential(new LowGearGyroDrive(45000, -10));
//	    addSequential(new MoveIntake(0.4, 0.6));
	    
    }
}
