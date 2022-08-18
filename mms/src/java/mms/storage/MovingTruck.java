package mms.storage;

import mms.exceptions.BadItemException;
import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.utility.Packable;
import mms.utility.Size;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a moving truck storage container
 */
public class MovingTruck extends Storage {

    /**
     * Constructor with dimensions
     * @param width the width of the box
     * @param height the height of the box
     * @param length the length of the boc
     */
    public MovingTruck(double width, double height, double length) {
        super(width, height, length);
    }

    /**
     * Constructor with dimensions and size
     * @param width the width of the box
     * @param height the height of the box
     * @param length the length of the boc
     * @param size size of the truck
     */
    public MovingTruck(double width, double height, double length, Size size) {
        super(width, height, length, size);
    }

    @Override
    protected int getMultiplier() {
        return 4;
    }

    @Override
    public void pack(Packable item) throws PackingException {
        boolean hasFurniture = getElements().stream()
                .filter(i -> i instanceof Furniture).collect(Collectors.toList()).size() > 0;

        if (hasFurniture && !item.getClass().equals(Furniture.class)) {
            throw new BadItemException();
        }

        double packedLength = getElements().stream()
                .map(e -> e.getLength()).reduce(0.0, (total, len) -> total + len);
        if (packedLength + item.getLength() > getLength() - 1500) {
            throw new StorageFullException();
        }

        super.pack(item);
    }

    @Override
    public Packable unpack() {
        List<Packable> furniture = getElements().stream()
                .filter(e -> e instanceof Furniture).collect(Collectors.toList());

        if (furniture.size() > 0) {
            return getElements().remove(getElements().indexOf(furniture.get(0)));
        }

        if (getElements().size() > 0) {
            return getElements().remove(getElements().size() - 1);
        }

        return null;
    }

    /**
     * Calculates volume of truck
     * @return the volume of truck
     */
    public double getVolume() {
        return getWidth() * getHeight() * (getLength() - 1500);
    }

    @Override
    public String toString() {
        return "MovingTruck(" + getElements().size() + "/" + getCapacity() + ")";
    }
}