package org.usfirst.frc.team188.robot.automodes;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSideLeftScaleAuto extends CommandGroup {

    public RightSideLeftScaleAuto() {
        addSequential(new ErrorAuto("autonomous mode RightSideLeftScaleAuto not completed."));
    }
}
