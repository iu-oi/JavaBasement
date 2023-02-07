package javaDungeon.game;

import java.io.Serializable;

public class Tile implements Serializable {

    private Thing item;
    private Thing entity;
    private int xPos;
    private int yPos;

    public Thing getItem() {
        return item;
    }

    public void setItem(Thing item) {
        this.item = item;
        this.item.setTile(this);
    }

    public void removeThing() {
        item.removeTile();
        item = null;
    }

    public Thing getEntity() {
        return entity;
    }

    public void setEntity(Thing entity) {
        this.entity = entity;
        this.entity.setTile(this);
    }

    public void removeEntity() {
        entity.removeTile();
        entity = null;
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