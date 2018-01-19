package personas;

import utils.MathEx;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Algorithm with complex use of trust levels.
 * Acts upon personal trust towards individuals
 * and trust towards ethnicity.
 *
 * @author Vojtěch Kinkor
 */
public class TrustComplexPerson extends SuspectedPerson {
    private Map<Person, Double> trustMap = new HashMap<>();

    private double defaultTrust;
    private double increaseStep = 0.1;
    private double decreaseStep = 0.1;

    private double groupIncreaseStep = 0.1;
    private double groupDecreaseStep = 0.1;

    /**
     * Algorithm with complex use of trust levels.
     * Acts upon personal trust towards individuals
     * and trust towards ethnicity.
     *
     * @param defaultTrust Default trust level for strangers.
     */
    public TrustComplexPerson(double defaultTrust) {
        this.defaultTrust = defaultTrust;
    }

    /**
     * Algorithm with complex use of trust levels.
     * Acts upon personal trust towards individuals
     * and trust towards ethnicity.
     *
     * @param defaultTrust Default trust level for strangers.
     * @param increaseStep Increase in trust for cooperation.
     * @param decreaseStep Decrease in trust for crossing.
     */
    public TrustComplexPerson(double defaultTrust, double increaseStep, double decreaseStep) {
        this(defaultTrust, increaseStep, decreaseStep, increaseStep * 0.5, decreaseStep * 0.5);
    }

    /**
     * Algorithm with complex use of trust levels.
     * Acts upon personal trust towards individuals
     * and trust towards ethnicity.
     *
     * @param defaultTrust Default trust level for strangers.
     * @param increaseStep Increase in trust for cooperation.
     * @param decreaseStep Decrease in trust for crossing.
     * @param groupIncreaseStep Increase in ethnicity trust for cooperation.
     * @param groupDecreaseStep Decrease in ethnicity trust for cooperation.
     */
    public TrustComplexPerson(double defaultTrust, double increaseStep, double decreaseStep, double groupIncreaseStep, double groupDecreaseStep) {
        this.defaultTrust = defaultTrust;
        this.increaseStep = increaseStep;
        this.decreaseStep = decreaseStep;
        this.groupIncreaseStep = groupIncreaseStep;
        this.groupDecreaseStep = groupDecreaseStep;
    }

    /**
     * Makes a decision for trial.
     * Uses personal trust towards individual and trust towards ethnicity.
     * Computes trust level (eg. 0.7) and with that probability cooperates (ie. in 70 %).
     *
     * <p>{@code true} denotes 'being good' - cooperation with other player.
     * <br>{@code false} denotes 'being bad' - rat the other player out/cross him.
     *
     * @param opponent Oppponent.
     * @return Decision.
     */
    @Override
    public boolean decide(Person opponent) {
        trustMap.putIfAbsent(opponent, defaultTrust); // add first true results - "not yet betrayed" state
        ethnicGroup.setBiasTowardsIfAbsent(opponent.getEthnicity(), 0.5);

        Double opponentTrust = trustMap.get(opponent);
        Double ethnicTrust = ethnicGroup.getEthnicityBias(opponent.getEthnicity());

        double trust = opponentTrust * (ethnicTrust * 2);
        double rand = new Random().nextDouble();

        // Cooperate with `trust` %
        // Eg. if trust is 0.7 and our random generator says 0.6, then we trust him.
        return rand < trust;
    }

    /**
     * Event called after a trial (when two chosen people returns their decision).
     * Adjusts trust towards opponent and ethnic based on his decision.
     *
     * @param opponent Opponent.
     * @param hisDecision Opponent's decision.
     * @param myDecision My decision.
     */
    @Override
    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {

        trustMap.compute(opponent, (person, trust) -> {
            if(hisDecision) {
                return MathEx.clamp(trust + increaseStep);
            }
            else {
                return MathEx.clamp(trust - decreaseStep);
            }
        });

        ethnicGroup.computeBiasTowards(opponent.getEthnicity(), (ethnicity, trust) -> {
            if(hisDecision) {
                return MathEx.clamp(trust + groupIncreaseStep);
            }
            else {
                return MathEx.clamp(trust - groupDecreaseStep);
            }
        });

    }


}
