package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Reads magical signatures without changing artifacts.
 */
public class EnchantmentScanner implements ArtifactVisitor {

    private int signaturesFound;

    @Override
    public void visit(Weapon weapon) {
        signaturesFound++;
        System.out.println("EnchantmentScanner: " + weapon.getName()
                + " hums with +" + weapon.getAttackBonus() + " battle resonance");
    }

    @Override
    public void visit(Potion potion) {
        signaturesFound++;
        System.out.println("EnchantmentScanner: " + potion.getName()
                + " stores " + potion.getHealing() + " healing energy");
    }

    @Override
    public void visit(Scroll scroll) {
        signaturesFound++;
        System.out.println("EnchantmentScanner: " + scroll.getName()
                + " contains the spell '" + scroll.getSpellName() + "'");
    }

    @Override
    public void visit(Ring ring) {
        signaturesFound++;
        System.out.println("EnchantmentScanner: " + ring.getName()
                + " bends time with +" + ring.getMagicBonus() + " arcane focus");
    }

    @Override
    public void visit(Armor armor) {
        signaturesFound++;
        System.out.println("EnchantmentScanner: " + armor.getName()
                + " projects +" + armor.getDefenseBonus() + " protective wards");
    }

    public int getSignaturesFound() {
        return signaturesFound;
    }
}
