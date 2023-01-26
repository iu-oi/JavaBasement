package javaDungeon.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import javaDungeon.Dungeon;
import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;
import javaDungeon.game.World;

public class PlayScreen implements Screen {

    private Game backend;

    public PlayScreen(int playerId, Dungeon frontend) {
        backend = new Game(frontend);
        backend.play(playerId);
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {
                Thing active = backend.getActiveThing(x, y);
                if (active != null) {
                    terminal.write(active.getGlyph(), x, y, active.getColor());
                } else {
                    terminal.write('X', x, y, AsciiPanel.red);
                }
            }
        }

    }

    @Override
    public Screen respondToKeyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_W:
                backend.playerStartMoving(Direction.DIR_UP);
                break;
            case KeyEvent.VK_A:
                backend.playerStartMoving(Direction.DIR_LEFT);
                break;
            case KeyEvent.VK_S:
                backend.playerStartMoving(Direction.DIR_DOWN);
                break;
            case KeyEvent.VK_D:
                backend.playerStartMoving(Direction.DIR_RIGHT);
                break;
            case KeyEvent.VK_UP:
                backend.playerStartAttacking(Direction.DIR_UP);
                break;
            case KeyEvent.VK_LEFT:
                backend.playerStartAttacking(Direction.DIR_LEFT);
                break;
            case KeyEvent.VK_DOWN:
                backend.playerStartAttacking(Direction.DIR_DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                backend.playerStartAttacking(Direction.DIR_RIGHT);
                break;
        }
        return this;
    }

    @Override
    public Screen respondToKeyReleased(KeyEvent key) {
        Direction currentDirection = backend.getPlayerDir();
        Direction currentAttackingDirection = backend.getPlayerAttackDir();
        switch (key.getKeyCode()) {
            case KeyEvent.VK_W:
                if (currentDirection == Direction.DIR_UP)
                    backend.playerStopMoving();
                break;
            case KeyEvent.VK_A:
                if (currentDirection == Direction.DIR_LEFT)
                    backend.playerStopMoving();
                break;
            case KeyEvent.VK_S:
                if (currentDirection == Direction.DIR_DOWN)
                    backend.playerStopMoving();
                break;
            case KeyEvent.VK_D:
                if (currentDirection == Direction.DIR_RIGHT)
                    backend.playerStopMoving();
                break;
            case KeyEvent.VK_UP:
                if (currentAttackingDirection == Direction.DIR_UP)
                    backend.playerStopAttacking();
                break;
            case KeyEvent.VK_LEFT:
                if (currentAttackingDirection == Direction.DIR_LEFT)
                    backend.playerStopAttacking();
                break;
            case KeyEvent.VK_DOWN:
                if (currentAttackingDirection == Direction.DIR_DOWN)
                    backend.playerStopAttacking();
                break;
            case KeyEvent.VK_RIGHT:
                if (currentAttackingDirection == Direction.DIR_RIGHT)
                    backend.playerStopAttacking();
                break;
        }
        return this;
    }

}