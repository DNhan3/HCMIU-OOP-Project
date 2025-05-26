package entity.Monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Monster extends Entity {
    private int movingLength;
    private int initialX;
    private BufferedImage[] leftImages = new BufferedImage[7];
    private BufferedImage[] rightImages = new BufferedImage[7];
    
    public Monster(GamePanel gp, int worldX, int worldY) {
        super(gp);
        this.worldX = worldX;
        this.worldY = worldY;
        setDefaultValue();
        movingLength = 2 * gp.tileSize;
        initialX = worldX;
        this.collisionBox.width = 48; // Set the width of the collision box
        this.collisionBox.height = 48; // Set the height of the collision box
    }

    public void setDefaultValue() {
        speedX = 0;
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public void setMovingLength(int movingLength) {
        this.movingLength = movingLength;
    }

    public void draw(Graphics2D monster) {
        if (isAlive) {
            screenX = worldX - gp.mainCharacter.getWorldX() + gp.mainCharacter.getScreenX();
            screenY = worldY - gp.mainCharacter.getWorldY() + gp.mainCharacter.getScreenY();
            // Animation logic
            int frameCount = leftImages.length;
            int frameIndex = (int)((System.currentTimeMillis() / 100) % frameCount);

            BufferedImage currentImage;
            if (direction.equals("left")) {
                currentImage = leftImages[frameIndex];
            } else {
                currentImage = rightImages[frameIndex];
            }
            monster.drawImage(currentImage, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void update() {
        if (isAlive) {
            if (worldX >= initialX + movingLength) {
                speedX = -1; // Move left
                direction = "left";
            } else if (worldX <= initialX) {
                speedX = 1; // Move right
                direction = "right";
            }
            worldX += speedX;

            checkCollision();
        }
    }

    public void checkCollision() {
        collisionBox.setLocation(worldX, worldY);

        if (isAlive && gp.mainCharacter.getCollisionBox().intersects(this.collisionBox)) {
            int mainBottom = gp.mainCharacter.getWorldY() + gp.tileSize;
            int monsterTop = this.worldY;

            if (mainBottom >= monsterTop && gp.mainCharacter.getSpeedY() > 0) {
                isAlive = false;
                gp.mainCharacter.setSpeedY(gp.mainCharacter.getSpeedY() - 10); // Bounce up after kill
            } else {
                gp.mainCharacter.setAlive(false);
            }
        }
    }

}
