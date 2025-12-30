package map;

public class GameMap {
    private Tile[][] tiles;
    private int width;
    private int height;
    
    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        generateMap();
    }
    
    private void generateMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(TileType.GRASS);
            }
        }
    }
    
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }
}
