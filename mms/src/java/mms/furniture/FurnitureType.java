package mms.furniture;

public enum FurnitureType {
    CHAIR (0.5, 1.5, 0.5),
    TABLE (3, 5, 1),
    BED (1.5, 2, 0.5),
    DESK(1.2,  2, 1),
    TELEVISION(1.3, 0.75, 0.1);


    double width;
    double height;
    double length;
     FurnitureType(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }
}