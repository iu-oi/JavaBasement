package javaDungeon.game.mob.creature.player;

import java.awt.Color;

import javaDungeon.game.Game;
import javaDungeon.game.mob.bullet.Note;

public class Player3 extends Player<Note> {

    public static final Color COLOR = new Color(255, 255, 0);
    public static final int HEALTH = 8;
    public static final int SPEED = 20;
    public static final int ATTACK_SPEED = 4;

    public Player3(Game game) {
        super(COLOR, game, HEALTH, SPEED, ATTACK_SPEED);
    }

}
