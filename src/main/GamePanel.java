package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Toolkit;

import inGameEntity.Player;
import utils.CollisionChecker;
import utils.KeyHandler;
import utils.ProjectileManager;
import utils.CoordinateManager;
import utils.CrepsManager;
import utils.ScreenManagement;
import utils.TileMangement;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;

    public KeyHandler keyHandler = KeyHandler.getInstance();
    public CoordinateManager mouseTrack = CoordinateManager.getInstance();
    public ScreenManagement screenManagement = ScreenManagement.getInstance(this);
    public TileMangement tileM = TileMangement.getInstance(this);
    public Player mainCharacter = Player.getInstance(this);
    public CollisionChecker collisionChecker = CollisionChecker.getInstance(this);
    public CrepsManager crepsManager = CrepsManager.getInstance(this);
    public ProjectileManager projectileManager = ProjectileManager.getInstance();
    
    public final int FPS = 30;
    
    public final int originalTileSize = 32;
    public final int tileSize = originalTileSize*2;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 50; // map
    public final int maxWorldRow = 25; // map

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS; // Assuming 60 FPS
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();
            double remainingTime = nextDrawTime - System.nanoTime();
            if (remainingTime > 0) {
                try {
                    Thread.sleep((long) (remainingTime / 1000000)); // Convert to milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            nextDrawTime += drawInterval;
        }
    }

    public void update() {
        screenManagement.update();
        mainCharacter.update(keyHandler);
        crepsManager.updateCreps();
        projectileManager.updateProjectiles();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        mainCharacter.draw(g2);
        crepsManager.drawCreps(g2);
        projectileManager.drawProjectiles(g2);
        Toolkit.getDefaultToolkit().sync();
        g2.dispose();
    }

}
