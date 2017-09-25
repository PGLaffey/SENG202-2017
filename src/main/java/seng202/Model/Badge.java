package seng202.Model;

//import seng202.exceptions.BadgeLevelException;
//import seng202.exceptions.BadgeTypeException;
import java.io.Serializable;
import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;

/**
 * Badge class that serves as a superclass to all the different types of badges.
 */

public class Badge implements Serializable{

    private String filePath = "images/Badges/";

    //Badge types array
    private String[] types = {"Distance","Time","Routes"};
    //Requirements for each level of a badge, arranged [type index from types array][level]
    private int[][] requirements = {{0,2000,10000,50000,100000,200000,500000, MAX_VALUE},{0,120,600,3000,6000,12000,30000, MAX_VALUE},{0,2,5,10,50,100,200, MAX_VALUE}};
    //Units for each badge type, arranged [type index from types array]
    private String[] units = {"metres","minutes","trips"};
    //Method of gaining each badge type, [type index from types array]
    private String[] methods = {"Travel","Cycle","Take"};
    //Colours of each badge leve, arranged by level index
    private String[] colours = {"blank","black","green","red","bronze","silver","gold"};

    //Num remaining units until next level
    private int remaining = 0;

    //Type of badge
    private String badgeType;
    //Number of units gained towards badge
    private int value;
    //Level of badge
    private int level;
    //Description of badge
    private String description;
    //Icon object of badge
    private String icon = "";
    //Name of badge
    private String name;

    /**
     * Main constructor for Badge class
     * @param badgeType Type of badge
     * @param value Number of units user has gained towards badge
     * @param level Level of badge
     * @param description Description of badge
     * @param name Name of badge
     * @param icon filiName of badge image
     * //@throws BadgeTypeException If badge type is not in approved list (types array)
     * //@throws BadgeLevelException If Level is not in range 0<=level<=6
     */
    public Badge(String badgeType, int value, int level, String description, String name, String icon) {
        /*
        if (!(Arrays.asList(types).contains(badgeType))) {
            throw new BadgeTypeException(badgeType);
        }
        if (level < 0 || level > 6) {
            throw new BadgeLevelException(level, badgeType);
        }
         */
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
     * //@throws BadgeLevelException If badge type is not in array types
     * //@throws BadgeTypeException If level is not in range 0<=level<=6
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
     * //@throws BadgeLevelException
     * //@throws BadgeTypeException
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
     * //@throws BadgeLevelException If level is not in range 0<=level<=6
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
                strRemaining = Integer.toString(hours) + " hours & " + Integer.toString(minutes) + " minutes";
            }
        } else if (units[getBadgeTypeIndex()].equals("metres")) {
            if (remaining % 1000 == 0) {
                strRemaining = Integer.toString(remaining/1000) + " kilometres";
            } else if (remaining < 1000) {
                strRemaining = Integer.toString(remaining) + " metres";
            } else {
                int kilometres = remaining / 1000;
                int metres = remaining - (kilometres * 1000);
                strRemaining = Integer.toString(kilometres) + " kilometres & " + Integer.toString(metres) + " metres";
            }
        } else {
            strRemaining = Integer.toString(remaining) + " " + units[getBadgeTypeIndex()];
        }
        return strRemaining;
    }

    /**
     * Setter for badgeType
     * @param badgeType The type of badge
     * //@throws BadgeTypeException If badgeType is not in array types
     */
    public void setBadgeType(String badgeType) {
        //if (!(Arrays.asList(types).contains(badgeType))) {
        //    throw new BadgeTypeException(badgeType);
        //}
        this.badgeType = badgeType;
    }

    /**
     * Setter for value
     * @param value Number of units the user has achieved towards the badge
     */
    public void setValue(int value) { this.value = value; }

    /**
     * Setter for level
     * @param level The level of badge the user as attained
     * //@throws BadgeLevelException If level is not in range 0<=level<=6
     */
    public void setLevel(int level) {
        /*
        if (level < 0 || level > 6) {
            throw new BadgeLevelException(level, badgeType);
        }
        */
        this.level = level;
    }

    /**
     * Setter for description
     * @param description Description of the badge
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Setter for icon
     * @param icon Filename of badge image
     */
    public void setIcon(String icon) { this.icon = icon; }

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
     *      Calculates remaining based on value and levelCap
     * //@throws BadgeLevelException
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
     *      Writes a description of the badge using level, badgeType, and remaining
     * //@throws BadgeLevelException If badge level isn't in range 0<=level<=6
     */
    public void updateDescription() {
        int type = getBadgeTypeIndex();
        String output;
        if (level == 0) {
            output = "You don't have a " + badgeType + " badge yet.";
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

        if (level < 6 && level >= 0) {
            output += ("\n" + methods[type] + " " + getStrRemaining() + " to earn");
            if (level > 0) {
                output += " the next";
            }
            output += " one.";
        } else if (level == 6) {
            output += "\nYou seem to be at the top of your game, and we've run out of " + badgeType + " badges to give you!";
        }
        description = output;
    }

    /**
     * Core updater for Badge class
     *      Calls all updater methods
     *      Also sets value given a new value
     * @param value The number of units the user has gained towards the badge
     * //@throws BadgeLevelException If level is not in range 0<=level<=6
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
     *      Calls all updater methods
     *      Uses existing value
     * //@throws BadgeLevelException If level not in range 0<=level<=6
     */
    public void updateBadge() {
        this.updateBadge(this.getValue());
    }
}