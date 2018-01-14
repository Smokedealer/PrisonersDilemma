package personas;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class TitForTwoTats extends SuspectedPerson {
    private HashMap<Person, LinkedList<Boolean>> history = new HashMap<>(); // set of TF2T opponents which I can betray


    @Override
    public boolean decide(Person opponent) {
        if(!history.containsKey(opponent)) {
            history.put(opponent, new LinkedList<Boolean>(){{ add(true); add(true); }}); // add first true results - "not yet betrayed" state
        }

        LinkedList<Boolean> prev = history.get(opponent);

        return prev.getFirst() || prev.getLast();
    }

    @Override
    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {
        LinkedList<Boolean> prev = history.get(opponent);

        prev.add(hisDecision);
        prev.removeFirst();
    }


}
