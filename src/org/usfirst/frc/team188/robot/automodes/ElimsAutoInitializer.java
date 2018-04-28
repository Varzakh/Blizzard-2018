package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.ElimsSideSelector;
import org.usfirst.frc.team188.robot.commands.GetGameData;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElimsAutoInitializer extends CommandGroup {
	
    public ElimsAutoInitializer() {
    	addSequential(new GetGameData());
    	addSequential(new ElimsSideSelector());
    }
}
