package mms.storage;

import mms.exceptions.BadItemException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.utility.Packable;
import mms.utility.Size;

import java.util.List;
import java.util.stream.Collectors;

public class MovingTruck extends Storage {

    public MovingTruck(double width, double height, double length) {
        super(width, height, length);
    }

    public MovingTruck(double width, double height, double length, Size size) {
        super(width, height, length, size);
    }

    @Override
    protected int getMultiplier() {
        return 4;
    }

    @Override
    public void pack(Packable item) throws StorageFullException, BadItemException {
        if (exceedsStorage(this, item)) {
            throw new StorageFullException();
        }

        boolean hasFurniture = getElements().stream().filter(i -> i instanceof Furniture).collect(Collectors.toList()).size() > 0;
        if (hasFurniture && !item.getClass().equals(Furniture.class)){
            throw new BadItemException();
        }
        getElements().add(item);
    }

    public Packable unpack() {
        List<Packable> furniture = getElements().stream().filter(e -> e instanceof Furniture).collect(Collectors.toList());
        if (furniture.size() > 0) {
            return getElements().remove(getElements().indexOf(furniture.get(0)));
        }
        if (getElements().size() > 0) {
            return getElements().remove(getElements().size() - 1);
        }
        return null;
    }

    public double getVolume() {
        return getWidth() * getHeight() * (getLength() - 1500);
    }

    @Override
    public String toString() {
        return "MovingTruck(" + getElements().size() + "/" + getCapacity() + ")";
    }
}