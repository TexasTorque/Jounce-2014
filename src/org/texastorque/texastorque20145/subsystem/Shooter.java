package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.constants.Ports;
import org.texastorque.texastorque20145.torquelib.Motor;
import org.texastorque.texastorque20145.torquelib.controlloop.BangBang;

public class Shooter extends Subsystem {

    private Motor shooterAMotor;
    private Motor shooterBMotor;

    private double targetRPM;
    private double currentRPM;
    private double shooterMotorSpeed;

    private BangBang rpmController;

    public final static int OFF = 0;
    public final static int OPEN_LOOP = 1;
    public final static int CLOSED_LOOP = 2;

    public Shooter() {
        shooterAMotor = new Motor(new Victor(Ports.SHOOTER_A_PORT), false);
        shooterBMotor = new Motor(new Victor(Ports.SHOOTER_B_PORT), true);

        rpmController = new BangBang();
    }

    public void update() {
        state = input.getShooterState();
        
        currentRPM = feedback.getShooterRPM();

        switch (state) {
            case OFF:
                shooterMotorSpeed = 0.0;
                feedback.setShooterSpunUp(false);
                break;
            case OPEN_LOOP:
                shooterMotorSpeed = input.getShooterOpenLoopSpeed();
                feedback.setShooterSpunUp(true);
                break;
            case CLOSED_LOOP:
                targetRPM = input.getShooterRPM();
                rpmController.setSetpoint(targetRPM);
                shooterMotorSpeed = rpmController.calculate(currentRPM);
                feedback.setShooterSpunUp(rpmController.isDone());
                break;
            default:
                shooterMotorSpeed = 0.0;
                feedback.setShooterSpunUp(false);
                break;
        }

        if (outputEnabled)
        {
            shooterAMotor.set(shooterMotorSpeed);
            shooterBMotor.set(shooterMotorSpeed);
        }
    }

    public void updateGains() {
        rpmController.setDoneRange(Constants.rpmDoneRange.getInt());
    }

    public void pushToDashboard() {
        SmartDashboard.putNumber("RPM", currentRPM);
        SmartDashboard.putNumber("TargetRPM", targetRPM);
    }
}
