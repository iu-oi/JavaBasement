package javaBasement.screen;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import asciiPanel.AsciiPanel;
import javaBasement.event.*;

public abstract class Screen {

    public static final int REFRESH_RATE = 50;

    public static final char QUIT = (char) 0x3e;
    public static final char LOAD = (char) 0x92;
    public static final char SAVE = (char) 0x92;
    public static final char RESUME = (char) 0x22;
    public static final Color TITLE_COLOR = Color.white;
    public static final Color OPTION_COLOR = Color.white;
    public static final Color ACTIVE_OPTION_COLOR = Color.gray;

    protected AsciiPanel mainPaniel;
    protected AsciiPanel subPanel;

    private List<Bar> barList = new ArrayList<>();
    private int selection = 0;

    protected Bar addBar(char glyph, Color color) {
        Bar bar = new Bar(glyph, color);
        barList.add(bar);
        return bar;
    }

    protected Bar addBar(char glyph, Color color, String description) {
        Bar bar = new Bar(glyph, color, description);
        barList.add(bar);
        return bar;
    }

    protected Bar addBar(char glyph, Color color, String description, int value) {
        Bar bar = new Bar(glyph, color, description, value);
        barList.add(bar);
        return bar;
    }

    protected Bar getSelection() {
        return barList.get(selection);
    }

    protected void selectPreviousBar() {
        selection = selection > 0 ? selection - 1 : barList.size() - 1;
    }

    protected void selectNextBar() {
        selection = selection < barList.size() - 1 ? selection + 1 : 0;
    }

    Screen(AsciiPanel mainPanel, AsciiPanel subPanel) {
        this.mainPaniel = mainPanel;
        this.subPanel = subPanel;
    }

    protected void display(char glyph, int x, int y, Color color) {
        mainPaniel.write(glyph, x, y, color);
    }

    protected void displayTitle(String title) {
        mainPaniel.writeCenter(title, mainPaniel.getHeightInCharacters() / 2);
    }

    protected void displayStatus() {
        for (int i = 0; i < barList.size(); i++) {
            Bar bar = barList.get(i);
            subPanel.write(bar.getImageGlyph(), 0, i, bar.getImageColor());
            subPanel.write(bar.getDescription(), 1, i, OPTION_COLOR);
        }
    }

    protected void displayOptions() {
        for (int i = 0; i < barList.size(); i++) {
            Bar bar = barList.get(i);
            if (i == selection) {
                subPanel.write(bar.getImageGlyph(), 0, i, bar.getImageColor());
                subPanel.write(bar.getDescription(), 1, i, ACTIVE_OPTION_COLOR);
            } else {
                subPanel.write(bar.getImageGlyph(), 0, i, OPTION_COLOR);
                subPanel.write(bar.getDescription(), 1, i, OPTION_COLOR);
            }
        }
    }

    public abstract void refresh() throws Victory, Defeat;

    public abstract Screen keyPressed(int keyCode);

    public abstract Screen keyReleased(int keyCode) throws Quit;

}
