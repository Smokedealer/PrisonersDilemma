package personas;

import java.util.HashMap;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class TrustBasedPerson extends SuspectedPerson {
    private HashMap<Person, Double> trustMap = new HashMap<>(); // set of TF2T opponents which I can betray

    private double defaultTrust = 0.5;
    private double increaseStep = 0.1;
    private double decreaseStep = 0.1;

    public TrustBasedPerson(double defaultTrust) {
        this.defaultTrust = defaultTrust;
    }

    public TrustBasedPerson(double defaultTrust, double increaseStep, double decreaseStep) {
        this.defaultTrust = defaultTrust;
        this.increaseStep = increaseStep;
        this.decreaseStep = decreaseStep;
    }

    @Override
    public boolean decide(Person opponent) {
        if(!trustMap.containsKey(opponent)) {
            trustMap.put(opponent, defaultTrust); // add first true results - "not yet betrayed" state
        }

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
