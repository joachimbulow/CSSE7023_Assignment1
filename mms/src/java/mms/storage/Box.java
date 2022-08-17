package mms.storage;

import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.Laptop;
import mms.utility.Packable;
import mms.utility.Size;

import java.util.List;
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
        return "Box (" + getWidth() + ", " + getHeight() + ", " + getLength() + ") " + getSize().name() + " - " + comment;
    }

    public boolean isFragile() {
        List<Packable> televisions = getElements().stream().filter(i -> i instanceof Furniture).collect(Collectors.toList());
        for (Packable tv : televisions) {
            if (((Furniture)tv).getType().equals(FurnitureType.TELEVISION)){
                return true;
            }
        }
        return getElements().stream().filter(i -> i instanceof Laptop).collect(Collectors.toList()).size() > 0;
    }

    public void pack(Packable item) {
        getElements().add(item);
    }


}