package org.texastorque.texastorque20145.autonomous;

import org.texastorque.texastorque20145.feedback.FeedbackSystem;
import org.texastorque.texastorque20145.input.InputSystem;

public abstract class AutoMode extends InputSystem {
    
    private FeedbackSystem feedback;
    
    public void setFeedBackSystem(FeedbackSystem fb)
    {
        feedback = fb;
    }
    
    public void wait(double seconds)
    {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
