package org.texastorque.texastorque20145.constants;

import org.texastorque.texastorque20145.torquelib.Parameters.Constant;

public class Constants {
    public final static Constant autoMode = new Constant("A_Mode", 0);
    
    //  -----  Drivebase  -----
    public final static Constant feetPerClick = new Constant("D_FeetPerClick", 0.003403392);;
    
    public final static Constant lowGearSensitivity = new Constant("D_LowSensitivity", 0.75);
    public final static Constant highGearSensitivity = new Constant("D_HighSensitivity", 0.75);
    
    //  -----  Shooter  -----
    public final static Constant fenderRPM = new Constant("S_FenderRPM", 4500);
    public final static Constant farRPM = new Constant("S_FarRPM", 5000);
    public final static Constant runFarRPM = new Constant("S_RunFarRPM", 5000);
    public final static Constant inboundRPM = new Constant("S_InboundRPM", 1000);
    public final static Constant lowGoalRPM = new Constant("S_LowGoalRPM", 500);
    public final static Constant offRPM = new Constant("S_OffRPM", 0);
    public final static Constant rpmDoneRange = new Constant("S_DoneRange", 100);
    public final static Constant rpmFilterSize = new Constant("S_RPMFilterSize", 1);
    
    public final static Constant inboundPower = new Constant("S_InboundPower", -0.35);
    public final static Constant shooterIntakePower = new Constant("S_ShooterIntakePower", 0.3);
    public final static Constant shooterOuttakePower = new Constant("S_ShooterOuttakePower", -0.3);
    
    public final static Constant openLoopFenderPower = new Constant("S_OpenLoopFenderPower", 0.8);
    public final static Constant openLoopLowGoalPower = new Constant("S_OpenLoopLowGoalPower", 0.2);
    
    //  -----  Clapper  -----
    public final static Constant shootDifference = new Constant("C_UpDifference", 0.09);
    
    //  ----- Intake  -----
    public final static Constant frontDownAngle = new Constant("I_FrontDownAngle", -80.0);
    public final static Constant rearDownAngle = new Constant("I_RearDownAngle", -110);
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
    public final static Constant intakeDoneRange = new Constant("I_DoneRange", 3.0);
    
    public final static Constant intakeDegreesPerClick = new Constant("I_DegreesPerClick", 0.28);
    public final static Constant intakeZeroAngle = new Constant("I_ZeroAngle", 90);
    public final static Constant carryPower = new Constant("I_CarryPower", 0.5);
    
    //Front
    public final static Constant frontIntakeKff = new Constant("I_FrontKff", 0.0);
    public final static Constant frontIntakeKp = new Constant("I_FrontKp", 0.0);
    public final static Constant frontIntakeKi = new Constant("I_FrontKi", 0.0);
    public final static Constant frontIntakeKd = new Constant("I_FrontKd", 0.0);
    public final static Constant frontIntakeE = new Constant("I_FrontE", 0.0);
    
    //Rear
    public final static Constant rearIntakeBackWallDifference = new Constant("I_BackWallDifference", 11.0);
    
    public final static Constant rearIntakeKff = new Constant("I_RearKff", 0.15);
    public final static Constant rearIntakeKp = new Constant("I_RearKp", 0.0085);
    public final static Constant rearIntakeKi = new Constant("I_RearKi", 0.0);
    public final static Constant rearIntakeKd = new Constant("I_RearKd", 0.0001);
    public final static Constant rearIntakeE = new Constant("I_RearE", 0.0);
    
}
