package org.texastorque.texastorque20145;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20145.autonomous.AutoMode;
import org.texastorque.texastorque20145.autonomous.AutoPicker;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.feedback.FeedbackSystem;
import org.texastorque.texastorque20145.feedback.SensorFeedback;
import org.texastorque.texastorque20145.input.DriverInput;
import org.texastorque.texastorque20145.input.InputSystem;
import org.texastorque.texastorque20145.subsystem.Drivebase;
import org.texastorque.texastorque20145.torquelib.Parameters;

public class Robot extends TorqueIterative {
    
    Parameters params;
    
    Drivebase drivebase;
    Compressor compressor;
    
    InputSystem input;
    
    FeedbackSystem feedback;
    FeedbackSystem sensorFeedback;
    
    Thread AutoThread;

    public void robotInit() {
        params = new Parameters();
        
        drivebase = new Drivebase();
        compressor = new Compressor(1, Constants.compressorSwitch.getInt(), 1, Constants.compressorRelay.getInt());
        
        input = new DriverInput();
        
        sensorFeedback = new SensorFeedback();
    }
    
    public void teleopInit() {
        compressor.start();
        
        drivebase.setInputSystem(input);
        drivebase.enableOutput(true);
    }

    public void teleopPeriodic() {
        drivebase.update();
        SmartDashboard.putBoolean("compswtch", compressor.getPressureSwitchValue());
    }
    
    public void teleopContinuous()
    {
        input.run();
    }
    
    public void autonomousInit()
    {
        params.load();
        feedback = sensorFeedback;
        drivebase.setFeedbackSystem(feedback);
        
        AutoMode autoInput = AutoPicker.getAutoMode();
        autoInput.setFeedBackSystem(feedback);
        
        drivebase.setInputSystem(autoInput);
        
        drivebase.enableOutput(true);
        
        AutoThread = new Thread(autoInput);
        AutoThread.start();
    }

    public void autonomousPeriodic() {
        drivebase.update();
    }
    
}
