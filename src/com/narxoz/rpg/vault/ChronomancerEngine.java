package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.visitor.CurseDetector;
import com.narxoz.rpg.visitor.EnchantmentScanner;
import com.narxoz.rpg.visitor.GoldAppraiser;
import com.narxoz.rpg.visitor.WeightCalculator;
import java.util.ArrayList;
import java.util.List;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 */
public class ChronomancerEngine {

    /**
     * Runs the vault sequence for the supplied party.
     *
     * @param party the heroes entering the vault
     * @return a summary of the demonstrated vault run
     */
    public VaultRunResult runVault(List<Hero> party) {
        if (party == null || party.isEmpty()) {
            throw new IllegalArgumentException("The vault needs at least one hero.");
        }

        List<Hero> heroes = new ArrayList<>(party);
        Inventory vaultInventory = createVaultInventory();

        System.out.println();
        System.out.println("== Party enters the Chronomancer's Vault ==");
        for (Hero hero : heroes) {
            System.out.println("Party member: " + hero);
        }

        System.out.println();
        System.out.println("== Mixed artifact inventory ==");
        System.out.println("Artifacts found: " + vaultInventory.size());

        GoldAppraiser goldAppraiser = new GoldAppraiser();
        EnchantmentScanner enchantmentScanner = new EnchantmentScanner();
        CurseDetector curseDetector = new CurseDetector();
        WeightCalculator weightCalculator = new WeightCalculator();

        System.out.println();
        System.out.println("== Visitor appraisal through Inventory.accept(visitor) ==");
        applyVisitor("Gold appraisal", vaultInventory, goldAppraiser);
        applyVisitor("Enchantment scan", vaultInventory, enchantmentScanner);
        applyVisitor("Curse detection", vaultInventory, curseDetector);

        System.out.println();
        System.out.println("== Open/Closed proof: fourth visitor added without artifact edits ==");
        applyVisitor("Weight calculation", vaultInventory, weightCalculator);

        System.out.println();
        System.out.println("Visitor summaries:");
        System.out.println("Total resale estimate: " + goldAppraiser.getTotalGold() + " gold");
        System.out.println("Magical signatures found: " + enchantmentScanner.getSignaturesFound());
        System.out.println("Curses found: " + curseDetector.getCursesFound());
        System.out.println("Total carry weight: " + weightCalculator.getTotalWeight());

        Hero focusHero = heroes.get(0);
        focusHero.setInventory(vaultInventory.copy());

        Caretaker caretaker = new Caretaker();
        System.out.println();
        System.out.println("== Memento snapshot ==");
        System.out.println("Before save: " + focusHero);
        caretaker.save(focusHero.createMemento());
        int mementosCreated = caretaker.size();
        System.out.println("Snapshot stored. Caretaker size: " + caretaker.size());

        System.out.println();
        System.out.println("== Vault trap changes hero state ==");
        focusHero.takeDamage(45);
        focusHero.spendMana(20);
        focusHero.spendGold(30);
        Inventory cursedInventory = focusHero.getInventory().copy();
        cursedInventory.addArtifact(new Ring("Echo Ring of Debt", 45, 1, 8));
        focusHero.setInventory(cursedInventory);
        System.out.println("After trap: " + focusHero);

        System.out.println();
        System.out.println("== Time rewind from opaque HeroMemento ==");
        HeroMemento rewindPoint = caretaker.undo();
        focusHero.restoreFromMemento(rewindPoint);
        int restoredCount = rewindPoint == null ? 0 : 1;
        System.out.println("After rewind: " + focusHero);
        System.out.println("Caretaker size after undo: " + caretaker.size());

        return new VaultRunResult(vaultInventory.size(), mementosCreated, restoredCount);
    }

    private void applyVisitor(String label, Inventory inventory, ArtifactVisitor visitor) {
        System.out.println("-- " + label + " --");
        inventory.accept(visitor);
    }

    private Inventory createVaultInventory() {
        Inventory inventory = new Inventory();
        inventory.addArtifact(new Weapon("Hourglass Saber", 120, 7, 11));
        inventory.addArtifact(new Potion("Crimson Rewind Tonic", 55, 2, 40));
        inventory.addArtifact(new Scroll("Scroll of Void Minute", 95, 1, "Void Minute"));
        inventory.addArtifact(new Ring("Chrono Loop Band", 140, 1, 7));
        inventory.addArtifact(new Armor("Aegis of Yesterday", 110, 12, 8));
        return inventory;
    }
}
