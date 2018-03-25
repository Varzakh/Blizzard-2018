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
public class RightSwitchAuto extends CommandGroup {

    public RightSwitchAuto() {
    	addParallel(new MoveElevator(1));
	    addSequential(new ShiftGears('l'));
	    addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(15000, 0));
		addSequential(new Turn(45));
		addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(52000, 45));
		addSequential(new Turn(0));
		addParallel(new ResetEnc());
		addSequential(new WaitCommand(0.25));
		addSequential(new LowGearGyroDrive(65000, 0));
		addSequential(new MoveIntake(0.5, 0.6));
		addParallel(new MoveElevator(0));
		addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(-45000, 0));
		addParallel(new PivotIntake('d'));
		addSequential(new Turn(70));
		addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(-73000, 70));
		addSequential(new Turn(0));
		addSequential(new ResetEnc());
		addParallel(new LowGearGyroDrive(45000, 0));
		addSequential(new MoveIntake(-1.0, 1.0));
		addSequential(new ResetEnc());
		addParallel(new MoveIntake(-1.0, 1.0));
		addSequential(new LowGearGyroDrive(-32000, 0));
		addParallel(new MoveElevator(1));
		addSequential(new Turn(90));
		addSequential(new ResetEnc());
		addSequential(new LowGearGyroDrive(54000, 90));
		addSequential(new Turn(0));
//		addSequential(new LowGearGyroDrive(60000, 0));
//		addSequential(new MoveIntake(0.5, 0.6));
    }
}
