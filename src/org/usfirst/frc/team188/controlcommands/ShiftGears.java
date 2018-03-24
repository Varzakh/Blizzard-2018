package org.usfirst.frc.team188.controlcommands;

import org.usfirst.frc.team188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftGears extends Command {
	
	char gear;

    public ShiftGears(char gear) {
        requires(Robot.base);
        this.gear = gear;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(gear == 'l') Robot.base.shiftGears(false, true);
    	if(gear == 'h') Robot.base.shiftGears(true, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
