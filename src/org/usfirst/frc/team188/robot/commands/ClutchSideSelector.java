package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.automodes.ClutchAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSide3CubeAuto;
import org.usfirst.frc.team188.robot.automodes.MobilityAuto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ClutchSideSelector extends Command {

	CommandGroup clutchAuto;
	
    public ClutchSideSelector() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L')
    		clutchAuto = new LeftSide3CubeAuto();
//    	else if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') 
//    		switchAuto = new LeftSideLeftSwitchAuto();
    	else clutchAuto = new ClutchAuto();
    	clutchAuto.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(clutchAuto != null && !clutchAuto.isCanceled())
    		clutchAuto.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
