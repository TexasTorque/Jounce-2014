package org.texastorque.texastorque20145.feedback;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.torquelib.TorqueCounter;
import org.texastorque.texastorque20145.torquelib.TorqueQuadrature;

public class SensorFeedback extends FeedbackSystem {
    
    //Drivebase
    private TorqueQuadrature leftDriveEncoder;
    private TorqueQuadrature rightDriveEncoder;
    
     //Intake
    private TorqueQuadrature frontIntakeEncoder;
    private TorqueQuadrature rearIntakeEncoder;
    private DigitalInput frontIntakeHallEffectSensor;
    private DigitalInput rearIntakeHallEffectSensor;
    private DigitalInput frontIntakeBumperSwitch;
    private DigitalInput rearIntakeBumperSwitch;
    
    public void resetFrontIntakeAngle()
    {
        frontIntakeEncoder.reset();
    }
    
    public void resetRearIntakeAngle()
    {
        rearIntakeEncoder.reset();
    }
    
    //Shooter
    private TorqueCounter flyWheelCounter;
    private double previousRPM;
    
    public SensorFeedback()
    {
        //Drivebase
        leftDriveEncoder = new TorqueQuadrature(Constants.leftDriveEncoderAPort.getInt(),
                Constants.leftDriveEncoderBPort.getInt(), true, CounterBase.EncodingType.k2X);
        rightDriveEncoder = new TorqueQuadrature(Constants.rightDriveEncoderAPort.getInt(),
                Constants.rightDriveEncoderBPort.getInt(), true, CounterBase.EncodingType.k2X);
        
        leftDriveEncoder.start();
        rightDriveEncoder.start();
        
        //Intake
        frontIntakeEncoder = new TorqueQuadrature(Constants.frontIntakeEncoderAPort.getInt(),
                Constants.frontIntakeEncoderBPort.getInt(), true, CounterBase.EncodingType.k4X);
        rearIntakeEncoder = new TorqueQuadrature(Constants.rearIntakeEncoderAPort.getInt(),
                Constants.rearIntakeEncoderBPort.getInt(), true, CounterBase.EncodingType.k4X);
        frontIntakeHallEffectSensor = new DigitalInput(Constants.frontIntakeHallEffect.getInt());
        rearIntakeHallEffectSensor = new DigitalInput(Constants.rearIntakeHallEffect.getInt());
        
        frontIntakeEncoder.start();
        rearIntakeEncoder.start();
        
        //Shooter
        flyWheelCounter = new TorqueCounter(Constants.shooterCounterPort.getInt());
        flyWheelCounter.setFilterSize(Constants.rpmFilterSize.getInt());
        flyWheelCounter.start();
        previousRPM = 0.0;
    }
    
    public void run()
    {
        //Drivebase
        leftDriveEncoder.calc();
        rightDriveEncoder.calc();
        
        //Temporary. Needs to be multiplied by scalars to get real units. (e.g ft/s)
        leftPosition = leftDriveEncoder.getPosition() * Constants.feetPerClick.getDouble();
        leftVelocity = leftDriveEncoder.getRate() * Constants.feetPerClick.getDouble();
        leftAcceleration = leftDriveEncoder.getAcceleration() * Constants.feetPerClick.getDouble();
        
        rightPosition = rightDriveEncoder.getPosition() * Constants.feetPerClick.getDouble();
        rightVelocity = rightDriveEncoder.getRate() * Constants.feetPerClick.getDouble();
        rightAcceleration = rightDriveEncoder.getAcceleration() * Constants.feetPerClick.getDouble();
        
        //Intake
        frontIntakeEncoder.calc();
        rearIntakeEncoder.calc();
        
        frontIntakeHallEffect = frontIntakeHallEffectSensor.get();
        rearIntakeHallEffect = rearIntakeHallEffectSensor.get();
        
        frontIntakeAngle = frontIntakeEncoder.getPosition() * Constants.intakeDegreesPerClick.getDouble()
                         + Constants.intakeZeroAngle.getDouble();
        rearIntakeAngle = rearIntakeEncoder.getPosition() * Constants.intakeDegreesPerClick.getDouble()
                         + Constants.intakeZeroAngle.getDouble();
        
        if (backWallOpen)
        {
            rearIntakeAngle -= Constants.rearIntakeBackWallDifference.getDouble();
        }
        
        //Shooter
        flyWheelCounter.calc();
        double currentRpm = flyWheelCounter.getRate() * 60 / 100;
        shooterRPM = (currentRpm < 20000) ? currentRpm : previousRPM;
        previousRPM = shooterRPM;
    }
}
