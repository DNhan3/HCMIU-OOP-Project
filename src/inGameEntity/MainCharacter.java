package inGameEntity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import entity.Entity;
import main.GamePanel;
import utils.KeyHandler;

public class MainCharacter extends Entity {
    private boolean[] collisionOn = new boolean[4]; // 1 = up, 2 = down, 3 = left, 4 = right check direction of
                                                    // collision
    private int[] collisionTile = new int[4]; // 1 = up, 2 = down, 3 = left, 4 = right check tile number of collision

    private static MainCharacter instance = null;

    private MainCharacter(GamePanel gp) {
        super(gp, "MainCharacter", gp.tileSize, gp.tileSize, 8, new Rectangle(0, 0, gp.tileSize / 2, gp.tileSize / 2), new boolean[4], new int[4]);
    }

    public static MainCharacter getInstance(GamePanel gp) {
        if (instance == null) {
            instance = new MainCharacter(gp);
        }
        return instance;
    }

    // Projectile

    private void handleMovementInput(KeyHandler keyH) {
        if (keyH.leftPressed) {
            if (!getCollisionOn()[0])
                setWorldX(getWorldX() - getSpeed());
            else
                setWorldX(getCollisionTile()[0] * gp.tileSize - this.getColGap());
        } else if (keyH.rightPressed) {
            if (!getCollisionOn()[1])
                setWorldX(getWorldX() + getSpeed());
            else
                setWorldX(getCollisionTile()[1] * gp.tileSize - gp.tileSize + this.getColGap() - 1);
        }
        if (keyH.upPressed) {
            if (!getCollisionOn()[2])
                setWorldY(getWorldY() - getSpeed());
            else
                setWorldY(getCollisionTile()[2] * gp.tileSize - this.getRowGap());
        } else if (keyH.downPressed) {
            if (!getCollisionOn()[3])
                setWorldY(getWorldY() + getSpeed());
            else
                setWorldY(getCollisionTile()[3] * gp.tileSize - gp.tileSize + this.getRowGap() - 1);
        }

    }

    public void draw(Graphics2D g2) {
        g2.setColor(java.awt.Color.RED);
        g2.fillRect(getScreenX(), getScreenY(), gp.tileSize, gp.tileSize);
    }

    public void update(KeyHandler keyH) {
        handleMovementInput(keyH);
        gp.collisionChecker.checkTile(this);
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


}
