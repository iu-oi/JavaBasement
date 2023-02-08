package javaDungeon.game.entity.creature.enemy;

import java.awt.Color;

import javaDungeon.game.*;
import javaDungeon.game.entity.creature.Creature;

public class Dwarf extends Enemy {

    public static final Color COLOR = new Color(255, 0, 0);
    public static final char GLYPH = (char) 0x91;
    public static final int HEALTH = 100;
    public static final int STEP_INTERVAL = 8;
    public static final int DAMAGE = 2;

    protected Creature creatureTracked;

    public Dwarf(World world, Creature creatureTracked) {
        super(COLOR, GLYPH, world, HEALTH, STEP_INTERVAL, DAMAGE);
        this.creatureTracked = creatureTracked;
    }

    @Override
    public Direction nextStep() {
        Direction result = null;
        int currentMaxWeight = -1;
        int diffX = this.getX() - creatureTracked.getX();
        int diffY = this.getY() - creatureTracked.getY();
        if (Math.abs(diffX) == 1 || Math.abs(diffY) == 1) {
            return null;
        }
        for (Direction probe : Direction.values()) {
            if (world.testForeground(this, probe)) {
                int weight = 0;
                if (probe == Direction.UP) {
                    weight += diffY > 0 ? diffY + diffY : 0;
                } else if (probe == Direction.LEFT) {
                    weight += diffX > 0 ? diffX + diffX : 0;
                } else if (probe == Direction.DOWN) {
                    weight += diffY < 0 ? -diffY - diffY : 0;
                } else if (probe == Direction.RIGHT) {
                    weight += diffX < 0 ? -diffX - diffX : 0;
                }
                if (currentMaxWeight < weight) {
                    currentMaxWeight = weight;
                    result = probe;
                }
            }
        }
        return result;
    }

}
