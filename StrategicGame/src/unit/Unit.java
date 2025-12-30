package unit;

import java.util.Map;

public abstract class Unit {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int range;
    protected Map<String, Integer> cost;
    
    public abstract void specialAbility();
    
    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - defense);
        hp -= actualDamage;
        if (hp < 0) hp = 0;
    }
    
    public boolean isAlive() { return hp > 0; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public Map<String, Integer> getCost() { return cost; }
}
