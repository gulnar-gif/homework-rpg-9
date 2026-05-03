package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Flags suspicious artifacts by using concrete visitor overloads.
 */
public class CurseDetector implements ArtifactVisitor {

    private int cursesFound;

    @Override
    public void visit(Weapon weapon) {
        if (weapon.getAttackBonus() > 12) {
            cursesFound++;
            System.out.println("CurseDetector: " + weapon.getName()
                    + " is too eager for battle - curse suspected");
        } else {
            System.out.println("CurseDetector: " + weapon.getName()
                    + " is stable");
        }
    }

    @Override
    public void visit(Potion potion) {
        if (potion.getHealing() < 25) {
            cursesFound++;
            System.out.println("CurseDetector: " + potion.getName()
                    + " has a bitter temporal aftertaste - curse suspected");
        } else {
            System.out.println("CurseDetector: " + potion.getName()
                    + " is safe to drink");
        }
    }

    @Override
    public void visit(Scroll scroll) {
        if (scroll.getSpellName().toLowerCase().contains("void")) {
            cursesFound++;
            System.out.println("CurseDetector: " + scroll.getName()
                    + " references forbidden void runes - curse confirmed");
        } else {
            System.out.println("CurseDetector: " + scroll.getName()
                    + " contains no hostile runes");
        }
    }

    @Override
    public void visit(Ring ring) {
        if (ring.getMagicBonus() > 6) {
            cursesFound++;
            System.out.println("CurseDetector: " + ring.getName()
                    + " loops time too tightly - curse suspected");
        } else {
            System.out.println("CurseDetector: " + ring.getName()
                    + " is safe to wear");
        }
    }

    @Override
    public void visit(Armor armor) {
        if (armor.getDefenseBonus() < 4) {
            cursesFound++;
            System.out.println("CurseDetector: " + armor.getName()
                    + " has brittle wards - curse suspected");
        } else {
            System.out.println("CurseDetector: " + armor.getName()
                    + " shields cleanly");
        }
    }

    public int getCursesFound() {
        return cursesFound;
    }
}
