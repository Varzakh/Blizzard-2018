package org.usfirst.frc.team188.robot.controlcommands;

import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.subsystems.ElevatorPID;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevator extends Command {
	
	ElevatorPID elevatorPID;
	boolean wasHanging = false;
	int position;
	int targetCount = 0;
	
    public MoveElevator(int position) {
    	requires(Robot.elevator);
    	this.position = position;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevatorPID = Robot.elevator.moveElevator(position);
    	 elevatorPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (DriverStation.getInstance().isAutonomous()) return false;
    	if(wasHanging && !Robot.m_oi.hang.get()) return true;
    	wasHanging = Robot.m_oi.hang.get();
    	if(Robot.elevator.getElevatorInputActive()) return true;
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
