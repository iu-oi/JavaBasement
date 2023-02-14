package javaBasement.game.entity.creature.player;

import java.awt.Color;

import javaBasement.game.World;
import javaBasement.game.weapon.Guitar;

public class Player1 extends Player<Guitar> {

    public static final Color COLOR = Color.red;
    public static final int HEALTH = 6;
    public static final int STEP_INTERVAL = 5;
    public static final int RANGED_ATTACK_INTERVAL = 10;

    public Player1(World world) {
        super(COLOR, world, HEALTH, STEP_INTERVAL, RANGED_ATTACK_INTERVAL, new Guitar(world));
    }

}
