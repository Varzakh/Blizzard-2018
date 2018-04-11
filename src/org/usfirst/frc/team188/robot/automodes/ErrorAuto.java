package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.controlcommands.HighGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;
import org.usfirst.frc.team188.robot.controlcommands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ErrorAuto extends CommandGroup {

    public ErrorAuto(String error) {
    	System.out.println("ERROR: " + error);
    	
    	addSequential(new ShiftGears('h'));
    	addSequential(new HighGearGyroDrive(223000,0));
    }
}
