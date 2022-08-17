package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.utility.Packable;
import mms.utility.Size;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Storage {

    private double width;
    private double height;
    private double length;
    private Size size;
    private List<Packable> elements;

    public Storage(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.size = Size.MEDIUM;
    }

    public Storage(double width, double height, double length, Size size) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.size = size;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public List<Packable> getElements() {
        return elements;
    }

    public List<Packable> getElementsOfType(Packable reference) {
        return elements.stream().filter(s -> s.getClass().equals(reference.getClass())).collect(Collectors.toList());
    }

    public Size getSize() {
        return this.size;
    }

    public void pack(Packable item) throws PackingException {

        if (exceedsStorage(getWidth(), getHeight(), getLength(), getElements(), item)) {
            throw new StorageFullException();
        }
        this.elements.add(item);
    }

    public static boolean exceedsStorage(double width, double height, double length, List<Packable> elements, Packable item) {
        double packedWidth = elements.stream().map(e -> e.getWidth()).reduce(0.0, (total, wid) -> total + wid);
        double packedHeight = elements.stream().map(e -> e.getHeight()).reduce(0.0, (total, hei) -> total + hei);
        double packedLength = elements.stream().map(e -> e.getLength()).reduce(0.0, (total, len) -> total + len);
        int exceeding = 0;
        if (packedWidth + item.getWidth() > width) {
            exceeding++;
        }
        if (packedHeight + item.getHeight() > height) {
            exceeding++;
        }
        if (packedLength + item.getLength() > length) {
            exceeding++;
        }
        return exceeding > 1;
    }

    public Packable unpack() {
        if (elements.size() == 0) {
            return null;
        }
        return elements.remove(0);
    }

    public int getCapacity() {
        return getMultiplier() * getSizeMultiplier(size);
    }

    protected abstract int getMultiplier();

    //Static utility method for translating size to corresponding multiplier
    public static int getSizeMultiplier(Size size) {
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

    public int getOccupiedCapacity() {
        return elements.size();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return getClass().getSimpleName() + " (" + df.format(width) + ", " + df.format(height) + ", " + df.format(length) + ")" + size.name();
    }

    public String toString(int level) {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");
        // Header
        for (int i = 1; i <= level; i++) {
            sb.append("\t");
        }
        sb.append(getClass().getSimpleName() + " (" + df.format(width) + ", " + df.format(height) + ", " + df.format(length) + ")" + size.name());
        sb.append(System.lineSeparator());
        //Items
        for (Packable p : elements) {
            for (int i = 1; i <= level + 1; i++) {
                sb.append("\t");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}