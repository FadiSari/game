package building;

import java.util.*;

public class Farm extends Building {
    public Farm() {
        this.name = "Ferme";
        this.cost = new HashMap<>();
        cost.put("Or", 40);
        cost.put("Bois", 40);
        this.constructionTime = 2;
    }
    
    @Override
    public Map<String, Integer> produce() {
        Map<String, Integer> production = new HashMap<>();
        production.put("Bois", 15);
        return production;
    }
}
