package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.ResetEnc;
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
public class ClutchAuto extends CommandGroup {

    public ClutchAuto() {
    	addSequential(new ShiftGears('h'));
    	addParallel(new MoveIntake(0,0));
    	addParallel(new PivotIntake('d'));
    	addParallel(new MoveElevator(0));
    	addSequential(new MultiGearGyroDrive(266500,0,0,true));
    	addParallel(new ShiftGears('l'));
    	addSequential(new WaitCommand(0.25));
    	addSequential(new Turn(90,13));
    	addSequential(new ResetEnc());    	
    	addSequential(new ShiftGears('h'));
    	addSequential(new MultiGearGyroDrive(70000,90));
    	addParallel(new MoveIntake(0.15,3));
    	addSequential(new MultiGearGyroDrive(0,90));
    	addSequential(new ShiftGears('l'));
    	addSequential(new Turn(125));
    	addSequential(new ResetEnc());
    	addParallel(new MoveIntake(-0.8,4));
    	addSequential(new LowGearGyroDrive(20000,125),2);
    	addSequential(new LowGearGyroDrive(0,125));
    	addSequential(new Turn(90));
    	addSequential(new ResetEnc());
    	addSequential(new LowGearGyroDrive(50000,90));
    }
}
