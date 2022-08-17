package mms.storage;

import mms.personal.Laptop;
import mms.utility.Packable;
import mms.utility.Size;

import java.util.stream.Collectors;

public class Box extends Storage implements Packable{

    private String comment;

    public Box(double width, double height, double length, String comment) {
        super(width, height, length);
        this.comment = comment;
    }

    public Box(double width, double height, double length, Size size, String comment) {
        super(width, height, length, size);
        this.comment = comment;
    }

    @Override
    protected int getMultiplier() {
        return 2;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Box (" + getWidth() + ", " + getHeight() + ", " + getLength() + ")" + getSize().name() + " - " + comment;
    }

    public boolean isFragile() {
        return getElements().stream().filter(i -> i.getClass().equals(Laptop.class)).collect(Collectors.toList()).size() > 0;
    }

    public void pack(Packable item) {
        getElements().add(item);
    }


}