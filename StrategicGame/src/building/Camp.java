package building;

import java.util.*;

public class Camp extends Building {
    public Camp() {
        this.name = "Camp d'entraînement";
        this.cost = new HashMap<>();
        cost.put("Or", 80);
        cost.put("Bois", 50);
        this.constructionTime = 3;
    }
    
    @Override
    public Map<String, Integer> produce() {
        return new HashMap<>();
    }
}