package unit;

import java.util.*;

public class Soldier extends Unit {
    public Soldier() {
        this.name = "Soldat";
        this.hp = 100;
        this.maxHp = 100;
        this.attack = 45;
        this.defense = 5;
        this.range = 1;
        this.cost = new HashMap<>();
        cost.put("Or", 30);
    }
    
    @Override
    public void specialAbility() {
        System.out.println(name + " utilise Bouclier! Défense +5");
        defense += 5;
    }
}