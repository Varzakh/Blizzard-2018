package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.subsystems.BaseEncPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TuneBaseEncPID extends Command {
	
	PIDSubsystem baseEncPID;
	double maxEnc = 0;
	double power = 1;

    public TuneBaseEncPID() {
    	requires(Robot.base);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.base.frontLeft.configOpenloopRamp(0.2, 0);
		Robot.base.backLeft.configOpenloopRamp(0.2, 0);
		Robot.base.frontRight.configOpenloopRamp(0.2, 0);
		Robot.base.backRight.configOpenloopRamp(0.2, 0);
    	baseEncPID = new BaseEncPID(SmartDashboard.getNumber("Base Enc P", 0.0), SmartDashboard.getNumber("Base Enc I", 0.0), 
    			SmartDashboard.getNumber("Base Enc D", 0.0), SmartDashboard.getNumber("Base Enc Setpoint", 0.0),power);
    	baseEncPID.enable();
    	maxEnc = 0;
    	Robot.base.resetEnc();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(SmartDashboard.getNumber("Base Enc P", 0.0) != baseEncPID.getPIDController().getP()|| 
    			SmartDashboard.getNumber("Base Enc I", 0.0) != baseEncPID.getPIDController().getI() || 
    			SmartDashboard.getNumber("Base Enc D", 0.0) != baseEncPID.getPIDController().getD() ||   
    			SmartDashboard.getNumber("Base Enc Setpoint", 0.0) != baseEncPID.getPIDController().getSetpoint() ){
    		baseEncPID.disable();
    		baseEncPID = new BaseEncPID(SmartDashboard.getNumber("Base Enc P", 0.0), SmartDashboard.getNumber("Base Enc I", 0.0), 
        			SmartDashboard.getNumber("Base Enc D", 0.0), SmartDashboard.getNumber("Base Enc Setpoint", 0.0),power);
    		baseEncPID.enable();
    	}
    	maxEnc = Math.max(maxEnc,Robot.base.getEnc());
    	SmartDashboard.putNumber("Base Enc", Robot.base.getEnc());
    	SmartDashboard.putNumber("Max Base Enc", maxEnc);
    	SmartDashboard.putNumber("Back Right Output Value", Robot.base.backRight.getMotorOutputPercent());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.base.frontLeft.configOpenloopRamp(0, 0);
		Robot.base.backLeft.configOpenloopRamp(0, 0);
		Robot.base.frontRight.configOpenloopRamp(0, 0);
		Robot.base.backRight.configOpenloopRamp(0, 0);
    	baseEncPID.disable();
    	Robot.base.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
