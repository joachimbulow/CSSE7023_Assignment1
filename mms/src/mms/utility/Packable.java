package mms.utility;

/**
 * Interface for packable items
 */
public interface Packable {

    /**
     * Getting height of item
     * @return the height of the item
     */
    double getHeight();

    /**
     * Getting width of item
     * @return the width of the item
     */
    double getWidth();

    /**
     * Getting length of item
     * @return the length of the item
     */
    double getLength();

    /**
     * Getting volume of item
     * @return the volume of the item
     */
    default double getVolume() {
        return getWidth() * getHeight() * getLength();
    }
}