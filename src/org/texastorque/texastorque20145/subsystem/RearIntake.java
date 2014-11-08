package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    public final static int CALIBRATE = 7;

    private double targetAngle;
    private double currentAngle;
    private boolean backWallPosition;

    private double calibrationOffset;
    public boolean wantCalibrate;
    private boolean firstPhase;
    private boolean passedHallEffect;

    private Motor angleMotor;
    private Motor rollerMotor;
    private Solenoid backWallSolenoid;

    private TorquePID anglePID;

    public RearIntake() {
        angleMotor = new Motor(new Victor(Constants.rearIntakeAnglePort.getInt()), false);
        rollerMotor = new Motor(new Victor(Constants.rearIntakeRollerPort.getInt()), false);
        backWallSolenoid = new Solenoid(Constants.backWallSolenoidPort.getInt());

        anglePID = new TorquePID();

        wantCalibrate = false;
        calibrationOffset = -5.0;
        firstPhase = true;
        passedHallEffect = false;
    }

    public void update() {
        backWallPosition = input.backWallIsOpen();
        backWallSolenoid.set(backWallPosition);

        state = input.getRearIntakeState();

        if (wantCalibrate) {
            state = CALIBRATE;
        }

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
                break;
            case HOLD:
                targetAngle = Constants.rearHoldAngle.getDouble();
                rollerMotor.set(0.0);
                break;
            case CALIBRATE:
                zero();
                anglePID.setSetpoint(targetAngle);
                break;
            default:
                targetAngle = Constants.downAngle.getDouble();
                rollerMotor.set(0.0);
                break;
        }

        currentAngle = feedback.getRearIntakeAngle();

        if (input.rearIntakeIsManual()) {
            angleMotor.set(input.getRearAngleManualSpeed());
        } else {
            double feedForward = Math.cos(currentAngle) * Constants.rearIntakeKff.getDouble();
            anglePID.setSetpoint(targetAngle);
            double pid = anglePID.calculate(currentAngle);

            if (state == DOWN && anglePID.isDone()) {
                angleMotor.set(0.0);
            } else {
                angleMotor.set(feedForward + pid);
            }
        }
    }

    private void zero() {
        if (firstPhase) {
            anglePID.setSetpoint(Constants.intakeZeroAngle.getDouble());
        } else {
            anglePID.setSetpoint(Constants.intakeZeroAngle.getDouble() + calibrationOffset);
        }
        if (!feedback.getRearIntakeHallEffect() && anglePID.isDone()) {
            firstPhase = false;
            if (Math.abs(calibrationOffset) < 0.5) {
                wantCalibrate = false;
            }
            if (passedHallEffect) {
                calibrationOffset *= -1;
                passedHallEffect = false;
            } else {
                calibrationOffset *= -2;
            }
        } else if (feedback.getRearIntakeHallEffect() && anglePID.isDone()) {
            wantCalibrate = false;
        } else if (feedback.getRearIntakeHallEffect()) {
            firstPhase = false;
            //reset angle
            calibrationOffset /= 2;
            passedHallEffect = true;
        }
    }

    public void updateGains() {
        anglePID.setPIDGains(Constants.rearIntakeKp.getDouble(),
                Constants.rearIntakeKi.getDouble(),
                Constants.rearIntakeKd.getDouble());
        anglePID.setEpsilon(Constants.rearIntakeE.getDouble());
        anglePID.setDoneRange(Constants.intakeDoneRange.getDouble());
    }

    public void pushToDashboard() {
        SmartDashboard.putNumber("RearAngle", currentAngle);
        SmartDashboard.putNumber("RearTargetAngle", targetAngle);
        SmartDashboard.putNumber("RearIntakeState", state);
        SmartDashboard.putBoolean("BackWallOpen", input.backWallIsOpen());
    }
}
