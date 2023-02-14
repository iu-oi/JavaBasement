package javaBasement.screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import javaBasement.event.Quit;
import javaBasement.game.entity.creature.player.*;

public class StartScreen extends Screen {

    public StartScreen(AsciiPanel mainPanel, AsciiPanel subPanel) {
        super(mainPanel, subPanel);
        addBar(Player.GLYPH, Player1.COLOR, "Player1", 1);
        addBar(Player.GLYPH, Player2.COLOR, "Player2", 2);
        addBar(Player.GLYPH, Player3.COLOR, "Player3", 3);
        addBar(Player.GLYPH, Player4.COLOR, "Player4", 4);
        addBar(Player.GLYPH, Player5.COLOR, "Player5", 5);
        addBar(Player.GLYPH, Player6.COLOR, "Player6", 6);
        addBar(Player.GLYPH, Player7.COLOR, "Player7", 7);
        addBar(LOAD, Screen.ACTIVE_OPTION_COLOR, "load", 0);
        addBar(QUIT, Screen.ACTIVE_OPTION_COLOR, "quit", -1);
    }

    @Override
    public void refresh() {
        displayTitle("Java Basement");
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
    public Screen keyReleased(int keyCode) throws Quit {
        if (keyCode == KeyEvent.VK_ENTER) {
            int value = getSelection().getValue();
            if (value == -1) {
                throw new Quit();
            } else if (value == 0) {
                return new PlayScreen(mainPaniel, subPanel);
            } else if (value >= 1 && value <= 7) {
                return new PlayScreen(value, mainPaniel, subPanel);
            }
        }
        return this;
    }

}
