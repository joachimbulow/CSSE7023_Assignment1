package mms.storage;

import mms.utility.Packable;
import mms.utility.Size;

public class Bag extends Storage {

    public Bag(double width, double height, double length) {
        super(width, height, length);
    }

    public Bag(double width, double height, double length, Size size) {
        super(width, height, length, size);
    }


    @Override
    protected int getMultiplier() {
        return 1;
    }

    public void pack(Packable item) {
        getElements().add(item);
    }
}