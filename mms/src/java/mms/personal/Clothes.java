package mms.personal;

import mms.utility.Size;

public class Clothes extends Personal {

    private Size size;
    private ClotheType type;

    public Clothes(String owner, Size size, ClotheType type) {
        super(owner, getWidthFromSize(size), getHeightFromSize(size), getLengthFromSize(size));
        this.size = size;
        this.type = type;
    }

    public Size getSize() {
        return size;
    }

    public ClotheType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Clothes (" + getOwner() + ") (" + size.name() + ", " + type.name() + ")";
    }


    // Static utility functions

    private static double getWidthFromSize(Size size) {
        switch (size){
            case SMALL:
                return 40;
            case MEDIUM:
                return 50;
            case LARGE:
                return 55;
            default:
                return 0;
        }
    }
    private static double getHeightFromSize(Size size) {
        switch (size){
            case SMALL:
                return 65;
            case MEDIUM:
                return 70;
            case LARGE:
                return 75;
            default:
                return 0;
        }
    }

    private static double getLengthFromSize(Size size) {
        switch (size){
            case SMALL:
                return 10;
            case MEDIUM:
                return 10;
            case LARGE:
                return 10;
            default:
                return 0;
        }
    }
}
