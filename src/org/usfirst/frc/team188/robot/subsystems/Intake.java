package org.usfirst.frc.team188.robot.subsystems;

import org.usfirst.frc.team188.robot.Constants;
import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	boolean timerStarted;
	Timer t;
	
//	double intakePower = 1.0, outtakePower = 1.0;
	
	public static DoubleSolenoid pivotPiston = RobotMap.intakePivotPiston;
	
	//Motors
	public TalonSRX intakeLeft = new TalonSRX(RobotMap.intakeLeft);
	public TalonSRX intakeRight = new TalonSRX(RobotMap.intakeRight);
	
	//Digital Sensors
	public DigitalInput lightSensor = new DigitalInput(RobotMap.intakeLightSensor);
	
	public Intake() {
		t = new Timer();
		timerStarted = false;
	}
	
	public void moveIntake(){
		
		// If stick registering input
		if (Robot.m_oi.slowestOuttake.get()) {
			intakeLeft.set(ControlMode.PercentOutput, 0.1);
			intakeRight.set(ControlMode.PercentOutput, -0.1);
		} else if (Robot.m_oi.slowOuttake.get()) {
			intakeLeft.set(ControlMode.PercentOutput, 0.25);
			intakeRight.set(ControlMode.PercentOutput, -0.25);
		} else if (Math.abs(Robot.m_oi.stick2.getRawAxis(1)) > 0.05) {
			// If outtaking, half speed
			if (Robot.m_oi.stick2.getRawAxis(1) < 0) {
				intakeLeft.set(ControlMode.PercentOutput, -Robot.m_oi.stick2.getRawAxis(1)*0.5);
				intakeRight.set(ControlMode.PercentOutput, Robot.m_oi.stick2.getRawAxis(1)*0.5);
			} else {
				intakeLeft.set(ControlMode.PercentOutput, -Robot.m_oi.stick2.getRawAxis(1)*0.7);
				intakeRight.set(ControlMode.PercentOutput, Robot.m_oi.stick2.getRawAxis(1)*0.7);
			}
		} else if ((!timerStarted || t.get() > 1.0)
				&& Robot.elevator.getElevatorEnc() < Constants.elevatorPresets[2] - 15) {
			// Trim, if timer is started and past 1 second and elevator is below scale preset
			intakeLeft.set(ControlMode.PercentOutput, -0.35);
			intakeRight.set(ControlMode.PercentOutput, 0.35);
		} else {
			intakeLeft.set(ControlMode.PercentOutput, 0.0);
			intakeRight.set(ControlMode.PercentOutput, 0.0);
		}
	}
	
	public void drive(double power){
		intakeLeft.set(ControlMode.PercentOutput, power);
		intakeRight.set(ControlMode.PercentOutput, -power);
	}
	
	public void drive(double leftPower,double rightPower) {
		intakeLeft.set(ControlMode.PercentOutput, leftPower);
		intakeRight.set(ControlMode.PercentOutput, -rightPower);
	}
	
	public void pivot(char dir){
		if (dir == 'u')
			pivotPiston.set(Value.kForward);
		else if(dir == 'd')
			pivotPiston.set(Value.kReverse);
	}
	
	public void pivotIntake(){
		if (Robot.m_oi.pivotIn.get())
			pivotPiston.set(Value.kForward);
		else if(Robot.m_oi.pivotOut.get())
			pivotPiston.set(Value.kReverse);
	}
	
	public void stop(){
		intakeLeft.set(ControlMode.PercentOutput, 0);
		intakeRight.set(ControlMode.PercentOutput, 0);
		pivotPiston.set(Value.kOff);
	}
	
    public void initDefaultCommand() {
    }
}

