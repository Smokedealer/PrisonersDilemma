package personas;

import utils.MathEx;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Created by ike.
 */
public class TrustComplexPerson extends SuspectedPerson {
    private Map<Person, Double> trustMap = new HashMap<>();

    private double defaultTrust;
    private double increaseStep = 0.1;
    private double decreaseStep = 0.1;

    public TrustComplexPerson(double defaultTrust) {
        this.defaultTrust = defaultTrust;
    }

    public TrustComplexPerson(double defaultTrust, double increaseStep, double decreaseStep) {
        this.defaultTrust = defaultTrust;
        this.increaseStep = increaseStep;
        this.decreaseStep = decreaseStep;
    }

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
                return MathEx.clamp(trust + increaseStep);
            }
            else {
                return MathEx.clamp(trust - decreaseStep);
            }
        });

    }


}
