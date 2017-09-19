package seng202.exceptions;

/**
 * Created by sgi55 on 20/09/17.
 */
public class BadgeTypeException extends Exception {
    public BadgeTypeException(String badgeType) {
        super("There is no badge of type '" + badgeType + "'");
    }
}
