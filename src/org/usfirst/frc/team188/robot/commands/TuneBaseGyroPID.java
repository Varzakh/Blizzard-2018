package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.subsystems.BaseGyroPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TuneBaseGyroPID extends Command {

	PIDSubsystem baseGyroPID;
	
    public TuneBaseGyroPID() {
    	requires(Robot.base);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	baseGyroPID = new BaseGyroPID(SmartDashboard.getNumber("Base Gyro P", 0.0), SmartDashboard.getNumber("Base Gyro I", 0.0), 
    			SmartDashboard.getNumber("Base Gyro D", 0.0), SmartDashboard.getNumber("Base Gyro Setpoint", 0.0), 0.8);
    	baseGyroPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(SmartDashboard.getNumber("Base Gyro P", 0.0) != baseGyroPID.getPIDController().getP()|| 
    			SmartDashboard.getNumber("Base Gyro I", 0.0) != baseGyroPID.getPIDController().getI() || 
    			SmartDashboard.getNumber("Base Gyro D", 0.0) != baseGyroPID.getPIDController().getD() ||   
    			SmartDashboard.getNumber("Base Gyro Setpoint", 0.0) != baseGyroPID.getPIDController().getSetpoint() ){
    		baseGyroPID.disable();
    		baseGyroPID = new BaseGyroPID(SmartDashboard.getNumber("Base Gyro P", 0.0), SmartDashboard.getNumber("Base Gyro I", 0.0), 
        			SmartDashboard.getNumber("Base Gyro D", 0.0), SmartDashboard.getNumber("Base Gyro Setpoint", 0.0), 0.8);
    		baseGyroPID.enable();
    	}
    	SmartDashboard.putNumber("Gyro", Robot.base.getGyro());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
