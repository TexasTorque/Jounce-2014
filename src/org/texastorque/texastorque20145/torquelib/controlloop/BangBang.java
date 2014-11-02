package org.texastorque.texastorque20145.torquelib.controlloop;

public class BangBang extends ControlLoop {
    
    public BangBang()
    {
        super();
    }
    
    public double calculate(double current)
    {
        currentValue = current;
        if (currentValue < setPoint)
        {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}
