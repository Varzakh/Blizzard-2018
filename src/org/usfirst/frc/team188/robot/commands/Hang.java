package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.controlcommands.MoveElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Hang extends CommandGroup {

    public Hang() {
    	addSequential(new MoveElevator(4));
    	addSequential(new WaitCommand(1));
    	addSequential(new MoveElevator(1));
    }
}
