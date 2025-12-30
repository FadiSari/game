
package building;

import java.util.*;

public class Mine extends Building {
 public Mine() {
     this.name = "Mine";
     this.cost = new HashMap<>();
     cost.put("Or", 50);
     cost.put("Bois", 30);
     this.constructionTime = 2;
 }
 
 @Override
 public Map<String, Integer> produce() {
     Map<String, Integer> production = new HashMap<>();
     production.put("Or", 20);
     return production;
 }
}