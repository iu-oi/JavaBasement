package javaDungeon.game;

public class Tile<T extends Thing> {

    private T thing;
    private T entity;
    private int xPos;
    private int yPos;

    public T getThing() {
        return thing;
    }

    public void setThing(T thing) {
        this.thing = thing;
        this.thing.setTile(this);
    }

    public void removeThing() {
        thing.removeTile();
        thing = null;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T thing) {
        this.entity = thing;
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