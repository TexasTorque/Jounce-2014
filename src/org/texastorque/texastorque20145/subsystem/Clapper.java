package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.constants.Ports;

public class Clapper extends Subsystem {

    private Solenoid frontClapper;
    private Solenoid rearClapper;
    
    private boolean frontUp;
    private boolean rearUp;

    public final static int DOWN = 0;
    public final static int FRONT_OUTTAKE = 1;
    public final static int REAR_OUTTAKE = 2;
    public final static int SHOOT = 3;

    private double frontUpTime;

    public Clapper() {
        frontClapper = new Solenoid(Ports.CLAPPER_FRONT_PORT);
        rearClapper = new Solenoid(Ports.CLAPPER_REAR_PORT);
    }

    public void update() {
        state = input.getClapperState();

        switch (state) {
            case DOWN:
                frontUp = false;
                rearUp = false;
                break;
            case FRONT_OUTTAKE:
                frontUp = false;
                rearUp = true;
                break;
            case REAR_OUTTAKE:
                frontUp = true;
                rearUp = false;
                break;
            case SHOOT:
                if (feedback.isShooterSpunUp()) {
                    if (!frontUp) {
                        frontUpTime = Timer.getFPGATimestamp();
                    }
                    frontUp = true;
                    if (Timer.getFPGATimestamp() - Constants.shootDifference.getDouble() > frontUpTime) {
                        rearUp = true;
                    } else {
                        rearUp = false;
                    }
                }
                break;
            default:
                frontUp = false;
                rearUp = false;
                break;
        }
        
        if (outputEnabled) {
            frontClapper.set(frontUp);
            rearClapper.set(rearUp);
        }
    }

    public void pushToDashboard() {
        SmartDashboard.putNumber("ClapperState", state);
    }
}
