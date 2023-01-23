package com.sfanshen.graphics;

import com.sfanshen.generator.Generator;
import com.sfanshen.main.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GeneratorTab extends GameTab {

    public ArrayList<Generator> generators;

    public GeneratorTab(String name, ArrayList<Generator> generators) {
        super(name);
        this.generators = generators;
    }

    public GeneratorTab(String name, Generator[] generators) {
        super(name);
        this.generators = new ArrayList<>(Arrays.asList(generators));
    }


    public void draw(Graphics2D g) {
        for (Generator generator : this.generators) {
            //If upgrade is unlocked
            if (generator.isUnlocked) {
                //Draws upgrade
                generator.generatorFrame.draw(g);
            }
            //If the upgrade is not unlocked, draw a blank upgrade instead, indicating there is an upgrade to be unlocked by the player
            else {
                drawBlank(g, x, y);
            }
        }
    }

    public void drawBlank(Graphics2D g, int x, int y) {

    }

}
