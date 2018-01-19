package personas;

import java.util.Random;


/**
 * "Random answer" algorithm.
 *
 * @author Matěj Kareš
 */
public class Rozmar extends SuspectedPerson {

    /**
     * Makes a decision for trial. Unpredictable result, acts randomly.
     * <p>{@code true} denotes 'being good' - cooperation with other player.
     * <br>{@code false} denotes 'being bad' - rat the other player out/cross him.
     *
     * @param opponent Oppponent.
     * @return Decision - completely randomly {@code true} or {@code false}.
     */
    @Override
    public boolean decide(Person opponent) {
        return new Random().nextDouble() < 0.5;
    }

}
