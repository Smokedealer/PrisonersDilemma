package personas;

/**
 * @author ike
 */
public abstract class InsiderPerson extends Person {

    /**
     * @deprecated Use decide(Personality opponent) instead.
     */
    public final boolean decide() {
        throw new UnsupportedOperationException();
    }

    /**
     * Make a decision for trial based on the personality of opponent
     * True denotes 'being good' - cooperation with other player.
     * False denotes 'being bad' - rat the other player out/doublecross.
     *
     * @return Decision.
     */
    public abstract boolean decide(Personality opponent);

}
