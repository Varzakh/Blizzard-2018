package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.SwitchSideSelector;
import org.usfirst.frc.team188.robot.commands.GetGameData;
import org.usfirst.frc.team188.robot.commands.MultiSideSelector;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MultiAutoInitializer extends CommandGroup {
	
    public MultiAutoInitializer() {
    	addSequential(new GetGameData());
    	addSequential(new MultiSideSelector());
    }
}
