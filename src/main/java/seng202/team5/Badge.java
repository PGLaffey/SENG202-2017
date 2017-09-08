package seng202.team5;
//TODO check that this is the correct import statement for image. jack push test
//TODO is requirement a String or an int; different in two places in the UML diagram.

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Badge class that serves as a superclass to all the different types of badges.
 *
 */

public class Badge {
/**
}
    private int id;
    private String requirement;
    private String badgeType;
    private String description;
    private boolean obtained;
    private int level;
    private Image icon;
*/
    /**
     * Class constructor specifying what type of badge it is.
     * @param badgeType The type of badge.
     */
    /**
    public Badge(String badgeType) {
        this.badgeType = badgeType;
        this.level = 0;
    }
*/
    /**
     * Overloaded class constructor specifying what type of badge and what level it is.
     * @param badgeType The type of badge.
     * @param level The level of the badge.
     */
    /**
    public Badge(String badgeType, int level) {
        this.badgeType = badgeType;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return badgeType + level;
    }

    public String getRequirement() {
        return requirement;
    }

    public String getDescription() {
        return description;
    }

    public boolean getObtained() {
        return obtained;
    }

    public int getBadgeLevel() {
        return level;
    }

    public Image getIcon() {
        return icon;
    }

    public void obtain() {
        obtained = true;
    }
*/
    //private int id;
    //private String requirement;
    private String badgeType;
    private int value; //time for BadgeTime, distance for BadgeDistance, number of routes of BadgeRoutes\
    private int level;
    private String description;
    private Image icon;

    private String name;

    public Badge(String badgeType, int value, int level, String description, Image icon) {
        this.badgeType = badgeType;
        this.value = value;
        this.level = level;
        this.description = description;
        this.icon = icon;
    }

    public Badge(String badgeType, int value, int level, String description) {
        this(badgeType, value, level, description, null);
    }

    /**
     * Getters and Setters
     */

    public String getBadgeType(){ return badgeType; }

    public int getValue() { return value; }

    public int getLevel() { return level; }

    public String getDescription() { return description; }

    public Image getIcon() { return icon; }

    public String getName() { return name; }

    public void setBadgeType(String badgeType) { this.badgeType = badgeType; }

    public void setValue(int value) { this.value = value; }

    public void setLevel(int level) { this.level = level; }

    public void setDescription(String description) { this.description = description; }

    public void setIcon(Image icon) { this.icon = icon; }

    public void setName(String name) { this.name = name; }

    public void updateName() {
        String name = badgeType + " badge, level " + level;
        this.name = name;
    }

    //TODO fix this mess:
    public void pullIcon(String fileName) {
        Image icon = Toolkit.getDefaultToolkit().getImage(fileName);
        this.icon = icon;
        }

    }
}