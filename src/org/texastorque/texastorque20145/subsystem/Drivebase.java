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
    
    public Drivebase()
    {
        left = new Motor(new Victor(Ports.LEFT_DRIVE_PORT), false);
        right = new Motor(new Victor(Ports.RIGHT_DRIVE_PORT), true);
        shifter = new Solenoid(Ports.SHIFTER_PORT);
    }

    public void update() {
        if (outputEnabled)
        {
            left.set(input.getLeftSpeed());
            right.set(input.getRightSpeed());
            shifter.set(input.getGear());
        }
    }

    public void pushToDashboard()
    {
        SmartDashboard.putNumber("LeftSpeed", input.getLeftSpeed());
        SmartDashboard.putNumber("RightSpeed", input.getRightSpeed());
        SmartDashboard.putBoolean("Gear", input.getGear());
    }
}
