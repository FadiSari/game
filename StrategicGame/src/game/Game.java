package game;

import player.Player;
import map.GameMap;
import building.*;
import unit.*;
import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private GameMap map;
    private Player player;
    private List<Unit> enemies;
    private int turn = 1;
    
    public void start() {
        System.out.println("=== JEU DE STRATEGIE ===\n");
        map = new GameMap(8, 8);
        player = new Player("Joueur");
        enemies = new ArrayList<>();
        System.out.println ("IMPORTANT : YOU NEED TO BUILD A CAMP BEFORE ANYTHING ELSE !!");
        
        player.addResource("Or", 200);
        player.addResource("Bois", 150);
        player.addResource("Pierre", 150);
        
        spawnEnemies();
        gameLoop();
    }
    
    private void spawnEnemies() {
        Soldier enemy1 = new Soldier();
        enemy1.setName("Bandit Nord");
        enemies.add(enemy1);
        
        Archer enemy2 = new Archer();
        enemy2.setName("Archer Ennemi");
        enemies.add(enemy2);
        
        Knight enemy3 = new Knight();
        enemy3.setName("Chevalier Noir");
        enemies.add(enemy3);
        
        Soldier enemy4 = new Soldier();
        enemy4.setName("Pillard");
        enemies.add(enemy4);
        
        System.out.println("⚠️ " + enemies.size() + " ennemis détectés sur la carte!");
    }
    
    private void gameLoop() {
        while (true) {
            System.out.println("\n--- TOUR " + turn + " ---");
            displayStatus();
            
            if (enemies.isEmpty()) {
                System.out.println("\n╔════════════════════════════════════╗");
                System.out.println("║   🎉 VICTOIRE TOTALE! 🎉          ║");
                System.out.println("║                                    ║");
                System.out.println("║  Tous les ennemis sont éliminés!  ║");
                System.out.println("║  Tours joués: " + turn + "                      ║");
                System.out.println("╚════════════════════════════════════╝");
                return;
            }
            
            System.out.println("\n1. Construire un bâtiment");
            System.out.println("2. Créer une unité");
            System.out.println("3. Attaquer");
            System.out.println("4. Collecter les ressources");
            System.out.println("5. Passer le tour");
            System.out.println("6. Quitter");
            System.out.print("Choix: ");

            int choice = getIntInput();

            switch (choice) {
                case 1: buildBuilding(); break;
                case 2: createUnit(); break;
                case 3: attack(); break;
                case 4: collectResources(); break;
                case 5: nextTurn(); break;
                case 6: return;
            }
            
            if (player.getUnits().isEmpty() && !player.hasCamp()) {
                System.out.println("\n╔════════════════════════════════════╗");
                System.out.println("║   ☠️  DÉFAITE  ☠️                  ║");
                System.out.println("║                                    ║");
                System.out.println("║  Vous n'avez plus d'unités ni de   ║");
                System.out.println("║  camp pour en créer!               ║");
                System.out.println("╚════════════════════════════════════╝");
                return;
            }
        }
    }
    
    private void displayStatus() {
        System.out.println("\nRessources: " + player.getResources());
        System.out.println("Bâtiments: " + player.getBuildings().size());
        System.out.println("Unités: " + player.getUnits().size());
        System.out.println("⚔️ Ennemis restants: " + enemies.size() + "/4");
    }
    
    private void buildBuilding() {
        System.out.println("\n1. Mine (Or: 50, Bois: 30) - Produit 20 Or/tour");
        System.out.println("2. Camp (Or: 80, Bois: 50) - Permet créer unités");
        System.out.println("3. Ferme (Or: 40, Bois: 40) - Produit 15 Bois/tour");
        System.out.print("Type: ");
        
        int type = getIntInput();
        Building building = null;
        
        switch (type) {
            case 1: building = new Mine(); break;
            case 2: building = new Camp(); break;
            case 3: building = new Farm(); break;
            default: System.out.println("Type invalide!"); return;
        }
        
        System.out.print("Combien de " + building.getName() + " voulez-vous construire? ");
        int quantity = getIntInput();
        
        if (quantity <= 0) {
            System.out.println("✗ Quantité invalide!");
            return;
        }
        

        Map<String, Integer> totalCost = new java.util.HashMap<>();
        for (Map.Entry<String, Integer> entry : building.getCost().entrySet()) {
            totalCost.put(entry.getKey(), entry.getValue() * quantity);
        }
        
        if (player.hasResources(totalCost)) {
            player.spend(totalCost);
            int built = 0;
            for (int i = 0; i < quantity; i++) {
                Building newBuilding = null;
                switch (type) {
                    case 1: newBuilding = new Mine(); break;
                    case 2: newBuilding = new Camp(); break;
                    case 3: newBuilding = new Farm(); break;
                }
                player.addBuilding(newBuilding);
                built++;
            }
            System.out.println("✓ " + built + " " + building.getName() + "(s) construit(s)!");
        } else {
            System.out.println("✗ Ressources insuffisantes pour construire " + quantity + " bâtiment(s)!");
            System.out.println("Coût total requis: " + totalCost);
        }
    }
    
    private void createUnit() {
        if (!player.hasCamp()) {
            System.out.println("✗ Vous devez construire un Camp d'abord!");
            return;
        }
        
        System.out.println("\n1. Soldat (Or: 30, HP: 100, ATK: 15)");
        System.out.println("2. Archer (Or: 40, HP: 80, ATK: 20)");
        System.out.println("3. Cavalier (Or: 60, HP: 120, ATK: 25)");
        System.out.print("Type: ");
        
        int type = getIntInput();
        Unit unit = null;
        
        switch (type) {
            case 1: unit = new Soldier(); break;
            case 2: unit = new Archer(); break;
            case 3: unit = new Knight(); break;
            default: System.out.println("Type invalide!"); return;
        }
        
        System.out.print("Combien de " + unit.getName() + "(s) voulez-vous créer? ");
        int quantity = getIntInput();
        
        if (quantity <= 0) {
            System.out.println("✗ Quantité invalide!");
            return;
        }
        
     
        Map<String, Integer> totalCost = new java.util.HashMap<>();
        for (Map.Entry<String, Integer> entry : unit.getCost().entrySet()) {
            totalCost.put(entry.getKey(), entry.getValue() * quantity);
        }
        
        if (player.hasResources(totalCost)) {
            player.spend(totalCost);
            int created = 0;
            for (int i = 0; i < quantity; i++) {
                Unit newUnit = null;
                switch (type) {
                    case 1: newUnit = new Soldier(); break;
                    case 2: newUnit = new Archer(); break;
                    case 3: newUnit = new Knight(); break;
                }
                player.addUnit(newUnit);
                created++;
            }
            System.out.println("✓ " + created + " " + unit.getName() + "(s) créé(s)!");
        } else {
            System.out.println("✗ Ressources insuffisantes pour créer " + quantity + " unité(s)!");
            System.out.println("Coût total requis: " + totalCost);
        }
    }
    

    private void attack() {
        if (player.getUnits().isEmpty()) {
            System.out.println("✗ Aucune unité pour attaquer!");
            return;
        }
        
        if (enemies.isEmpty()) {
            System.out.println("✓ Aucun ennemi restant!");
            return;
        }
        
    
        System.out.println("\n🎯 Ennemis disponibles:");
        for (int i = 0; i < enemies.size(); i++) {
            Unit e = enemies.get(i);
            System.out.println((i+1) + ". " + e.getName() + " (HP: " + e.getHp() + ", ATK: " + e.getAttack() + ")");
        }
        System.out.print("Choisir ennemi: ");
        int enemyIdx = getIntInput() - 1;
        
        if (enemyIdx < 0 || enemyIdx >= enemies.size()) {
            System.out.println("✗ Ennemi invalide!");
            return;
        }
        
        Unit enemy = enemies.get(enemyIdx);
        
        System.out.println("\nVos unités:");
        for (int i = 0; i < player.getUnits().size(); i++) {
            Unit u = player.getUnits().get(i);
            System.out.println((i+1) + ". " + u.getName() + " (HP: " + u.getHp() + ", ATK: " + u.getAttack() + ")");
        }
        
        System.out.print("Combien d'unités voulez-vous envoyer au combat? ");
        int quantity = getIntInput();
        
        if (quantity <= 0 || quantity > player.getUnits().size()) {
            System.out.println("✗ Quantité invalide!");
            return;
        }
        
        List<Integer> attackers = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            System.out.print("Choisir unité #" + (i+1) + ": ");
            int idx = getIntInput() - 1;
            
            if (idx >= 0 && idx < player.getUnits().size() && !attackers.contains(idx)) {
                attackers.add(idx);
            } else {
                System.out.println("✗ Choix invalide ou déjà sélectionné!");
                i--;
            }
        }
        
   
       System.out.println("\n⚔️ COMBAT MULTIPLE! " + attackers.size() + " unités attaquent!");
        
       for (int i = 0; i < attackers.size(); i++) {
      
           if (enemy.getHp() <= 0) {
               System.out.println("✓ L'ennemi est déjà vaincu!");
               break;
           }
           
    
           int idx = attackers.get(i);
           Unit attacker = player.getUnits().get(idx);
           
           
           if (attacker.getHp() <= 0) {
               System.out.println("✗ " + attacker.getName() + " est déjà mort!");
               continue;
           }
            
            Combat.fight(attacker, enemy);
            
            if (attacker.getHp() <= 0) {
                System.out.println("✗ Votre " + attacker.getName() + " est mort!");
            }
        }
        
   
        if (enemy.getHp() <= 0) {
            System.out.println("\n✓ VICTOIRE! " + enemy.getName() + " éliminé!");
            enemies.remove(enemyIdx);
            player.addResource("Or", 30);
            player.addResource("Pierre", 10);
            System.out.println("💰 Butin: +30 Or, +10 Pierre");
        }
        
   
        player.getUnits().removeIf(u -> u.getHp() <= 0);
    }
    
    private void collectResources() {
        System.out.println("\n💰 Collecte des ressources...");
        

        player.addResource("Pierre", 15);
        System.out.println("  +15 Pierre (collecte automatique)");
        

        if (!player.getBuildings().isEmpty()) {
            for (Building b : player.getBuildings()) {
                Map<String, Integer> production = b.produce();
                for (Map.Entry<String, Integer> entry : production.entrySet()) {
                    player.addResource(entry.getKey(), entry.getValue());
                    System.out.println("  +" + entry.getValue() + " " + entry.getKey() + " (depuis " + b.getName() + ")");
                }
            }
        }
        
        System.out.println("✓ Ressources collectées!");
    }
    
    private void nextTurn() {
        turn++;
        
        for (Building b : player.getBuildings()) {
            Map<String, Integer> production = b.produce();
            for (Map.Entry<String, Integer> entry : production.entrySet()) {
                player.addResource(entry.getKey(), entry.getValue());
            }
        }
        
        System.out.println("✓ Tour suivant! Ressources produites.");
    }
    
    private int getIntInput() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return 0;
        }
    }
}