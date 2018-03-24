package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team188.robot.subsystems.ElevatorPID;

/**
 *
 */
public class TuneElevatorPID extends Command {

	PIDSubsystem elevatorPID; 
	
    public TuneElevatorPID() {
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevatorPID = new ElevatorPID(SmartDashboard.getNumber("Elevator P", 0.0), 
    			SmartDashboard.getNumber("Elevator I", 0.0), 
    			SmartDashboard.getNumber("Elevator D", 0.0), 
    			SmartDashboard.getNumber("Elevator Setpoint", 0.0));
    	elevatorPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(SmartDashboard.getNumber("Elevator P", 0.0) != elevatorPID.getPIDController().getP() || 
    	    	SmartDashboard.getNumber("Elevator I", 0.0) != elevatorPID.getPIDController().getI() || 
    	    	SmartDashboard.getNumber("Elevator D", 0.0) != elevatorPID.getPIDController().getD() || 
    	    	SmartDashboard.getNumber("Elevator Setpoint", 0.0) != elevatorPID.getPIDController().getSetpoint()){
    		elevatorPID.disable();
    		elevatorPID = new ElevatorPID(SmartDashboard.getNumber("Elevator P", 0.0), 
    				SmartDashboard.getNumber("Elevator I", 0.0), 
    				SmartDashboard.getNumber("Elevator D", 0.0), 
    				SmartDashboard.getNumber("Elevator Setpoint", 0.0));
        	elevatorPID.enable();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	elevatorPID.disable();
    	Robot.elevator.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
