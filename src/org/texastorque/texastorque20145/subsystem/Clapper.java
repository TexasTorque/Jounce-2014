package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20145.constants.Constants;

public class Clapper extends Subsystem {

    private Solenoid frontClapper;
    private Solenoid rearClapper;

    public final static int DOWN = 0;
    public final static int FRONT_OUTTAKE = 1;
    public final static int REAR_OUTTAKE = 2;
    public final static int SHOOT = 3;

    private double frontUpTime;

    public Clapper() {
        frontClapper = new Solenoid(Constants.clapperFrontPort.getInt());
        rearClapper = new Solenoid(Constants.clapperRearPort.getInt());
    }

    public void update() {
        state = input.getClapperState();

        switch (state) {
            case DOWN:
                frontClapper.set(false);
                rearClapper.set(false);
                break;
            case FRONT_OUTTAKE:
                frontClapper.set(false);
                rearClapper.set(true);
                break;
            case REAR_OUTTAKE:
                frontClapper.set(true);
                rearClapper.set(false);
                break;
            case SHOOT:
                if (feedback.isShooterSpunUp()) {
                    if (!frontClapper.get()) {
                        frontUpTime = Timer.getFPGATimestamp();
                    }
                    frontClapper.set(true);
                    if (Timer.getFPGATimestamp() - Constants.shootDifference.getDouble() > frontUpTime) {
                        rearClapper.set(true);
                    }
                }
                break;
            default:
                frontClapper.set(false);
                rearClapper.set(false);
                break;
        }
    }

    public void pushToDashboard() {
        SmartDashboard.putNumber("ClapperState", state);
    }
}
