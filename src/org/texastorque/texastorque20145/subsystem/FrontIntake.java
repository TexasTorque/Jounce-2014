package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.torquelib.Motor;
import org.texastorque.texastorque20145.torquelib.controlloop.TorquePID;

public class FrontIntake extends Subsystem {
    
    public final static int DOWN = 0;
    public final static int INTAKE = 1;
    public final static int OUTTAKE = 2;
    public final static int UP = 3;
    public final static int PUSH_OTHER_SIDE = 4;
    public final static int CARRY = 5;
    public final static int HOLD = 6;
    
    private double targetAngle;
    private double currentAngle;
    
    private Motor angleMotor;
    private Motor rollerMotor;
    
    private TorquePID anglePID;
    
    public FrontIntake()
    {
        angleMotor = new Motor(new Victor(Constants.frontIntakeAnglePort.getInt()), false);
        rollerMotor = new Motor(new Victor(Constants.frontIntakeRollerPort.getInt()), false);
        
        anglePID = new TorquePID();
    }
    
    public void update()
    {
        state = input.getFrontIntakeState();
        
        switch (state)
        {
            case DOWN:
                targetAngle = Constants.downAngle.getDouble();
                rollerMotor.set(0.0);
                break;
            case INTAKE:
                targetAngle = Constants.intakeFrontAngle.getDouble();
                rollerMotor.set(1.0);
                break;
            case OUTTAKE:
                targetAngle = Constants.outtakeFrontAngle.getDouble();
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
                targetAngle = Constants.frontCarryAngle.getDouble();
                break;
            case HOLD:
                targetAngle = Constants.frontHoldAngle.getDouble();
                rollerMotor.set(0.0);
                break;
            default:
                targetAngle = Constants.downAngle.getDouble();
                rollerMotor.set(0.0);
                break;
        }
        
        currentAngle = feedback.getFrontIntakeAngle();

        if (input.frontIntakeIsManual()) {
            angleMotor.set(input.getFrontAngleManualSpeed());
        } else {
            double feedForward = Math.cos(currentAngle) * Constants.frontIntakeKff.getDouble();
            anglePID.setSetpoint(targetAngle);
            double pid = anglePID.calculate(currentAngle);

            if (state == DOWN && anglePID.isDone()) {
                angleMotor.set(0.0);
            } else {
                angleMotor.set(feedForward + pid);
            }
        }
    }
    
    public void updateGains()
    {
        anglePID.setPIDGains(Constants.frontIntakeKp.getDouble(), 
                             Constants.frontIntakeKi.getDouble(),
                             Constants.frontIntakeKd.getDouble());
        anglePID.setEpsilon(Constants.frontIntakeE.getDouble());
        anglePID.setDoneRange(Constants.intakeDoneRange.getDouble());
    }
    
    public void pushToDashboard()
    {
        SmartDashboard.putNumber("FrontAngle", currentAngle);
        SmartDashboard.putNumber("FrontTargetAngle", targetAngle);
        SmartDashboard.putNumber("FrontIntakeState", state);
    }
}
