package javaDungeon.game.entity.creature.player;

import java.awt.Color;

import javaDungeon.game.World;
import javaDungeon.game.weapon.GrenadeLauncher;

public class Player1 extends Player<GrenadeLauncher> {

    public static final Color COLOR = new Color(255, 0, 0);
    public static final int HEALTH = 6;
    public static final int STEP_INTERVAL = 5;
    public static final int RANGED_ATTACK_INTERVAL = 10;

    public Player1(World world) {
        super(COLOR, world, HEALTH, STEP_INTERVAL, RANGED_ATTACK_INTERVAL, new GrenadeLauncher(world));
    }

}
