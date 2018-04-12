package org.usfirst.frc.team188.robot.subsystems;

import org.usfirst.frc.team188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */

public class Base extends Subsystem {
	
	public static DoubleSolenoid shifter = RobotMap.baseShifter;
	
	int encOffset;
	
	double low = 0.8, high = 1.0, speed = low;
	
	//Motors
	public TalonSRX frontLeft = new TalonSRX(RobotMap.frontLeft);
	public TalonSRX frontRight = new TalonSRX(RobotMap.frontRight);
	public TalonSRX backLeft = new TalonSRX(RobotMap.backLeft);
	public TalonSRX backRight = new TalonSRX(RobotMap.backRight);
	
	public void resetEnc() {
//		frontLeft.getSensorCollection().setQuadraturePosition(0, 0);
		encOffset += getEnc();
		
	}
	
	public int getEnc(){
		return -frontLeft.getSensorCollection().getQuadraturePosition() - encOffset;
	}
	
	public double frontLeftVoltage(){
		return frontLeft.getOutputCurrent();
	}
	public double frontRightVoltage(){
		return frontRight.getOutputCurrent();
	}
	public double backLeftVoltage(){
		return backLeft.getOutputCurrent();
	}
	public double backRightVoltage(){
		return backRight.getOutputCurrent();
	}
	
	
	//Encoder baseEnc = new Encoder(RobotMap.baseEncoderLeft1,RobotMap.baseEncoderLeft2);
	
	public AHRS navx = new AHRS(RobotMap.navxPort);
	
	public double encPwr = 0;
	public double gyroPwr = 0;
	
	public boolean pistonForward = false;
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Base() {
		encOffset = 0;
		frontLeft.configOpenloopRamp(0, 0);
		frontRight.configOpenloopRamp(0, 0);
		backLeft.configOpenloopRamp(0, 0);
		backRight.configOpenloopRamp(0, 0);
		/*not sure
		/*mannual overide to brake mode on talons */
		
	}
	
	
	
	

//	public int getBaseEnc() {
//		return baseEnc.get();
//	}
	
	public double getGyro() {
		//System.out.println(navx.getAngle());
		return navx.getAngle();
	}
	
	public double getPitch() {
		return navx.getPitch();
	}
	
	public double getRoll() {
		return navx.getRoll();
	}
	
	public double getYaw() {
		return navx.getYaw();
	}
	
	public void resetGyro() {
		navx.reset();
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void drive(double stickY, double stickX){ 
		frontLeft.set(ControlMode.PercentOutput,-( -stickY + stickX) * speed);
		backLeft.set(ControlMode.PercentOutput, -(-stickY + stickX) * speed);
		frontRight.set(ControlMode.PercentOutput, -(stickY + stickX) * speed);
		backRight.set(ControlMode.PercentOutput, -(stickY + stickX) * speed);
	}
	
	
	
	public void driveForward(double leftPower, double rightPower) {
		frontLeft.set(ControlMode.PercentOutput, leftPower * speed);
		backLeft.set(ControlMode.PercentOutput, leftPower * speed);
		frontRight.set(ControlMode.PercentOutput, -rightPower * speed);
		backRight.set(ControlMode.PercentOutput, -rightPower * speed);
	}
	
	public void shiftGears(Button shifterButton, Button shifterButton2){
		if(shifterButton.get()){
			shifter.set(Value.kForward);
			setSpeed(high);
		}
		else if (shifterButton2.get()){
			shifter.set(Value.kReverse);
			setSpeed(low);
		}
		
	}
	
	public void shiftGears(boolean shifterButton, boolean shifterButton2){
		if(shifterButton){
//			shifter.set(Value.kForward);
			setSpeed(high);
		}
		else if (shifterButton2){
//			shifter.set(Value.kReverse);
			setSpeed(low);
		}
		
	}
	
	public void shiftToLow() {
		shifter.set(Value.kReverse);
		setSpeed(low);
	}
	
	public void shiftToHigh() {
		shifter.set(Value.kForward);
		setSpeed(high);
	}
	
	public void stop(){
		frontLeft.set(ControlMode.PercentOutput, 0);
		backLeft.set(ControlMode.PercentOutput, 0);
		frontRight.set(ControlMode.PercentOutput, 0);
		backRight.set(ControlMode.PercentOutput, 0);
		shifter.set(Value.kOff);
	}
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

