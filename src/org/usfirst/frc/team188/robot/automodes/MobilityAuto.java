package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.controlcommands.HighGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MobilityAuto extends CommandGroup {

    public MobilityAuto() {
    	addSequential(new ShiftGears('h'));
    	addSequential(new HighGearGyroDrive(223000,0));
    }
}
