package javaDungeon.game.entity;

import java.awt.Color;

import javaDungeon.game.*;

public abstract class Entity extends Thing {

    protected final World world;

    public Entity(Color color, char glyph, World world) {
        super(color, glyph);
        this.world = world;
    }

    public abstract boolean isObsolete();

}
