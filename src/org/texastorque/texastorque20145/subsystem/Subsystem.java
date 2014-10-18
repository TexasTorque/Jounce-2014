package org.texastorque.texastorque20145.subsystem;

import org.texastorque.texastorque20145.input.InputSystem;

public abstract class Subsystem {
    protected InputSystem input;
    
    protected boolean outputEnabled;
    
    public void enableOutput(boolean enable)
    {
        outputEnabled = enable;
    }
    
    public void setInputSystem(InputSystem in)
    {
        input = in;
    }
    
    public abstract void update();
}
