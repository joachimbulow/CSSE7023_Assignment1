package mms.storage;

import mms.exceptions.BadItemException;
import mms.exceptions.StorageFullException;
import mms.personal.Personal;
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

    public void pack(Packable item) throws StorageFullException, BadItemException {
        if (exceedsStorage(this, item)) {
            throw new StorageFullException();
        }
        if (!(item instanceof Personal)) {
            throw new BadItemException();
        }
        getElements().add(item);
    }
}