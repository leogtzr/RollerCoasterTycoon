package com.craftinginjava.rollercoastertycoon.world;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class Camera {

    private int x;
    private int y;

    public void move(int xOffset, int yOffset) {
        this.x += xOffset;
        this.y += yOffset;
    }

}
