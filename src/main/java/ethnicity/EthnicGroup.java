package ethnicity;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ike
 */
public class EthnicGroup implements Ethnicity {

    private int score = 0;
    private String name;
    public Map<Ethnicity, Double> trustLevels = new HashMap<>();

    public EthnicGroup(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets score sum.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets new score sum.
     * @param score New score.
     */
    private void setScore(int score) {
        this.score = score;
    }

    /**
     * Adds score to sum.
     * @param score Score to be added to sum.
     */
    public void addScore(int score){
        this.score += score;
    }

}
