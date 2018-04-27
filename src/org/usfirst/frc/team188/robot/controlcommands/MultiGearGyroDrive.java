package org.usfirst.frc.team188.robot.controlcommands;

import org.usfirst.frc.team188.robot.Constants;
import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.subsystems.BaseEncPID;
import org.usfirst.frc.team188.robot.subsystems.BaseGyroPID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MultiGearGyroDrive extends Command {

	int endpoint;
	double power = 0.8;
	
	int count;
	int onTargetCount;
	int defaultTargetCount = 2;
	
	boolean onTarget;
	boolean switched;
	boolean coasting;
	int trueEndpoint;
	
	BaseEncPID baseEncHighPID;
	BaseEncPID baseEncLowPID;
	BaseGyroPID baseGyroPID;
	
	double pEncHigh = Constants.highGearBaseEncPID[0];
	double iEncHigh = Constants.highGearBaseEncPID[1];
	double dEncHigh = Constants.highGearBaseEncPID[2];
	
	double pEncLow = Constants.baseEncPID[0];
	double iEncLow = Constants.baseEncPID[1];
	double dEncLow = Constants.baseEncPID[2];
	
	double pGyro = Constants.highGearGyroPID[0];
	double iGyro = Constants.highGearGyroPID[1];
	double dGyro = Constants.highGearGyroPID[2];
	
    public MultiGearGyroDrive(int encSetpoint, double gyroSetpoint) {
    	requires(Robot.base);
    	baseEncHighPID = new BaseEncPID(pEncHigh,iEncHigh,dEncHigh,encSetpoint,this.power,true,true);
    	baseEncLowPID = new BaseEncPID(pEncLow,iEncLow,dEncLow,encSetpoint,this.power,true,false);
    	baseGyroPID = new BaseGyroPID(pGyro,iGyro,dGyro,gyroSetpoint,this.power,true);
    	this.endpoint = 0;
    	this.onTargetCount = defaultTargetCount;
    	this.onTarget = false;
    }
    
    public MultiGearGyroDrive(int encSetpoint, double gyroSetpoint, int endpoint) {
    	this(encSetpoint,gyroSetpoint);
    	this.endpoint = endpoint;
    }
    
    public MultiGearGyroDrive(int encSetpoint, double gyroSetpoint, int onTargetCount, boolean weRDumm) {
    	this(encSetpoint,gyroSetpoint);
    	this.onTargetCount = onTargetCount;
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	coasting = false;
    	switched = false;
    	count = 0;
    	Robot.base.encPwr = 0;
    	Robot.base.gyroPwr = 0;
    	baseEncHighPID.enable();
    	baseGyroPID.enable();
    	baseEncLowPID.disable();
    	Robot.base.frontLeft.configOpenloopRamp(0.2, 0);
		Robot.base.backLeft.configOpenloopRamp(0.2, 0);
		Robot.base.frontRight.configOpenloopRamp(0.2, 0);
		Robot.base.backRight.configOpenloopRamp(0.2, 0);
		if (endpoint == 0) trueEndpoint = (int) baseEncHighPID.getPIDController().getSetpoint();
		else trueEndpoint = endpoint;
		SmartDashboard.putNumber("True Endpoint", trueEndpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("True Endpoint", trueEndpoint);
    	if (!coasting && trueEndpoint - Robot.base.getEnc() < 120000) {
    		System.out.println("COASTING");
    		baseEncHighPID.disable();
    		baseGyroPID.disable();
    		
    		Robot.base.frontLeft.configOpenloopRamp(0, 0);
    		Robot.base.backLeft.configOpenloopRamp(0, 0);
    		Robot.base.frontRight.configOpenloopRamp(0, 0);
    		Robot.base.backRight.configOpenloopRamp(0, 0);
    	
    		Robot.base.frontLeft.setNeutralMode(NeutralMode.Coast);
    		Robot.base.backLeft.setNeutralMode(NeutralMode.Coast);
    		Robot.base.frontRight.setNeutralMode(NeutralMode.Coast);
    		Robot.base.backRight.setNeutralMode(NeutralMode.Coast);
    		
    		Robot.base.frontLeft.set(ControlMode.PercentOutput, 0);
    		Robot.base.backLeft.set(ControlMode.PercentOutput, 0);
    		Robot.base.frontRight.set(ControlMode.PercentOutput, 0);
    		Robot.base.backRight.set(ControlMode.PercentOutput, 0);
    		
    		coasting = true;
    	}
    	else if (!switched && trueEndpoint - Robot.base.getEnc() < 90000) {
    		System.out.println("SWITCHING");
    		Robot.base.frontLeft.configOpenloopRamp(0.2, 0);
    		Robot.base.backLeft.configOpenloopRamp(0.2, 0);
    		Robot.base.frontRight.configOpenloopRamp(0.2, 0);
    		Robot.base.backRight.configOpenloopRamp(0.2, 0);
    		
    		Robot.base.frontLeft.setNeutralMode(NeutralMode.Brake);
    		Robot.base.backLeft.setNeutralMode(NeutralMode.Brake);
    		Robot.base.frontRight.setNeutralMode(NeutralMode.Brake);
    		Robot.base.backRight.setNeutralMode(NeutralMode.Brake);
    		
    		Robot.base.shiftToLow();
    		
    		baseEncLowPID.enable();
    		baseGyroPID.enable();
    		
    		switched = true;
    	}
    	if (baseGyroPID.getPIDController().isEnabled())
    		Robot.base.drive(Robot.base.encPwr, Robot.base.gyroPwr);
    	
    	onTarget = (switched ? baseEncLowPID.onTarget() : baseEncHighPID.onTarget());
    	
    	if (baseGyroPID.onTarget() && onTarget) count++;
    	else count = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (endpoint != 0 && Math.abs(Robot.base.getEnc()) >= Math.abs(endpoint)) return true;
        return count > onTargetCount;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("FINISHED");
    	Robot.base.frontLeft.configOpenloopRamp(0, 0);
		Robot.base.backLeft.configOpenloopRamp(0, 0);
		Robot.base.frontRight.configOpenloopRamp(0, 0);
		Robot.base.backRight.configOpenloopRamp(0, 0);
    	Robot.base.drive(0, 0);
    	baseEncHighPID.disable();
    	baseEncLowPID.disable();
    	baseGyroPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
