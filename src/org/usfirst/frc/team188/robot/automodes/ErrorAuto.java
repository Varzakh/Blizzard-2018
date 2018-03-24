package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ErrorAuto extends CommandGroup {

    public ErrorAuto(String error) {
    	System.out.println("ERROR: " + error);
    	
    	addSequential(new LowGearGyroDrive(25000, 0));
    	addSequential(new Turn(180));
    	addSequential(new Turn(0));
    	addSequential(new Turn(180));
    	addSequential(new Turn(0));
    	addSequential(new Turn(180));
    	addSequential(new Turn(0));
    }
}
