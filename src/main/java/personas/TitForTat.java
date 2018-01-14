package personas;

import java.util.HashSet;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class TitForTat extends SuspectedPerson {
    private HashSet<Person> crossedMe = new HashSet<>(); // set of TF2T opponents which I can betray


    @Override
    public boolean decide(Person opponent) {
        return !crossedMe.contains(opponent);
    }

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
