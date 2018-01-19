package personas;


/**
 * "Always cooperate" algorithm.
 *
 * @author Matěj Kareš
 */
public class Kavka extends SuspectedPerson {

    /**
     * Makes a decision for trial. Always cooperates.
     * <p>{@code true} denotes 'being good' - cooperation with other player.
     * <br>{@code false} denotes 'being bad' - rat the other player out/cross him.
     *
     * @param opponent Oppponent.
     * @return Decision - always {@code true}.
     */
    @Override
    public boolean decide(Person opponent) {
        return true;
    }

}
