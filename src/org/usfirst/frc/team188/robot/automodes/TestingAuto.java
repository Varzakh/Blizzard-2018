package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.DelayedCommand;
import org.usfirst.frc.team188.robot.controlcommands.MoveElevator;
import org.usfirst.frc.team188.robot.controlcommands.RampTurn;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;
import org.usfirst.frc.team188.robot.controlcommands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class TestingAuto extends CommandGroup {

    public TestingAuto() {
    	addSequential(new ShiftGears('l'));
    	addParallel(new MoveElevator(1));
    	addSequential(new WaitCommand(0.5));
    	addParallel(new DelayedCommand(0.2,new MoveElevator(3)));
    	addSequential(new Turn(90));
    	addSequential(new WaitCommand(1));
    	addSequential(new RampTurn(0));
    }
}
