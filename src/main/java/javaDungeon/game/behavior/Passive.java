package javaDungeon.game.behavior;

public interface Passive {

    int detectDamage();

    boolean takeDamage(int damage, int frame);

}
