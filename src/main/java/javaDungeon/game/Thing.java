package javaDungeon.game;

import java.awt.Color;
import java.io.Serializable;

public class Thing implements Serializable{

    protected final Color color;
    protected final char glyph;

    public Color getColor() {
        return this.color;
    }

    public char getGlyph() {
        return this.glyph;
    }

    private Tile tile;

    public int getX() {
        return this.tile.getxPos();
    }

    public int getY() {
        return this.tile.getyPos();
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void removeTile() {
        tile = null;
    }

    public Thing(Color color, char glyph) {
        this.color = color;
        this.glyph = glyph;
    }

}