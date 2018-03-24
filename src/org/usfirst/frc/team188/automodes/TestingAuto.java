package org.usfirst.frc.team188.automodes;

import org.usfirst.frc.team188.robot.commands.TuneElevatorPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestingAuto extends CommandGroup {

    public TestingAuto() {
    	
    	addSequential(new TuneElevatorPID());

    }
}
