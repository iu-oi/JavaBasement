package javaDungeon.game.mob.creature.player;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Axe;

public class Player4 extends Player<Axe> {

    public static final Color COLOR = new Color(0, 255, 0);
    public static final int HEALTH = 4;
    public static final int SPEED = 20;
    public static final int ATTACK_SPEED = 4;

    public Player4(Game game) {
        super(COLOR, game, HEALTH, SPEED, ATTACK_SPEED);
    }

}
