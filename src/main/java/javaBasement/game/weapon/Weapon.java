package javaBasement.game.weapon;

import java.awt.Color;
import java.io.Serializable;

import javaBasement.game.*;

public abstract class Weapon implements Serializable {

    protected World world;

    Weapon(World world) {
        this.world = world;
    }

    public abstract int getDamage();

    public abstract char getGlyph();

    public abstract void rangedAttack(Color color, int x, int y, Direction direction);

}
