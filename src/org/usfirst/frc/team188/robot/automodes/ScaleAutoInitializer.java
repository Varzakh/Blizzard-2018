package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.GetGameData;
import org.usfirst.frc.team188.robot.commands.ScaleSideSelector;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleAutoInitializer extends CommandGroup {

    public ScaleAutoInitializer(char side) {
    	addSequential(new GetGameData());
    	addSequential(new ScaleSideSelector(side));
    }
}
