package org.usfirst.frc.team188.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DelayedCommand extends CommandGroup {

    public DelayedCommand(double t, Command c) {
        addSequential(new WaitCommand(t));
        addSequential(c);
    }
    
    public DelayedCommand(double t, Command[] c) {
        addSequential(new WaitCommand(t));
        addSequential(new Print("WAIT DONE"));
        for (int i = 0; i < c.length - 1; ++i) {
        	addParallel(c[i]);
        }
        addSequential(new Print("PARALLELS DONE"));
        addSequential(c[c.length-1]);
        addSequential(new Print("SEQUENTIAL DONE"));
    }
}
