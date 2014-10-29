package org.texastorque.texastorque20145;

import org.texastorque.texastorque20145.input.DriverInput;
import org.texastorque.texastorque20145.input.InputSystem;
import org.texastorque.texastorque20145.subsystem.Drivebase;
import org.texastorque.texastorque20145.torquelib.Parameters;

public class Robot extends TorqueIterative {
    
    Parameters params;
    Drivebase drivebase;
    
    InputSystem input;

    public void robotInit() {
        params = new Parameters();
        drivebase = new Drivebase();
        
        input = new DriverInput();
    }

    public void autonomousPeriodic() {

    }
    
    public void teleopInit() {
        drivebase.setInputSystem(input);
        drivebase.enableOutput(true);
    }

    public void teleopPeriodic() {
        drivebase.update();
    }
    
    public void teleopContinuous()
    {
        input.run();
        
    }

    public void testPeriodic() {

    }

}
