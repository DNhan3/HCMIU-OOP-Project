package entity;

import main.GamePanel;
import java.awt.Rectangle;

public class Entity {
    public GamePanel gp;
    private int worldX, worldY; // World coordinates
    private int speed = 0;

    private String direction = "right";
    private int colGap;
    private int rowGap;
    private Rectangle collisionBox;

    private boolean isAlive = true;

    public Entity(GamePanel gp, String name, int worldX, int worldY, int speed, Rectangle collisionBox, boolean[] collisionOn,
            int[] collisionTile) {
        this.gp = gp;
        this.worldX = worldX;
        this.worldY = worldY;
        this.collisionBox = collisionBox;
        colGap = (gp.tileSize - collisionBox.width) / 2;
        rowGap = (gp.tileSize - collisionBox.height) / 2;
        this.speed = speed;
        this.isAlive = true;
        System.out.println("Entity " + name + " created at (" + worldX + ", " + worldY + ") with speed " + speed);
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getScreenX() {
        return worldX - gp.screenManagement.getScreenX();
    }

    public int getScreenY() {
        return worldY - gp.screenManagement.getScreenY();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speedX) {
        this.speed = speedX;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getColGap() {
        return colGap;
    }

    public void setColGap(int colGap) {
        this.colGap = colGap;
    }

    public int getRowGap() {
        return rowGap;
    }

    public void setRowGap(int rowGap) {
        this.rowGap = rowGap;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }



}
