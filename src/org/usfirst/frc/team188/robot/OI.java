/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team188.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Joystick stick = new Joystick(0);
	public Joystick stick2 = new Joystick(1);
	
	//Stick 1
	public Button resetEncButton = new JoystickButton(stick,1);
	public Button resetGyroButton = new JoystickButton(stick,2);
	public Button resetBaseEncButton = new JoystickButton(stick,10);
	public Button shifterButton = new JoystickButton(stick,6);
	public Button shifterButton2 = new JoystickButton(stick,5);
	
	//Stick 2
	public Button intakeButton = new JoystickButton(stick2,5);
	public Button outtakeButton = new JoystickButton(stick2,6);
	public Button pivotUp = new JoystickButton(stick2, 9);
	public Button pivotDown = new JoystickButton(stick2,10);
	public Button pivotIn = new JoystickButton(stick2, 7);
	public Button pivotOut = new JoystickButton(stick2,8);
	public Button winchButton = new JoystickButton(stick2,12);
	public Button hang = new JoystickButton(stick2,10);
	
	public Button[] elevatorPresets = 
			{new JoystickButton(stick2,1), 
			new JoystickButton(stick2,2), 
			new JoystickButton(stick2,3), 
			new JoystickButton(stick2,4),
			new JoystickButton(stick2,10)};
	

	

}
