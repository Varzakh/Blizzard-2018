package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.automodes.LeftSide3CubeAuto;
import org.usfirst.frc.team188.robot.automodes.MobilityAuto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElimsSideSelector extends Command {
	
	CommandGroup elimsAuto;

    public ElimsSideSelector() {
    }

    protected void initialize() {
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L')
    		elimsAuto = new LeftSide3CubeAuto();
//    	else if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') 
//    		switchAuto = new LeftSideLeftSwitchAuto();
    	else elimsAuto = new MobilityAuto();
    	elimsAuto.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	if(elimsAuto != null && !elimsAuto.isCanceled())
    		elimsAuto.cancel();
    }

    protected void interrupted() {
    	end();
    }
}
