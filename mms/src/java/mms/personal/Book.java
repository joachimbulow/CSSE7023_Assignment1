package mms.personal;

public class Book extends Personal {

    private String title;
    private boolean isFiction;

    public Book(String owner, String title, boolean isFiction){
        super(owner, 20, 20, 5);
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.isFiction = isFiction;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book (" + getOwner() + ") Title: " + title + "(" + isFiction + ")";
    }


}