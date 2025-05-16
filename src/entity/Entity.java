package entity;

import main.GamePanel;
import java.awt.Rectangle;


public class Entity {
    GamePanel gp;
    public int worldX, worldY;      // World coordinates
    public int screenX, screenY;    // Screen coordinates

    public int speedX = 0;
    public int speedY = 0;
    public String direction = "right";
    public String oldAction = "idle";
    public boolean grounded = false;
    public boolean[] collisionOn = new boolean[5]; // 1 = up, 2 = down, 3 = left, 4 = right check direction of collision
    public int[] collisionTile = new int[5]; // 1 = up, 2 = down, 3 = left, 4 = right check tile number of collision
    public Rectangle collisionBox;

    public Entity(GamePanel gp) {
        this.gp = gp;

        // Initialize collision box
        collisionBox = new Rectangle(0, 0, 32, 32); // Default size, can be adjusted
    }

}
