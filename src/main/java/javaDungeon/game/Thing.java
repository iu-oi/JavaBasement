package javaDungeon.game;

import java.awt.Color;
import java.io.Serializable;

public class Thing implements Serializable{

    private final Color color;
    private final char glyph;
    private Tile<? extends Thing, ? extends Thing> tile;

    public int getX() {
        return this.tile.getxPos();
    }

    public int getY() {
        return this.tile.getyPos();
    }

    public void setTile(Tile<? extends Thing, ? extends Thing> tile) {
        this.tile = tile;
    }

    public void removeTile() {
        tile = null;
    }

    public Thing(Color color, char glyph) {
        this.color = color;
        this.glyph = glyph;
    }

    public Color getColor() {
        return this.color;
    }

    public char getGlyph() {
        return this.glyph;
    }

}