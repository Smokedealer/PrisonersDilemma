package personas;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class TitForTat extends Person {
    private boolean lastOpponentOutcome = true;

    public boolean decide() {
        return lastOpponentOutcome;
    }

    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {
        lastOpponentOutcome = hisDecision;
    }

}
