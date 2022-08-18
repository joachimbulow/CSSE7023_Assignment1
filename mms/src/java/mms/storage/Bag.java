package mms.storage;

import mms.exceptions.BadItemException;
import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.personal.Personal;
import mms.utility.Packable;
import mms.utility.Size;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A special type of storage that can hold personal items
 */
public class Bag extends Storage implements Packable {

    /**
     * Constructor with dimensions
     * @param width width of the bag
     * @param height height of the bag
     * @param length length of the bag
     */
    public Bag(double width, double height, double length) {
        super(width, height, length);
    }

    /**
     * Constructor with dimensions and size
     * @param width width of the bag
     * @param height height of the bag
     * @param length length of the bag
     * @param size size of the bag
     */
    public Bag(double width, double height, double length, Size size) {
        super(width, height, length, size);
    }


    @Override
    protected int getMultiplier() {
        return 1;
    }

    /**
     * Method for packing an item to the bag
     * @param item to pack
     * @throws BadItemException when packing non personal item
     */
    public void pack(Packable item) throws PackingException {
        if (!(item instanceof Personal)) {
            throw new BadItemException();
        }

        List<Packable> personalElements = getElements().stream().filter(ele -> ele instanceof Personal).collect(Collectors.toList());
        if (personalElements.size() * Personal.getBaseWeight() > 1500) {
            throw new StorageFullException();
        }

        super.pack(item);
    }

    @Override
    public double getVolume() {
        return getWidth() * getHeight() * getLength();
    }
}