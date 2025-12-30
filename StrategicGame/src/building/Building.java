package building;

import java.util.Map;

public abstract class Building {
    protected String name;
    protected Map<String, Integer> cost;
    protected int constructionTime;
    
    public abstract Map<String, Integer> produce();
    
    public String getName() { return name; }
    public Map<String, Integer> getCost() { return cost; }
    public int getConstructionTime() { return constructionTime; }
}

