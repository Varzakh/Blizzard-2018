package org.usfirst.frc.team188.robot.subsystems;

import org.usfirst.frc.team188.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorPID extends PIDSubsystem {
	
	double power;

    // Initialize your subsystem here
    public ElevatorPID(double p, double i, double d, double setpoint) {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("ElevatorPID",p,i,d);
    	setSetpoint(setpoint);
    	setAbsoluteTolerance(10);
    	this.power = 1.0;
    	
    }
    
    public ElevatorPID(double p, double i, double d, double setpoint, double power) {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("ElevatorPID",p,i,d);
    	setSetpoint(setpoint);
    	setAbsoluteTolerance(10);
    	//setOutputRange(-power, power);
    	this.power = power;
    }
    

    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.elevator.getElevatorEnc();
    }

    protected void usePIDOutput(double output) {
    	//Encoder is INVERTED
    	output *= power;
    	SmartDashboard.putNumber("PID Output", -output);
    	Robot.elevator.elevator1.set(ControlMode.PercentOutput, -output);
    	Robot.elevator.elevator2.set(ControlMode.PercentOutput,-output);
    	Robot.elevator.elevator3.set(ControlMode.PercentOutput, -output);
    	SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.getElevatorEnc());
    	
    }
}
