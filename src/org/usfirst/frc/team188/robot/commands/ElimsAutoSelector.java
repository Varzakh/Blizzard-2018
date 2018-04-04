package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.automodes.LeftSideLeftScaleAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSideLeftSwitchAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSideRightSwitchAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSideScaleSwitchAuto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElimsAutoSelector extends Command {

	CommandGroup selectedAuto;
	
    public ElimsAutoSelector() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
    		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L')
    			selectedAuto = new LeftSideScaleSwitchAuto();
    		else
    			selectedAuto = new LeftSideLeftScaleAuto();
    	} else {
    		if (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L')
    			selectedAuto = new LeftSideLeftSwitchAuto();
    		else
    			selectedAuto = new LeftSideRightSwitchAuto();
    	}
    	selectedAuto.start();
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
    	if(selectedAuto != null && !selectedAuto.isCanceled()) 
    		selectedAuto.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
