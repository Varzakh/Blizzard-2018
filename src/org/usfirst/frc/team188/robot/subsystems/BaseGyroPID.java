package org.usfirst.frc.team188.robot.subsystems;

import org.usfirst.frc.team188.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BaseGyroPID extends PIDSubsystem {

	double power;
	boolean store;
	
    // Initialize your subsystem here
    public BaseGyroPID(double p, double i, double d, double setpoint) {
    	super("Base Gyro PID",p,i,d);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(setpoint);
    	setAbsoluteTolerance(7);
    	this.power = 0.0;
    	this.store = false;
    	disable();
    }

    public BaseGyroPID(double p, double i, double d, double setpoint, double power) {
    	super("Base Gyro PID",p,i,d);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(setpoint);
    	setAbsoluteTolerance(7);
    	this.power = power;
    	this.store = false;
    	disable();
    }
    
    public BaseGyroPID(double p, double i, double d, double setpoint, double power, boolean store) {
    	super("Base Gyro PID",p,i,d);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(setpoint);
    	setAbsoluteTolerance(7);
    	this.power = power;
    	this.store = store;
    	disable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        //Converts the Gyro output to a fixed angle instead of the raw displacement in degrees
    	double input =  Robot.base.getGyro() % 360;
    	/*Makes the Robot take the shortest path to get to an angle (e.g. If we want to get to 90 degrees from 0 degrees, 
    	 we move 90 degrees clockwise, and not 270 degrees counterclockwise)*/
    	if(Math.abs(getSetpoint() - input) > Math.abs(getSetpoint() - input + 360)){
    		input = input - 360;
    	}
    	if(Math.abs(getSetpoint() - input) > Math.abs(getSetpoint() - input - 360)){
    		input = input + 360;
    	}   	 
    	return input;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	    	
//    	Robot.base.encPwr = 0.8; //FOR TUNING, REMOVE LATER
    	
    	SmartDashboard.putNumber("Gyro PID",output);
    	SmartDashboard.putNumber("Gyro", Robot.base.getGyro());
    	if (!store)
    		Robot.base.drive(0, output*(1- power)); //-power
    	else
    		Robot.base.gyroPwr = output*(1- power);
    	//System.out.print("Angle: ");
    	//Robot.base.getGyro();
    }
}
