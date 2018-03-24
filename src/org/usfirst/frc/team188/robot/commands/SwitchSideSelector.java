package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.automodes.ErrorAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSwitchAuto;
import org.usfirst.frc.team188.robot.automodes.RightSwitchAuto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchSideSelector extends Command {
	
	CommandGroup switchAuto;

    public SwitchSideSelector() {
    }

    protected void initialize() {
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L')
    		switchAuto = new LeftSwitchAuto();
    	else if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') 
    		switchAuto = new RightSwitchAuto();
    	else switchAuto = new ErrorAuto("SwitchSideSelectorW could not select a side.");
    	switchAuto.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	if(switchAuto != null && !switchAuto.isCanceled())
    		switchAuto.cancel();
    }

    protected void interrupted() {
    	end();
    }
}
