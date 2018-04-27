package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.controlcommands.HighGearGyroDrive;
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
public class MobilityAuto extends CommandGroup {

    public MobilityAuto() {
    	addParallel(new MoveIntake(0, 0));
    	addSequential(new ShiftGears('h'));  //shift to high
    	
	    addSequential(new HighGearGyroDrive(167000, 0),2.2);
	    
	    addSequential(new ShiftGears('l'));  //shift to low
    }
}
