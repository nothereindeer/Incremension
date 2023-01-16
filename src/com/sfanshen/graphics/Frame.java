package com.sfanshen.graphics;

import com.sfanshen.upgrade.Upgrade;

import java.util.ArrayList;

public abstract class Frame {

    public String name;
    int x, y, width, height;
    public Frame(String name, int x, int y, int width, int height){
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
}
