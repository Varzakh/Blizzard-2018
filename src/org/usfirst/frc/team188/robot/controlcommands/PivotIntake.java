package org.usfirst.frc.team188.robot.controlcommands;

import org.usfirst.frc.team188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PivotIntake extends Command {

	char dir;
    public PivotIntake(char dir) {
    	this.dir = dir;
    }

    protected void initialize() {
    	Robot.intake.pivot(dir);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
