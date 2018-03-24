package org.usfirst.frc.team188.robot.automodes;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSideRightScaleAuto extends CommandGroup {

    public LeftSideRightScaleAuto() {
        addSequential(new ErrorAuto("autonomous mode LeftSideRightScaleAuto not completed."));
    }
}
