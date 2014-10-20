package org.texastorque.texastorque20145.input;

import org.texastorque.texastorque20145.subsystem.Clapper;
import org.texastorque.texastorque20145.subsystem.FrontIntake;
import org.texastorque.texastorque20145.subsystem.RearIntake;
import org.texastorque.texastorque20145.subsystem.Shooter;
import org.texastorque.texastorque20145.torquelib.GenericController;

public class DriverInput extends InputSystem {

    private GenericController driver;
    private GenericController operator;

    public DriverInput() {
        driver = new GenericController(1, GenericController.LOGITECH, 0.1);
        operator = new GenericController(2, GenericController.XBOX, 0.0);
    }

    public void run() {
        backWallOpen = false;

        //Drivebase
        leftSpeed = driver.getLeftYAxis() + driver.getRightXAxis();
        rightSpeed = driver.getLeftYAxis() - driver.getRightXAxis();
        gear = driver.getLeftBumper();

        //Shooter
        if (operator.getRightTrigger() && operator.getXButton()) {
            shooterState = Shooter.LOW_GOAL;
            backWallOpen = true;
        } else if (operator.getYButton()) {
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
