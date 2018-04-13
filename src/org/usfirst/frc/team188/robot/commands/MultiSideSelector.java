package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.automodes.ErrorAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSideLeftScaleAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSideLeftSwitchAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSwitchAuto;
import org.usfirst.frc.team188.robot.automodes.MobilityAuto;
import org.usfirst.frc.team188.robot.automodes.RightSwitchAuto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MultiSideSelector extends Command {
	
	CommandGroup switchAuto;

    public MultiSideSelector() {
    }

    protected void initialize() {
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L')
    		switchAuto = new LeftSideLeftScaleAuto();
    	else if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') 
    		switchAuto = new LeftSideLeftSwitchAuto();
    	else switchAuto = new MobilityAuto();
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
