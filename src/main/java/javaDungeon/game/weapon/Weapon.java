package javaDungeon.game.weapon;

import java.awt.Color;

import javaDungeon.game.*;

public abstract class Weapon {

    protected World world;

    Weapon(World world) {
        this.world = world;
    }

    public abstract int getDamage();

    public abstract char getGlyph();

    public abstract void rangedAttack(Color color, int xPos, int yPos, Direction direction);

}
