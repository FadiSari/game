package map;

public class TileType {
    public static final TileType GRASS = new TileType("Grass", true);
    public static final TileType WATER = new TileType("Water", false);
    public static final TileType MOUNTAIN = new TileType("Mountain", false);
    public static final TileType FOREST = new TileType("Forest", true);
    
    private String name;
    private boolean walkable;
    
    private TileType(String name, boolean walkable) {
        this.name = name;
        this.walkable = walkable;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isWalkable() {
        return walkable;
    }
}

