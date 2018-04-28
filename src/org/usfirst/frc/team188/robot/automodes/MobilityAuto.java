package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.controlcommands.HighGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.MoveIntake;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
