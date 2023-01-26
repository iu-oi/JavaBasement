package javaDungeon.entities;

import java.awt.Color;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javaDungeon.blocks.Floor;
import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;

public class Creature<T extends Bullet> extends Mob {

    private T bullet;

    protected ScheduledFuture<?> attackHandler;

    public void setBullet(T bullet) {
        if (attackHandler != null) {
            attackHandler.cancel(false);
        }
        this.bullet = bullet;
        bullet.owner = this;
        attackHandler = game.scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    actionLock.lock();
                    if (isAttacking()) {
                        fireBullet(getAttackDir());
                    }
                } finally {
                    actionLock.unlock();
                }
            }
        }, 0, Mob.speedToMilliseconds(getAttackSpeed()), TimeUnit.MILLISECONDS);
    }

    public T getBullet() {
        return bullet;
    }

    public void fireBullet(Direction attackDir) {
        Thing front = queryAdjacent(attackDir);

        if (front instanceof Floor) {
            Bullet newBullet = bullet.clone();
            newBullet.setDir(attackDir);
            newBullet.startMoving();
            world.putEntity(newBullet, front.getX(), front.getY());
            newBullet.startAction();
        }
    }

    private final int attackSpeed;

    public int getAttackSpeed() {
        return attackSpeed;
    }

    private volatile boolean attacking = false;

    public synchronized void startAttacking() {
        attacking = true;
    }

    public synchronized void stopAttacking() {
        attacking = false;
    }

    public synchronized boolean isAttacking() {
        return attacking;
    }

    private volatile Direction attackDir = Direction.DIR_UP;

    public synchronized void setAttackDir(Direction attackDir) {
        this.attackDir = attackDir;
    }

    public synchronized Direction getAttackDir() {
        return attackDir;
    }

    Creature(Color color, char glyph, Game game, int health, int speed, int attackSpeed) {
        super(color, glyph, game, health, speed);
        this.attackSpeed = attackSpeed;
        game.creatureFactory.incCreatureCount();
        Game.consoleLog(this + " created...");
    }

    @Override
    public synchronized void loseHealth(int damage) {
        Game.consoleLog(this + " got " + damage + " damage... ");
        super.loseHealth(damage);
        Game.consoleLog(this + " : " + getHealth());
    }

    @Override
    public void die() {
        Game.consoleLog(this + " died...");
        game.creatureFactory.decCreatureCount();
        if (attackHandler != null) {
            attackHandler.cancel(false);
        }
        super.die();
    }

}