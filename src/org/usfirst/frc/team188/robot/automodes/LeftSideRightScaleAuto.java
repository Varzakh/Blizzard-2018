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
public class LeftSideRightScaleAuto extends CommandGroup {

    public LeftSideRightScaleAuto() {
//        addSequential(new ErrorAuto("autonomous mode LeftSideRightScaleAuto not completed."));
    	addSequential(new ShiftGears('h'));
    	addSequential(new HighGearGyroDrive(340000,0,200000)); //drive forward initially
    	addSequential(new ShiftGears('l'));
    	addSequential(new LowGearGyroArc(700000,90,0.5,400000));  //drive and arc to right side
    	addSequential(new LowGearGyroDrive(514000,90));  //drive a bit more
    	addSequential(new Turn(0));  //turn towards scale
    	addParallel(new ResetEnc());
    	addParallel(new MoveElevator(3));  //move elevator to scale position
    	addParallel(new PivotIntake('d'));
    	addSequential(new WaitCommand(1));
    	addSequential(new LowGearGyroDrive(45000,0));  //drive towards elevator
    	addSequential(new MoveIntake(0.3,0.6));  //SCORE FIRST CUBE
    	addParallel(new PivotIntake('u'));
    	addSequential(new LowGearGyroDrive(0,0));  //drive back
    	addParallel(new MoveElevator(0));  //elevator down
    	addSequential(new WaitCommand(1));
    	addParallel(new PivotIntake('d'));
    	addSequential(new ResetEnc());
    	addSequential(new Turn(180));  //turn towards switch
    	
    	addParallel(new MoveIntake(-1.0,3.0));
    	addSequential(new LowGearGyroDrive(20000,180));
    	addSequential(new Turn(190));
    	addSequential(new Turn(170));
    	addSequential(new Turn(180));
    	addSequential(new Turn(0));  //turn towards scale
    	addParallel(new MoveElevator(2));  //elevator partway up
    	addSequential(new LowGearGyroDrive(40000,0));  //drive partway towards scale
    	addParallel(new MoveElevator(3));  //elevator to scale position
    	addSequential(new LowGearGyroDrive(65000,0));
    	addSequential(new WaitCommand(0.3));
    	addSequential(new MoveIntake(0.4,0.4));  //SCORE SECOND CUBE
    	addParallel(new PivotIntake('u'));
    	
    }
}
