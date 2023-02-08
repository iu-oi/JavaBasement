package javaDungeon.game.weapon;

import java.awt.Color;

import javaDungeon.game.*;
import javaDungeon.game.entity.Bullet.Note;

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
    public void rangedAttack(Color color, int xPos, int yPos, Direction direction) {
        int newX = xPos + direction.diffX();
        int newY = yPos + direction.diffY();
        if (world.testForeground(newX, newY)) {
            world.newEntity(new Note(color, world, direction), newX, newY);
        }
    }

}
