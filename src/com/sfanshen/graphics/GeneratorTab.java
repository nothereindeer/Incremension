package com.sfanshen.graphics;

import com.sfanshen.currency.Generator;
import com.sfanshen.upgrade.BoostUpgrade;
import com.sfanshen.upgrade.FeatureUpgrade;
import com.sfanshen.upgrade.Upgrade;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GeneratorTab extends GameTab {

    ArrayList<Generator> generators;

    public GeneratorTab(String name, ArrayList<Generator> generators) {
        super(name);
        this.generators = generators;
    }

    public GeneratorTab(String name, Generator[] generators) {
        super(name);
        this.generators = new ArrayList<>(Arrays.asList(generators));
    }


    public void draw(Graphics2D g) {
//        for (Generator generator : this.generators) {
//            if (generator.upgradeButton.isMouseHovering){
//                UpgradeTab.drawUpgDesc(g, upgrade);
//            }
//            //If upgrade is unlocked
//            if (upgrade.isUnlocked) {
//                //Draws upgrade
//                if (upgrade instanceof BoostUpgrade)
//                    drawBoostUpgrade(g, upgrade);
//                else if (upgrade instanceof FeatureUpgrade)
//                    drawFeatureUpgrade(g, upgrade);
//
//            }
//
//            //If the upgrade is not unlocked, draw a blank upgrade instead, indicating there is an upgrade to be unlocked by the player
//            else {
//                drawBlank(g, x, y);
//            }
//        }
    }
}
