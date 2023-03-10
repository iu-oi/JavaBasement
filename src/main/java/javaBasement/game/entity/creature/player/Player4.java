package javaBasement.game.entity.creature.player;

import java.awt.Color;

import javaBasement.game.World;
import javaBasement.game.weapon.Axe;

public class Player4 extends Player<Axe> {

    public static final Color COLOR = Color.green;
    public static final int HEALTH = 4;
    public static final int STEP_INTERVAL = 5;
    public static final int RANGED_ATTACK_INTERVAL = 3;

    public Player4(World world) {
        super(COLOR, world, HEALTH, STEP_INTERVAL, RANGED_ATTACK_INTERVAL, new Axe(world));
    }

}
