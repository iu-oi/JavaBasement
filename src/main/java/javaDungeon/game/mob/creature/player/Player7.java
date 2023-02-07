package javaDungeon.game.mob.creature.player;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Axe;

public class Player7 extends Player<Axe> {

    public static final Color COLOR = new Color(255, 0, 255);
    public static final int HEALTH = 1;
    public static final int SPEED = 20;
    public static final int ATTACK_SPEED = 4;

    public Player7(Game game) {
        super(COLOR, game, HEALTH, SPEED, ATTACK_SPEED);
    }

}
