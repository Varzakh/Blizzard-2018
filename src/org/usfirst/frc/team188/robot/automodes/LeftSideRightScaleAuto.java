package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.DelayedCommand;
import org.usfirst.frc.team188.robot.commands.ResetEnc;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroArc;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.MoveElevator;
import org.usfirst.frc.team188.robot.controlcommands.MoveIntake;
import org.usfirst.frc.team188.robot.controlcommands.MultiGearGyroDrive;
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
    	addSequential(new MoveIntake(0,0));
    	addSequential(new PivotIntake('d'));
    	addSequential(new ShiftGears('h'));
    	
//    	addSequential(new HighGearGyroDrive(224000,0,1,true)); // (340000) drive forward initially
    	addSequential(new MultiGearGyroDrive(266500,0,0,true));
    	
    	addSequential(new ShiftGears('l'));
    	addSequential(new WaitCommand(0.25));
    	
//    	addSequential(new LowGearGyroArc(700000,90,0.5,308000));  //drive and arc to right side
    	addSequential(new Turn(90,13));
    	addSequential(new ResetEnc());
    	
    	addSequential(new ShiftGears('h'));
//    	addParallel(new MoveElevator(1));
    	addParallel(new DelayedCommand(1.7,new MoveElevator(1)));
//    	addSequential(new HighGearGyroDrive(480000,90));  //drive a bit more
    	addSequential(new MultiGearGyroDrive(219000,90));  //switched from high to multi, 187000, 233000 
    	
    	addSequential(new ShiftGears('l'));
//    	addParallel(new DelayedCommand(0.3,new Command[] {new ResetEnc(),
//    				new MoveElevator(3),new PivotIntake('d'),new WaitCommand(0.2)}));
    	addSequential(new Turn(-15));  //turn towards scale
    	addParallel(new ResetEnc());
    	addParallel(new MoveElevator(3));  //move elevator to scale position
    	addParallel(new PivotIntake('d'));
    	addSequential(new WaitCommand(1));
    	addParallel(new DelayedCommand(0.4,new MoveIntake(0.2,0.65))); //0.3
    	addSequential(new LowGearGyroArc(37000,-15));  //drive towards scale, 33000
//    	addSequential(new MoveIntake(0.2,0.6));  //SCORE FIRST CUBE
    	addParallel(new PivotIntake('u'));
    	addSequential(new LowGearGyroArc(17500,-15));  //drive back
    	addParallel(new MoveElevator(0));  //elevator down
    	addSequential(new WaitCommand(1));
    	
    	addParallel(new PivotIntake('d'));
    	addSequential(new Turn(200,11));  //turn towards switch
    	addSequential(new ResetEnc());    	
    	addParallel(new MoveIntake(-0.7,3.0));
    	
//    	addSequential(new LowGearGyroDrive(45000,200),1.5);  //29000
    	addSequential(new LowGearGyroDrive(45000,200),1.5);
    	
    	addSequential(new MoveIntake(-0.7,0.25));
    	addParallel(new MoveIntake(-0.7,2));
    	addSequential(new Turn(0,11));  //turn towards scale
    	
    	addSequential(new ResetEnc());
    	addParallel(new MoveElevator(3));
    	addSequential(new WaitCommand(0.5));
//    	addParallel(new DelayedCommand(0.8, new MoveIntake(0.2,6.0)));
    	addParallel(new DelayedCommand(0.8, new MoveIntake(0.6, 0, 6.0)));
    	addSequential(new LowGearGyroDrive(52500,0));  //38000
//    	addSequential(new MoveIntake(0.2,0.6));
    	addSequential(new LowGearGyroArc(10000,0));
    	
    }
}
