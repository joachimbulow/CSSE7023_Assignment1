package mms.personal;

/**
 * Book - a type of personal item
 */
public class Book extends Personal {

    /**
     * The title of the book
     */
    private final String title;

    /**
     * Whether the boolean is fiction or not
     */
    private final boolean isFiction;

    /**
     * Constructor
     * @param owner the owner of the book
     * @param title the title of the book
     * @param isFiction whether the book is fiction or not
     * @throws IllegalArgumentException if title is null or empty
     */
    public Book(String owner, String title, boolean isFiction) {
        super(owner, 20, 20, 5);
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.title = title;
        this.isFiction = isFiction;
    }

    /**
     * Getter for book title
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book (" + getOwner() + ") Title: "
                + title + " (" + (isFiction ?  "Fiction" : "Non-Fiction") + ")";
    }


    @Override
    public double getVolume() {
        return getWidth() * getHeight() * getLength();
    }
}