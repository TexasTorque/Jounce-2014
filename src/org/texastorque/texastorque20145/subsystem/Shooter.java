package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Victor;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.torquelib.Motor;

public class Shooter extends Subsystem {

    private Motor shooterAMotor;
    private Motor shooterBMotor;

    private volatile int shooterState;

    private double targetRPM;
    private boolean reverse;

    private final static int OFF = 0;
    private final static int FENDER = 1;
    private final static int FAR = 2;
    private final static int RUN_FAR = 3;
    private final static int INBOUND = 4;

    public Shooter() {
        shooterAMotor = new Motor(new Victor(Constants.shooterAPort.getInt()), true);
        shooterBMotor = new Motor(new Victor(Constants.shooterBPort.getInt()), false);
    }

    public void setState(int state) {
        shooterState = state;
    }

    public void update() {
        switch (shooterState) {
            case FENDER:
                targetRPM = Constants.fenderRPM.getDouble();
                reverse = false;
                break;
            case FAR:
                targetRPM = Constants.farRPM.getDouble();
                reverse = false;
                break;
            case RUN_FAR:
                targetRPM = Constants.runFarRPM.getDouble();
                reverse = false;
                break;
            case INBOUND:
                targetRPM = Constants.inboundRPM.getDouble();
                reverse = true;
                break;
            case OFF:
                targetRPM = Constants.offRPM.getDouble();
                reverse = false;
                break;
            default:
                targetRPM = Constants.offRPM.getDouble();
                reverse = false;
                break;
        }
        
    }
}
