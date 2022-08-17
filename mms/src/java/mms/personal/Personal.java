package mms.personal;

import mms.utility.Packable;

public abstract class Personal implements Packable {

    private String owner;
    private double width;
    private double height;
    private double length;

    public Personal(String owner) {
        if (owner == null || owner.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.owner = owner;

    }

    public Personal(String owner, double width, double height, double length) {
        if (owner == null || owner.isEmpty() || width < 0 || height < 0 || length < 0) {
            throw new IllegalArgumentException();
        }
        this.owner = owner;
        this.width = width;
        this.height = height;
        this.length = length;
    }

    protected void setDimensions( double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + owner + ")";
    }
}