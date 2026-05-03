package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Fourth visitor proving new reports can be added without editing artifacts.
 */
public class WeightCalculator implements ArtifactVisitor {

    private int totalWeight;

    @Override
    public void visit(Weapon weapon) {
        totalWeight += weapon.getWeight();
        System.out.println("WeightCalculator: weapon load from " + weapon.getName()
                + " = " + weapon.getWeight());
    }

    @Override
    public void visit(Potion potion) {
        totalWeight += potion.getWeight();
        System.out.println("WeightCalculator: potion load from " + potion.getName()
                + " = " + potion.getWeight());
    }

    @Override
    public void visit(Scroll scroll) {
        totalWeight += scroll.getWeight();
        System.out.println("WeightCalculator: scroll load from " + scroll.getName()
                + " = " + scroll.getWeight());
    }

    @Override
    public void visit(Ring ring) {
        totalWeight += ring.getWeight();
        System.out.println("WeightCalculator: ring load from " + ring.getName()
                + " = " + ring.getWeight());
    }

    @Override
    public void visit(Armor armor) {
        totalWeight += armor.getWeight();
        System.out.println("WeightCalculator: armor load from " + armor.getName()
                + " = " + armor.getWeight());
    }

    public int getTotalWeight() {
        return totalWeight;
    }
}
