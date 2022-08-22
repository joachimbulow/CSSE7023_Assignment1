package mms.personal;

/**
 * Represents a laptop computer
 */
public class Laptop extends Personal {

    /**
     * Age of the laptop
     */
    private final int age;

    /**
     * Constructor specifyng owner and age
     * @param owner the owner of the laptop
     * @param age age of the laptop
     */
    public Laptop(String owner, int age) {
        super(owner, 35, 20, 2);
        if (age < 0) {
            throw new IllegalArgumentException();
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "Laptop (" + getOwner() + ") - " + age;
    }

    @Override
    public double getVolume() {
        return getWidth() * getHeight() * getLength();
    }
}