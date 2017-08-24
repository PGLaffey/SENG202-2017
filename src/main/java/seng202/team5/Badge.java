package seng202.team5;
//TODO check that this is the correct import statement for image. jack push test
//TODO is requirement a String or an int; different in two places in the UML diagram.

import java.awt.*;

public class Badge {
    private int id;
    private String requirement;
    private String badgeType;
    private String description;
    private boolean obtained;
    private int level;
    private Image icon;

    public Badge(String badgeType) {
        this.badgeType = badgeType;
        this.level = 0;
    }

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
}
