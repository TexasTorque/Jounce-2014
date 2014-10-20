package org.texastorque.texastorque20145.constants;

import org.texastorque.texastorque20145.torquelib.Parameters.Constant;

public class Constants {
    public final static Constant autoMode = new Constant("A_Mode", 0);
    
    //  -----  Drivebase  -----
    public final static Constant leftDrivePort = new Constant("D_LeftPort", 1);
    public final static Constant rightDrivePort = new Constant("D_RightPort", 2);
    public final static Constant shifterPort = new Constant("D_Shifter", 1);
    public final static Constant leftDriveEncoderAPort = new Constant("D_LeftDriveEncoderA", 8);
    public final static Constant leftDriveEncoderBPort = new Constant("D_LeftDriveEncoderB", 9);
    public final static Constant rightDriveEncoderAPort = new Constant("D_RightDriveEncoderA", 10);
    public final static Constant rightDriveEncoderBPort = new Constant("D_RightDriveEncoderB", 11);
    
    //  -----  Shooter  -----
    public final static Constant shooterAPort = new Constant("S_ShooterAPort", 3);
    public final static Constant shooterBPort = new Constant("S_ShooterBPort", 4);
    public final static Constant shooterCounterPort = new Constant("S_ShooterCouner", 1);
    
    public final static Constant fenderRPM = new Constant("S_FenderRPM", 4500);
    public final static Constant farRPM = new Constant("S_FarRPM", 5000);
    public final static Constant runFarRPM = new Constant("S_RunFarRPM", 5000);
    public final static Constant inboundRPM = new Constant("S_InboundRPM", 1000);
    public final static Constant lowGoalRPM = new Constant("S_LowGoalRPM", 500);
    public final static Constant offRPM = new Constant("S_OffRPM", 0);
    
    public final static Constant openLoopFenderPower = new Constant("S_OpenLoopFenderPower", 0.8);
    public final static Constant openLoopInboundPower = new Constant("S_OpenLoopInboundPower", -0.2);
    public final static Constant openLoopLowGoalPower = new Constant("S_OpenLoopLowGoalPower", 0.2);
    
    //  -----  Clapper  -----
    public final static Constant clapperFrontPort = new Constant("C_ClapperFrontPort", 2);
    public final static Constant clapperRearPort = new Constant("C_ClapperRearPort", 3);
    
    //  ----- Intake  -----
    public final static Constant downAngle = new Constant("I_DownAngle", -90);
    public final static Constant upAngle = new Constant("I_UpAngle", 90);
    public final static Constant inAngle = new Constant("I_InAngle", -120);
    public final static Constant intakeFrontAngle = new Constant("I_IntakeFrontAngle", -20);
    public final static Constant intakeRearAngle = new Constant("I_IntakeRearAngle", 0);
    public final static Constant outtakeFrontAngle = new Constant("I_OuttakeFrontAngle", 0);
    public final static Constant outtakeRearAngle = new Constant("I_OuttakeRearAngle", 0);
    
    //Front
    public final static Constant frontIntakeEncoderAPort = new Constant("I_FrontIntakeEncoderA", 2);
    public final static Constant frontIntakeEncoderBPort = new Constant("I_FrontIntakeEncoderB", 3);
    public final static Constant frontIntakeHallEffect = new Constant("I_FrontIntakeHallEffect", 4);
    public final static Constant frontIntakeAnglePort = new Constant("I_FrontIntakeAnglePort", 5);
    public final static Constant frontIntakeRollerPort = new Constant("I_FrontIntakeRollerPort", 6);
    
    //Rear
    public final static Constant rearIntakeEncoderAPort = new Constant("I_RearIntakeEncoderA", 5);
    public final static Constant rearIntakeEncoderBPort = new Constant("I_RearIntakeEncoderB", 6);
    public final static Constant rearIntakeHallEffect = new Constant("I_RearIntakeHallEffect", 7);
    public final static Constant rearIntakeAnglePort = new Constant("I_RearIntakeAnglePort", 7);
    public final static Constant rearIntakeRollerPort = new Constant("I_RearIntakeRollerPort", 8);
    
}
