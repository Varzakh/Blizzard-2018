/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team188.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//Base Motors
	public static int frontLeft = 2;
	public static int backLeft = 3;
	public static int frontRight = 4;
	public static int backRight = 11; 

	//Base Digital Sensors
	public static int baseEncoderLeft1 = 3;
	public static int baseEncoderLeft2 = 4;
	
	//Base Pneumatics
	public static DoubleSolenoid baseShifter = new DoubleSolenoid(2,5);
	
	//Elevator Motors
	public static int elevator1 = 6;
	public static int elevator2 = 7;
	public static int elevator3 = 8;
	
	//Elevator Digital Sensors
	public static int elevatorLightSwitch = 2;
	public static int elevatorEnc1 = 1;
	public static int elevatorEnc2 = 0;
	
	//Intake Motors
	public static int intakeLeft = 9;
	public static int intakeRight = 12;
	
	//Intake Digital Sensors
	public static int intakeLightSensor = 13;
	
	//Shifter Pneumatics
	public static Compressor compressor = new Compressor(0);
	
	//Hang Pneumatics
	public static DoubleSolenoid hangPiston = new DoubleSolenoid(3,4);
	
	//Intake Pneumatics
	public static DoubleSolenoid intakePivotPiston = new DoubleSolenoid(1,6); //may be 0, 7

	//navX
	public static Port navxPort = Port.kMXP;
	

}
