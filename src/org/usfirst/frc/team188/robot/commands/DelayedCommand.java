package org.usfirst.frc.team188.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DelayedCommand extends Command {
	
	double time;
	Command cmd;
	Timer t;

    public DelayedCommand(double time, Command cmd) {
    	this.time = time;
    	this.cmd = cmd;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	t = new Timer();
    	t.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(t.get() > time && !cmd.isRunning()) cmd.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return cmd.isCompleted();
    }

    // Called once after isFinished returns true
    protected void end() {
    	cmd.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
