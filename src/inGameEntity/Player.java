package inGameEntity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import entity.Entity;
import main.GamePanel;
import skills.Projectile;
import utils.KeyHandler;

public class Player extends Entity {
    private static Player instance = null;
    public static Player getInstance(GamePanel gp) {
        if (instance == null) {
            instance = new Player(gp);
        }
        return instance;
    }
    
    private Player(GamePanel gp) {
        super(gp, "Player", (int)(gp.tileSize*1.5), (int)(gp.tileSize*1.5), gp.tileSize, gp.tileSize, new Rectangle(48, 48), 5, 10, 5, 5, new boolean[4], new int[4]);
    }

    private int ticks = 0;
    private int stuntDurations = 0;
    private Rectangle hitBox = new Rectangle(0, 0, gp.tileSize, gp.tileSize);


    private void handleMovementInput(KeyHandler keyH) {
        if (keyH.leftPressed) {
            if (!getCollisionOn()[0])
                worldX = worldX - getSpeed();
            else
                worldX = getCollisionTile()[0] * gp.tileSize + getWidth()/2 - getColGap();
        } else if (keyH.rightPressed) {
            if (!getCollisionOn()[1])
                worldX = worldX + getSpeed();
            else
                worldX = getCollisionTile()[1] * gp.tileSize + gp.tileSize - getWidth()/2 + getColGap() - 1;
        }
        if (keyH.upPressed) {
            if (!getCollisionOn()[2])
                worldY = worldY - getSpeed();
            else
                worldY = getCollisionTile()[2] * gp.tileSize + getHeight()/2 - getRowGap();
        } else if (keyH.downPressed) {
            if (!getCollisionOn()[3])
                worldY = worldY + getSpeed();
            else
                worldY = getCollisionTile()[3] * gp.tileSize + gp.tileSize - getHeight()/2 + getRowGap() - 1;
        }
    }

    public void checkCollisionWithEnemies(){
        for (Crep enemy : gp.crepsManager.getCreps()) {
            if (!enemy.isAlive()) {
                continue;
            }
            Rectangle playerDamageZone = new Rectangle();
            playerDamageZone.width = getWidth();
            playerDamageZone.height = getHeight();
            playerDamageZone.x = worldX;
            playerDamageZone.y = worldY;

            Rectangle enemyBox = new Rectangle();
            enemyBox.width = enemy.getWidth();
            enemyBox.height = enemy.getHeight();
            enemyBox.x = enemy.worldX;
            enemyBox.y = enemy.worldY;

            if (playerDamageZone.intersects(enemyBox)) {
                takeDamage(enemy);
            }
        }
    }

    public void takeDamage(Crep enemy) {
        // Placeholder for getting damaged logic
        knockBack(gp.tileSize / 2, this.worldX - enemy.worldX, this.worldY - enemy.worldY);
    }

    public void knockBack(int length, int dx, int dy) {
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        dx = (int)((dx / distance) * length);
        dy = (int)((dy / distance) * length);

        worldX += dx;
        worldY += dy;

        if (getCollisionOn()[0]){
            worldX = getCollisionTile()[0] * gp.tileSize - this.getColGap();
        }
        if (getCollisionOn()[1]){
            worldX = getCollisionTile()[1] * gp.tileSize - gp.tileSize + this.getColGap() - 1;
        }
        if (getCollisionOn()[2]){
            worldY = getCollisionTile()[2] * gp.tileSize - this.getRowGap();
        }
        if (getCollisionOn()[3]){
            worldY = getCollisionTile()[3] * gp.tileSize - gp.tileSize + this.getRowGap() - 1;
        }
        stuntDurations = 5;
    }

    public void autoAttack() {
        if (ticks % 30 == 0) { // Example: every 30 ticks
            Point target = gp.mouseTrack.getMouseWorldPosition(gp);
            Projectile projectile = new Projectile(gp, target, this, 1, 15, 300);
            projectile.setCaster(this);
            gp.projectileManager.addProjectile(projectile);
        }
    }

    public void ticksController() {
        ticks++;
        autoAttack();
        if (ticks > 60) {
            ticks = 0;
        }
    }

    public void updateHitBox() {
        hitBox.x = worldX + getColGap()/2;
        hitBox.y = worldY + getRowGap()/2;
        hitBox.width = gp.tileSize - getColGap() * 2;
        hitBox.height = gp.tileSize - getRowGap() * 2;
    }
    
    public void update(KeyHandler keyH) {
        if (stuntDurations > 0) {
            stuntDurations--;
            return;
        }
        handleMovementInput(keyH);
        gp.collisionChecker.checkTile(this);
        checkCollisionWithEnemies();
        updateHitBox();
        ticksController();
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(java.awt.Color.RED);
        g2.fillRect(getScreenX(), getScreenY(), gp.tileSize, gp.tileSize);
    }

    public int getStuntDurations() {
        return stuntDurations;
    }

    public void setStuntDurations(int stuntDurations) {
        this.stuntDurations = stuntDurations;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }


}
