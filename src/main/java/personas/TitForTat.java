package personas;

import java.util.HashSet;
import java.util.Set;


/**
 * "Tit for tat" algorithm.
 * Repeats actions of his opponent, starts with cooperation.
 *
 * @author Matěj Kareš
 * @author Vojtěch Kinkor
 */
public class TitForTat extends SuspectedPerson {
    private Set<Person> crossedMe = new HashSet<>(); // set of opponents who crossed/betrayed me

    /**
     * Makes a decision for trial. Repeats actions of his opponent, starts with cooperation.
     * <p>{@code true} denotes 'being good' - cooperation with other player.
     * <br>{@code false} denotes 'being bad' - rat the other player out/cross him.
     *
     * @param opponent Oppponent.
     * @return Decision.
     */
    @Override
    public boolean decide(Person opponent) {
        return !crossedMe.contains(opponent); // if he crossed me (my notes contains his name), then I will cross him too!
    }

    /**
     * Event called after a trial (when two chosen people returns their decision).
     * Adds opponent into a set of "traitor" if he crossed.
     * Removes opponent from that set if he cooperated.
     *
     * @param opponent Opponent.
     * @param hisDecision Opponent's decision.
     * @param myDecision My decision.
     */
    @Override
    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {
        if(hisDecision) {
            crossedMe.remove(opponent);
        }
        else {
            crossedMe.add(opponent);
        }
    }

}
