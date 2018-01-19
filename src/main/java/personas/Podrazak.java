package personas;


/**
 * "Always defect" algorithm.
 *
 * @author Matěj Kareš
 */
public class Podrazak extends SuspectedPerson {

    /**
     * Makes a decision for trial. Always defect/cross the opponent.
     * <p>{@code true} denotes 'being good' - cooperation with other player.
     * <br>{@code false} denotes 'being bad' - rat the other player out/cross him.
     *
     * @param opponent Oppponent.
     * @return Decision - always {@code false}.
     */
    @Override
    public boolean decide(Person opponent) {
        return false;
    }

}
