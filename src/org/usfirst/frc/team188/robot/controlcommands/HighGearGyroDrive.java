package org.usfirst.frc.team188.robot.controlcommands;

import org.usfirst.frc.team188.robot.Constants;
import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.subsystems.BaseEncPID;
import org.usfirst.frc.team188.robot.subsystems.BaseGyroPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HighGearGyroDrive extends Command {
	
	int endpoint;
	double power;
	
	int count;
	int onTargetCount;
	int defaultTargetCount = 10;
	
	BaseEncPID baseEncPID;
	BaseGyroPID baseGyroPID;
	
	double pEnc = Constants.highGearBaseEncPID[0];
	double iEnc = Constants.highGearBaseEncPID[1];
	double dEnc = Constants.highGearBaseEncPID[2];
	
	double pGyro = Constants.highGearGyroPID[0];
	double iGyro = Constants.highGearGyroPID[1];
	double dGyro = Constants.highGearGyroPID[2];

	
    public HighGearGyroDrive(int encSetpoint, double gyroSetpoint) {
    	requires(Robot.base);
    	this.power = 0.8;
    	baseEncPID = new BaseEncPID(pEnc,iEnc,dEnc,encSetpoint,this.power,true,true);
    	baseGyroPID = new BaseGyroPID(pGyro,iGyro,dGyro,gyroSetpoint,this.power,true);
    	this.endpoint = 0;
    	this.onTargetCount = defaultTargetCount;
    }
    
    public HighGearGyroDrive(int encSetpoint, double gyroSetpoint, int endpoint) {
    	requires(Robot.base);
    	this.power = 0.8;
    	baseEncPID = new BaseEncPID(pEnc,iEnc,dEnc,encSetpoint,this.power,true,true);
    	baseGyroPID = new BaseGyroPID(pGyro,iGyro,dGyro,gyroSetpoint,this.power,true);
    	this.endpoint = endpoint;
    	this.onTargetCount = defaultTargetCount;
    }
    
    public HighGearGyroDrive(int encSetpoint, double gyroSetpoint, int onTargetCount, boolean weRDumm) {
    	requires(Robot.base);
    	this.power = 0.8;
    	baseEncPID = new BaseEncPID(pEnc,iEnc,dEnc,encSetpoint,this.power,true,true);
    	baseGyroPID = new BaseGyroPID(pGyro,iGyro,dGyro,gyroSetpoint,this.power,true);
    	this.endpoint = 0;
    	this.onTargetCount = onTargetCount;
    }    
    
    protected void initialize() {
    	count = 0;
    	Robot.base.encPwr = 0;
    	Robot.base.gyroPwr = 0;
    	baseEncPID.enable();
    	baseGyroPID.enable();
    	Robot.base.frontLeft.configOpenloopRamp(0.2, 0);
		Robot.base.backLeft.configOpenloopRamp(0.2, 0);
		Robot.base.frontRight.configOpenloopRamp(0.2, 0);
		Robot.base.backRight.configOpenloopRamp(0.2, 0);
    }

    protected void execute() {
    	Robot.base.drive(Robot.base.encPwr, Robot.base.gyroPwr);
    	if (baseGyroPID.onTarget() && baseEncPID.onTarget()) count++;
    	else count = 0;
    }

    protected boolean isFinished() {
    	if (endpoint != 0 && Math.abs(Robot.base.getEnc()) >= Math.abs(endpoint)) return true;
        return count > onTargetCount;
    }

    protected void end() {
    	Robot.base.frontLeft.configOpenloopRamp(0, 0);
		Robot.base.backLeft.configOpenloopRamp(0, 0);
		Robot.base.frontRight.configOpenloopRamp(0, 0);
		Robot.base.backRight.configOpenloopRamp(0, 0);
    	Robot.base.drive(0, 0);
    	baseEncPID.disable();
    	baseGyroPID.disable();
    }

    protected void interrupted() {
    	end();
    }
}
