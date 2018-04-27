package org.usfirst.frc.team188.robot.controlcommands;

import org.usfirst.frc.team188.robot.Constants;
import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.subsystems.BaseGyroPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RampTurn extends Command {
	
BaseGyroPID baseGyroPID;
	
	double pGyro = Constants.gyroTurnPID[0];
	double iGyro = Constants.gyroTurnPID[1];
	double dGyro = Constants.gyroTurnPID[2];
	
	int onTargetCount;
	int defaultTargetCount = 18;
	
	int count;

    public RampTurn(double angle) {
    	requires(Robot.base);
    	baseGyroPID = new BaseGyroPID(pGyro,iGyro,dGyro,angle,0.0);
    	this.onTargetCount = defaultTargetCount;
    }
    
    public RampTurn(double angle, int onTargetCount) {
    	this(angle);
    	this.onTargetCount = onTargetCount;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.base.frontLeft.configOpenloopRamp(0.4, 0);
		Robot.base.backLeft.configOpenloopRamp(0.4, 0);
		Robot.base.frontRight.configOpenloopRamp(0.4, 0);
		Robot.base.backRight.configOpenloopRamp(0.4, 0);
		
		baseGyroPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(baseGyroPID.onTarget()) count++;
    	else count = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count > onTargetCount;
    }

    // Called once after isFinished returns true
    protected void end() {
    	baseGyroPID.disable();
    	Robot.base.frontLeft.configOpenloopRamp(0, 0);
		Robot.base.backLeft.configOpenloopRamp(0, 0);
		Robot.base.frontRight.configOpenloopRamp(0, 0);
		Robot.base.backRight.configOpenloopRamp(0, 0);
    	Robot.base.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
