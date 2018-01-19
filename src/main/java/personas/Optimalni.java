package personas;

import java.util.HashSet;


/**
 * Optimal basic algorithm with knowledge of opponents behaviour rules (their implementation) in advance.
 *
 * @author Vojtěch Kinkor
 */
public class Optimalni extends SuspectedPerson {
    private HashSet<Person> canBeCrossed = new HashSet<>(); // set of TF2T opponents which I can betray


    /**
     * Makes a decision for trial based on opponents behaviour rules (class).
     * Tries to get the best results - cross, who can be safely crossed,
     * cooperate rather then cross each other.
     * <p>{@code true} denotes 'being good' - cooperation with other player.
     * <br>{@code false} denotes 'being bad' - rat the other player out/cross him.
     *
     * @param opponent Oppponent.
     * @return Decision.
     */
    @Override
    public boolean decide(Person opponent) {

        if(opponent instanceof Optimalni) {
            return true; // it's always better to cooperate
        }
        if(opponent instanceof TitForTat) {
            return true; // it's always better to cooperate
        }
        if(opponent instanceof TitForTwoTats) {
            if(canBeCrossed.contains(opponent)) {
                return false; // if cooperated before, then we can cross him once
            }
            return true; // initially cooperate
        }

        return false; // better to cross if we know nothing
    }

    /**
     * Event called after a trial (when two chosen people returns their decision).
     * Remembers opponents TitForTwoTats, so we can cross them once.
     *
     * @param opponent Opponent.
     * @param hisDecision Opponent's decision.
     * @param myDecision My decision.
     */
    @Override
    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {
        if(opponent instanceof TitForTwoTats) {
            if(hisDecision && !canBeCrossed.contains(opponent)) {
                canBeCrossed.add(opponent); // he cooperated, we did too, then we can cross him
            }
            else {
                canBeCrossed.remove(opponent); // we already crossed him, so now be nice for once
            }
        }
    }

}
