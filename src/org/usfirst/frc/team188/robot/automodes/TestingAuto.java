package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.TuneBaseEncPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestingAuto extends CommandGroup {

    public TestingAuto() {
//    	addSequential(new ShiftGears('l'));
//    	addParallel(new MoveElevator(1));
//    	addSequential(new WaitCommand(0.5));
//    	addParallel(new DelayedCommand(0.2,new MoveElevator(3)));
//    	addSequential(new Turn(90));
//    	addSequential(new WaitCommand(1));
//    	addSequential(new RampTurn(0));
    	
    	addSequential(new LeftSideLeftSwitchAuto());
//    	addSequential(new TuneBaseEncPID());
//    	addSequential(new LeftSide3CubeAuto());
    	
//    	addParallel(new MoveElevator(1));  //elevator to scale position
//	    addSequential(new WaitCommand(1.0));
//	    addSequential(new MoveIntake(0.6, 0, 0.6));

    }
}
