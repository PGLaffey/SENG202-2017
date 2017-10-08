package seng202.Model;

import java.util.Arrays;
import static java.lang.Integer.MAX_VALUE;

/**
 * The Badge Object constructor and methods.
 */
public class Badge{

    private String filePath = "images/Badges/";
    private String[] types = {"Distance","Time","Routes"}; //Badge types array
    
    //Requirements for each level of a badge, arranged [type index from types array][level]
    private int[][] requirements = {{0,2000,10000,50000,100000,200000,500000, MAX_VALUE},
    		{0,120,600,3000,6000,12000,30000, MAX_VALUE},{0,2,5,10,50,100,200, MAX_VALUE}}; 
    
    //Units for each badge type, arranged [type index from types array]
    private String[] units = {"metres","minutes","routes"}; 
    
    //Method of gaining each badge type, [type index from types array]
    private String[] methods = {"Travel","Cycle","Take"}; 
    private int remaining = 0; //Num remaining units until next level

    private String badgeType; //Type of badge
    private int value; //Number of units gained towards badge
    private int level; //Level of badge
    private String description; //Description of badge
    private String icon = ""; //Icon object of badge
    private String name; //Name of badge

    
    /**
     * Main constructor for Badge class.
     * @param badgeType 	- Type of badge
     * @param value			- Number of units user has gained towards badge
     * @param level 		- Level of badge
     * @param description 	- Description of badge
     * @param name 			- Name of badge
     * @param icon 			- FileName of badge image
     */
    public Badge(String badgeType, int value, int level, String description, String name, 
    		String icon) {
        this.badgeType = badgeType;
        this.value = value;
        this.level = level;
        this.description = description;
        this.name = name;
        if (icon == null) {
            this.icon = filePath + "badgeBlank0.png";
        } 
        else {
            this.icon = icon;
        }
    }


    /**
     * Overloaded constructor for badge type for constructing badge with only type and value all 
     * other variables are formed using built-in algorithms.
     * @param badgeType 	- Type of badge
     * @param value 		- Number of units user has earned towards badge
     */
    public Badge(String badgeType, int value) {
        this(badgeType, value, 0, "", "", null);
        updateBadge();
    }


    /**
     * Overloaded constructor for Badge class for constructing badge from scratch using only badge
     * type all other variables are formed using built-in algorithms based on value being 0.
     * @param badgeType - Type of badge
     */
    public Badge(String badgeType) {
        this(badgeType, 0, 0, "", "",  null);
        updateBadge();
    }


    /**
     * Getter for value.
     * @return - Number of units user has gained towards badge
     */
    public int getValue() { 
    	return value; 
    }


    /**
     * Getter for level.
     * @return - Level of badge
     */
    public int getLevel() { 
    	return level; 
    }


    /**
     * Getter for description.
     * @return - Description of badge
     */
    public String getDescription() { 
    	return description; 
    }


    /**
     * Getter for icon.
     * @return - Filename of badge image
     */
    public String getIcon() { 
    	return icon; 
    }


    /**
     * Getter for name.
     * @return - Name of badge
     */
    public String getName() { 
    	return name; 
    }


    /**
     * Getter for badge type index.
     * @return - Index of badge type in array types
     */
    private int getBadgeTypeIndex() { 
    	return Arrays.asList(types).indexOf(badgeType); 
    }


    /**
     * Getter for level cap.
     * @return - The value at which the badgetype will update to the next level
     */
    public int getLevelCap() { 
    	return requirements[getBadgeTypeIndex()][level+1]; 
    }


    /**
     * Getter for remaining.
     * @return - Amount of remaining units until level-up
     */
    public int getRemaining() { 
    	return remaining; 
    }


    /**
     * Function to make a string of the users completed time or distance in hours and minutes, or 
     * kilometres and minutes respectively.
     * @return - A string representation of the time or distance a user has completed.
     */
    public String achievedString() {
        String achievedString = "";
        switch(units[getBadgeTypeIndex()]) {
            case "minutes":
                if (getValue() < 60) {
                    achievedString += Integer.toString(getValue()) + " minutes";
                }
                else {
                    int hours = getValue() / 60;
                    int minutes = getValue() - (hours * 60);
                    achievedString += Integer.toString(hours) + " hours";
                    if (minutes > 0) {
                        achievedString += " and " + Integer.toString(minutes) + " minutes";
                    }
                }
                break;
            case "metres":
                if (getValue() < 1000) {
                    achievedString += Integer.toString(getValue()) + " metres";
                }
                else {
                    int kilometres = getValue() / 1000;
                    int metres = getValue() - (kilometres * 1000);
                    achievedString += Integer.toString(kilometres) + " kilometres";
                    if (metres > 0) {
                        achievedString += " and " + Integer.toString(metres) + " metres";
                    }
                }
                break;
        }
        return achievedString;
    }


