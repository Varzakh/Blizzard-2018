package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DisplaySensors extends Command {

    public DisplaySensors() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	SmartDashboard.putBoolean("light", Robot.elevator.getLimitSwitch());
		SmartDashboard.putString("bottom", Character.toString(Robot.elevator.getDirection()));
    	SmartDashboard.putNumber("Gyro", Robot.base.getGyro());
    	SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.getElevatorEnc());
    	SmartDashboard.putNumber("Base Enc", Robot.base.getEnc());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
