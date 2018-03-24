package org.usfirst.frc.team188.robot.controlcommands;

import org.usfirst.frc.team188.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveIntake extends Command {
	double time;
	int pivotDirection;
	double rotatePower;
	Timer t;
    
    public MoveIntake(double rotatePower, double time) {
    	requires(Robot.intake);
    	this.time = time;
    	this.rotatePower = rotatePower;
    }

    protected void initialize() {
    	t = new Timer();
    	t.start();
    }

    protected void execute() {
    	Robot.intake.drive(rotatePower);
    }

    protected boolean isFinished() {
        return t.get() > time;
    }

    protected void end() {
    	Robot.intake.drive(-0.2);
    }

    protected void interrupted() {
    	end();
    }
}
