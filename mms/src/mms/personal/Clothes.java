package mms.personal;

import mms.utility.Size;

/**
 * Represents clothes
 */
public class Clothes extends Personal {

    /**
     * Size of the clothes
     */
    private final Size size;

    /**
     * Type of clothes
     */
    private final ClotheType type;

    /**
     * Constructor with owner, size, and type
     * @param owner owner of the clothes
     * @param size the size of the clothes
     * @param type the type of clothes
     */
    public Clothes(String owner, Size size, ClotheType type) {
        super(owner, getWidthFromSize(size), getHeightFromSize(size), getLengthFromSize(size));
        this.size = size;
        this.type = type;
    }

    /**
     * Getter for size
     * @return the size of the clothes
     */
    public Size getSize() {
        return size;
    }

    /**
     * Getter for type
     * @return type of clothes
     */
    public ClotheType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Clothes (" + getOwner() + ") (" + size.name() + ", " + type.name() + ")";
    }


    // Static utility functions


    private static double getWidthFromSize(Size size) {
        switch (size) {
            case SMALL:
                return 40;
            case MEDIUM:
                return 50;
            case LARGE:
                return 55;
            default:
                return 0;
        }
    }

    private static double getHeightFromSize(Size size) {
        switch (size) {
            case SMALL:
                return 65;
            case MEDIUM:
                return 70;
            case LARGE:
                return 75;
            default:
                return 0;
        }
    }

    private static double getLengthFromSize(Size size) {
        switch (size) {
            case SMALL:
            case MEDIUM:
            case LARGE:
                return 10;
            default:
                return 0;
        }
    }

    @Override
    public double getVolume() {
        return getWidth() * getHeight() * getLength();
    }
}
