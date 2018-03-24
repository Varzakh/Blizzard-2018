package org.usfirst.frc.team188.controlcommands;

import org.usfirst.frc.team188.robot.Constants;
import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.subsystems.BaseGyroPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {
	
	BaseGyroPID baseGyroPID;
	
	double pGyro = Constants.gyroTurnPID[0];
	double iGyro = Constants.gyroTurnPID[1];
	double dGyro = Constants.gyroTurnPID[2];
	
	int count;

    public Turn(double angle) {
    	requires(Robot.base);
    	baseGyroPID = new BaseGyroPID(pGyro,iGyro,dGyro,angle,0.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	baseGyroPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(baseGyroPID.onTarget()) count++;
    	else count = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > 18;
    }

    // Called once after isFinished returns true
    protected void end() {
    	baseGyroPID.disable();
    	Robot.base.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
