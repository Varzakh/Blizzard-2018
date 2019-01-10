package org.usfirst.frc.team188.robot.commands;

import java.util.List;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import org.usfirst.frc.team188.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import com.opencsv.CSVReader;

/**
 *
 */
public class DriverCommand extends Command {
	Reader leftReader, rightReader;
	CSVReader leftCsvReader, rightCsvReader;
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
    	try {
    		URI leftPath = new URI("leftPath.csv");
    		URI rightPath = new URI("rightPath.csv");
    		leftReader = Files.newBufferedReader(Paths.get(leftPath));
    		leftCsvReader = new CSVReader(leftReader);
    		rightReader = Files.newBufferedReader(Paths.get(rightPath));
    		rightCsvReader = new CSVReader(leftReader);
    	} catch (Exception e){
    	}	
    	lastTime = System.currentTimeMillis(); 
//    	t = new Timer();
//    	t.start();
    }
    long lastTime;
	List<String> leftNext = null;
	List<String> rightNext = null;
    protected void execute() {
    	if (leftNext == null || System.currentTimeMillis() > lastTime + 10){
    		try {
    			leftNext = new ArrayList<String>();
    			Collections.addAll(leftNext, leftCsvReader.readNext());
    			rightNext = new ArrayList<String>();
    			Collections.addAll(rightNext,rightCsvReader.readNext());
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    	double leftPos = Double.parseDouble(leftNext.get(0));
    	double rightPos = Double.parseDouble(rightNext.get(0));
    	
    	double leftVel = Double.parseDouble(leftNext.get(1));
    	double rightVel = Double.parseDouble(rightNext.get(1));
    	double vMax = 8.0;
    	
//    	Base Controls
    	Robot.base.drive(leftVel/8.0, rightVel/8.0);
    	//Robot.base.shiftGears(Robot.m_oi.shifterButton, Robot.m_oi.shifterButton2);
    	
////    	Intake Controls
//    	Robot.intake.pivotIntake();
//    	Robot.intake.moveIntake();   	
//   
////    	Elevator Controls
//    	Robot.elevator.moveElevator();

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
