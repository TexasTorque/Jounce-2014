package org.texastorque.texastorque20145;

import edu.wpi.first.wpilibj.Compressor;
import org.texastorque.texastorque20145.autonomous.AutoMode;
import org.texastorque.texastorque20145.autonomous.AutoPicker;
import org.texastorque.texastorque20145.constants.Ports;
import org.texastorque.texastorque20145.feedback.FeedbackSystem;
import org.texastorque.texastorque20145.feedback.SensorFeedback;
import org.texastorque.texastorque20145.input.DriverInput;
import org.texastorque.texastorque20145.input.InputSystem;
import org.texastorque.texastorque20145.subsystem.Clapper;
import org.texastorque.texastorque20145.subsystem.Drivebase;
import org.texastorque.texastorque20145.subsystem.FrontIntake;
import org.texastorque.texastorque20145.subsystem.RearIntake;
import org.texastorque.texastorque20145.subsystem.Shooter;
import org.texastorque.texastorque20145.torquelib.Parameters;

public class Robot extends TorqueIterative {

    Parameters params;

    Drivebase drivebase;
    Shooter shooter;
    Clapper clapper;
    RearIntake rearIntake;
    FrontIntake frontIntake;

    Compressor compressor;

    InputSystem input;
    InputSystem driverInput;

    FeedbackSystem feedback;
    FeedbackSystem sensorFeedback;

    Thread AutoThread;

    public void robotInit() {
        getWatchdog().feed();
        
        params = new Parameters();
        params.load();

        drivebase = new Drivebase();
        shooter = new Shooter();
        clapper = new Clapper();
        rearIntake = new RearIntake();
        frontIntake = new FrontIntake();

        compressor = new Compressor(1, Ports.PRESSURE_SWITCH, 1, Ports.COMPRESSOR_RELAY);

        driverInput = new DriverInput();
        input = driverInput;

        sensorFeedback = new SensorFeedback();
        feedback = sensorFeedback;
    }

    public void teleopInit() {
        getWatchdog().feed();
        
        params.load();
        compressor.start();

        drivebase.setInputSystem(input);
        drivebase.enableOutput(true);

        shooter.setInputSystem(input);
        shooter.setFeedbackSystem(feedback);
        shooter.updateGains();

        clapper.setInputSystem(input);
        clapper.setFeedbackSystem(feedback);
        clapper.updateGains();

        rearIntake.setInputSystem(input);
        rearIntake.setFeedbackSystem(feedback);
        rearIntake.updateGains();

        frontIntake.setInputSystem(input);
        frontIntake.setFeedbackSystem(feedback);
        frontIntake.updateGains();
    }

    public void teleopPeriodic() {
        getWatchdog().feed();
        
        input.run();
        feedback.run();
        
        drivebase.update();
        shooter.update();
        clapper.update();
        rearIntake.update();
        frontIntake.update();
    }

    public void teleopContinuous() {
        getWatchdog().feed();
        
        drivebase.pushToDashboard();
        shooter.pushToDashboard();
        clapper.pushToDashboard();
        rearIntake.pushToDashboard();
        frontIntake.pushToDashboard();
    }

    public void autonomousInit() {
        getWatchdog().feed();
        
        params.load();

        feedback = sensorFeedback;
        AutoMode autoInput = AutoPicker.getAutoMode();
        autoInput.setFeedBackSystem(feedback);

        drivebase.setFeedbackSystem(feedback);
        drivebase.setInputSystem(autoInput);
        drivebase.enableOutput(true);
        
        shooter.setInputSystem(input);
        shooter.setFeedbackSystem(feedback);
        shooter.updateGains();

        clapper.setInputSystem(input);
        clapper.setFeedbackSystem(feedback);
        clapper.updateGains();

        rearIntake.setInputSystem(input);
        rearIntake.setFeedbackSystem(feedback);
        rearIntake.updateGains();

        frontIntake.setInputSystem(input);
        frontIntake.setFeedbackSystem(feedback);
        frontIntake.updateGains();

        AutoThread = new Thread(autoInput);
        AutoThread.start();
    }

    public void autonomousPeriodic() {
        getWatchdog().feed();
        
        input.run();
        feedback.run();

        drivebase.update();
        shooter.update();
        clapper.update();
        rearIntake.update();
        frontIntake.update();
    }

    public void disabledInit() {
        getWatchdog().feed();
        
        drivebase.setFeedbackSystem(feedback);
        drivebase.setInputSystem(driverInput);
        
        shooter.setInputSystem(input);
        shooter.setFeedbackSystem(feedback);
        shooter.updateGains();

        clapper.setInputSystem(input);
        clapper.setFeedbackSystem(feedback);
        clapper.updateGains();

        rearIntake.setInputSystem(input);
        rearIntake.setFeedbackSystem(feedback);
        rearIntake.updateGains();

        frontIntake.setInputSystem(input);
        frontIntake.setFeedbackSystem(feedback);
        frontIntake.updateGains();
    }

    public void disabledPeriodic() {
        getWatchdog().feed();
        
        input.run();
        feedback.run();
        
        if (driverInput.resetFrontIntakeAngle()) {
            feedback.resetFrontIntakeAngle();
        }
        if (driverInput.resetRearIntakeAngle())
        {
            feedback.resetRearIntakeAngle();
        }
    }
    
    public void disabledContinuous() {
        getWatchdog().feed();
        
        drivebase.pushToDashboard();
        shooter.pushToDashboard();
        clapper.pushToDashboard();
        rearIntake.pushToDashboard();
        frontIntake.pushToDashboard();
    }

    public void testInit() {
        getWatchdog().feed();
    }

}
