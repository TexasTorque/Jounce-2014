package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20145.constants.Ports;
import org.texastorque.texastorque20145.torquelib.Motor;

public class Drivebase extends Subsystem {
    
    private Motor left;
    private Motor right;
    private Solenoid shifter;
    
    private double leftSpeed;
    private double rightSpeed;
    private boolean shiftState;
    
    public Drivebase()
    {
        left = new Motor(new Victor(Ports.LEFT_DRIVE_PORT), false);
        right = new Motor(new Victor(Ports.RIGHT_DRIVE_PORT), true);
        shifter = new Solenoid(Ports.SHIFTER_PORT);
    }

    public void update() {
        leftSpeed = input.getLeftSpeed();
        rightSpeed = input.getRightSpeed();
        shiftState = input.getGear();
        
        if (outputEnabled)
        {
            left.set(leftSpeed);
            right.set(rightSpeed);
            shifter.set(shiftState);
        }
    }

    public void pushToDashboard()
    {
        SmartDashboard.putNumber("LeftSpeed", leftSpeed);
        SmartDashboard.putNumber("RightSpeed", rightSpeed);
        SmartDashboard.putBoolean("Gear", shiftState);
    }
}
