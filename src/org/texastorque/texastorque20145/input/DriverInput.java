package org.texastorque.texastorque20145.input;

import org.texastorque.texastorque20145.torquelib.GenericController;

public class DriverInput extends InputSystem {
    
    private GenericController driver;
    private GenericController operator;

    public DriverInput() {
        driver = new GenericController(1, GenericController.LOGITECH, 0.1);
        operator = new GenericController(2, GenericController.XBOX, 0.0);
    }

    public void run() {
        //Drivebase
        leftSpeed = driver.getLeftYAxis() + driver.getRightXAxis();
        rightSpeed = driver.getLeftYAxis() - driver.getRightXAxis();
        gear = driver.getLeftBumper();
        
        //Shooter
    }

}
