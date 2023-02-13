package javaDungeon.screen;

import java.awt.Color;

public class Bar {

    public static final int WIDTH = 8;

    private char imageGlyph;
    private Color imageColor;
    private String description;
    private int value;

    Bar(char imageGlyph, Color imageColor) {
        this.imageGlyph = imageGlyph;
        this.imageColor = imageColor;
        description = "";
        value = 0;
    }

    Bar(char imageGlyph, Color imageColor, String description) {
        this.imageGlyph = imageGlyph;
        this.imageColor = imageColor;
        this.description = description;
        value = 0;
    }

    Bar(char imageGlyph, Color imageColor, String description, int value) {
        this.imageGlyph = imageGlyph;
        this.imageColor = imageColor;
        this.description = description;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getImageGlyph() {
        return imageGlyph;
    }

    public void setImageGlyph(char imageGlyph) {
        this.imageGlyph = imageGlyph;
    }

    public Color getImageColor() {
        return imageColor;
    }

    public void setImageColor(Color imageColor) {
        this.imageColor = imageColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
