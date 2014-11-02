package org.texastorque.texastorque20145.subsystem;

import org.texastorque.texastorque20145.feedback.FeedbackSystem;
import org.texastorque.texastorque20145.input.InputSystem;

public abstract class Subsystem {
    protected InputSystem input;
    protected FeedbackSystem feedback;
    
    protected boolean outputEnabled;
    protected volatile int state;
    
    public void enableOutput(boolean enable)
    {
        outputEnabled = enable;
    }
    
    public void setInputSystem(InputSystem in)
    {
        input = in;
    }
    
    public void setFeedbackSystem(FeedbackSystem feed)
    {
        feedback = feed;
    }
    
    public abstract void update();
    
    public void updateGains()
    {
        
    }
}
