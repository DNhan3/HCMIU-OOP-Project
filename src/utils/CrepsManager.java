package utils;

import main.GamePanel;
import java.util.ArrayList;
import inGameEntity.Crep;

public class CrepsManager {
    private static CrepsManager instance = null;
    public static CrepsManager getInstance(GamePanel gp) {
        if (instance == null) {
            instance = new CrepsManager(gp);
        }
        return instance;
    }

    private ArrayList<Crep> creps;

    private CrepsManager(GamePanel gp) {
        creps = new ArrayList<>();
        creps.add(new Crep(gp, (int)(4.5*gp.tileSize), (int)(4.5*gp.tileSize))); // Placeholder for index 0/
        creps.add(new Crep(gp, 10*gp.tileSize, 5*gp.tileSize)); // Placeholder for index 1
        creps.add(new Crep(gp, 15*gp.tileSize, 10*gp.tileSize)); // Placeholder for index 2
        creps.add(new Crep(gp, 20*gp.tileSize, 15*gp.tileSize)); // Placeholder for index 3
    }

    public void updateCreps() {
        for (int i = 0; i < creps.size(); i++) {
            Crep crep = creps.get(i);
            if (!crep.isAlive()) {
                creps.remove(i);
                i--;
                continue;
            }
            crep.update();
        }
    }

    public void drawCreps(java.awt.Graphics g2) {
        for (Crep crep : creps) {
            if (!crep.isAlive()) {
                continue;
            }
            crep.draw(g2);
        }
    }

    public ArrayList<Crep> getCreps() {
        return creps;
    }
}
