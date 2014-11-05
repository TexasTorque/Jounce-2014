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
import org.texastorque.texastorque20145.subsystem.Clapper;
import org.texastorque.texastorque20145.subsystem.Drivebase;
import org.texastorque.texastorque20145.subsystem.Shooter;
import org.texastorque.texastorque20145.torquelib.Parameters;

public class Robot extends TorqueIterative {

    Parameters params;

    Drivebase drivebase;
    Shooter shooter;
    Clapper clapper;
    
    Compressor compressor;

    InputSystem input;

    FeedbackSystem feedback;
    FeedbackSystem sensorFeedback;

    Thread AutoThread;

    public void robotInit() {
        params = new Parameters();

        drivebase = new Drivebase();
        shooter = new Shooter();
        clapper = new Clapper();
        
        compressor = new Compressor(1, Constants.compressorSwitch.getInt(), 1, Constants.compressorRelay.getInt());

        input = new DriverInput();

        sensorFeedback = new SensorFeedback();
        feedback = sensorFeedback;
    }

    public void teleopInit() {
        compressor.start();

        drivebase.setInputSystem(input);
        drivebase.enableOutput(true);
        
        shooter.setInputSystem(input);
        shooter.setFeedbackSystem(feedback);
        
        clapper.setInputSystem(input);
    }

    public void teleopPeriodic() {
        drivebase.update();
        shooter.update();
        clapper.update();
    }

    public void teleopContinuous() {
        input.run();
        feedback.run();
        SmartDashboard.putNumber("rpm", feedback.getShooterRPM());
    }

    public void autonomousInit() {
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
