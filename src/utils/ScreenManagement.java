package utils;

import main.GamePanel;

public class ScreenManagement {
    private static ScreenManagement instance = null;

    GamePanel gp;
    private float lerp = 0.1f; // smoothing amount
    private float screenX, screenY;

    public static ScreenManagement getInstance(GamePanel gp) {
        if (instance == null) {
            instance = new ScreenManagement(gp);
        }
        return instance;
    }

    private ScreenManagement(GamePanel gp){
        this.gp = gp;
        this.screenX = gp.screenWidth / 2;
        this.screenY = gp.screenHeight / 2;
    }

    public void update() {
        screenX+=(gp.mainCharacter.worldX-screenX-gp.screenWidth/2 + gp.tileSize)*lerp;
        screenY+=(gp.mainCharacter.worldY-screenY-gp.screenHeight/2 + gp.tileSize*2)*lerp;
    }

    public int getScreenX() {
        return (int)screenX;
    }

    public int getScreenY() {
        return (int)screenY;
    }
}
