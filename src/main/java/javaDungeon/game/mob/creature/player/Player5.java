package javaDungeon.game.mob.creature.player;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Axe;

public class Player5 extends Player<Axe> {

    public static final Color COLOR = new Color(0, 255, 255);
    public static final int HEALTH = 4;
    public static final int SPEED = 20;
    public static final int ATTACK_SPEED = 4;

    public Player5(Game game) {
        super(COLOR, game, HEALTH, SPEED, ATTACK_SPEED);
    }

}