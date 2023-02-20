package com.sfanshen.main;

import com.sfanshen.generator.Generator;
import com.sfanshen.graphics.GameTab;
import com.sfanshen.graphics.GeneratorTab;

public class GenerationThread extends Thread {

    public void run() {
        while (FinalGame.isRunning) {

            generatorProduction();

            try {
                this.sleep(1000 / Global.ticksPerSec);
            } catch (Exception e) {
                System.out.println("Thread error: " + e);
            }
        }
    }


    public static void generatorProduction() {
        for (GameTab gameTab : Global.gameTabs.values()) {
            if (gameTab instanceof GeneratorTab) {
                for (Generator generator : ((GeneratorTab) gameTab).generators) {
                    generator.generate();
                }
            }
        }
    }
}
