package entity.Monster;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TheDog extends Monster {
    private BufferedImage[] leftImages = new BufferedImage[7];
    private BufferedImage[] rightImages = new BufferedImage[7];

    public TheDog(GamePanel gp, int worldX, int worldY) {
        super(gp, worldX, worldY);
        getImage();
    }

    @Override
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

    @Override
    public void setDefaultValue() {
        super.setDefaultValue();
        setMovingLength(gp.tileSize);
    }

    private BufferedImage setup(String string) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream(string + ".png"));
    }


    public void getImage() {
        try {
            leftImages[0] = setup("/res/monster/left/1");
            leftImages[1] = setup("/res/monster/left/2");
            leftImages[2] = setup("/res/monster/left/3");
            leftImages[3] = setup("/res/monster/left/4");
            leftImages[4] = setup("/res/monster/left/5");
            leftImages[5] = setup("/res/monster/left/6");
            leftImages[6] = setup("/res/monster/left/7");

            rightImages[0] = setup("/res/monster/right/1");
            rightImages[1] = setup("/res/monster/right/2");
            rightImages[2] = setup("/res/monster/right/3");
            rightImages[3] = setup("/res/monster/right/4");
            rightImages[4] = setup("/res/monster/right/5");
            rightImages[5] = setup("/res/monster/right/6");
            rightImages[6] = setup("/res/monster/right/7");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
