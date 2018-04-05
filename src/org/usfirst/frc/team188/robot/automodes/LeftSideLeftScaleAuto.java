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
    	addSequential(new ShiftGears('h'));  //shift to high
    	addParallel(new MoveElevator(2));
    	addParallel(new PivotIntake('d'));  //intake down
	    addSequential(new HighGearGyroDrive(258000, 0)); //215000, high gear
	    addSequential(new ShiftGears('l'));  //shift to low
	    addSequential(new Turn(43));  //turn towards scale
	    addParallel(new MoveElevator(3));  //elevator to scale position
	    addSequential(new WaitCommand(0.2));
	    addSequential(new MoveIntake(0.4, 0.6)); //SCORE FIRST CUBE
	    addParallel(new MoveElevator(0));  //elevator down
	    addParallel(new PivotIntake('u'));
	    addSequential(new WaitCommand(0.5));
	    addParallel(new PivotIntake('d'));
	    addSequential(new Turn(144));  //turn towards first switch cube
	    addSequential(new ResetEnc());
	    addParallel(new MoveIntake(-0.5, 4));  //run intake to pickup cube
	    addSequential(new LowGearGyroDrive(66000, 144)); //33000
	    addSequential(new MoveIntake(-1,0.5));
	    addParallel(new MoveIntake(-1.0,2));
	    addParallel(new MoveElevator(1));  //elevator up partway
	    addSequential(new Turn(10));  //turn towards scale
	    addSequential(new ResetEnc());
	    addParallel(new MoveElevator(3));  //elevator to scale position
	    addSequential(new LowGearGyroArc(46000, 10));  //drive towards scale
	    addSequential(new MoveIntake(0.25, 0.6)); //SCORE SECOND CUBE
	    addSequential(new LowGearGyroArc(0, 10));  //drive back
//	    addSequential(new WaitCommand(0.2));
	    addParallel(new PivotIntake('u'));
	    addParallel(new MoveElevator(0));  //elevator down
	    addSequential(new WaitCommand(0.5));
	    addParallel(new PivotIntake('d'));
	    addSequential(new Turn(135));  //turn towards second switch cube
	    
	    addSequential(new ResetEnc());
	    addParallel(new MoveIntake(-1.0, 3));
	    addSequential(new LowGearGyroDrive(34000, 135));  //move to second switch cube
	    addParallel(new MoveIntake(-1.0, 2));
	    addParallel(new MoveElevator(1));
	    addSequential(new Turn(-10));
	    addParallel(new MoveElevator(3));
	    addSequential(new LowGearGyroDrive(25000, -10));
	    addSequential(new MoveIntake(0.4, 0.6));
	    
    }
}
