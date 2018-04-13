package org.usfirst.frc.team188.robot.subsystems;

import org.usfirst.frc.team188.robot.Constants;
import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
		
	int lastPressed = -1;
	boolean tilted = false;
	char direction = 'u';
	boolean[] presetPressed = {false,false,false,false};
	double upRamp = 0.0, downRamp = 0.4, teleRamp = 0.0;
	double power = 0.45;
	
	
	//Motors
	public TalonSRX elevator1 = new TalonSRX(RobotMap.elevator1);
	public TalonSRX elevator2 = new TalonSRX(RobotMap.elevator2);
	public TalonSRX elevator3 = new TalonSRX(RobotMap.elevator3);
	
	//Digital Sensors
	public DigitalInput lightSwitch = new DigitalInput(RobotMap.elevatorLightSwitch);
	public Encoder enc = new Encoder(RobotMap.elevatorEnc1,RobotMap.elevatorEnc2);
	
	ElevatorPID elevatorUpPID = new ElevatorPID(
			Constants.elevatorUpPID[0],
			Constants.elevatorUpPID[1],
			Constants.elevatorUpPID[2],
			0); 
	
	ElevatorPID elevatorDownSmallPID = new ElevatorPID(
			Constants.elevatorDownSmallPID[0],
			Constants.elevatorDownSmallPID[1],
			Constants.elevatorDownSmallPID[2],
			0); 
	
	ElevatorPID elevatorDownBigPID = new ElevatorPID(
			Constants.elevatorDownBigPID[0],
			Constants.elevatorDownBigPID[1],
			Constants.elevatorDownBigPID[2],
			0); 

	public Elevator() {
	}
	
	public char getDirection() {
		return direction;
	}
	
	public boolean getLimitSwitch() {
		return false;
//		return lightSwitch.get();
	}
	
	public boolean getDownSwitch() {
		return getLimitSwitch() && getElevatorEnc() < 200;
	}
	
	public boolean getUpSwitch() {
		return getLimitSwitch() && !getDownSwitch();
	}
	
	public boolean getElevatorInputActive() {
		if (Robot.m_oi.stick2.getRawButton(1) ||
	        Robot.m_oi.stick2.getRawButton(2) ||
	        Robot.m_oi.stick2.getRawButton(3) ||
	        Robot.m_oi.stick2.getRawButton(4) ||
	        Robot.m_oi.stick2.getRawButton(10) ||
	        Math.abs(Robot.m_oi.stick2.getRawAxis(3)) > 0.05)
			return true;
		return false;
	}
	
	public void rampElevator(double ramp) {
		Robot.elevator.elevator1.configOpenloopRamp(ramp, 0);
		Robot.elevator.elevator2.configOpenloopRamp(ramp, 0);
		Robot.elevator.elevator3.configOpenloopRamp(ramp, 0);
	}
	
	public void disablePID() {
		elevatorUpPID.disable();
		elevatorDownBigPID.disable();
		elevatorDownSmallPID.disable();
	}
	
	private void disablePID(ElevatorPID pid){
		if (pid.getPIDController().isEnabled()){
			pid.disable();
		}
	}
	
	public void moveElevator(){
		
		//Limit switch safety
		if(direction == 'd' && getDownSwitch()) {
			disablePID();
			stop();
		}
		else if(direction == 'u' && getUpSwitch()) {
			disablePID();
			stop();
		}
						
		//Robot tipping safety
		if ((Math.abs(Robot.base.getPitch()) > 30 || Math.abs(Robot.base.getRoll()) > 30) && getElevatorEnc() > Constants.elevatorPresets[1]) {
			tilted = true;
		} else {
			tilted = false;
		}
		
		//Button / Joystick inputs
		for(int i = 0; i < Robot.m_oi.elevatorPresets.length; i++){
			if (Robot.m_oi.elevatorPresets[i].get()){
				lastPressed = i; //Preset to use
				break;
			} else if (i == Robot.m_oi.elevatorPresets.length - 1 || Math.abs(Robot.m_oi.stick2.getRawAxis(3)) > 0.05){
				lastPressed = -1; //Joystick input
				disablePID(elevatorUpPID);
				disablePID(elevatorDownBigPID);
				disablePID(elevatorDownSmallPID);
			}
		}
		
		
		//Antitip enables down PID to preset 1, only if the elevator has not already hit the bottom limit switch
		if (tilted) {
			
			direction = 'd';
			rampElevator(downRamp);
			
			if (Robot.elevator.getElevatorEnc() - Constants.elevatorPresets[1] > 300){
				elevatorDownBigPID.setSetpoint(Constants.elevatorPresets[1]);
				elevatorDownBigPID.enable();
				disablePID(elevatorDownSmallPID);
				disablePID(elevatorUpPID);
			}
			else{
				elevatorDownSmallPID.setSetpoint(Constants.elevatorPresets[1]);
				elevatorDownSmallPID.enable();
				disablePID(elevatorUpPID);
				disablePID(elevatorDownBigPID);
			}
		}
		
		else if (lastPressed != -1){
			if (Robot.elevator.getElevatorEnc() < Constants.elevatorPresets[lastPressed]){
				
				direction = 'u';
				rampElevator(upRamp);
				
				elevatorUpPID.setSetpoint(Constants.elevatorPresets[lastPressed]);
				elevatorUpPID.enable();
				disablePID(elevatorDownSmallPID);
				disablePID(elevatorDownBigPID);
				
			}
			else {
				
				direction = 'd';
				rampElevator(downRamp);
				
				if (Robot.elevator.getElevatorEnc() - Constants.elevatorPresets[lastPressed] > 300){
					elevatorDownBigPID.setSetpoint(Constants.elevatorPresets[lastPressed]);
					elevatorDownBigPID.enable();
					disablePID(elevatorDownSmallPID);
					disablePID(elevatorUpPID);
				}
				else{
					elevatorDownSmallPID.setSetpoint(Constants.elevatorPresets[lastPressed]);
					elevatorDownSmallPID.enable();
					disablePID(elevatorUpPID);
					disablePID(elevatorDownBigPID);
				}				
			}
		}
		else {
			disablePID(elevatorUpPID);
			disablePID(elevatorDownBigPID);
			disablePID(elevatorDownSmallPID);
			rampElevator(teleRamp);
			
			direction = 'a';
			
			elevator1.set(ControlMode.PercentOutput, Robot.m_oi.stick2.getRawAxis(3)*power);
			elevator2.set(ControlMode.PercentOutput, Robot.m_oi.stick2.getRawAxis(3)*power);
			elevator3.set(ControlMode.PercentOutput, Robot.m_oi.stick2.getRawAxis(3)*power);
		}
	}
	
	public ElevatorPID moveElevator(int position){

		if (Robot.elevator.getElevatorEnc() < Constants.elevatorPresets[position]){
			elevatorUpPID.setSetpoint(Constants.elevatorPresets[position]);
			disablePID();
			return elevatorUpPID;
		}
		else{
			if (Robot.elevator.getElevatorEnc() - Constants.elevatorPresets[position] > 300){
				elevatorDownBigPID.setSetpoint(Constants.elevatorPresets[position]);
				disablePID();
				return elevatorDownBigPID;
			}
			else{
				elevatorDownSmallPID.setSetpoint(Constants.elevatorPresets[position]);
				disablePID();
				return elevatorDownSmallPID;
			}
		}
		
	}
	
	public ElevatorPID getActivePID() {
		
		if (elevatorUpPID.getPIDController().isEnabled())
			return elevatorUpPID;
		if (elevatorDownBigPID.getPIDController().isEnabled())
			return elevatorDownBigPID;
		if (elevatorDownSmallPID.getPIDController().isEnabled())
			return elevatorDownSmallPID;
		
		return null;
		
	}
	
	public double getActiveSetpoint() {
		
		if (elevatorUpPID.getPIDController().isEnabled())
			return elevatorUpPID.getSetpoint();
		if (elevatorDownBigPID.getPIDController().isEnabled())
			return elevatorDownBigPID.getSetpoint();
		if (elevatorDownSmallPID.getPIDController().isEnabled())
			return elevatorDownSmallPID.getSetpoint();
		
		return -1;
	}
	
	
	public double getElevatorEnc(){
		return enc.get();
	}
	
	public void stop(){
		elevator1.set(ControlMode.PercentOutput, 0);
		elevator2.set(ControlMode.PercentOutput, 0);
		elevator3.set(ControlMode.PercentOutput, 0);
	}

    public void initDefaultCommand() {
    }
}

