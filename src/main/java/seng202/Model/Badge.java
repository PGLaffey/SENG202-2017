package seng202.Model;

import java.util.Arrays;
import static java.lang.Integer.MAX_VALUE;

/**
 * The Badge Object constructor and methods.
 */

public class Badge{

    private String filePath = "images/Badges/";
    private String[] types = {"Distance","Time","Routes"}; //Badge types array
    private int[][] requirements = {{0,2000,10000,50000,100000,200000,500000, MAX_VALUE},{0,120,600,3000,6000,12000,30000, MAX_VALUE},{0,2,5,10,50,100,200, MAX_VALUE}}; //Requirements for each level of a badge, arranged [type index from types array][level]
    private String[] units = {"metres","minutes","trips"}; //Units for each badge type, arranged [type index from types array]
    private String[] methods = {"Travel","Cycle","Take"}; //Method of gaining each badge type, [type index from types array]
    private int remaining = 0; //Num remaining units until next level

    private String badgeType; //Type of badge
    private int value; //Number of units gained towards badge
    private int level; //Level of badge
    private String description; //Description of badge
    private String icon = ""; //Icon object of badge
    private String name; //Name of badge

    /**
     * Main constructor for Badge class
     * @param badgeType Type of badge
     * @param value Number of units user has gained towards badge
     * @param level Level of badge
     * @param description Description of badge
     * @param name Name of badge
     * @param icon filiName of badge image
     */
    public Badge(String badgeType, int value, int level, String description, String name, String icon) {
        this.badgeType = badgeType;
        this.value = value;
        this.level = level;
        this.description = description;
        this.name = name;
        if (icon == null) {
            this.icon = filePath + "badgeBlank0.png";
        } else {
            this.icon = icon;
        }
    }


    /**
     * Overloaded constructor for badge type
     *  For constructing badge with only type and value
     *  all other variables are formed using built-in algorithms
     * @param badgeType Type of badge
     * @param value Number of units user has earned towards badge
     */
    public Badge(String badgeType, int value) {
        this(badgeType, value, 0, "", "", null);
        updateBadge();
    }


    /**
     * Overloaded constructor for Badge class
     *  For constructing badge from scratch using only badge type
     *  all other variables are formed using built-in algorithms based on value being 0
     * @param badgeType Type of badge
     */
    public Badge(String badgeType) {
        this(badgeType, 0, 0, "", "",  null);
        updateBadge();
    }


    /**
     * Getter for badge type
     * @return badgeType Type of badge
     */
    public String getBadgeType(){ return badgeType; }


    /**
     * Getter for value
     * @return value Number of units user has gained towards badge
     */
    public int getValue() { return value; }


    /**
     * Getter for level
     * @return level Level of badge
     */
    public int getLevel() { return level; }


    /**
     * Getter for description
     * @return description Description of badge
     */
    public String getDescription() { return description; }


    /**
     * Getter for icon
     * @return icon Filename of badge image
     */
    public String getIcon() { return icon; }


    /**
     * Getter for name
     * @return name Name of badge
     */
    public String getName() { return name; }


    /**
     * getter for badge type index
     * @return badgeTypeIndex Index of badge type in array types
     */
    private int getBadgeTypeIndex() { return Arrays.asList(types).indexOf(badgeType); }


    /**
     * Getter for requirements
     * @return requirements Array of requirements for the type of badge
     */
    public int[] getRequirements() { return requirements[getBadgeTypeIndex()]; }


    /**
     * Getter for level cap
     * @return levelCap The value at which the badgetype will update to the next level
     */
    public int getLevelCap() { return requirements[getBadgeTypeIndex()][level+1]; }


    /**
     * Getter for remaining
     * @return remaining Amount of remaining units until level-up
     */
    public int getRemaining() { return remaining; }


