package javaDungeon.game.entity.creature.player;

import java.awt.Color;

import javaDungeon.game.World;
import javaDungeon.game.weapon.Guitar;

public class Player6 extends Player<Guitar> {

    public static final Color COLOR = new Color(0, 0, 255);
    public static final int HEALTH = 3;
    public static final int STEP_INTERVAL = 5;
    public static final int RANGED_ATTACK_INTERVAL = 6;

    public Player6(World world) {
        super(COLOR, world, HEALTH, STEP_INTERVAL, RANGED_ATTACK_INTERVAL, new Guitar(world));
    }

}
