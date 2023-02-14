package javaBasement.game.entity;

import java.awt.Color;

import javaBasement.game.*;

public abstract class Entity extends Thing {

    protected final World world;

    public Entity(Color color, char glyph, World world) {
        super(color, glyph);
        this.world = world;
    }

    public abstract boolean isObsolete();

}
