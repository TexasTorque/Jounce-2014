package org.texastorque.texastorque20145.input;

public abstract class InputSystem implements Runnable {

    //Drivebase
    protected volatile double leftSpeed;
    protected volatile double rightSpeed;
    protected volatile boolean gear;

    public synchronized double getLeftSpeed() {
        return leftSpeed;
    }

    public synchronized double getRightSpeed() {
        return rightSpeed;
    }

    public synchronized boolean getGear() {
        return gear;
    }

    //Shooter
    protected volatile int shooterState;

    public synchronized int getShooterState() {
        return shooterState;
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
}
