package player;

import building.Building;
import building.Camp;
import unit.Unit;
import java.util.*;

public class Player {
    private String name;
    private Map<String, Integer> resources;
    private List<Building> buildings;
    private List<Unit> units;
    
    public Player(String name) {
        this.name = name;
        this.resources = new HashMap<>();
        this.buildings = new ArrayList<>();
        this.units = new ArrayList<>();
    }
    
    public void addResource(String type, int amount) {
        resources.put(type, resources.getOrDefault(type, 0) + amount);
    }
    
    public boolean hasResources(Map<String, Integer> cost) {
        for (Map.Entry<String, Integer> entry : cost.entrySet()) {
            if (resources.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
    
    public void spend(Map<String, Integer> cost) {
        for (Map.Entry<String, Integer> entry : cost.entrySet()) {
            resources.put(entry.getKey(), resources.get(entry.getKey()) - entry.getValue());
        }
    }
    
    public boolean hasCamp() {
        for (Building b : buildings) {
            if (b instanceof Camp) return true;
        }
        return false;
    }
    
    public void addBuilding(Building b) { buildings.add(b); }
    public void addUnit(Unit u) { units.add(u); }
    
    public Map<String, Integer> getResources() { return resources; }
    public List<Building> getBuildings() { return buildings; }
    public List<Unit> getUnits() { return units; }
    public String getName() { return name; }
}
