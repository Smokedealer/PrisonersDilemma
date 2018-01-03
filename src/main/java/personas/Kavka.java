package personas;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class Kavka extends Person {

    public boolean decide() {
        return true;
    }

    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {
        //TODO count score or whatever
    }
}
