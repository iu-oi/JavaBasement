package javaBasement.game.weapon;

import java.awt.Color;

import javaBasement.game.*;
import javaBasement.game.entity.bullet.FlyingAxe;

public class Axe extends Weapon {

    public Axe(World world) {
        super(world);
    }

    @Override
    public int getDamage() {
        return FlyingAxe.DAMAGE;
    }

    @Override
    public char getGlyph() {
        return FlyingAxe.GLYPH;
    }

    @Override
    public void rangedAttack(Color color, int x, int y, Direction direction) {
        int newX = x + direction.diffX();
        int newY = y + direction.diffY();
        if (world.testForeground(newX, newY)) {
            world.newEntity(new FlyingAxe(color, world, direction), newX, newY);
        }
    }

}
