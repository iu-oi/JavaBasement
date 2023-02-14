package javaBasement.game.weapon;

import java.awt.Color;

import javaBasement.game.*;
import javaBasement.game.entity.bullet.Grenade;

public class GrenadeLauncher extends Weapon {

    public GrenadeLauncher(World world) {
        super(world);
    }

    @Override
    public int getDamage() {
        return Grenade.DAMAGE;
    }

    @Override
    public char getGlyph() {
        return Grenade.GLYPH;
    }

    @Override
    public void rangedAttack(Color color, int x, int y, Direction direction) {
        int newX = x + direction.diffX();
        int newY = y + direction.diffY();
        if (world.testForeground(newX, newY)) {
            world.newEntity(new Grenade(color, world, direction), newX, newY);
        }
    }

}
