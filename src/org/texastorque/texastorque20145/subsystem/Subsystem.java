package org.texastorque.texastorque20145.subsystem;

import org.texastorque.texastorque20145.input.InputSystem;

public abstract class Subsystem {
    private InputSystem input;
    
    public void setInputSystem(InputSystem in)
    {
        input = in;
    }
    
    public abstract void update();
}
