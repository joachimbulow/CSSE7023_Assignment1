package mms.furniture;

import mms.utility.Packable;

/**
 * Furniture class to identify when furniture is packed
 */
public class Furniture implements Packable {

    /**
     * Type of the furniture
     */
    private final FurnitureType type;

    /**
     * Constructor
     * @param type - the type of the furniture
     */
    public Furniture(FurnitureType type) {
        this.type = type;
    }

    /**
     * getter for the furniture type
     * @return the type of the furniture instance
     */
    public FurnitureType getType() {
        return type;
    }

    @Override
    public double getHeight() {
        return type.height * 100;
    }

    @Override
    public double getWidth() {
        return type.width * 100 ;
    }

    @Override
    public double getLength() {
        return type.length * 100;
    }

    @Override
    public double getVolume() {
        return type.width * type.height * type.length * 1000000;
    }

    @Override
    public String toString() {
        return "Furniture (" + type.name() + ")";
    }

}