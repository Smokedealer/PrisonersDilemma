package ethnicity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


/**
 * @author ike
 */
public class EthnicGroup implements Ethnicity {

    private int score = 0;
    private String name;
    private Map<Ethnicity, Double> trustLevels = new HashMap<>();

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

    /**
     * Sets the bias towards a group. Value has to be between 0.0 and 1.0
     * otherwise it will be clamped to the nearest value.
     *
     * @param ethnicity target group the bias is towards
     * @param bias Amount of bias towards the group between 0.0 inclusive and 1.0 inclusive
     */
    public void setBiasTowards(Ethnicity ethnicity, double bias){
        if(bias < 0.0) bias = 0.0; // Clamp minimum
        if(bias > 1.0) bias = 1.0; // Clamp maximum

        trustLevels.put(ethnicity, bias); // Replace old or insert
    }

    /**
     * Sets the bias towards a group. Value has to be between 0.0 and 1.0
     * otherwise it will be clamped to the nearest value.
     *
     * @param ethnicity target group the bias is towards
     * @param bias Amount of bias towards the group between 0.0 inclusive and 1.0 inclusive
     */
    public void setBiasTowardsIfAbsent(Ethnicity ethnicity, double bias){
        if(bias < 0.0) bias = 0.0; // Clamp minimum
        if(bias > 1.0) bias = 1.0; // Clamp maximum

        trustLevels.putIfAbsent(ethnicity, bias); // Replace old or insert
    }

    public void computeBiasTowards(Ethnicity ethnicity, BiFunction<? super Ethnicity, ? super Double, ? extends Double> function) {
        trustLevels.computeIfPresent(ethnicity, function);
    }


    public double getEthnicityBias(Ethnicity ethnicity){
        return trustLevels.get(ethnicity);
    }


    public Map<Ethnicity, Double> getTrustLevels() {
        return trustLevels;
    }
}
