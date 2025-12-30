package map;

public class Tile {
    private TileType type;
    private boolean accessible;
    
    public Tile(TileType type) {
        this.type = type;
        this.accessible = type != TileType.WATER && type != TileType.MOUNTAIN;
    }
    
    public TileType getType() { return type; }
    public boolean isAccessible() { return accessible; }
}

// ============================================
// map/TileType.java
