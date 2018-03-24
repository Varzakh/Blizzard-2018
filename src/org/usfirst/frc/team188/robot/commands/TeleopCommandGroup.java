package org.usfirst.frc.team188.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TeleopCommandGroup extends CommandGroup {

    public TeleopCommandGroup() {
    	addParallel(new DisplaySensors());
    	addSequential(new DriverCommand());
    }
}
