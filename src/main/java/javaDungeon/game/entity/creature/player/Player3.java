package javaDungeon.game.entity.creature.player;

import java.awt.Color;

import javaDungeon.game.World;
import javaDungeon.game.weapon.Guitar;

public class Player3 extends Player<Guitar> {

    public static final Color COLOR = Color.yellow;
    public static final int HEALTH = 4;
    public static final int STEP_INTERVAL = 3;
    public static final int RANGED_ATTACK_INTERVAL = 10;

    public Player3(World world) {
        super(COLOR, world, HEALTH, STEP_INTERVAL, RANGED_ATTACK_INTERVAL, new Guitar(world));
    }

}
