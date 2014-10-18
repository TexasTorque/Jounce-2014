package org.texastorque.texastorque20145.input;

public abstract class InputSystem implements Runnable {
    //Drivebase
    protected volatile double leftSpeed;
    protected volatile double rightSpeed;
    protected volatile boolean gear;
    
    public synchronized double getLeftSpeed()
    {
        return leftSpeed;
    }
    
    public synchronized double getRightSpeed()
    {
        return rightSpeed;
    }
    
    public synchronized boolean getGear()
    {
        return gear;
    }
}
