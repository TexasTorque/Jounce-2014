package org.texastorque.texastorque20145.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.torquelib.Motor;

public class Drivebase extends Subsystem {
    
    private Motor left;
    private Motor right;
    private Solenoid shifter;
    
    public Drivebase()
    {
        left = new Motor(new Victor(Constants.leftDrivePort.getInt()), false);
        right = new Motor(new Victor(Constants.rightDrivePort.getInt()), true);
        shifter = new Solenoid(Constants.shifterPort.getInt());
    }

    public void update() {
        if (outputEnabled)
        {
            left.set(input.getLeftSpeed());
            right.set(input.getRightSpeed());
            shifter.set(input.getGear());
        }
    }

}
