package factory;

import entity.Tile;
import main.GamePanel;

public class BasicTileFactory implements TileFactory{
    @Override
    public Tile createTile(int tileID, GamePanel gp) {
        switch (tileID) {
            case 0:
                return new Tile(gp, 0);
            case 1:
                return new Tile(gp, 1);
            default:
                return new Tile(gp, 0);
        }
    }
}
