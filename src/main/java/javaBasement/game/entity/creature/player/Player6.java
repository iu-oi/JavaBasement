package javaBasement.game.entity.creature.player;

import java.awt.Color;

import javaBasement.game.World;
import javaBasement.game.weapon.Guitar;

public class Player6 extends Player<Guitar> {

    public static final Color COLOR = Color.MAGENTA;
    public static final int HEALTH = 3;
    public static final int STEP_INTERVAL = 5;
    public static final int RANGED_ATTACK_INTERVAL = 6;

    public Player6(World world) {
        super(COLOR, world, HEALTH, STEP_INTERVAL, RANGED_ATTACK_INTERVAL, new Guitar(world));
    }

}
