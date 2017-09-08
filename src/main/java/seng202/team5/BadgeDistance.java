package seng202.team5;
import seng202.exceptions.BadgeLevelException;

import java.awt.Image;
/**
 * Created by sgi55 on 8/09/17.
 */
public class BadgeDistance extends Badge {

    private int level1Distance = 1000;
    private int level2Distance = 10000;
    private int level3Distance = 20000;
    private int level4Distance = 50000;
    private int level5Distance = 100000;
    private int level6Distance = 200000;
    private int level7Distance = 500000;

    private int distanceRemaining = 0;

    public BadgeDistance(int value) throws BadgeLevelException {
        this(value, 0, "", null);
    }

    public BadgeDistance(int value, int level, String description, Image icon) throws BadgeLevelException {
        super("Distance", value, level, description, icon);
        this.updateBadge();
    }

    /**
     * Calculates and returns the distance cap of a given level
     * @param level A level of distance badge
     * @return distanceCap The distance cap of a given level
     * @throws BadgeLevelException If the given level is not a legitimate level of distance badge
     */
    public int getDistanceCap(int level) throws BadgeLevelException {
        int distanceCap = 0;
        switch (level) {
            case 0: distanceCap = 0; break;
            case 1: distanceCap =  level1Distance; break;
            case 2: distanceCap =  level2Distance; break;
            case 3: distanceCap =  level3Distance; break;
            case 4: distanceCap =  level4Distance; break;
            case 5: distanceCap =  level5Distance; break;
            case 6: distanceCap =  level6Distance; break;
            case 7: distanceCap =  level7Distance; break;
            default: throw new BadgeLevelException(level, this.getBadgeType());
        }
        return distanceCap;
    }

    /**
     *Updaters for the BadgeDistance class
     */

    public void updateDistanceRemaining() throws BadgeLevelException {
        int distanceRemaining = 0;
        distanceRemaining = (this.getDistanceCap(this.getLevel()) - this.getValue());
        this.distanceRemaining = distanceRemaining;
        if (distanceRemaining <= 0) {
            this.updateBadge();
        }
    }

    public void updateLevel() {
        int value = this.getValue();
        int level = 0;
        if (value >= level1Distance) {
            level = 1;
        } else if (value >= level2Distance) {
            level = 2;
        } else if (value >= level3Distance) {
            level = 3;
        } else if (value >= level4Distance) {
            level = 4;
        } else if (value >= level5Distance) {
            level = 5;
        } else if (value >= level6Distance) {
            level = 6;
        } else if (value >= level7Distance) {
            level = 7;
        }
        this.setLevel(level);
    }

    public void updateIcon() throws BadgeLevelException {
        Image icon;
        switch (this.getLevel()) {
            case 0: super.pullIcon("badgeDistanceBlank.jpg"); break;
            case 1: super.pullIcon("badgeDistanceBlack.jpg"); break;
            case 2: super.pullIcon("badgeDistanceGreen.jpg"); break;
            case 3: super.pullIcon("badgeDistanceRed.jpg"); break;
            case 4: super.pullIcon("badgeDistanceBronze.jpg"); break;
            case 5: super.pullIcon("badgeDistanceSilver.jpg"); break;
            case 6: super.pullIcon("badgeDistanceGold.jpg"); break;
            case 7: super.pullIcon("badgeDistancePlatinum.jpg"); break;
            default:
                super.pullIcon("badgeDistanceBlank.jpg");
                throw new BadgeLevelException(this.getLevel(), this.getBadgeType());
        }
    }

    public void updateDescription() throws BadgeLevelException {
        int level = this.getLevel();
        String description = "";
        if (level == 0) {
            description = "You have no distance badges yet.";
        } else if (level > 0 && level <= 7) {
            description = "This is your beautiful ";
            switch (level) {
                case 1: description += "black distance badge (level 1)."; break;
                case 2: description += "green distance badge (level 2)."; break;
                case 3: description += "red distance badge (level 3)."; break;
                case 4: description += "bronze distance badge (level 4)."; break;
                case 5: description += "silver distance badge (level 5)."; break;
                case 6: description += "gold distance badge (level 6)."; break;
                case 7: description += "platinum distance badge (level 7)."; break;
            }
        } else {
            throw new BadgeLevelException(level, this.getBadgeType());

        }
        if (level < 7) {
            description += " Cycle " + this.distanceRemaining + " more metres to earn the next badge! :)";
        }
        this.setDescription(description);
    }

    public void updateBadge(int value) throws BadgeLevelException {
        this.setValue(value);
        this.updateLevel();
        this.updateIcon();
        this.updateDistanceRemaining();
        this.updateDescription();
        this.updateName();
    }

    public void updateBadge() throws BadgeLevelException {
        this.updateBadge(this.getValue());
    }
}
