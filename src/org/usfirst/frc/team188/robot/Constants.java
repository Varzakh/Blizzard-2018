package org.usfirst.frc.team188.robot;

public class Constants {
	
	public static double[] elevatorUpPID = {0.008, 0, 0.012}; //0.006, 0, 0.009
	public static double[] elevatorDownSmallPID = {0.007, 0, 0.011}; //0.005, 0, 0.01
	public static double[] elevatorDownBigPID = {0.0055, 0, 0.012}; //0.0055, 0, 0.023
	
	public static double[] highGearGyroPID = {0.3, 0, 0.3};
	public static double[] gyroTurnPID = {0.04, 0, 0.075};
	public static double[] gyroArcPID = {0.08, 0, 0.1};
	public static double[] gyroStraightPID = {0.3, 0, 0.3};
	
	public static double[] highGearBaseEncPID = {0.000006, 0, 0.009}; //0.000007, 0, 0.00008
	public static double[] baseEncPID = {0.00001, 0, 0.000053}; //0.000037071, 0, 0.00002652 P reduced to minimize overshoot (sketch)
	public static double[] baseArcPID = {0.000055, 0, 0.00008};
	public static double[] baseLowEncPID = {0.00008, 0, 0.0001};
	
	public static double[] elevatorPresets = {38, 250, 625, 865, 645}; //35, 235, 415, 610, 465
	
}
