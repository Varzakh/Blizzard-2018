package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.GetGameData;
import org.usfirst.frc.team188.robot.commands.SwitchSideSelector;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchAutoInitializer extends CommandGroup {
	
    public SwitchAutoInitializer() {
    	addSequential(new GetGameData());
    	addSequential(new SwitchSideSelector());
    }
}
