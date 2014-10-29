package org.texastorque.texastorque20145.input;

public abstract class InputSystem implements Runnable {

    //Drivebase
    protected volatile double leftSpeed;
    protected volatile double rightSpeed;
    protected volatile boolean isHighGear;

    public synchronized double getLeftSpeed() {
        return leftSpeed;
    }

    public synchronized double getRightSpeed() {
        return rightSpeed;
    }

    public synchronized boolean getGear() {
        return isHighGear;
    }

    //Shooter
    protected volatile int shooterState;
    protected volatile boolean shooterManual;

    public synchronized int getShooterState() {
        return shooterState;
    }
    
    public synchronized boolean shooterIsManual()
    {
        return shooterManual;
    }
    
    //Clapper
    protected volatile int clapperState;
    
    public synchronized int getClapperState()
    {
        return clapperState;
    }

    //Front Intake
    protected volatile int frontIntakeState;
    protected volatile double manualFrontAngleSpeed;
    protected volatile boolean frontIntakeManual;

    public synchronized int getFrontIntakeState() {
        return frontIntakeState;
    }

    public synchronized double getFrontAngleManualSpeed() {
        return manualFrontAngleSpeed;
    }
    
    public synchronized boolean frontIntakeIsManual()
    {
        return frontIntakeManual;
    }

    //Rear Intake
    protected volatile int rearIntakeState;
    protected volatile double manualRearAngleSpeed;
    protected volatile boolean rearIntakeManual;
    protected volatile boolean backWallOpen;

    public synchronized int getRearIntakeState() {
        return rearIntakeState;
    }

    public synchronized double getRearAngleManualSpeed() {
        return manualRearAngleSpeed;
    }
    
    public synchronized boolean rearIntakeIsManual()
    {
        return rearIntakeManual;
    }
    
    public synchronized boolean backWallIsOpen()
    {
        return backWallOpen;
    }
}
