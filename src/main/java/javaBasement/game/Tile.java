package javaBasement.game;

import java.io.Serializable;

public class Tile implements Serializable {

    private Thing background;
    private Thing foreground;
    private int x;
    private int y;

    public Thing getBackground() {
        return background;
    }

    public void setBackground(Thing t) {
        this.background = t;
        this.background.setTile(this);
    }

    public void removeBackground() {
        background.removeTile();
        background = null;
    }

    public Thing getForeground() {
        return foreground;
    }

    public void setForeground(Thing t) {
        this.foreground = t;
        this.foreground.setTile(this);
    }

    public void removeForeground() {
        foreground.removeTile();
        foreground = null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tile() {
        this.x = -1;
        this.y = -1;
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

}