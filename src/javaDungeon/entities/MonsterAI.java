package javaDungeon.entities;

import javaDungeon.game.Direction;

public interface MonsterAI {

    public final Direction[] directions = Direction.values();

    public void calcNextDir(Monster<? extends Bullet> creature);
}