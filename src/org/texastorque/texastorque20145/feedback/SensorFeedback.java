package org.texastorque.texastorque20145.feedback;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.constants.Ports;
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
    private boolean previousFrontHallEffect;
    private boolean previousRearHallEffect;
    private boolean frontCalibrated;
    private boolean rearCalibrated;

    private DigitalInput frontIntakeBumperSwitch;
    private DigitalInput rearIntakeBumperSwitch;

    public void resetFrontIntakeAngle() {
        frontIntakeEncoder.reset();
    }

    public void resetRearIntakeAngle() {
        rearIntakeEncoder.reset();
    }

    //Shooter
    private TorqueCounter flyWheelCounter;
    private double previousRPM;

    public SensorFeedback() {
        //Drivebase
        leftDriveEncoder = new TorqueQuadrature(Ports.LEFT_DRIVE_ENCODER_A,
                Ports.LEFT_DRIVE_ENCODER_B, true, CounterBase.EncodingType.k2X);
        rightDriveEncoder = new TorqueQuadrature(Ports.RIGHT_DRIVE_ENCODER_A,
                Ports.RIGHT_DRIVE_ENCODER_B, true, CounterBase.EncodingType.k2X);

        leftDriveEncoder.start();
        rightDriveEncoder.start();

        //Intake
        frontIntakeEncoder = new TorqueQuadrature(Ports.FRONT_ANGLE_ENCODER_A,
                Ports.FRONT_ANGLE_ENCODER_B, true, CounterBase.EncodingType.k4X);
        rearIntakeEncoder = new TorqueQuadrature(Ports.REAR_ANGLE_ENCODER_A,
                Ports.REAR_ANGLE_ENCODER_B, true, CounterBase.EncodingType.k4X);
        frontIntakeHallEffectSensor = new DigitalInput(Ports.FRONT_INTAKE_HALL_EFFECT);
        rearIntakeHallEffectSensor = new DigitalInput(Ports.REAR_INTAKE_HALL_EFFECT);

        frontIntakeEncoder.start();
        rearIntakeEncoder.start();

        //Shooter
        flyWheelCounter = new TorqueCounter(Ports.SHOOTER_ENCODER);
        flyWheelCounter.setFilterSize(1);
        flyWheelCounter.start();
        previousRPM = 0.0;
    }

    public void run() {
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

        if (!previousFrontHallEffect && frontIntakeHallEffect && wantCalibrate && !frontCalibrated) {
            frontIntakeEncoder.reset();
            frontCalibrated = true;
        }
        if (!previousRearHallEffect && rearIntakeHallEffect && wantCalibrate && !rearCalibrated) {
            rearIntakeEncoder.reset();
            rearCalibrated = true;
        }

        previousFrontHallEffect = frontIntakeHallEffect;
        previousRearHallEffect = rearIntakeHallEffect;

        frontIntakeAngle = frontIntakeEncoder.getPosition() * Constants.intakeDegreesPerClick.getDouble()
                + Constants.intakeZeroAngle.getDouble();
        rearIntakeAngle = rearIntakeEncoder.getPosition() * Constants.intakeDegreesPerClick.getDouble()
                + Constants.intakeZeroAngle.getDouble();

        if (backWallOpen) {
            rearIntakeAngle -= Constants.rearIntakeBackWallDifference.getDouble();
        }

        //Shooter
        flyWheelCounter.calc();
        double currentRpm = flyWheelCounter.getRate() * 60 / 100;
        shooterRPM = (currentRpm < 20000) ? currentRpm : previousRPM;
        previousRPM = shooterRPM;
    }
}
