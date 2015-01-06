package org.texastorque.texastorque20145;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Watchdog;
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
    Watchdog watchdog;

    // Subsystems
    Drivebase drivebase;
    Shooter shooter;
    Clapper clapper;
    RearIntake rearIntake;
    FrontIntake frontIntake;

    Compressor compressor;

    // Input
    InputSystem input;
    InputSystem driverInput;

    FeedbackSystem feedback;
    FeedbackSystem sensorFeedback;

    Thread AutoThread;

    public void robotInit() {
        watchdog = getWatchdog();
        watchdog.setEnabled(true);
        watchdog.setExpiration(0.5);
        watchdog.feed();
        
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
    
    private void updateSubsystems(boolean enable)
    {
        drivebase.setInputSystem(input);
        drivebase.enableOutput(enable);

        shooter.setInputSystem(input);
        shooter.setFeedbackSystem(feedback);
        shooter.updateGains();
        shooter.enableOutput(enable);

        clapper.setInputSystem(input);
        clapper.setFeedbackSystem(feedback);
        clapper.updateGains();
        clapper.enableOutput(enable);

        rearIntake.setInputSystem(input);
        rearIntake.setFeedbackSystem(feedback);
        rearIntake.updateGains();
        rearIntake.enableOutput(enable);

        frontIntake.setInputSystem(input);
        frontIntake.setFeedbackSystem(feedback);
        frontIntake.updateGains();
        frontIntake.enableOutput(enable);
    }

    // ---------- TeleOP ----------
    
    public void teleopInit() {
        watchdog.feed();
        
        params.load();
        compressor.start();

        updateSubsystems(true);
    }

    public void teleopPeriodic() {
        watchdog.feed();
        
        input.run();
        feedback.run();
        
        drivebase.update();
        shooter.update();
        clapper.update();
        rearIntake.update();
        frontIntake.update();
    }

    public void teleopContinuous() {
        watchdog.feed();
        
        drivebase.pushToDashboard();
        shooter.pushToDashboard();
        clapper.pushToDashboard();
        rearIntake.pushToDashboard();
        frontIntake.pushToDashboard();
    }

    // ---------- Autonomous ----------
    
    public void autonomousInit() {
        watchdog.feed();
        
        params.load();

        feedback = sensorFeedback;
        
        AutoMode autoInput = AutoPicker.getAutoMode();
        autoInput.setFeedBackSystem(feedback);
        input = autoInput;

        updateSubsystems(true);

        AutoThread = new Thread(autoInput);
        AutoThread.start();
    }

    public void autonomousPeriodic() {
        watchdog.feed();
        
        input.run();
        feedback.run();

        drivebase.update();
        shooter.update();
        clapper.update();
        rearIntake.update();
        frontIntake.update();
    }

    public void autonomousContinuous() {
        watchdog.feed();
        
        drivebase.pushToDashboard();
        shooter.pushToDashboard();
        clapper.pushToDashboard();
        rearIntake.pushToDashboard();
        frontIntake.pushToDashboard();
    }
    
    // ---------- Disabled ----------

    public void disabledInit() {
        watchdog.feed();
        
        input = driverInput;
        feedback = sensorFeedback;
        
        updateSubsystems(false);
    }

    public void disabledPeriodic() {
        watchdog.feed();
        
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
        watchdog.feed();
        
        drivebase.pushToDashboard();
        shooter.pushToDashboard();
        clapper.pushToDashboard();
        rearIntake.pushToDashboard();
        frontIntake.pushToDashboard();
    }
    
    // ---------- Test ----------

    public void testInit() {
        watchdog.feed();
        
        input = driverInput;
        feedback = sensorFeedback;
        
        updateSubsystems(false);
    }

}
