package org.texastorque.texastorque20145.constants;

import org.texastorque.texastorque20145.torquelib.Parameters.Constant;

public class Constants {
    public final static Constant autoMode = new Constant("A_Mode", 0);
    
    //  -----  Compressor -----
    public final static Constant compressorSwitch = new Constant("P_Switch", 1);
    public final static Constant compressorRelay = new Constant("P_Relay", 8);
    
    //  -----  Drivebase  -----
    public final static Constant leftDrivePort = new Constant("D_LeftPort", 1);
    public final static Constant rightDrivePort = new Constant("D_RightPort", 9);
    public final static Constant shifterPort = new Constant("D_Shifter", 4);
    public final static Constant leftDriveEncoderAPort = new Constant("D_LeftDriveEncoderA", 6);
    public final static Constant leftDriveEncoderBPort = new Constant("D_LeftDriveEncoderB", 7);
    public final static Constant rightDriveEncoderAPort = new Constant("D_RightDriveEncoderA", 8);
    public final static Constant rightDriveEncoderBPort = new Constant("D_RightDriveEncoderB", 9);
    
    public final static Constant feetPerClick = new Constant("D_FeetPerClick", 0.003403392);;
    
    public final static Constant lowGearSensitivity = new Constant("D_LowSensitivity", 0.75);
    public final static Constant highGearSensitivity = new Constant("D_HighSensitivity", 0.75);
    
    //  -----  Shooter  -----
    public final static Constant shooterAPort = new Constant("S_ShooterAPort", 6);
    public final static Constant shooterBPort = new Constant("S_ShooterBPort", 4);
    public final static Constant shooterCounterPort = new Constant("S_ShooterCounter", 5);
    
    public final static Constant fenderRPM = new Constant("S_FenderRPM", 4500);
    public final static Constant farRPM = new Constant("S_FarRPM", 5000);
    public final static Constant runFarRPM = new Constant("S_RunFarRPM", 5000);
    public final static Constant inboundRPM = new Constant("S_InboundRPM", 1000);
    public final static Constant lowGoalRPM = new Constant("S_LowGoalRPM", 500);
    public final static Constant offRPM = new Constant("S_OffRPM", 0);
    public final static Constant rpmDoneRange = new Constant("S_DoneRange", 100);
    public final static Constant rpmFilterSize = new Constant("S_RPMFilterSize", 10);
    
    public final static Constant inboundPower = new Constant("S_InboundPower", -0.35);
    
    public final static Constant openLoopFenderPower = new Constant("S_OpenLoopFenderPower", 0.8);
    public final static Constant openLoopInboundPower = new Constant("S_OpenLoopInboundPower", -0.2);
    public final static Constant openLoopLowGoalPower = new Constant("S_OpenLoopLowGoalPower", 0.2);
    
    //  -----  Clapper  -----
    public final static Constant clapperFrontPort = new Constant("C_ClapperFrontPort", 5);
    public final static Constant clapperRearPort = new Constant("C_ClapperRearPort", 3);
    
    public final static Constant shootDifference = new Constant("C_UpDifference", 0.09);
    
    //  ----- Intake  -----
    public final static Constant downAngle = new Constant("I_DownAngle", -80.0);
    public final static Constant upAngle = new Constant("I_UpAngle", 90);
    public final static Constant inAngle = new Constant("I_InAngle", -120);
    public final static Constant intakeFrontAngle = new Constant("I_IntakeFrontAngle", -20);
    public final static Constant intakeRearAngle = new Constant("I_IntakeRearAngle", 0);
    public final static Constant outtakeFrontAngle = new Constant("I_OuttakeFrontAngle", 0);
    public final static Constant outtakeRearAngle = new Constant("I_OuttakeRearAngle", 0);
    public final static Constant frontCarryAngle = new Constant("I_FrontCarryAngle", 90);
    public final static Constant rearCarryAngle = new Constant("I_RearCarryAngle", 90);
    public final static Constant frontHoldAngle = new Constant("I_FrontHoldAngle", 90);
    public final static Constant rearHoldAngle = new Constant("I_RearHoldAngle", 90);
    
    public final static Constant intakeDegreesPerClick = new Constant("I_DegreesPerClick", 0.28);
    public final static Constant intakeZeroAngle = new Constant("I_ZeroAngle", 90);
    public final static Constant carryPower = new Constant("I_CarryPower", 0.5);
    
    //Front
    public final static Constant frontIntakeEncoderAPort = new Constant("I_FrontIntakeEncoderA", 3);
    public final static Constant frontIntakeEncoderBPort = new Constant("I_FrontIntakeEncoderB", 2);
    public final static Constant frontIntakeHallEffect = new Constant("I_FrontIntakeHallEffect", 14);
    public final static Constant frontIntakeAnglePort = new Constant("I_FrontIntakeAnglePort", 10);
    public final static Constant frontIntakeRollerPort = new Constant("I_FrontIntakeRollerPort", 5);
    
    public final static Constant frontIntakeKff = new Constant("I_FrontKff", 0.0);
    public final static Constant frontIntakeKp = new Constant("I_FrontKp", 0.0);
    public final static Constant frontIntakeKi = new Constant("I_FrontKi", 0.0);
    public final static Constant frontIntakeKd = new Constant("I_FrontKd", 0.0);
    public final static Constant frontIntakeE = new Constant("I_FrontE", 0.0);
    
    //Rear
    public final static Constant rearIntakeEncoderAPort = new Constant("I_RearIntakeEncoderA", 10);
    public final static Constant rearIntakeEncoderBPort = new Constant("I_RearIntakeEncoderB", 11);
    public final static Constant rearIntakeHallEffect = new Constant("I_RearIntakeHallEffect", 13);
    public final static Constant rearIntakeAnglePort = new Constant("I_RearIntakeAnglePort", 7);
    public final static Constant rearIntakeRollerPort = new Constant("I_RearIntakeRollerPort", 2);
    public final static Constant backWallSolenoidPort = new Constant("I_BackWallSolenoidPort", 1);
    
    public final static Constant rearIntakeBackWallDifference = new Constant("I_BackWallDifference", 11.0);
    
    public final static Constant rearIntakeKff = new Constant("I_RearKff", 0.15);
    public final static Constant rearIntakeKp = new Constant("I_RearKp", 0.0085);
    public final static Constant rearIntakeKi = new Constant("I_RearKi", 0.0);
    public final static Constant rearIntakeKd = new Constant("I_RearKd", 0.0001);
    public final static Constant rearIntakeE = new Constant("I_RearE", 0.0);
    
}
