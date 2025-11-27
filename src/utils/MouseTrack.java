package utils;

import java.awt.MouseInfo;
import java.awt.Point;

public class MouseTrack {
    private static MouseTrack instance = null;
    Point mousePos;
    
    private MouseTrack() {
        mousePos = getMousePosition();
    }

    public static MouseTrack getInstance() {
        if (instance == null) {
            instance = new MouseTrack();
        }
        return instance;
    }

    public Point getMousePosition() {
        return MouseInfo.getPointerInfo().getLocation();
    }
}
