package org.texastorque.texastorque20145.autonomous;

import org.texastorque.texastorque20145.constants.Constants;

public class AutoPicker {

    private static final int doNothing = 0;
    private static final int oneHot = 1;
    private static final int twoHot = 2;
    private static final int threeHot = 3;
    private static final int driveForward = 4;

    public static AutoMode getAutoMode() {
        int autoMode = (int) Constants.autoMode.getDouble();

        switch (autoMode) {
            case doNothing:
                return new DoNothingAuto();
            case oneHot:
                break;
            case twoHot:
                break;
            case threeHot:
                break;
            case driveForward:
                return new DriveForwardAuto();
            default:
                return new DoNothingAuto();

        }
        
        return new DoNothingAuto();
    }
}
