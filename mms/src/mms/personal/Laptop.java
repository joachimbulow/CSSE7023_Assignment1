package mms.personal;

public class Laptop extends Personal {

    private int age;

    public Laptop(String owner, int age) {
        super(owner, 35, 20, 2);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Laptop (" + getOwner() + ") - " + age;
    }
}