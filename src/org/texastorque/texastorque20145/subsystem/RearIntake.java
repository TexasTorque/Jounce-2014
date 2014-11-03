package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.torquelib.Motor;
import org.texastorque.texastorque20145.torquelib.controlloop.TorquePID;

public class RearIntake extends Subsystem {

    public final static int DOWN = 0;
    public final static int INTAKE = 1;
    public final static int OUTTAKE = 2;
    public final static int UP = 3;
    public final static int PUSH_OTHER_SIDE = 4;
    public final static int CARRY = 5;
    public final static int HOLD = 6;

    private double targetAngle;
    private double currentAngle;
    private boolean backWallPosition;

    private Motor angleMotor;
    private Motor rollerMotor;
    private Solenoid backWallSolenoid;

    private TorquePID anglePID;

    public RearIntake() {
        angleMotor = new Motor(new Victor(Constants.rearIntakeAnglePort.getInt()), false);
        rollerMotor = new Motor(new Victor(Constants.rearIntakeRollerPort.getInt()), false);
        backWallSolenoid = new Solenoid(Constants.backWallSolenoidPort.getInt());

        anglePID = new TorquePID();
    }

    public void update() {
        backWallPosition = input.backWallIsOpen();
        backWallSolenoid.set(backWallPosition);

        state = input.getRearIntakeState();

        switch (state) {
            case DOWN:
                targetAngle = Constants.downAngle.getDouble();
                rollerMotor.set(0.0);
                break;
            case INTAKE:
                targetAngle = Constants.intakeRearAngle.getDouble();
                rollerMotor.set(1.0);
                break;
            case OUTTAKE:
                targetAngle = Constants.outtakeRearAngle.getDouble();
                rollerMotor.set(-1.0);
                break;
            case UP:
                targetAngle = Constants.upAngle.getDouble();
                rollerMotor.set(0.0);
                break;
            case PUSH_OTHER_SIDE:
                targetAngle = Constants.inAngle.getDouble();
                rollerMotor.set(1.0);
                break;
            case CARRY:
                targetAngle = Constants.rearCarryAngle.getDouble();
                if (feedback.getRearBumperSwitch()) {
                    rollerMotor.set(0.0);
                } else {
                    rollerMotor.set(Constants.carryPower.getDouble());
                }
                break;
            case HOLD:
                targetAngle = Constants.rearHoldAngle.getDouble();
                rollerMotor.set(0.0);
                break;
            default:
                targetAngle = Constants.downAngle.getDouble();
                rollerMotor.set(0.0);
                break;
        }

        if (input.rearIntakeIsManual()) {
            angleMotor.set(input.getRearAngleManualSpeed());
        } else {
            double feedForward = Math.cos(feedback.getRearIntakeAngle()) * Constants.rearIntakeKff.getDouble();

            anglePID.setSetpoint(targetAngle);
            double pid = anglePID.calculate(currentAngle);

            angleMotor.set(feedForward + pid);
        }
    }

    public void updateGains() {
        anglePID.setPIDGains(Constants.rearIntakeKp.getDouble(),
                Constants.rearIntakeKi.getDouble(),
                Constants.rearIntakeKd.getDouble());
        anglePID.setEpsilon(Constants.rearIntakeE.getDouble());
    }
}
