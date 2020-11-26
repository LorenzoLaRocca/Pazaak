package nl.rug.oop.cardgame.util;

/**
 * Represents types that can possibly be empty
 */
public interface Emptiable {
    /**
     * Check if there are any items in this Emptiable.
     */
    boolean isEmpty();

    /**
     * Make this structure empty such that isEmpty() return true
     */
    void empty();
}