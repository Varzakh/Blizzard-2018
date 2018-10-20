package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.ResetEnc;
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
public class LeftSideLeftSwitchAuto extends CommandGroup {

    public LeftSideLeftSwitchAuto() {
    	System.out.println("Running LeftSideSwitchAuto");
    	addParallel(new MoveIntake(0,0));
    	addSequential(new ShiftGears('l'));
    	addParallel(new MoveElevator(1));
    	addParallel(new PivotIntake('d'));
    	addSequential(new LowGearGyroDrive(167000,0));
    	addParallel(new MoveIntake(-0.5,5));
    	addSequential(new WaitCommand(0.6));
    	addSequential(new Turn(90));
    	addSequential(new ResetEnc());
    	addSequential(new WaitCommand(0.8));
    	addSequential(new LowGearGyroDrive(12700,90),3);
    	addSequential(new PivotIntake('d'));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new MoveIntake(0.5,0.6));
    	System.out.println("Running LeftSideSwitchAuto");
    }
}
