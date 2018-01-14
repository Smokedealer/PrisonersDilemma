package personas;

import java.util.Random;

/**
 * Created by Matěj Kareš on 03.01.2018.
 */
public class Rozmar extends SuspectedPerson {

    @Override
    public boolean decide(Person opponent) {
        return new Random().nextDouble() < 0.5;
    }

}
