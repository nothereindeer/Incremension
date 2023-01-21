package com.sfanshen.graphics;

import com.sfanshen.currency.Generator;

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


    void draw(Graphics2D g) {

    }
}
