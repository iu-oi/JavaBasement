package javaDungeon.screens;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import asciiPanel.AsciiPanel;
import javaDungeon.Application;
import javaDungeon.game.Direction;
import javaDungeon.game.Game;
import javaDungeon.game.Thing;
import javaDungeon.game.World;

public class PlayScreen implements Screen {

    private String dataSave;
    private Application mainFrame;
    private Game backend;

    public PlayScreen(String dataSave, int playerId, Application mainFrame) {
        this.mainFrame = mainFrame;
        this.dataSave = dataSave;
        backend = new Game(this);
        playGame(playerId);
    }

    public PlayScreen(String dataSave, Application mainFrame) {
        this.mainFrame = mainFrame;
        this.dataSave = dataSave;
        try {
            FileInputStream fileInputStream = new FileInputStream(dataSave);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            backend = (Game) (objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
            backend.resume(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void playGame(int playerId) {
        backend.play(playerId);
    }

    public void quitGame(boolean gameStatus) {
        backend.pause();
        if (gameStatus) {
            mainFrame.setScreen(new WinScreen(mainFrame));
        } else {
            mainFrame.setScreen(new LoseScreen(mainFrame));
        }
    }

    public void pauseGame() {
        backend.pause();
    }

    public void resumeGame() {
        backend.resume();
    }

    public void saveGame() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dataSave);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(backend);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            case KeyEvent.VK_ESCAPE:
                pauseGame();
                return new PauseScreen(this, mainFrame);
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
                backend.playerStartShooting(Direction.DIR_UP);
                break;
            case KeyEvent.VK_LEFT:
                backend.playerStartShooting(Direction.DIR_LEFT);
                break;
            case KeyEvent.VK_DOWN:
                backend.playerStartShooting(Direction.DIR_DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                backend.playerStartShooting(Direction.DIR_RIGHT);
                break;
        }
        return this;
    }

    @Override
    public Screen respondToKeyReleased(KeyEvent key) {
        Direction currentDirection = backend.playerDirection();
        Direction currentAttackingDirection = backend.playerAttackDirection();
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
                    backend.playerStopShooting();
                break;
            case KeyEvent.VK_LEFT:
                if (currentAttackingDirection == Direction.DIR_LEFT)
                    backend.playerStopShooting();
                break;
            case KeyEvent.VK_DOWN:
                if (currentAttackingDirection == Direction.DIR_DOWN)
                    backend.playerStopShooting();
                break;
            case KeyEvent.VK_RIGHT:
                if (currentAttackingDirection == Direction.DIR_RIGHT)
                    backend.playerStopShooting();
                break;
        }
        return this;
    }

}