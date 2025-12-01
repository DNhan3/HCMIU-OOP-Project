package utils;

import java.awt.MouseInfo;
import java.awt.Point;
import main.GamePanel;
import java.awt.IllegalComponentStateException;

public class CoordinateManager {
    private static CoordinateManager instance = null;
    Point mousePos;
    
    private CoordinateManager() {
        mousePos = getMousePosition();
    }

    public static CoordinateManager getInstance() {
        if (instance == null) {
            instance = new CoordinateManager();
        }
        return instance;
    }

    public Point getMousePosition() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    /**
     * Returns the mouse position relative to the top-left of the game panel (in pixels).
     * If the panel is not yet showing on screen this may throw; we catch and return (0,0).
     */
    public Point getMousePanelPosition(GamePanel gp) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        try {
            Point panelLoc = gp.getLocationOnScreen();
            return new Point(p.x - panelLoc.x, p.y - panelLoc.y);
        } catch (IllegalComponentStateException e) {
            return new Point(0, 0);
        }
    }

    /**
     * Returns the mouse position in world/game coordinates (pixels).
     * This adds the camera/screen offset (from ScreenManagement) to the
     * panel-local mouse position.
     */
    public Point getMouseWorldPosition(GamePanel gp) {
        Point panelPos = getMousePanelPosition(gp);
        int worldX = gp.screenManagement.getScreenX() + panelPos.x;
        int worldY = gp.screenManagement.getScreenY() + panelPos.y;
        return new Point(worldX, worldY);
    }
}
