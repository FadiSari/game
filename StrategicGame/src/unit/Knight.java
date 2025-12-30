 package unit;

import java.util.*;

public class Knight extends Unit {
    public Knight() {
        this.name = "Cavalier";
        this.hp = 120;
        this.maxHp = 120;
        this.attack = 70;
        this.defense = 7;
        this.range = 1;
        this.cost = new HashMap<>();
        cost.put("Or", 60);
    }
    
    @Override
    public void specialAbility() {
        System.out.println(name + " utilise Charge! Attaque +15");
        attack += 15;
    }
}