    /**
     * Getter for a string representation ofremaining
     * @return strRemaining A string representation of remaining including units
     */
    public String getStrRemaining() {
        String strRemaining;
        updateRemaining();
        if (level == 6) {
            return "Max level achieved";
        }
        if (units[getBadgeTypeIndex()].equals("minutes")) {
            if (remaining % 60 == 0) {
                strRemaining = Integer.toString(remaining/60) + " hours";
            } else if (remaining < 60) {
                strRemaining = Integer.toString(remaining) + " minutes";
            } else {
                int hours = remaining / 60;
                int minutes = remaining - (hours * 60);
                strRemaining = Integer.toString(hours) + " hours and " + Integer.toString(minutes) + " minutes";
            }
        } else if (units[getBadgeTypeIndex()].equals("metres")) {
            if (remaining % 1000 == 0) {
                strRemaining = Integer.toString(remaining/1000) + " kilometres";
            } else if (remaining < 1000) {
                strRemaining = Integer.toString(remaining) + " metres";
            } else {
                int kilometres = remaining / 1000;
                int metres = remaining - (kilometres * 1000);
                strRemaining = Integer.toString(kilometres) + " kilometres and " + Integer.toString(metres) + " metres";
            }
        } else {
            strRemaining = Integer.toString(remaining) + " " + units[getBadgeTypeIndex()];
        }
        return strRemaining;
    }


    /**
     * Setter for value
     * @param value Number of units the user has achieved towards the badge
     */
    public void setValue(int value) { this.value = value; }


    /**
     * Setter for level
     * @param level The level of badge the user as attained
     */
    public void setLevel(int level) {
        this.level = level;
    }


    /**
     * Setter for description
     * @param description Description of the badge
     */
    public void setDescription(String description) { this.description = description; }


    /**
     * Setter for name
     * @param name Name of the badge
     */
    public void setName(String name) { this.name = name; }


    /**
     * Updater for name
     *      Creates name using badgeType and Level
     */
    public void updateName() { this.name = badgeType + " badge, level " + level; }


    /**
     * Updater for remaining
     * Calculates remaining based on value and levelCap
     */
    public void updateRemaining() {
        int remaining = (getLevelCap() - value);
        this.remaining = remaining;
        if (remaining <= 0) {
            level += 1;
            updateRemaining();
        }
    }


    /**
     * Updater for level
     *      Recalculates level based on value and the requirements array
     */
    public void updateLevel() {
        int level = 0;
        if (value >= requirements[getBadgeTypeIndex()][1]) {
            level = 1;
        } if (value >= requirements[getBadgeTypeIndex()][2]) {
            level = 2;
        } if (value >= requirements[getBadgeTypeIndex()][3]) {
            level = 3;
        } if (value >= requirements[getBadgeTypeIndex()][4]) {
            level = 4;
        } if (value >= requirements[getBadgeTypeIndex()][5]) {
            level = 5;
        } if (value >= requirements[getBadgeTypeIndex()][6]) {
            level = 6;
        }
        this.level = level;
    }


    /**
     * Updater for icon
     *      Recalls icon using badgeType and level
     */
    public void updateIcon() {
        String fileName = "badge";
        fileName = fileName + badgeType + (Integer.toString(level));
        icon = filePath + fileName + ".png";
    }


    /**
     * Updater for description
     * Writes a description of the badge using level, badgeType, and remaining
     */
    public void updateDescription() {
        int type = getBadgeTypeIndex();
        String output;
        if (level == 0) {
            output = "You don't have a " + badgeType + " badge yet.";
            output += ("\n" + methods[type] + " " + getStrRemaining() + " to earn your first one!");
        } else if (level >= 1 && level <= 6) {
            if (level == 6) {
                output = "Nice Work! This is your Level 6 " + badgeType + " Badge.";
                output += "\nYou're at the top of your game, and we've run out of " + badgeType + " Badges to give you!";
            } else {
                output = "This is your Level " + (Integer.toString(level)) + " " + badgeType + " Badge.";
                output += "\n" + methods[type] + " " + getStrRemaining() + " to earn the next one!";
            }
        } else {
            output = "Error: You've exceeded the current possible levels, good job.";
        }
        description = output;
    }


    /**
     * Core updater for Badge class
     * Calls all updater methods
     * Also sets value given a new value
     * @param value The number of units the user has gained towards the badge
     */
    public void updateBadge(int value) {
        this.setValue(value);
        this.updateLevel();
        this.updateIcon();

        this.updateRemaining();
        this.updateDescription();
        this.updateName();
    }


    /**
     * Overloaded core updater for badge class
     * Calls all updater methods
     * Uses existing value
     */
    public void updateBadge() {
        this.updateBadge(this.getValue());
    }
}