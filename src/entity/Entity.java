package entity;

import main.GamePanel;
import java.awt.Rectangle;

public class Entity {
    public GamePanel gp;
    public int worldX, worldY; // World coordinates
    private int width, height;
    private int direction = 0; // 0: up, 1: down, 2: left, 3: right
    private String name;
    
    private int speed = 0;
    private int damage;
    private int hp;
    private int maxHp;

    // collision
    private Rectangle collisionBox;
    private boolean[] collisionOn = new boolean[4]; // 1 = up, 2 = down, 3 = left, 4 = right check direction of
    private int[] collisionTile = new int[4]; // 1 = up, 2 = down, 3 = left, 4 = right check tile number of collision
    private int colGap;
    private int rowGap;

    private boolean isAlive = true;

    public Entity(GamePanel gp, String name, int worldX, int worldY, int width, int height, Rectangle collisionBox, int speed, int damage, int hp, int maxHp,
            boolean[] collisionOn,
            int[] collisionTile) {
        this.gp = gp;
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;
        this.collisionBox = collisionBox;
        colGap = (width - collisionBox.width) / 2;
        rowGap = (height - collisionBox.height) / 2;
        this.speed = speed;
        this.damage = damage;
        this.hp = hp;
        this.maxHp = maxHp;
        this.isAlive = true;
        this.name = name;
    }

    public int getScreenX() {
        return worldX - gp.screenManagement.getScreenX() - width/2;
    }

    public int getScreenY() {
        return worldY - gp.screenManagement.getScreenY() - height/2;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speedX) {
        this.speed = speedX;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
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

    public boolean[] getCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(int index, boolean collisionOn) {
        this.collisionOn[index] = collisionOn;
    }

    public int[] getCollisionTile() {
        return collisionTile;
    }

    public void setCollisionTile(int index, int collisionTile) {
        this.collisionTile[index] = collisionTile;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void damageHp(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
            this.isAlive = false;
        }
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}