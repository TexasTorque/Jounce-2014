package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Victor;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.torquelib.Motor;

public class FrontIntake extends Subsystem {
    
    public final static int DOWN = 0;
    public final static int INTAKE = 1;
    public final static int OUTTAKE = 2;
    public final static int UP = 3;
    public final static int MANUAL = 4;
    
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
                break;
            case INTAKE:
                targetAngle = Constants.intakeFrontAngle.getDouble();
                break;
            case OUTTAKE:
                targetAngle = Constants.outtakeFrontAngle.getDouble();
                break;
            case UP:
                targetAngle = Constants.upAngle.getDouble();
                break;
            default:
                targetAngle = Constants.downAngle.getDouble();
                break;
        }
    }
}
