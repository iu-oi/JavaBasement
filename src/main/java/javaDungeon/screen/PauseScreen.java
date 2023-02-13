package javaDungeon.screen;

import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import asciiPanel.AsciiPanel;
import javaDungeon.game.World;

public class PauseScreen extends Screen {

    private PlayScreen playScreen;
    private World world;

    private void saveWorld() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(World.DATA_SAVE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(world);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
            world.consoleLog(this, "Game saved to '" + World.DATA_SAVE + "'.");
        } catch (Exception e) {
            world.consoleLog(this, "Failed to save game to '" + World.DATA_SAVE + "'.");
        }
    }

    PauseScreen(AsciiPanel mainPanel, AsciiPanel subPanel, PlayScreen playScreen, World world) {
        super(mainPanel, subPanel);
        this.playScreen = playScreen;
        this.world = world;
        world.consoleLog(world, "Game paused.");
        addBar(RESUME, Screen.OPTION_COLOR, "resume", 1);
        addBar(SAVE, Screen.OPTION_COLOR, "save", 0);
        addBar(QUIT, Screen.OPTION_COLOR, "menu", -1);
    }

    @Override
    public void refresh() {
        displayTitle("Game paused");
        displayOptions();
    }

    @Override
    public Screen keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            selectPreviousBar();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            selectNextBar();
        }
        return this;
    }

    @Override
    public Screen keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            int value = getSelection().getValue();
            if (value == -1) {
                world.consoleLog(world, "Game aborted.");
                return new StartScreen(mainPaniel, subPanel);
            } else if (value == 0) {
                saveWorld();
            } else if (value == 1) {
                world.consoleLog(world, "Game resumed.");
                return playScreen;
            }
        }
        return this;
    }

}
