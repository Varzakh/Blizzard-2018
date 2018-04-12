package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.DelayedCommand;
import org.usfirst.frc.team188.robot.commands.TuneBaseEncPID;
import org.usfirst.frc.team188.robot.commands.TuneBaseGyroPID;
import org.usfirst.frc.team188.robot.commands.TuneElevatorPID;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroArc;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.MoveElevator;
import org.usfirst.frc.team188.robot.controlcommands.MoveIntake;
import org.usfirst.frc.team188.robot.controlcommands.PivotIntake;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestingAuto extends CommandGroup {

    public TestingAuto() {
//    	addParallel(new MoveElevator(1));
//    	addSequential(new PivotIntake('u'));
//    	addSequential(new DelayedCommand(1.0,new Command[] {new MoveIntake(1.0,1.0),new PivotIntake('d')}));
//    	addSequential(new MoveIntake(-0.5,5));

//    	addSequential(new TuneBaseEncPID());
    	
    	addSequential(new ShiftGears('l'));
    	addSequential(new LowGearGyroDrive(225000,0));
    }
}
