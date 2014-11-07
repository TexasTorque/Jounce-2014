package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Victor;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.torquelib.Motor;
import org.texastorque.texastorque20145.torquelib.controlloop.BangBang;

public class Shooter extends Subsystem {

    private Motor shooterAMotor;
    private Motor shooterBMotor;

    private double targetRPM;
    private double currentRPM;
    private double openLoopPower;
    
    private BangBang rpmController;

    public final static int OFF = 0;
    public final static int FENDER = 1;
    public final static int FAR = 2;
    public final static int RUN_FAR = 3;
    public final static int INBOUND = 4;
    public final static int LOW_GOAL = 5; 
    
    public Shooter() {
        shooterAMotor = new Motor(new Victor(Constants.shooterAPort.getInt()), false);
        shooterBMotor = new Motor(new Victor(Constants.shooterBPort.getInt()), true);
        
        rpmController = new BangBang();
    }

    public void update() {
        state = input.getShooterState();

        switch (state) {
            case FENDER:
                openLoopPower = Constants.openLoopFenderPower.getDouble();
                targetRPM = Constants.fenderRPM.getDouble();
                break;
            case FAR:
                openLoopPower = 0.0;
                targetRPM = Constants.farRPM.getDouble();
                break;
            case RUN_FAR:
                openLoopPower = 0.0;
                targetRPM = Constants.runFarRPM.getDouble();
                break;
            case INBOUND:
                openLoopPower = Constants.openLoopInboundPower.getDouble();
                targetRPM = Constants.inboundRPM.getDouble();
                break;
            case OFF:
                openLoopPower = 0.0;
                targetRPM = Constants.offRPM.getDouble();
                break;
            case LOW_GOAL:
                openLoopPower = Constants.openLoopLowGoalPower.getDouble();
                targetRPM = Constants.lowGoalRPM.getDouble();
                break;
            default:
                targetRPM = Constants.offRPM.getDouble();
                openLoopPower = 0.0;
                break;
        }
        
        rpmController.setSetpoint(targetRPM);
        feedback.setShooterSpunUp(rpmController.isDone());
        
        if (input.shooterIsManual())
        {
            shooterAMotor.set(openLoopPower);
            shooterBMotor.set(openLoopPower);
        } else {
            currentRPM = feedback.getShooterRPM();
            
            if (state == INBOUND)
            {
                shooterAMotor.set(Constants.inboundPower.getDouble());
                shooterBMotor.set(Constants.inboundPower.getDouble());
            } else {
                double power = rpmController.calculate(currentRPM);
                shooterAMotor.set(power);
                shooterBMotor.set(power);
            }
        }
    }

    public void updateGains() {
        rpmController.setDoneRange(Constants.rpmDoneRange.getInt());
    }
}
