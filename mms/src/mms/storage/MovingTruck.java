package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.PackingOrderException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.utility.Packable;
import mms.utility.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a moving truck storage container
 */
public class MovingTruck extends Storage {

    /**
     * The size of the cab of the truck
     */
    private static final int cabSize = 1500;

    /**
     * Constructor with dimensions
     *
     * @param width  the width of the box
     * @param height the height of the box
     * @param length the length of the box
     * @throws IllegalArgumentException when length is less than size of the cab
     */
    public MovingTruck(double width, double height, double length) throws IllegalArgumentException {
        super(width, height, length, Size.LARGE);
        if (length < cabSize) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Constructor with dimensions and size
     *
     * @param width  the width of the box
     * @param height the height of the box
     * @param length the length of the boc
     * @param size   size of the truck
     */
    public MovingTruck(double width, double height, double length, Size size)
            throws IllegalArgumentException {
        super(width, height, length, size);
        if (length < cabSize) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected int getMultiplier() {
        return 4;
    }

    @Override
    public void pack(Packable item) throws PackingException {
        boolean hasFurniture = getElements().stream()
                .filter(i -> i instanceof Furniture).collect(Collectors.toList()).size() > 0;

        // If any furniture is already packed, nothing other than furniture may be packed
        if (hasFurniture && !item.getClass().equals(Furniture.class)) {
            throw new PackingOrderException();
        }

        double packedLength = getElements().stream()
                .map(e -> e.getLength()).reduce(0.0, (total, len) -> total + len);
        if (packedLength + item.getLength() > getLength()) {
            throw new StorageFullException();
        }

        super.pack(item);
    }

    @Override
    public Packable unpack() {
        // Unpacking and repacking to conform to javadoc
        List<Packable> unpackedItems = new ArrayList<>();
        Packable unpackedItem = null;
        while (getOccupiedCapacity() > 0) {
            unpackedItems.add(super.unpack());
        }
        // Removing furniture FIFO
        Packable firstFurniture = unpackedItems.stream()
                .filter(p -> p instanceof Furniture).findFirst().orElse(null);
        if (firstFurniture != null) {
            unpackedItem = unpackedItems.remove(unpackedItems.indexOf(firstFurniture));
        } else if (unpackedItems.size() != 0) {
            // Else unpacking LIFO
            unpackedItem = unpackedItems.remove(unpackedItems.size() - 1);
        }
        //Repacking remaining items
        try {
            for (Packable p : unpackedItems) {
                super.pack(p);
            }
        } catch (Exception e) {
            System.out.println("A packing error occurred");
        }

        return unpackedItem;
    }

    @Override
    public double getLength() {
        return super.getLength() - cabSize;
    }

    /**
     * Calculates volume of truck
     *
     * @return the volume of truck
     */
    public double getVolume() {
        return getWidth() * getHeight() * getLength();
    }

    @Override
    public String toString() {
        return "MovingTruck (" + getElements().size() + "/" + getCapacity() + ")";
    }
}