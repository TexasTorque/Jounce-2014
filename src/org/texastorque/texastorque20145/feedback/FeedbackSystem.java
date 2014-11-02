package org.texastorque.texastorque20145.feedback;

public abstract class FeedbackSystem implements Runnable {

    //Drivebase
    protected volatile double leftPosition;
    protected volatile double leftVelocity;
    protected volatile double leftAcceleration;

    protected volatile double rightPosition;
    protected volatile double rightVelocity;
    protected volatile double rightAcceleration;
    
    protected volatile double gyroAngle;

    public synchronized double getLeftPosition() {
        return leftPosition;
    }

    public synchronized double getLeftVelocity() {
        return leftVelocity;
    }

    public synchronized double getLeftAcceleration() {
        return leftAcceleration;
    }

    public synchronized double getRightPosition() {
        return rightPosition;
    }

    public synchronized double getRightVelocity() {
        return rightVelocity;
    }

    public synchronized double getRightAcceleration() {
        return rightAcceleration;
    }
    
    public synchronized double getGyroAngle()
    {
        return gyroAngle;
    }

    //Shooter
    protected volatile double shooterRPM;

    public synchronized double getShooterRPM() {
        return shooterRPM;
    }
    
    //Intake
    protected volatile double frontIntakeAngle;
    protected volatile boolean frontIntakeHallEffect;
    
    public synchronized double getFrontIntakeAngle()
    {
        return frontIntakeAngle;
    }
    
    public synchronized boolean getFrontIntakeHallEffect()
    {
        return frontIntakeHallEffect;
    }
    
    protected volatile double rearIntakeAngle;
    protected volatile boolean rearIntakeHallEffect;
    protected volatile boolean backWallOpen;
    
    public synchronized double getRearIntakeAngle()
    {
        return rearIntakeAngle;
    }
    
    public synchronized boolean getRearIntakeHallEffect()
    {
        return rearIntakeHallEffect;
    }
    
    public synchronized void backWallIsOpen(boolean open)
    {
        backWallOpen = open;
    }
}
