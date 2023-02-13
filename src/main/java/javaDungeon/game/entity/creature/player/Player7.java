package javaDungeon.game.entity.creature.player;

import java.awt.Color;

import javaDungeon.game.World;
import javaDungeon.game.weapon.GrenadeLauncher;

public class Player7 extends Player<GrenadeLauncher> {

    public static final Color COLOR = Color.pink;
    public static final int HEALTH = 1;
    public static final int STEP_INTERVAL = 4;
    public static final int RANGED_ATTACK_INTERVAL = 3;

    public Player7(World world) {
        super(COLOR, world, HEALTH, STEP_INTERVAL, RANGED_ATTACK_INTERVAL, new GrenadeLauncher(world));
    }

}
