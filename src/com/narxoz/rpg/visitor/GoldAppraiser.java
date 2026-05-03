package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Estimates resale value for each artifact type.
 */
public class GoldAppraiser implements ArtifactVisitor {

    private int totalGold;

    @Override
    public void visit(Weapon weapon) {
        int appraisal = weapon.getValue() + weapon.getAttackBonus() * 12;
        totalGold += appraisal;
        System.out.println("GoldAppraiser: " + weapon.getName()
                + " weapon resale = " + appraisal + " gold");
    }

    @Override
    public void visit(Potion potion) {
        int appraisal = potion.getValue() + potion.getHealing() / 2;
        totalGold += appraisal;
        System.out.println("GoldAppraiser: " + potion.getName()
                + " potion resale = " + appraisal + " gold");
    }

    @Override
    public void visit(Scroll scroll) {
        int appraisal = scroll.getValue() + scroll.getSpellName().length() * 5;
        totalGold += appraisal;
        System.out.println("GoldAppraiser: " + scroll.getName()
                + " scroll resale = " + appraisal + " gold");
    }

    @Override
    public void visit(Ring ring) {
        int appraisal = ring.getValue() + ring.getMagicBonus() * 20;
        totalGold += appraisal;
        System.out.println("GoldAppraiser: " + ring.getName()
                + " ring resale = " + appraisal + " gold");
    }

    @Override
    public void visit(Armor armor) {
        int appraisal = armor.getValue() + armor.getDefenseBonus() * 10;
        totalGold += appraisal;
        System.out.println("GoldAppraiser: " + armor.getName()
                + " armor resale = " + appraisal + " gold");
    }

    public int getTotalGold() {
        return totalGold;
    }
}
