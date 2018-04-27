/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team188.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team188.robot.automodes.MobilityAuto;
import org.usfirst.frc.team188.robot.automodes.MultiAutoInitializer;
import org.usfirst.frc.team188.robot.automodes.ScaleAutoInitializer;
import org.usfirst.frc.team188.robot.automodes.SwitchAutoInitializer;
import org.usfirst.frc.team188.robot.automodes.TestingAuto;
import org.usfirst.frc.team188.robot.commands.TeleopCommandGroup;
import org.usfirst.frc.team188.robot.subsystems.Base;
import org.usfirst.frc.team188.robot.subsystems.Elevator;
import org.usfirst.frc.team188.robot.subsystems.Intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;


public class Robot extends TimedRobot {
	
	public static String gameData;
	public static int selectedAuto;
	boolean prevChangeAuto;
	String[] autos;
	
	public static OI m_oi;
	public static Base base;
	public static Elevator elevator;
	public static Intake intake;
		
	CommandGroup teleopCommandGroup;
	CommandGroup[] autoCommandGroups;
	CommandGroup autoCommandGroup;
	
	@Override
	public void robotInit() {
		m_oi = new OI();
		base = new Base();
		elevator = new Elevator();
		intake = new Intake();
		
		prevChangeAuto = false;
		selectedAuto = 0;
		
		teleopCommandGroup = new TeleopCommandGroup();
		autos = new String[] {
			"Multi Auto",
			"Scale Auto",
			"Switch Auto",
			"Mobility Auto",
			"Test Auto"
		};
		
		Robot.elevator.elevator1.configOpenloopRamp(0, 0);
		Robot.elevator.elevator2.configOpenloopRamp(0, 0);
		Robot.elevator.elevator3.configOpenloopRamp(0, 0);
		
		Robot.base.frontLeft.configOpenloopRamp(0, 0);
		Robot.base.backLeft.configOpenloopRamp(0, 0);
		Robot.base.frontRight.configOpenloopRamp(0, 0);
		Robot.base.backRight.configOpenloopRamp(0, 0);
	}

	@Override
	public void disabledInit() {
		if (autoCommandGroup != null && autoCommandGroup.isRunning())
			autoCommandGroup.cancel();
		if (autoCommandGroups == null) {
			autoCommandGroups = new CommandGroup[] {
				new MultiAutoInitializer(),
				new ScaleAutoInitializer('l'),
				new SwitchAutoInitializer(),
				new MobilityAuto(),
				new TestingAuto()
			};
		}
		
		base.resetEnc();
		
		SmartDashboard.putNumber("Base Enc P", 0.0);
    	SmartDashboard.putNumber("Base Enc I", 0.0);
    	SmartDashboard.putNumber("Base Enc D", 0.0);
    	SmartDashboard.putNumber("Base Enc Setpoint", 0.0);
    	
    	System.out.println("Auto Mode: " + autos[selectedAuto]);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		if (!prevChangeAuto && m_oi.stick.getRawButton(6)){
			++selectedAuto;
		} else if(!prevChangeAuto && m_oi.stick.getRawButton(5)){
			--selectedAuto;
		}
		
		if(selectedAuto >= autos.length) selectedAuto = 0;
		else if(selectedAuto < 0) selectedAuto = autos.length - 1;
		
		prevChangeAuto = m_oi.stick.getRawButton(5) || m_oi.stick.getRawButton(6);
		SmartDashboard.putString("Auto Mode", autos[selectedAuto]);
		if (prevChangeAuto)
			System.out.println("Auto Mode: " + autos[selectedAuto]);
		
		if(Robot.m_oi.resetEncButton.get()){
			Robot.elevator.enc.reset();
			Robot.base.resetEnc();
			System.out.println("Base and elevator encoders zeroed.");
		}
		
		if(Robot.m_oi.resetGyroButton.get()){
			Robot.base.resetGyro();
			System.out.println("Gyro zeroed.");
		}
		
		SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.getElevatorEnc());
		SmartDashboard.putNumber("Base Enc", Robot.base.getEnc());
		SmartDashboard.putNumber("Gyro", Robot.base.getGyro());
		SmartDashboard.putBoolean("Elevator Light Sensor", Robot.elevator.getLimitSwitch());
		
	}


	@Override
	public void autonomousInit() {		
		if (teleopCommandGroup.isRunning())
			teleopCommandGroup.cancel();
		
		autoCommandGroup = autoCommandGroups[selectedAuto];
		if (!autoCommandGroup.isRunning())
			autoCommandGroup.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Robot.base.shiftToHigh();
    	Robot.base.frontLeft.setNeutralMode(NeutralMode.Brake);
    	Robot.base.frontRight.setNeutralMode(NeutralMode.Brake);
    	Robot.base.backLeft.setNeutralMode(NeutralMode.Brake);
    	Robot.base.backRight.setNeutralMode(NeutralMode.Brake);
    	
		if (autoCommandGroup != null && autoCommandGroup.isRunning())
			autoCommandGroup.cancel();
		teleopCommandGroup.start();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Elevator Light Sensor", Robot.elevator.getLimitSwitch());
		SmartDashboard.putNumber("Elevator Power", Robot.elevator.elevator1.getMotorOutputVoltage());
	}


	@Override
	public void testPeriodic() {
	}
}
