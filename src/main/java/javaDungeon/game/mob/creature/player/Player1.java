package javaDungeon.game.mob.creature.player;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Bomb;

public class Player1 extends Player<Bomb> {

    public static final Color COLOR = new Color(255, 0, 0);
    public static final int HEALTH = 4;
    public static final int SPEED = 20;
    public static final int ATTACK_SPEED = 4;

    public Player1(Game game) {
        super(COLOR, game, HEALTH, SPEED, ATTACK_SPEED);
    }

}
