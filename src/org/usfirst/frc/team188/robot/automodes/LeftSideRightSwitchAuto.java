package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSideRightSwitchAuto extends CommandGroup {

    public LeftSideRightSwitchAuto() {
    	addSequential(new ShiftGears('l'));
    	addSequential(new LowGearGyroDrive(185000,0));
    }
}
