package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.ResetEnc;
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
public class LeftSide3CubeAuto extends CommandGroup {

    public LeftSide3CubeAuto() {
    	addParallel(new MoveIntake(0, 0));
    	addSequential(new ShiftGears('h'));  //shift to high
    	addParallel(new MoveElevator(2));
    	addParallel(new PivotIntake('d'));  //intake down
	    addSequential(new HighGearGyroDrive(236500, 0)); //intial sprint forward
	    addSequential(new ShiftGears('l'));  //shift to low
	    addSequential(new Turn(43));  //turn towards scale
	    addParallel(new MoveElevator(3));  //elevator to scale position
	    addSequential(new WaitCommand(0.2));
//	    addSequential(new MoveIntake(0.4, 0.6)); //SCORE FIRST CUBE
	    addSequential(new MoveIntake(0.4, 0.4)); //SCORE FIRST CUBE
	    addParallel(new MoveElevator(0));  //elevator down
	    addParallel(new PivotIntake('u'));
//	    addSequential(new WaitCommand(0.5));
	    addSequential(new WaitCommand(0.3));
	    addParallel(new PivotIntake('d'));
//	    addSequential(new Turn(148));  //turn towards first switch cube
	    addSequential(new Turn(148,10));  //turn towards first switch cube
	    addSequential(new ResetEnc());
	    addParallel(new MoveIntake(-0.55, 4));  //run intake to pick up cube
//	    addSequential(new LowGearGyroDrive(61000, 148)); //run towards cube
	    addSequential(new LowGearGyroDrive(59000, 148)); //run towards cube
//	    addSequential(new MoveIntake(-0.55,1));
	    addSequential(new MoveIntake(-0.55,0.7));
	    addParallel(new MoveIntake(-0.55,2));
	    addParallel(new MoveElevator(1));  //elevator up partway
	    addSequential(new Turn(10,10));  //turn towards scale
	    addSequential(new ResetEnc());
	    addParallel(new MoveElevator(3));  //elevator to scale position
	    addSequential(new LowGearGyroArc(46000, 10));  //drive towards scale
	    addSequential(new MoveIntake(0.2, 0.6)); //SCORE SECOND CUBE
	    addSequential(new LowGearGyroArc(0, 10));  //drive back
	    addParallel(new PivotIntake('u'));
	    addParallel(new MoveElevator(0));  //elevator down
//	    addSequential(new WaitCommand(0.5));
	    addSequential(new WaitCommand(0.3));
	    addParallel(new PivotIntake('d'));
//	    addSequential(new Turn(125));  //turn towards second switch cube
	    addSequential(new Turn(125,10));  //turn towards second switch cube
	    
	    addSequential(new ResetEnc());
	    addParallel(new MoveIntake(-0.9,-0.4, 3));
	    addSequential(new LowGearGyroDrive(35000, 125));  //move to second switch cube
//	    addSequential(new MoveIntake(-0.9,-0.4, 1.2));
	    addSequential(new MoveIntake(-0.9,-0.4, 0.8));
	    addParallel(new MoveElevator(1));
//	    addSequential(new Turn(-7));
	    addSequential(new Turn(-7,10));
	    addParallel(new MoveElevator(3));
	    addSequential(new LowGearGyroDrive(25000, -7));
	    addSequential(new MoveIntake(0.4, 0.6));
    }
}
