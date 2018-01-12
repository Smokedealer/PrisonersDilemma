package personas;

import java.util.Random;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class Rozmar extends Person {

    public boolean decide() {
        return new Random().nextDouble() < 0.5f;
    }

    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {

    }
}
