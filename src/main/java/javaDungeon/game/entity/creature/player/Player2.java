package javaDungeon.game.entity.creature.player;

import java.awt.Color;

import javaDungeon.game.World;
import javaDungeon.game.weapon.GrenadeLauncher;

public class Player2 extends Player<GrenadeLauncher> {

    public static final Color COLOR = Color.orange;
    public static final int HEALTH = 4;
    public static final int STEP_INTERVAL = 5;
    public static final int RANGED_ATTACK_INTERVAL = 10;

    public Player2(World world) {
        super(COLOR, world, HEALTH, STEP_INTERVAL, RANGED_ATTACK_INTERVAL, new GrenadeLauncher(world));
    }

}
