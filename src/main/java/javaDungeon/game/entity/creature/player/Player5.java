package javaDungeon.game.entity.creature.player;

import java.awt.Color;

import javaDungeon.game.World;
import javaDungeon.game.weapon.Axe;

public class Player5 extends Player<Axe> {

    public static final Color COLOR = new Color(0, 255, 255);
    public static final int HEALTH = 4;
    public static final int STEP_INTERVAL = 5;
    public static final int RANGED_ATTACK_INTERVAL = 3;

    public Player5(World world) {
        super(COLOR, world, HEALTH, STEP_INTERVAL, RANGED_ATTACK_INTERVAL, new Axe(world));
    }

}
