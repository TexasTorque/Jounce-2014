package org.texastorque.texastorque20145.input;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.subsystem.Clapper;
import org.texastorque.texastorque20145.subsystem.FrontIntake;
import org.texastorque.texastorque20145.subsystem.RearIntake;
import org.texastorque.texastorque20145.subsystem.Shooter;
import org.texastorque.texastorque20145.torquelib.GenericController;

public class DriverInput extends InputSystem {

    private GenericController driver;
    private GenericController operator;

    //Drivebase
    double oldWheel, quickStopAccumulator, negInertiaAccumulator;

    public DriverInput() {
        driver = new GenericController(1, GenericController.XBOX, 0.1);
        operator = new GenericController(2, GenericController.XBOX, 0.0);
    }

    public void run() {
        //Drivebase
        double throttle = -driver.getLeftYAxis();
        double wheel = -driver.getRightXAxis();
        isHighGear = driver.getLeftBumper();
        boolean isQuickTurn = driver.getRightBumper();

        double wheelNonLinearity;

        double negInertia = wheel - oldWheel;
        oldWheel = wheel;

        if (isHighGear) {
            wheelNonLinearity = 0.6;
            // Apply a sin function that's scaled to make it feel better.
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        } else {
            wheelNonLinearity = 0.5;
            // Apply a sin function that's scaled to make it feel better.
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
            wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel)
                    / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
        }

        double overPower;
        double sensitivity;

        double angularPower;
        double linearPower;

        // Negative inertia!
        double negInertiaScalar;
        if (isHighGear) {
            negInertiaScalar = 5.0;
            sensitivity = Constants.highGearSensitivity.getDouble();
        } else {
            if (wheel * negInertia > 0) {
                negInertiaScalar = 2.5;
            } else {
                if (Math.abs(wheel) > 0.65) {
                    negInertiaScalar = 5.0;
                } else {
                    negInertiaScalar = 3.0;
                }
            }
            sensitivity = Constants.lowGearSensitivity.getDouble();
        }
        double negInertiaPower = negInertia * negInertiaScalar;
        negInertiaAccumulator += negInertiaPower;

        wheel = wheel + negInertiaAccumulator;
        if (negInertiaAccumulator > 1) {
            negInertiaAccumulator -= 1;
        } else if (negInertiaAccumulator < -1) {
            negInertiaAccumulator += 1;
        } else {
            negInertiaAccumulator = 0;
        }
        linearPower = throttle;

        // Quickturn!
        if (isQuickTurn) {
            if (Math.abs(linearPower) < 0.2) {
                double alpha = 0.1;
                if (Math.abs(wheel) > 1) {
                    if (wheel < 0) {
                        wheel = -1;
                    } else {
                        wheel = 1;
                    }
                }
                quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha
                        * wheel * 5;
            }
            overPower = 1.0;
            angularPower = wheel;
        } else {
            overPower = 0.0;
            angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
            if (quickStopAccumulator > 1) {
                quickStopAccumulator -= 1;
            } else if (quickStopAccumulator < -1) {
                quickStopAccumulator += 1;
            } else {
                quickStopAccumulator = 0.0;
            }
        }

        rightSpeed = leftSpeed = linearPower;
        leftSpeed += angularPower;
        rightSpeed -= angularPower;

        if (leftSpeed > 1.0) {
            rightSpeed -= overPower * (leftSpeed - 1.0);
            leftSpeed = 1.0;
        } else if (rightSpeed > 1.0) {
            leftSpeed -= overPower * (rightSpeed - 1.0);
            rightSpeed = 1.0;
        } else if (leftSpeed < -1.0) {
            rightSpeed += overPower * (-1.0 - leftSpeed);
            leftSpeed = -1.0;
        } else if (rightSpeed < -1.0) {
            leftSpeed += overPower * (-1.0 - rightSpeed);
            rightSpeed = -1.0;
        }

        backWallOpen = false;

        //Shooter
//        if (operator.getRightTrigger() && operator.getXButton()) {
//            shooterState = Shooter.LOW_GOAL;
//            backWallOpen = true;
//        } else 
            if (operator.getYButton()) {
            shooterState = Shooter.FAR;
            backWallOpen = true;
        } else if (operator.getBButton()) {
            shooterState = Shooter.RUN_FAR;
            backWallOpen = true;
        } else if (operator.getAButton()) {
            shooterState = Shooter.INBOUND;
            backWallOpen = true;
        } else if (operator.getXButton()) {
            shooterState = Shooter.FENDER;
            backWallOpen = true;
        } else {
            shooterState = Shooter.OFF;
        }

        //Clapper
        SmartDashboard.putBoolean("dtrigger", driver.getRightTrigger());
        if (driver.getRightTrigger()) {
            clapperState = Clapper.SHOOT;
        } else if (operator.getLeftTrigger()) {
            clapperState = Clapper.REAR_OUTTAKE;
        } else if (operator.getRightTrigger()) {
            clapperState = Clapper.FRONT_OUTTAKE;
        } else {
            clapperState = Clapper.DOWN;
        }

        //Intake
        if (operator.getLeftBumper()) {
            frontIntakeState = FrontIntake.DOWN;
            rearIntakeState = RearIntake.INTAKE;
            backWallOpen = true;
        } else if (operator.getRightBumper()) {
            frontIntakeState = FrontIntake.INTAKE;
            rearIntakeState = RearIntake.DOWN;
        } else if (operator.getLeftTrigger()) {
            frontIntakeState = FrontIntake.PUSH_OTHER_SIDE;
            rearIntakeState = RearIntake.OUTTAKE;
        } else if (operator.getRightTrigger()) {
            frontIntakeState = FrontIntake.OUTTAKE;
            rearIntakeState = RearIntake.PUSH_OTHER_SIDE;
        } else {
            frontIntakeState = FrontIntake.DOWN;
            rearIntakeState = RearIntake.DOWN;
        }
    }
}
