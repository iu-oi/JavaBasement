package javaDungeon.game;

import java.io.Serializable;

public class Tile implements Serializable {

    private Thing background;
    private Thing foreground;
    private int xPos;
    private int yPos;

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

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public Tile() {
        this.xPos = -1;
        this.yPos = -1;
    }

    public Tile(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

}