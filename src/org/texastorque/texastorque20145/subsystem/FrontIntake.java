package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Victor;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.torquelib.Motor;

public class FrontIntake extends Subsystem {
    
    public final static int DOWN = 0;
    public final static int INTAKE = 1;
    public final static int OUTTAKE = 2;
    public final static int UP = 3;
    public final static int PUSH_OTHER_SIDE = 4;
    
    private double targetAngle;
    private double currentAngle;
    
    private Motor angleMotor;
    private Motor rollerMotor;
    
    public FrontIntake()
    {
        angleMotor = new Motor(new Victor(Constants.frontIntakeAnglePort.getInt()), false);
        rollerMotor = new Motor(new Victor(Constants.frontIntakeRollerPort.getInt()), false);
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
            default:
                targetAngle = Constants.downAngle.getDouble();
                rollerMotor.set(0.0);
                break;
        }
        
        if (input.frontIntakeIsManual())
        {
            angleMotor.set(input.getFrontAngleManualSpeed());
        } else {
            //control loop output
        }
    }
}
