package personas;

import utils.MathEx;
import java.util.HashMap;
import java.util.Map;


/**
 * Algorithm with simple use of trust levels.
 * Acts upon personal trust towards individuals.
 * Uses only trust threshold 0.5 for making decision,
 * so being called a "robot".
 *
 * @author Vojtěch Kinkor
 */
public class TrustRobotPerson extends SuspectedPerson {
    private Map<Person, Double> trustMap = new HashMap<>();

    private double defaultTrust = 0.5;
    private double increaseStep = 0.1;
    private double decreaseStep = 0.1;

    /**
     * Algorithm with simple use of trust levels.
     * Acts upon personal trust towards individuals.
     *
     * @param defaultTrust Default trust level for strangers.
     */
    public TrustRobotPerson(double defaultTrust) {
        this.defaultTrust = defaultTrust;
    }

    /**
     * Algorithm with simple use of trust levels.
     * Acts upon personal trust towards individuals.
     *
     * @param defaultTrust Default trust level for strangers.
     * @param increaseStep Increase in trust for cooperation.
     * @param decreaseStep Decrease in trust for crossing.
     */
    public TrustRobotPerson(double defaultTrust, double increaseStep, double decreaseStep) {
        this.defaultTrust = defaultTrust;
        this.increaseStep = increaseStep;
        this.decreaseStep = decreaseStep;
    }

    /**
     * Makes a decision for trial. Cooperates when trust is >= 0.5. Otherwise crosses.
     * <p>{@code true} denotes 'being good' - cooperation with other player.
     * <br>{@code false} denotes 'being bad' - rat the other player out/cross him.
     *
     * @param opponent Oppponent.
     * @return Decision.
     */
    @Override
    public boolean decide(Person opponent) {
        trustMap.putIfAbsent(opponent, defaultTrust); // add first true results - "not yet betrayed" state

        Double trust = trustMap.get(opponent);
        return trust >= 0.5;
    }


    /**
     * Event called after a trial (when two chosen people returns their decision).
     * Adjusts trust towards opponent based on his decision.
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

    }


}
