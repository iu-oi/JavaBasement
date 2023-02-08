package javaDungeon.game.entity.creature.player;

import java.awt.event.KeyEvent;
import java.awt.Color;

import javaDungeon.game.*;
import javaDungeon.game.behavior.Ranged;
import javaDungeon.game.entity.creature.Creature;
import javaDungeon.game.weapon.Weapon;

public abstract class Player<W extends Weapon> extends Creature implements Ranged {

    public static final int INVISIBLE_INTERVAL = 10;
    public static final char GLYPH = (char) 0x2;

    protected final int rangedAttackInterval;
    protected int lastRangedAttackFrame = 0;
    protected int lastHitFrame = 0;
    protected W weapon;

    public W getWeapon() {
        return weapon;
    }

    public void setWeapon(W weapon) {
        this.weapon = weapon;
    }

    protected volatile Direction stepDirection;
    protected volatile Direction rangedAttackDirection;
    protected volatile Direction lastStepDirection;
    protected volatile Direction lastRangedAttackDirection;

    private synchronized void setStepDirection(Direction stepDirection) {
        this.stepDirection = stepDirection;
    }

    private synchronized void setRangedAttackDirection(Direction rangedAttackDirection) {
        this.rangedAttackDirection = rangedAttackDirection;
    }

    @Override
    public boolean takeDamage(int damage, int frame) {
        if (frame - lastHitFrame >= INVISIBLE_INTERVAL) {
            if (super.takeDamage(damage, frame)) {
                lastHitFrame = frame;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    Player(Color color, World world, int health, int stepInterval, int rangedAttackInterval, W weapon) {
        super(color, GLYPH, world, health, stepInterval);
        this.rangedAttackInterval = rangedAttackInterval;
        this.weapon = weapon;
    }

    @Override
    public synchronized Direction nextStep() {
        Direction step = stepDirection;
        if (step != null && world.testForeground(this, step)) {
            return step;
        } else {
            return null;
        }
    }

    @Override
    public boolean takeStep(Direction direction, int frame) {
        if (direction != null && (direction != lastStepDirection ||
                frame == 1 || frame - lastStepFrame >= stepInterval)) {
            lastStepFrame = frame;
            lastStepDirection = direction;
            int newX = getX() + direction.diffX();
            int newY = getY() + direction.diffY();
            world.removeForeground(getX(), getY());
            world.putForeground(this, newX, newY);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public synchronized Direction nextRangedAttack() {
        Direction rangedAttack = rangedAttackDirection;
        if (rangedAttack != null && world.testForeground(this, rangedAttack)) {
            return rangedAttack;
        } else {
            return null;
        }
    }

    @Override
    public boolean rangedAttack(Direction direction, int frame) {
        if (direction != null && (direction != lastRangedAttackDirection || frame == 1
                || frame - lastRangedAttackFrame >= rangedAttackInterval)) {
            lastRangedAttackFrame = frame;
            lastRangedAttackDirection = direction;
            weapon.rangedAttack(color, getX(), getY(), direction);
            return true;
        } else {
            return false;
        }
    }

    public void keyPressed(KeyEvent key) {
        int keyCode = key.getKeyCode();
        Direction stepRequest = null;
        Direction rangedAttackRequest = null;
        if (keyCode == KeyEvent.VK_W) {
            stepRequest = Direction.UP;
        } else if (keyCode == KeyEvent.VK_A) {
            stepRequest = Direction.LEFT;
        } else if (keyCode == KeyEvent.VK_S) {
            stepRequest = Direction.DOWN;
        } else if (keyCode == KeyEvent.VK_D) {
            stepRequest = Direction.RIGHT;
        } else if (keyCode == KeyEvent.VK_UP) {
            rangedAttackRequest = Direction.UP;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            rangedAttackRequest = Direction.LEFT;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            rangedAttackRequest = Direction.DOWN;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rangedAttackRequest = Direction.RIGHT;
        }
        if (stepRequest != null && stepRequest != stepDirection) {
            setStepDirection(stepRequest);
        }
        if (rangedAttackRequest != null && rangedAttackRequest != rangedAttackDirection) {
            setRangedAttackDirection(rangedAttackRequest);
        }
    }

    public void keyReleased(KeyEvent key) {
        int keyCode = key.getKeyCode();
        Direction stepRequest = null;
        Direction rangedAttackRequest = null;
        if (keyCode == KeyEvent.VK_W) {
            stepRequest = Direction.UP;
        } else if (keyCode == KeyEvent.VK_A) {
            stepRequest = Direction.LEFT;
        } else if (keyCode == KeyEvent.VK_S) {
            stepRequest = Direction.DOWN;
        } else if (keyCode == KeyEvent.VK_D) {
            stepRequest = Direction.RIGHT;
        } else if (keyCode == KeyEvent.VK_UP) {
            rangedAttackRequest = Direction.UP;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            rangedAttackRequest = Direction.LEFT;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            rangedAttackRequest = Direction.DOWN;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rangedAttackRequest = Direction.RIGHT;
        }
        if (stepRequest != null && stepRequest == stepDirection) {
            setStepDirection(null);
        }
        if (rangedAttackRequest != null && rangedAttackRequest == rangedAttackDirection) {
            setRangedAttackDirection(null);
        }
    }

}
