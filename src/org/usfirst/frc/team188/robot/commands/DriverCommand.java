package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriverCommand extends Command {
	
//	double maxCurrent = 40;
//	double maxTime = 100000;
//	double lastTime = 1000000;
//	Timer t;

    public DriverCommand() {
    	requires(Robot.base);
    }

    protected void initialize() {
    	Robot.base.frontLeft.configOpenloopRamp(0, 0);
    	Robot.base.frontRight.configOpenloopRamp(0, 0);
    	Robot.base.backLeft.configOpenloopRamp(0, 0);
    	Robot.base.backRight.configOpenloopRamp(0, 0);
//    	t = new Timer();
//    	t.start();
    }

    protected void execute() {
    	
//    	Base Controls
    	Robot.base.drive(Robot.m_oi.stick.getRawAxis(1), Robot.m_oi.stick.getRawAxis(4));
    	Robot.base.shiftGears(Robot.m_oi.shifterButton, Robot.m_oi.shifterButton2);
    	
//    	Intake Controls
    	Robot.intake.pivotIntake();
    	Robot.intake.moveIntake();   	
   
//    	Elevator Controls
    	Robot.elevator.moveElevator();

//    	Current limiting on elevator
//   	if(Robot.elevator.elevator1.getOutputCurrent() < maxCurrent && 
//    			Robot.elevator.elevator2.getOutputCurrent() < maxCurrent && 
//    			Robot.elevator.elevator3.getOutputCurrent() < maxCurrent){
//   	 	lastTime = t.get();
//   	 }
//   	 	
//    	if(t.get() - lastTime > maxTime){
//    		Robot.elevator.stop();
//    	}


    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.base.stop();
    	Robot.elevator.stop();
    	Robot.intake.stop();
    }

    protected void interrupted() {
    	Robot.base.stop();
    	Robot.elevator.stop();
    	Robot.intake.stop();
    }
}
