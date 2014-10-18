package org.texastorque.texastorque20145.autonomous;

import org.texastorque.texastorque20145.constants.Constants;
import org.texastorque.texastorque20145.input.InputSystem;

public class AutoPicker {

    private static final int doNothing = 0;
    private static final int oneHot = 1;
    private static final int twoHot = 2;
    private static final int threeHot = 3;

    public static InputSystem getMode() {
        int autoMode = (int) Constants.autoMode.getDouble();

        switch (autoMode) {
            case doNothing:
                break;
            case oneHot:
                break;
            case twoHot:
                break;
            case threeHot:
                break;
            default:
                break;
        }
        return null;
    }
}
