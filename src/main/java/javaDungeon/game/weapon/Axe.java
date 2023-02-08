package javaDungeon.game.weapon;

import java.awt.Color;

import javaDungeon.game.*;
import javaDungeon.game.entity.Bullet.FlyingAxe;

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
    public void rangedAttack(Color color, int xPos, int yPos, Direction direction) {
        int newX = xPos + direction.diffX();
        int newY = yPos + direction.diffY();
        if (world.testForeground(newX, newY)) {
            world.newEntity(new FlyingAxe(color, world, direction), newX, newY);
        }
    }

}
