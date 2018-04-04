package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.controlcommands.HighGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;
import org.usfirst.frc.team188.robot.controlcommands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightSideRightScaleAuto extends CommandGroup {

    public RightSideRightScaleAuto() {
    	addSequential(new ShiftGears('h'));
	    addSequential(new HighGearGyroDrive(245000, 0)); //270000
//	    addSequential(new ShiftGears('l'));
//	    addSequential(new Turn(-30));
//	    addSequential(new Turn(-150));
//	    addSequential(new LowGearGyroDrive(33000, -150));
//	    addSequential(new WaitCommand(0.5));
//	    addSequential(new Turn(-7));
//	    addSequential(new LowGearGyroDrive(25000, -7));
//	    addSequential(new Turn(-140));
//	    addSequential(new LowGearGyroDrive(34000, -140));
    }
}
