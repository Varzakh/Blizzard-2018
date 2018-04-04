package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.TuneBaseEncPID;
import org.usfirst.frc.team188.robot.commands.TuneBaseGyroPID;
import org.usfirst.frc.team188.robot.commands.TuneElevatorPID;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroArc;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestingAuto extends CommandGroup {

    public TestingAuto() {
    	
    	addSequential(new LowGearGyroDrive(270000, 0));

    }
}
