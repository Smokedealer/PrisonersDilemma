package personas;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ike.
 */
public class TrustRobotPerson extends SuspectedPerson {
    private Map<Person, Double> trustMap = new HashMap<>();

    private double defaultTrust = 0.5;
    private double increaseStep = 0.1;
    private double decreaseStep = 0.1;

    public TrustRobotPerson(double defaultTrust) {
        this.defaultTrust = defaultTrust;
    }

    public TrustRobotPerson(double defaultTrust, double increaseStep, double decreaseStep) {
        this.defaultTrust = defaultTrust;
        this.increaseStep = increaseStep;
        this.decreaseStep = decreaseStep;
    }

    @Override
    public boolean decide(Person opponent) {
        trustMap.putIfAbsent(opponent, defaultTrust); // add first true results - "not yet betrayed" state

        Double trust = trustMap.get(opponent);
        return trust >= 0.5;
    }

    @Override
    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {

        trustMap.compute(opponent, (person, trust) -> {
            if(hisDecision) {
                return Math.min(1, trust + increaseStep);
            }
            else {
                return Math.max(0, trust - decreaseStep);
            }
        });

    }


}
