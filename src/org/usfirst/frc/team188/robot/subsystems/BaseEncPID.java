package org.usfirst.frc.team188.robot.subsystems;

import org.usfirst.frc.team188.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BaseEncPID extends PIDSubsystem {
	
	double power;
	boolean store;
	
    // Initialize your subsystem here
    public BaseEncPID(double p, double i, double d, double setpoint) {
    	super("BaseEncPID", p, i, d);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(setpoint);
    	setAbsoluteTolerance(10000);
    	this.power = 1.0;
    	this.store = false;
    	disable();
    }

    public BaseEncPID(double p, double i, double d, double setpoint, double power) {
    	super("BaseEncPID", p, i, d);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(setpoint);
    	setAbsoluteTolerance(10000);
    	this.power = power;
    	this.store = false;
    	disable();
    }
    
    public BaseEncPID(double p, double i, double d, double setpoint, double power, boolean store) {
    	super("BaseEncPID", p, i, d);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(setpoint);
    	setAbsoluteTolerance(10000);
    	this.power = power;
    	this.store = store;
    	disable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        //return Robot.base.getBaseEnc();
    	return Robot.base.getEnc();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	output = -output;
    	SmartDashboard.putNumber("Base Enc", Robot.base.getEnc());
    	SmartDashboard.putNumber("Base Enc PID", output);
    	
    	if (!store)
    		Robot.base.drive(output*power, 0.0);
    	else
    		Robot.base.encPwr = output*power;
    }
}
