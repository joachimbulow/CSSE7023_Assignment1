package mms.personal;

import mms.utility.Packable;

/**
 * Represents a personal item
 * Is abstract
 */
public abstract class Personal implements Packable {

    /**
     * Owner of the item
     */
    private final String owner;

    /**
     * Width of the item
     */
    private double width;

    /**
     * Height of the item
     */
    private double height;

    /**
     * Length of the item
     */
    private double length;

    /**
     * Constructor with owner
     * @param owner the owner of the item
     * @throws IllegalArgumentException if owner is null or empty
     */
    public Personal(String owner) {
        if (owner == null || owner.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.owner = owner;

    }

    /**
     * Constructor with owner and dimensions
     * @param owner the owner of the item
     * @param width the width of the box
     * @param height the height of the box
     * @param length the length of the boc
     * @throws IllegalArgumentException if owner is null or empty
     */
    public Personal(String owner, double width, double height, double length) {
        if (owner == null || owner.isEmpty() || width < 0 || height < 0 || length < 0) {
            throw new IllegalArgumentException();
        }
        this.owner = owner;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /**
     * Setter for dimensions
     * @param width the width of the box
     * @param height the height of the box
     * @param length the length of the boc
     */
    protected void setDimensions(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /**
     * Getter for owner of the item
     * @return owner
     */
    public String getOwner() {
        return owner;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + owner + ")";
    }
}