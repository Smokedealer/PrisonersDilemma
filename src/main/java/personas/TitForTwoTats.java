package personas;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * "Tit for 2 tats" algorithm.
 * Crosses opponent if he crossed us twice in a row.
 *
 * @author Matěj Kareš
 * @author Vojtěch Kinkor
 */
public class TitForTwoTats extends SuspectedPerson {
    private HashMap<Person, LinkedList<Boolean>> history = new HashMap<>(); // set of TF2T opponents which I can betray


    /**
     * Makes a decision for trial. Crosses opponent if he crossed us twice in a row.
     * <p>{@code true} denotes 'being good' - cooperation with other player.
     * <br>{@code false} denotes 'being bad' - rat the other player out/cross him.
     *
     * @param opponent Oppponent.
     * @return Decision.
     */
    @Override
    public boolean decide(Person opponent) {
        if(!history.containsKey(opponent)) {
            history.put(opponent, new LinkedList<Boolean>(){{ add(true); add(true); }}); // add first true results - "not yet betrayed" state
        }

        LinkedList<Boolean> prev = history.get(opponent);

        return prev.getFirst() || prev.getLast(); // cooperate if he cooperated in last or last but one trial
    }

    /**
     * Event called after a trial (when two chosen people returns their decision).
     * Remembers opponents last two moves.
     *
     * @param opponent Opponent.
     * @param hisDecision Opponent's decision.
     * @param myDecision My decision.
     */
    @Override
    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {
        LinkedList<Boolean> prev = history.get(opponent);

        prev.add(hisDecision);
        prev.removeFirst();
    }


}
