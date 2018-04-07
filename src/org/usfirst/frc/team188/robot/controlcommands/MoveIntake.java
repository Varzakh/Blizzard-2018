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
	double leftPower;
	double rightPower;
	Timer t;
    
    public MoveIntake(double rotatePower, double time) {
    	requires(Robot.intake);
    	this.time = time;
    	this.leftPower = rotatePower;
    	this.rightPower = rotatePower;
    }
    
    public MoveIntake(double leftPower, double rightPower, double time) {
    	requires(Robot.intake);
    	this.time = time;
    	this.leftPower = leftPower;
    	this.rightPower = rightPower;
    }

    protected void initialize() {
    	t = new Timer();
    	t.start();
    }

    protected void execute() {
    	Robot.intake.drive(leftPower,rightPower);
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
