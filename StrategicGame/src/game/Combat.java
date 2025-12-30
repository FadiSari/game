package game;

import unit.Unit;
import java.util.Random;

public class Combat {
    private static Random random = new Random();
    
    public static void fight(Unit attacker, Unit defender) {
        System.out.println("\n⚔️ Combat: " + attacker.getName() + " VS " + defender.getName());
        
        int damage = attacker.getAttack() + random.nextInt(10) - 5;
        defender.takeDamage(damage);
        
        System.out.println(attacker.getName() + " inflige " + damage + " dégâts!");
        System.out.println(defender.getName() + " HP: " + defender.getHp());
        
        if (defender.isAlive()) {
            int counterDamage = defender.getAttack() + random.nextInt(10) - 5;
            attacker.takeDamage(counterDamage);
            System.out.println(defender.getName() + " contre-attaque! " + counterDamage + " dégâts!");
            System.out.println(attacker.getName() + " HP: " + attacker.getHp());
        }
    }
}
