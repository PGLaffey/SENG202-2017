package seng202.team5;
//TODO check that this is the correct import statement for image. jack push test
//TODO is requirement a String or an int; different in two places in the UML diagram.

import java.awt.*;

public class Badge {
    private int id;
    private String name;
    private String requirement;
    private String badgeType;
    private String description;
    private boolean obtained;
    private int level;
    private Image icon;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRequirement() {
        return this.requirement;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getObtained() {
        return this.obtained;
    }

    //TODO What is this meant to do that getObtained() doesn't do?
    public void checkStatus() {

    }

    public Image getIcon() {
        return this.icon;
    }

    public void obtain() {
        this.obtained = true;
    }
}
