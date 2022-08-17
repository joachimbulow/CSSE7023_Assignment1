package mms.furniture;

import mms.utility.Packable;

public class Furniture implements Packable {

    private FurnitureType type;

    public Furniture(FurnitureType type) {
        this.type = type;
    }

    public FurnitureType getType() {
        return type;
    }

    @Override
    public double getHeight() {
        return type.height;
    }

    @Override
    public double getWidth() {
        return type.width;
    }

    @Override
    public double getLength() {
        return type.length;
    }

    @Override
    public String toString() {
        return "Furniture (" + type.name() + ")";
    }

}