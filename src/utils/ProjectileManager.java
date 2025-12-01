package utils;
import skills.Projectile;

import java.awt.Graphics;
import java.util.ArrayList;

public class ProjectileManager {
    private static ProjectileManager instance = null;
    public static ProjectileManager getInstance() {
        if (instance == null) {
            instance = new ProjectileManager();
        }
        return instance;
    }
    
    public ArrayList<Projectile> projectiles;

    public ProjectileManager() {
        projectiles = new ArrayList<>();
    }
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public void updateProjectiles() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            p.update();
            if (p.isExpired()) {
                projectiles.remove(i);
                i--;
            }
        }
    }

    public void drawProjectiles(Graphics g2) {
        for (Projectile p : projectiles) {
            p.draw(g2); // Assuming Projectile has a draw method
        }
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }
}