    /**
     * Getter for a string representation of remaining units till next level.
     * @return - A string representation of remaining including units
     */
    public String getStrRemaining() {
        String strRemaining;
        updateRemaining();
        if (level >= 6) {
            return "Max level achieved";
        }
        switch (units[getBadgeTypeIndex()]) {
            case "minutes":
                if (remaining < 60) {
                    strRemaining = Integer.toString(remaining) + " minutes";
                } 
                else {
                    int hours = remaining / 60;
                    int minutes = remaining - (hours * 60);
                    strRemaining = Integer.toString(hours) + " hours";
                    if (minutes > 0) {
                        strRemaining += " and " + Integer.toString(minutes) + " minutes";
                    }
                }
                break;
            case "metres":
                if (remaining < 1000) {
                    strRemaining = Integer.toString(remaining) + " metres";
                } 
                else {
                    int kilometres = remaining / 1000;
                    int metres = remaining - (kilometres * 1000);
                    strRemaining = Integer.toString(kilometres) + " kilometres";
                    if (metres > 0) {
                        strRemaining += " and " + Integer.toString(metres) + " metres";
                    }
                }
                break;
            default:
                strRemaining = Integer.toString(remaining) + " " + units[getBadgeTypeIndex()];
        }
        return strRemaining;
    }


    /**
     * Setter for value.
     * @param value - Number of units the user has achieved towards the badge
     */
    public void setValue(int value) { 
    	this.value = value; 
    }


    /**
     * Setter for level.
     * @param level - The level of badge the user as attained
     */
    public void setLevel(int level) {
        this.level = level;
    }


    /**
     * Setter for description.
     * @param description - Description of the badge
     */
    public void setDescription(String description) { 
    	this.description = description; 
    }


    /**
     * Setter for name.
     * @param name - Name of the badge
     */
    public void setName(String name) { 
    	this.name = name; 
    }


    /**
     * Updater for name, creates the name using badgeType and Level.
     */
    public void updateName() { 
    	this.name = badgeType + " badge, level " + level; 
    }


    /**
     * Updater for remaining, calculates remaining based on value and levelCap.
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
     * Updater for level, recalculates level based on value and the requirements array.
     */
    public void updateLevel() {
        int level = 0;
        if (value >= requirements[getBadgeTypeIndex()][1]) {
            level = 1;
        } 
        if (value >= requirements[getBadgeTypeIndex()][2]) {
            level = 2;
        } 
        if (value >= requirements[getBadgeTypeIndex()][3]) {
            level = 3;
        } 
        if (value >= requirements[getBadgeTypeIndex()][4]) {
            level = 4;
        } 
        if (value >= requirements[getBadgeTypeIndex()][5]) {
            level = 5;
        } 
        if (value >= requirements[getBadgeTypeIndex()][6]) {
            level = 6;
        }
        this.level = level;
    }


    /**
     * Updater for icon, recalls icon using badgeType and level.
     */
    public void updateIcon() {
        String fileName = "badge";
        fileName = fileName + badgeType + (Integer.toString(level));
        icon = filePath + fileName + ".png";
    }


    /**
     * Updater for description, writes a description of the badge using level, badgeType, and 
     * remaining.
     */
    public void updateDescription() {
        int type = getBadgeTypeIndex();
        String output;
        if (level == 0) {
            output = "You don't have a " + badgeType + " badge yet.";
            output += ("\n" + methods[type] + " " + getStrRemaining() 
            		+ " to earn your first one!");
        } 
        else if (level >= 1 && level <= 6) {
            if (level == 6) {
                output = "Nice Work! This is your Level 6 " + badgeType + " Badge.";
                output += "\nYou're at the top of your game, and we've run out of " + badgeType 
                		+ " Badges to give you!";
            } 
            else {
                output = "This is your Level " + (Integer.toString(level)) + " " + badgeType 
                		+ " Badge.";
                output += "\n" + methods[type] + " " + getStrRemaining() 
                		+ " to earn the next one!";
            }
        } 
        else {
            output = "Error: You've exceeded the current possible levels.";
        }
        description = output;
    }


    /**
     * Core updater for Badge class, calls all updater methods and sets value given a new value.
     * @param value - The number of units the user has gained towards the badge
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
     * Overloaded core updater for badge class, calls all updater methods and uses existing value
     * for value.
     */
    public void updateBadge() {
        this.updateBadge(this.getValue());
    }
}