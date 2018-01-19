package ethnicity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;


/**
 * Represents ethnic group - "something shared amongst group of people".
 *
 * @author Vojtěch Kinkor
 */
public class EthnicGroup implements Ethnicity {

    /**
     * Shared wealth of group.
     */
    private int score = 0;

    /**
     * Name of group/ethnic.
     */
    private String name;

    /**
     * Trustfulness towards other ethnics.
     */
    private Map<Ethnicity, Double> trustLevels = new HashMap<>();


    /**
     * Represents ethnic group - "something shared amongst group of people".
     * @param name Name of group.
     */
    public EthnicGroup(String name) {
        this.name = name;
    }

    /**
     * Gets name of ethnic group.
     */
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
     * @param ethnicity Target group the bias is towards.
     * @param bias Amount of bias towards the group between 0.0 inclusive and 1.0 inclusive.
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
     * @param ethnicity Target group the bias is towards.
     * @param bias Amount of bias towards the group between 0.0 inclusive and 1.0 inclusive.
     */
    public void setBiasTowardsIfAbsent(Ethnicity ethnicity, double bias){
        if(bias < 0.0) bias = 0.0; // Clamp minimum
        if(bias > 1.0) bias = 1.0; // Clamp maximum

        trustLevels.putIfAbsent(ethnicity, bias); // Replace old or insert
    }

    /**
     * Computes new bias towards a group. Value has to be between 0.0 and 1.0
     * otherwise it will be clamped to the nearest value.
     *
     * @param ethnicity Target group the bias is towards.
     * @param function Function to compute new bias. Returned value must be
     *                 between 0.0 inclusive and 1.0 inclusive.
     */
    public void computeBiasTowards(Ethnicity ethnicity, BiFunction<? super Ethnicity, ? super Double, ? extends Double> function) {
        trustLevels.computeIfPresent(ethnicity, function);
    }

    /**
     * Gets bias towards other ethnic.
     * @param ethnicity Target group the bias is towards.
     * @return Bias.
     */
    public double getEthnicityBias(Ethnicity ethnicity){
        return trustLevels.get(ethnicity);
    }

    /**
     * Gets all trust levels towards ethnicities.
     * @return Trust levels.
     */
    public Map<Ethnicity, Double> getTrustLevels() {
        return trustLevels;
    }
}
