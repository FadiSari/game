package unit;

import java.util.*;

public class Archer extends Unit {
    public Archer() {
        this.name = "Archer";
        this.hp = 80;
        this.maxHp = 80;
        this.attack = 40;
        this.defense = 3;
        this.range = 3;
        this.cost = new HashMap<>();
        cost.put("Or", 40);
    }
    
    @Override
    public void specialAbility() {
        System.out.println(name + " utilise Flèche précise! Attaque +10");
        attack += 10;
    }
}