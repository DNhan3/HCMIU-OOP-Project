package factory;

import entity.Tile;
import main.GamePanel;

public interface TileFactory {
    Tile createTile(int tileID, GamePanel gp);
}
