package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.DelayedCommand;
import org.usfirst.frc.team188.robot.commands.ResetEnc;
import org.usfirst.frc.team188.robot.controlcommands.HighGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroArc;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.MoveElevator;
import org.usfirst.frc.team188.robot.controlcommands.MoveIntake;
import org.usfirst.frc.team188.robot.controlcommands.PivotIntake;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;
import org.usfirst.frc.team188.robot.controlcommands.Turn;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftSideRightScaleAuto extends CommandGroup {

    public LeftSideRightScaleAuto() {
//        addSequential(new ErrorAuto("autonomous mode LeftSideRightScaleAuto not completed."));
    	addSequential(new MoveIntake(0,0));
    	addSequential(new PivotIntake('d'));
    	addSequential(new ShiftGears('h'));
    	addSequential(new HighGearGyroDrive(223500,0)); // (340000) drive forward initially
    	addSequential(new ShiftGears('l'));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new LowGearGyroArc(700000,90,0.5,300000));  //drive and arc to right side
    	addSequential(new ShiftGears('h'));
    	addParallel(new MoveElevator(2));
    	addSequential(new HighGearGyroDrive(483000,90));  //drive a bit more
    	addSequential(new ShiftGears('l'));
//    	addParallel(new DelayedCommand(0.3,new Command[] {new ResetEnc(),
//    				new MoveElevator(3),new PivotIntake('d'),new WaitCommand(0.2)}));
    	addSequential(new Turn(-15));  //turn towards scale
    	addParallel(new ResetEnc());
    	addParallel(new MoveElevator(3));  //move elevator to scale position
    	addParallel(new PivotIntake('d'));
    	addSequential(new WaitCommand(0.2));
    	addSequential(new LowGearGyroArc(37000,-15));  //drive towards scale
    	addSequential(new MoveIntake(0.2,0.6));  //SCORE FIRST CUBE
    	addParallel(new PivotIntake('u'));
    	addSequential(new LowGearGyroArc(17500,-15));  //drive back
    	addParallel(new MoveElevator(0));  //elevator down
    	addSequential(new WaitCommand(1));
    	addParallel(new PivotIntake('d'));
    	addSequential(new Turn(190));  //turn towards switch
    	addSequential(new ResetEnc());    	
    	addParallel(new MoveIntake(-1.0,3.0));
    	addSequential(new LowGearGyroDrive(29000,190));
    	addSequential(new MoveIntake(-1.0,0.5));
    	addSequential(new Turn(0));  //turn towards scale
    	
    	addSequential(new ResetEnc());
    	addParallel(new MoveElevator(3));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new LowGearGyroArc(38000,0));
    	addSequential(new MoveIntake(0.2,0.6));
    	addSequential(new LowGearGyroArc(10000,0));
    	
    }
}
