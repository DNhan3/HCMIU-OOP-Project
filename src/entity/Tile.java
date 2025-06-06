package entity;

import java.awt.Graphics2D;

import main.GamePanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile{
    private GamePanel gp;
    private int tileNum;
    private BufferedImage tileImage;
    private boolean isSolid = false;

    public Tile(GamePanel gp, int tileNum) {
        this.gp = gp;
        this.tileNum = tileNum;
        if (tileNum == 1) {
            isSolid = true;
        }

        try {
            if (tileNum < 0 || tileNum > 1) {
                throw new IllegalArgumentException("Invalid tile number: " + tileNum);
            }
            tileImage = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tile" + tileNum + ".png"));
        } catch (IOException | IllegalArgumentException e) {
            tileImage = null;
            System.out.println("Error loading tile image: " + e.getMessage());
        }
    }

    public void draw(Graphics2D g2d, int x, int y) {
        g2d.drawImage(tileImage, x, y, gp.tileSize, gp.tileSize, null);

    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public int getTileNum() {
        return tileNum;
    }

    public void setTileNum(int tileNum) {
        this.tileNum = tileNum;
    }

    public BufferedImage getTileImage() {
        return tileImage;
    }

    public void setTileImage(BufferedImage tileImage) {
        this.tileImage = tileImage;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

}
