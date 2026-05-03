package com.narxoz.rpg;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.List;

/**
 * Entry point for Homework 9: Chronomancer's Vault.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Hero arman = new Hero(
                "Arman",
                100,
                45,
                18,
                7,
                80,
                starterInventoryForArman());

        Hero dana = new Hero(
                "Dana",
                76,
                90,
                11,
                4,
                130,
                starterInventoryForDana());

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(arman, dana));

        System.out.println();
        System.out.println("== Final VaultRunResult ==");
        System.out.println(result);
    }

    private static Inventory starterInventoryForArman() {
        Inventory inventory = new Inventory();
        inventory.addArtifact(new Potion("Field Medic Flask", 25, 1, 18));
        return inventory;
    }

    private static Inventory starterInventoryForDana() {
        Inventory inventory = new Inventory();
        inventory.addArtifact(new Armor("Apprentice Ward Coat", 35, 4, 3));
        return inventory;
    }
}
