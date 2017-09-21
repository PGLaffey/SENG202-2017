package seng202.team5;
//TODO check that this is the correct import statement for image. jack push test
//TODO is requirement a String or an int; different in two places in the UML diagram.

import seng202.exceptions.BadgeLevelException;
import seng202.exceptions.BadgeTypeException;

import javafx.scene.image.Image;
import java.util.Arrays;

/**
 * Badge class that serves as a superclass to all the different types of badges.
 */

public class Badge {

    private String[] types = {"Distance","Time","Routes"};
    private int[][] requirements = {{0,2000,10000,50000,100000,200000,500000},{0,2,10,50,100,200,500},{0,2,5,10,50,100,200}};
        //{{Distance(m)},{Routes(n)},{Time(h)}}
    private String[] units = {"metres","hours","trips"};
    private String[] methods = {"Travel","Cycle for","Take"};
    private String[] colours = {"blank","black","green","red","bronze","silver","gold"};

    private int remaining = 0;

    private String badgeType;
    private int value; //see above
    private int level;
    private String description;
    private Image icon;
    private String name;

    public Badge(String badgeType, int value, int level, String description, String name, Image icon) throws BadgeTypeException, BadgeLevelException {
        if (!(Arrays.asList(types).contains(badgeType))) {
            throw new BadgeTypeException(badgeType);
        }
        if (level < 0 || level > 6) {
            throw new BadgeLevelException(level, badgeType);
        }
        this.badgeType = badgeType;
        this.value = value;
        this.level = level;
        this.description = description;
        this.name = name;
        this.icon = icon;
    }

    public Badge(String badgeType, int value) throws BadgeLevelException, BadgeTypeException {
        this(badgeType, value, 0, "", "",  null);
        updateBadge();
    }

    public Badge(String badgeType) throws BadgeLevelException, BadgeTypeException {
        this(badgeType, 0, 0, "", "",  null);
        updateBadge();
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

    private int getBadgeTypeIndex() { return Arrays.asList(types).indexOf(badgeType); }

    public int[] getRequirements() { return requirements[getBadgeTypeIndex()]; }

    public int getLevelCap() { return requirements[getBadgeTypeIndex()][level]; }

    public void setBadgeType(String badgeType) throws BadgeTypeException {
        if (badgeType != "Distance" || badgeType != "Time" || badgeType != "Routes") {
            throw new BadgeTypeException(badgeType);
        }
        this.badgeType = badgeType;
    }

    public void setValue(int value) { this.value = value; }

    public void setLevel(int level) throws BadgeLevelException {
        if (level < 0 || level > 6) {
            throw new BadgeLevelException(level, badgeType);
        }
        this.level = level;
    }

    public void setDescription(String description) { this.description = description; }

    public void setIcon(Image icon) { this.icon = icon; }

    public void setName(String name) { this.name = name; }

    public void updateName() {
        String name = badgeType + " badge, level " + level;
        this.name = name;
    }

    public void updateRemaining() throws BadgeLevelException {
        int remaining = (getLevelCap() - value);
        this.remaining = remaining;
        if (remaining <= 0) {
            updateBadge();
        }
    }

    public void updateLevel() {
        int level = 0;
        if (value >= requirements[getBadgeTypeIndex()][1]) {
            level = 1;
        } else if (value >= requirements[getBadgeTypeIndex()][2]) {
            level = 2;
        } else if (value >= requirements[getBadgeTypeIndex()][3]) {
            level = 3;
        } else if (value >= requirements[getBadgeTypeIndex()][4]) {
            level = 4;
        } else if (value >= requirements[getBadgeTypeIndex()][5]) {
            level = 5;
        } else if (value >= requirements[getBadgeTypeIndex()][6]) {
            level = 6;
        }
        this.level = level;
    }

    //TODO fix this mess:
    public void pullIcon(String fileName) {
        fileName = "resources/images/badges/" + fileName + ".png";
        Image image = new Image(fileName);
    }

    public void updateIcon() {
        String fileName = "badge";
        fileName = fileName + badgeType + (Integer.toString(level));
        pullIcon(fileName);
    }

    public void updateDescription() {
        int type = getBadgeTypeIndex();
        String output = "";
        if (level == 0) {
            output = "You don't have a " + badgeType + " yet.";
        } else if (level >= 1 && level <= 6) {
            if (level == 6) {
                output = "Wow! This is your amazing, ";
            } else {
                output = "This is your beautiful, ";
            }
            output += (colours[level] + ", level " + (Integer.toString(level)) + " " + badgeType + " badge.");
        } else {
            output = "To be quite honest, we're not entirely sure what this is...";
        }

        if (level < 6) {
            output += ("\n" + methods[type] + " " + (Integer.toString(remaining)) + " more " + units[type] + " to earn");
            if (level > 0) {
                output += " the next";
            }
            output += " one.";
        } else if (level == 6) {
            output += "\nYou seem to be at the top of your game, and we've run out of " + badgeType + " badges to give you!";
        }
        description = output;
    }

    public void updateBadge(int value) throws BadgeLevelException {
        this.setValue(value);
        this.updateLevel();
        this.updateIcon();
        this.updateRemaining();
        this.updateDescription();
        this.updateName();
    }

    public void updateBadge() throws BadgeLevelException {
        this.updateBadge(this.getValue());
    }

}