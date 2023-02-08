package javaDungeon.game.behavior;

public interface Passive {

    public int detectDamage();

    public boolean takeDamage(int damage, int frame);

}
