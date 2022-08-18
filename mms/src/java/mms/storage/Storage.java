package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.utility.Packable;
import mms.utility.Size;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an abstract entity that stores items
 */
public abstract class Storage {

    /**
     * Width of the storage container
     */
    private final double width;

    /**
     * Height of the storage container
     */
    private final double height;

    /**
     * Length of the storage container
     */
    private final double length;

    /**
     * Size of the storage container
     */
    private final Size size;

    /**
     * The elements of the storage container
     */
    private final List<Packable> elements;


    /**
     * Constructor with dimensions
     * @param width width of the bag
     * @param height height of the bag
     * @param length length of the bag
     * @throws IllegalArgumentException if any dimensions is 0 or negative
     */
    public Storage(double width, double height, double length) {
        if (width <= 0 || height <= 0 || length <= 0) {
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
        this.length = length;
        this.size = Size.MEDIUM;
        this.elements = new ArrayList<>();
    }

    /**
     * Constructor with dimensions and size
     * @param width width of the bag
     * @param height height of the bag
     * @param length length of the bag
     * @param size the size of the storage container
     * @throws IllegalArgumentException if any dimensions is 0 or negative
     */
    public Storage(double width, double height, double length, Size size) {
        if (width <= 0 || height <= 0 || length <= 0) {
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
        this.length = length;
        this.size = size;
        this.elements = new ArrayList<>();

    }

    /**
     * Get width
     * @return width of storage container
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get width
     * @return height of storage container
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get width
     * @return length of storage container
     */
    public double getLength() {
        return length;
    }

    /**
     * Get elements
     * @return items in the storage container in new list
     */
    public List<Packable> getElements() {
        return new ArrayList<>(elements);
    }

    /**
     * Get elements of type
     * @param reference obejct
     * @return items of same class as reference in the storage container in new list
     */
    public List<Packable> getElementsOfType(Packable reference) {
        return new ArrayList<>(elements.stream()
                .filter(s -> s.getClass().equals(reference.getClass()))
                .collect(Collectors.toList()));
    }

    /**
     * Get size
     * @return size of the storage container
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * Pack an item
     * @param item item to pack in the storage container
     * @throws StorageFullException if the storage is full or dimensions exceed size
     */
    public void pack(Packable item) throws PackingException {
        if (exceedsStorage(this, item)) {
            throw new StorageFullException();
        }
        this.elements.add(item);
    }

    private static boolean exceedsStorage(Storage storage, Packable item) {
        if (storage.getElements().size() == storage.getCapacity()) {
            return true;
        }
        double packedWidth = storage.getElements().stream().map(e -> e.getWidth())
                .reduce(0.0, (total, wid) -> total + wid);
        double packedHeight = storage.getElements().stream().map(e -> e.getHeight())
                .reduce(0.0, (total, hei) -> total + hei);
        double packedLength = storage.getElements().stream().map(e -> e.getLength())
                .reduce(0.0, (total, len) -> total + len);
        int exceeding = 0;
        if (packedWidth + item.getWidth() > storage.getWidth()) {
            exceeding++;
        }
        if (packedHeight + item.getHeight() > storage.getHeight()) {
            exceeding++;
        }
        if (packedLength + item.getLength() > storage.getLength()) {
            exceeding++;
        }
        return exceeding > 1;
    }

    /**
     * Unpack an item
     * @return the item that was unpacked
     */
    public Packable unpack() {
        if (elements.size() == 0) {
            return null;
        }
        return elements.remove(0);
    }

    /**
     * Calculates capacity of storage container
     * @return capacity
     */
    public int getCapacity() {
        return getMultiplier() * getSizeMultiplier(size);
    }

    protected abstract int getMultiplier();

    //Static utility method for translating size to corresponding multiplier
    private static int getSizeMultiplier(Size size) {
        switch (size) {
            case SMALL:
                return 3;
            case MEDIUM:
                return 5;
            case LARGE:
                return 10;
            default:
                return 0;
        }
    }

    /**
     * Get how many items are stored
     * @return the amount of items in the storage container
     */
    public int getOccupiedCapacity() {
        return elements.size();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return getClass().getSimpleName() + " (" + df.format(width) + ", " + df.format(height)
                + ", " + df.format(length) + ") " + size.name();
    }

    /**
     * Print the storage container and its content at the given level of indentation
     * Is used recursively to pretty print with indentation
     * @param level the level of indentation
     * @return a human readable print of the storage container
     * @throws IllegalArgumentException if the level of indentation is less than 0
     */
    public String toString(int level) throws IllegalArgumentException {
        if (level < 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");

        // Header section of storage item
        for (int i = 1; i <= level; i++) {
            sb.append("\t");
        }
        sb.append(getClass().getSimpleName() + " (" + df.format(width) + ", " + df.format(height)
                + ", " + df.format(length) + ") " + size.name());
        sb.append(System.lineSeparator());

        //Items
        for (Packable p : elements) {
            if (p instanceof Box) {
                sb.append(((Box) p).toString(level + 1));
            } else if (p instanceof Bag) {
                sb.append(((Bag) p).toString(level + 1));
            } else {
                for (int i = 1; i <= level + 1; i++) {
                    sb.append("\t");
                }
                sb.append(p.toString());
            }

            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}