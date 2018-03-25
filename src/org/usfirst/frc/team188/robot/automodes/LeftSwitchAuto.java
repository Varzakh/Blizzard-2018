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
public class LeftSwitchAuto extends CommandGroup {

    public LeftSwitchAuto() {
    	addParallel(new MoveElevator(1));
	    addSequential(new ShiftGears('l'));
		addSequential(new LowGearGyroDrive(15000, 0));
		addSequential(new Turn(-45));
		addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(95000, -45));
		addSequential(new Turn(0));
		addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(25000, 0));
		addSequential(new MoveIntake(0.3, 0.6));
		addParallel(new MoveElevator(0));
		addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(-48000, 0));
		addParallel(new PivotIntake('d'));
		addSequential(new Turn(-45));
		addSequential(new ResetEnc());
		addSequential(new WaitCommand(0.25));
		addSequential(new LowGearGyroDrive(-75000, -45));
		addSequential(new Turn(0));
		addSequential(new ResetEnc());
		addParallel(new LowGearGyroDrive(75000, 0));
		addSequential(new MoveIntake(-1.0, 1.0));
		addParallel(new MoveIntake(-1.0, 1.0));
		addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(-65000, 0));
		addParallel(new MoveElevator(1));
		addSequential(new Turn(-35));
		addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(91000, -35));
		addSequential(new Turn(0));
//		addSequential(new ResetEnc());
//		addSequential(new WaitCommand(0.5));
//		addSequential(new LowGearGyroDrive(35000, 0), 1.0);
//		addSequential(new MoveIntake(0.5, 0.6));
    }
}
