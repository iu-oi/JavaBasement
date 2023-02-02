package javaDungeon.entities;

import java.awt.Color;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javaDungeon.blocks.Floor;
import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Counter;
import javaDungeon.game.Thing;

public class Mob extends Thing implements Runnable {

    public static final Lock actionLock = new ReentrantLock();

    protected final Game game;

    private final int speed;
    private volatile boolean moving;
    private volatile Direction direction;
    private Counter health;
    private transient ScheduledFuture<?> actionHandler;

    public Mob(Color color, char glyph, Game game, int health, int speed) {
        super(color, glyph);
        this.game = game;
        this.speed = speed;
        moving = false;
        direction = Direction.DIR_UP;
        this.health = new Counter(health);
    }

    public int getSpeed() {
        return speed;
    }

    public void startMoving() {
        moving = true;
    }

    public void stopMoving() {
        moving = false;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int currentHealth() {
        return health.get();
    }

    public void loseHealth(int damage) {
        health.sub(damage);
    }

    public void addHealth(int bonus) {
        health.add(bonus);
    }

    public void startAction() {
        if (actionHandler == null) {
            actionHandler = Game.scheduler.scheduleAtFixedRate(this, 0, 2000 / speed, TimeUnit.MILLISECONDS);
        }
    }

    public void stopAction() {
        if (actionHandler != null) {
            actionHandler.cancel(false);
            actionHandler = null;
        }
    }

    public Thing getAdjacent(Direction adjacent) {
        return game.getActiveThing(getX() + adjacent.getxDir(), getY() + adjacent.getyDir());
    }

    public void move() {
        Thing thing = getAdjacent(direction);
        if (thing instanceof Floor) {
            game.removeEntity(getX(), getY());
            game.putEntity(this, thing.getX(), thing.getY());
        }
    }

    public void born(int x, int y) {
        game.addMob(this);
        game.putEntity(this, x, y);
        startAction();
    }

    public void see() {
    }

    public void die() {
        stopAction();
        game.removeEntity(getX(), getY());
        game.removeMob(this);
    }

    @Override
    public void run() {
        try {
            Mob.actionLock.lock();
            if (!health.check()) {
                see();
                if (moving) {
                    move();
                }
            } else {
                die();
            }
        } finally {
            Mob.actionLock.unlock();
        }
    }

}