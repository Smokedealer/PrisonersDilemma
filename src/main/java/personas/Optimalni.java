package personas;

import java.util.HashSet;

/**
 * Created by ike
 */
public class Optimalni extends InsiderPerson {
    private HashSet<Personality> betrayableTf2t = new HashSet<>(); // set of TF2T opponents which I can betray


    @Override
    public boolean decide(Personality opponent) {

        if(opponent instanceof Optimalni) {
            return true;
        }
        if(opponent instanceof TitForTat) {
            return true;
        }
        if(opponent instanceof TitForTwoTats) {
            if(betrayableTf2t.contains(opponent)) {
                betrayableTf2t.remove(opponent);
                return false;
            }
            return true;
        }

        return false; // better for me to cross everyone else
    }

    @Override
    public void onPostTrial(Personality opponent, boolean hisDecision, boolean myDecision) {
        if(opponent instanceof TitForTwoTats) {
            if(hisDecision) {
                betrayableTf2t.add(opponent);
            }
        }
    }

}
