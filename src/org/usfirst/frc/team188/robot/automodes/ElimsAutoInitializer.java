package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.ElimsAutoSelector;
import org.usfirst.frc.team188.robot.commands.GetGameData;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElimsAutoInitializer extends CommandGroup {

    public ElimsAutoInitializer() {
        addSequential(new GetGameData());
        addSequential(new ElimsAutoSelector());
    }
}
