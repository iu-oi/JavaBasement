package javaBasement.game.weapon;

import java.awt.Color;

import javaBasement.game.*;
import javaBasement.game.entity.bullet.Note;

public class Guitar extends Weapon {

    public Guitar(World world) {
        super(world);
    }

    @Override
    public int getDamage() {
        return Note.DAMAGE;
    }

    @Override
    public char getGlyph() {
        return Note.GLYPH;
    }

    @Override
    public void rangedAttack(Color color, int x, int y, Direction direction) {
        int newX = x + direction.diffX();
        int newY = y + direction.diffY();
        if (world.testForeground(newX, newY)) {
            world.newEntity(new Note(color, world, direction), newX, newY);
        }
    }

}
