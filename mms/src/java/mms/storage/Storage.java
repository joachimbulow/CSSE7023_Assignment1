package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.utility.Packable;
import mms.utility.Size;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
        this.elements = new ArrayList<>();
    }

    public Storage(double width, double height, double length, Size size) {
        this.width = width;
        this.height = height;
        this.length = length;
        this.size = size;
        this.elements = new ArrayList<>();

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

        if (exceedsStorage(this, item)) {
            throw new StorageFullException();
        }
        this.elements.add(item);
    }

    static protected boolean exceedsStorage(Storage storage, Packable item) {
        if (storage.getElements().size() == storage.getCapacity()) {
            return true;
        }
        double packedWidth = storage.getElements().stream().map(e -> e.getWidth()).reduce(0.0, (total, wid) -> total + wid);
        double packedHeight = storage.getElements().stream().map(e -> e.getHeight()).reduce(0.0, (total, hei) -> total + hei);
        double packedLength = storage.getElements().stream().map(e -> e.getLength()).reduce(0.0, (total, len) -> total + len);
        int exceeding = 0;
        if (packedWidth + item.getWidth() > storage.getWidth()) {
            exceeding++;
        }
        if (packedHeight + item.getHeight() > storage.getHeight()) {
            exceeding++;
        }
        if (packedLength + item.getLength() > storage.length) {
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

    public int getOccupiedCapacity() {
        return elements.size();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return getClass().getSimpleName() + " (" + df.format(width) + ", " + df.format(height) + ", " + df.format(length) + ") " + size.name();
    }

    public String toString(int level) {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");
        // Header
        for (int i = 1; i <= level; i++) {
            sb.append("\t");
        }
        sb.append(getClass().getSimpleName() + " (" + df.format(width) + ", " + df.format(height) + ", " + df.format(length) + ") " + size.name());
        sb.append(System.lineSeparator());
        //Items
        for (Packable p : elements) {
            for (int i = 1; i <= level + 1; i++) {
                sb.append("\t");
                sb.append(p.toString());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}