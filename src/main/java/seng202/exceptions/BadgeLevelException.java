package seng202.exceptions;

/**
 * Created by sgi55 on 8/09/17.
 */
public class BadgeLevelException extends Exception{
    public BadgeLevelException(String message) {
        super(message);
    }

    public BadgeLevelException(int level, String badgeType) {
        this("Badge of type '" + badgeType + "' has no level '" + level + "'");
    }
}
