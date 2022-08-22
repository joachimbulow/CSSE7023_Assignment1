package mms.storage;

import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.Laptop;
import mms.utility.Packable;
import mms.utility.Size;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Box - a kind of storage container
 */
public class Box extends Storage implements Packable {

    /**
     * Comment written on the box
     */
    private final String comment;

    /**
     * Constructor with dimensions and comment
     * @param width the width of the box
     * @param height the height of the box
     * @param length the length of the boc
     * @param comment the comment written on the box
     * @throws IllegalArgumentException if comment is null
     */
    public Box(double width, double height, double length, String comment) {
        super(width, height, length);
        if (comment == null) {
            throw new IllegalArgumentException();
        }
        this.comment = comment;
    }

    /**
     * Constructor with dimensions, size and comment
     * @param width the width of the box
     * @param height the height of the box
     * @param length the length of the boc
     * @param size the size of the box
     * @param comment the comment written on the box
     * @throws IllegalArgumentException if comment is null
     */
    public Box(double width, double height, double length, Size size, String comment) {
        super(width, height, length, size);
        if (comment == null) {
            throw new IllegalArgumentException();
        }
        this.comment = comment;
    }

    @Override
    protected int getMultiplier() {
        return 2;
    }

    /**
     * Getter for box comment
     * @return the comment on the box
     */
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Box (" + df.format(getWidth()) + ", " + df.format(getHeight()) + ", "
                + df.format(getLength()) + ") "
                + getSize().name() + " - " + (comment.isEmpty() ? "\0" : comment)
                + (isFragile() ? " FRAGILE" : "");
    }

    /**
     * Getter for whether the box cointains fragile items (tvs or laptops)
     * @return whether the box is fragile
     */
    public boolean isFragile() {
        List<Packable> televisions = getElements().stream().filter(i -> i instanceof Furniture)
                .collect(Collectors.toList());
        for (Packable tv : televisions) {
            if (((Furniture) tv).getType().equals(FurnitureType.TELEVISION)) {
                return true;
            }
        }
        return getElements().stream().filter(i -> i instanceof Laptop)
                .collect(Collectors.toList()).size() > 0;
    }


    @Override
    public double getVolume() {
        return getWidth() * getHeight() * getLength();
    }
}