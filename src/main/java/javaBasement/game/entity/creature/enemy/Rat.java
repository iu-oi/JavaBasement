package javaBasement.game.entity.creature.enemy;

import java.awt.Color;
import java.util.Date;
import java.util.Random;

import javaBasement.game.*;

public class Rat extends Enemy {

    public static final Color COLOR = Color.gray;
    public static final char GLYPH = (char) 0xf9;
    public static final int HEALTH = 10;
    public static final int STEP_INTERVAL = 10;
    public static final int DAMAGE = 2;

    protected final Random generator = new Random(new Date().getTime());

    public Rat(World world) {
        super(COLOR, GLYPH, world, HEALTH, STEP_INTERVAL, DAMAGE);
    }

    @Override
    public Direction nextStep() {
        Direction result = null;
        int currentMaxWeight = -1;
        for (Direction probe : Direction.values()) {
            if (world.testForeground(this, probe)) {
                int weight = generator.nextInt(10);
                if (weight > currentMaxWeight) {
                    currentMaxWeight = weight;
                    result = probe;
                }
            }
        }
        return result;
    }

}
