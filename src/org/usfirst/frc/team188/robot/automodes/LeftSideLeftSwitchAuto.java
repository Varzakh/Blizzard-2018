package org.usfirst.frc.team188.robot.automodes;

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
    	addSequential(new ShiftGears('l'));
    	addSequential(new LowGearGyroDrive(185000,0));
    	addSequential(new Turn(90));
    	addParallel(new MoveElevator(1));
    	addSequential(new WaitCommand(0.8));
    	addSequential(new LowGearGyroDrive(12700,0));
    	addSequential(new PivotIntake('d'));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new MoveIntake(0.5,0.6));
    }
}
