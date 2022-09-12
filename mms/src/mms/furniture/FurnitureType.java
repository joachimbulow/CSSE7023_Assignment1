package mms.furniture;


/**
 * Enum to define type of furniture
 * All dimensions are specified in meters
 */
public enum FurnitureType {

    /**
     * Chair with specified dimensions
     */
    CHAIR(0.5, 1.5, 0.5),

    /**
     * Table with specified dimensions
     */
    TABLE(3, 5, 1),

    /**
     * Bed with specified dimensions
     */
    BED(1.5, 2, 0.5),

    /**
     * Desk with specified dimensions
     */
    DESK(1.2,  2, 1),

    /**
     * Television with specified dimensions
     */
    TELEVISION(1.3, 0.75, 0.1);

    /**
     * Width of the furniture type
     */
    public final double width;

    /**
     * Height of the furniture type
     */
    public final double height;

    /**
     * Length of the furniture type
     */
    public final double length;

    FurnitureType(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }
}