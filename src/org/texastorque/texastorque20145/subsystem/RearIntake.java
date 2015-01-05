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

    private double targetAngle;
    private double currentAngle;
    private double angleMotorSpeed;
    private double rollerSpeed;
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
                targetAngle = Constants.rearDownAngle.getDouble();
                rollerSpeed = 0.0;
                break;
            case INTAKE:
                targetAngle = Constants.intakeRearAngle.getDouble();
                rollerSpeed = 0.0;
                break;
            case OUTTAKE:
                targetAngle = Constants.outtakeRearAngle.getDouble();
                rollerSpeed = 0.0;
            case UP:
                targetAngle = Constants.upAngle.getDouble();
                rollerSpeed = 0.0;
                break;
            case PUSH_OTHER_SIDE:
                targetAngle = Constants.inAngle.getDouble();
                rollerSpeed = 0.0;
                break;
            case CARRY:
                targetAngle = Constants.rearCarryAngle.getDouble();
                rollerSpeed = 0.0;
                break;
            case HOLD:
                targetAngle = Constants.rearHoldAngle.getDouble();
                rollerSpeed = 0.0;
                break;
            default:
                targetAngle = Constants.frontDownAngle.getDouble();
                rollerSpeed = 0.0;
                break;
        }

        currentAngle = feedback.getRearIntakeAngle();

        if (input.rearIntakeIsManual()) {
            angleMotorSpeed = input.getRearAngleManualSpeed();
        } else {
            anglePID.setSetpoint(targetAngle);
            double feedForward = Math.cos(targetAngle) * Constants.rearIntakeKff.getDouble();
            double pid = anglePID.calculate(currentAngle);

            angleMotorSpeed = feedForward + pid;
        }

        if (outputEnabled) {
            angleMotor.set(angleMotorSpeed);
            rollerMotor.set(rollerSpeed);
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
