package javaDungeon.entities;

import java.awt.Color;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javaDungeon.blocks.Floor;
import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;

public class Creature<T extends Bullet> extends Mob {

    private final int attackSpeed;
    private volatile boolean shooting = false;
    private volatile Direction shootDirection = Direction.DIR_UP;
    private volatile T bullet;
    private transient ScheduledFuture<?> attackHandler;

    Creature(Color color, char glyph, Game game, int health, int speed, int attackSpeed) {
        super(color, glyph, game, health, speed);
        this.attackSpeed = attackSpeed;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void startShooting() {
        shooting = true;
    }

    public void stopShooting() {
        shooting = false;
    }

    public boolean isShooting() {
        return shooting;
    }

    public Direction getShootDirection() {
        return shootDirection;
    }

    public void setShootDirection(Direction direction) {
        shootDirection = direction;
    }

    public void setBullet(T bullet) {
        this.bullet = bullet;
    }

    public T getBullet() {
        return bullet;
    }

    public void startAttacking() {
        if (attackHandler == null && bullet != null) {
            attackHandler = Game.scheduler.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {
                    try {
                        actionLock.lock();
                        if (isShooting() && currentHealth() > 0) {
                            Thing front = getAdjacent(shootDirection);
                            if (front instanceof Floor) {
                                Bullet newBullet = bullet.clone();
                                newBullet.setDirection(shootDirection);
                                newBullet.born(front.getX(), front.getY());
                            }
                        }
                    } finally {
                        actionLock.unlock();
                    }
                }

            }, 0, 2000 / attackSpeed, TimeUnit.MILLISECONDS);
        }
    }

    public void stopAttacking() {
        if (attackHandler != null) {
            attackHandler.cancel(false);
            attackHandler = null;
        }
    }

    @Override
    public void loseHealth(int damage) {
        super.loseHealth(damage);
        Game.consoleLog(this + " got " + damage + " damage, " + currentHealth() + " left.");
    }

    @Override
    public void born(int x, int y) {
        super.born(x, y);
        startAttacking();
        Game.consoleLog(this + " created...");
    }

    @Override
    public void die() {
        stopAttacking();
        super.die();
        Game.consoleLog(this + " died...");
    }

}