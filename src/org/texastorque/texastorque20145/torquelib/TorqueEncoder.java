package org.texastorque.texastorque20145.torquelib;

import edu.wpi.first.wpilibj.CounterBase;

public abstract class TorqueEncoder {
    protected CounterBase encoder;
    
    protected double previousTime;
    protected double previousRate;
    
    protected int currentPosition;
    protected int previousPosition;
    
    protected double rate;
    protected double acceleration;
    
    public abstract void calc();
    
    public void reset()
    {
        encoder.reset();
    }
    
    public void start()
    {
        encoder.start();
    }
    
    public int getPosition()
    {
        return currentPosition;
    }
    
    public double getRate()
    {
        return rate;
    }
    
    public double getAcceleration()
    {
        return acceleration;
    }
    
    public double getPeriod()
    {
        return encoder.getPeriod();
    }
}
