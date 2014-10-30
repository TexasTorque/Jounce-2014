package org.texastorque.texastorque20145.autonomous;

import edu.wpi.first.wpilibj.Timer;

public class DriveForwardAuto extends AutoMode {
    
    public DriveForwardAuto()
    {
        
    }
    
    public void run()
    {
        leftSpeed = 1.0;
        rightSpeed = 1.0;
        
        Timer.delay(1.5);
        
        leftSpeed = 0.0;
        rightSpeed = 0.0;
    }
}
