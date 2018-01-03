package personas;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class Podrazak extends Person {
    public Person newPerson() {
        return new Podrazak();
    }

    public boolean decide() {
        return false;
    }

    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {

    }
}
