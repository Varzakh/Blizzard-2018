package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.ClutchSideSelector;
import org.usfirst.frc.team188.robot.commands.ElimsSideSelector;
import org.usfirst.frc.team188.robot.commands.GetGameData;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ClutchAutoInitializer extends CommandGroup {

    public ClutchAutoInitializer() {
    	addSequential(new GetGameData());
    	addSequential(new ClutchSideSelector());
    }
}
