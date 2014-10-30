package org.texastorque.texastorque20145;

import org.texastorque.texastorque20145.autonomous.AutoMode;
import org.texastorque.texastorque20145.autonomous.AutoPicker;
import org.texastorque.texastorque20145.feedback.FeedbackSystem;
import org.texastorque.texastorque20145.feedback.SensorFeedback;
import org.texastorque.texastorque20145.input.DriverInput;
import org.texastorque.texastorque20145.input.InputSystem;
import org.texastorque.texastorque20145.subsystem.Drivebase;
import org.texastorque.texastorque20145.torquelib.Parameters;

public class Robot extends TorqueIterative {
    
    Parameters params;
    Drivebase drivebase;
    
    InputSystem input;
    
    FeedbackSystem feedback;
    FeedbackSystem sensorFeedback;
    
    Thread AutoThread;

    public void robotInit() {
        params = new Parameters();
        drivebase = new Drivebase();
        
        input = new DriverInput();
        
        sensorFeedback = new SensorFeedback();
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
    
    public void autonomousInit()
    {
        feedback = sensorFeedback;
        drivebase.setFeedbackSystem(feedback);
        
        AutoMode autoInput = AutoPicker.getAutoMode();
        autoInput.setFeedBackSystem(feedback);
        
        drivebase.setInputSystem(autoInput);
        
        AutoThread = new Thread(autoInput);
    }

    public void autonomousPeriodic() {
        drivebase.update();
    }
    
}
