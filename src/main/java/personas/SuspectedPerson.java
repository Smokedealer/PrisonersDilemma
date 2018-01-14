package personas;

/**
 * Created by Matěj Kareš on 02.01.2018.
 */
public abstract class SuspectedPerson implements Person {

    private static int lastID = 0;

    private final int id;
    private int score;

    public SuspectedPerson() {
        this.id = lastID++;
    }

    /**
     * Gets ID of person.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Gets score sum.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets new score sum.
     * @param score New score.
     */
    private void setScore(int score) {
        this.score = score;
    }

    /**
     * Adds score to sum.
     * @param score Score to be added to sum.
     */
    public void addScore(int score){
        this.score += score;
    }

    /**
     * <p>Make a decision for trial.
     * <p>{@code true} denotes 'being good' - cooperation with other player.
     * <br>{@code false} denotes 'being bad' - rat the other player out/doublecross.
     *
     * @param opponent Oppponent.
     * @return Decision.
     */
    public abstract boolean decide(Person opponent);

    /**
     * Event called after a trial (when two chosen people returns their decision).
     * @param opponent Opponent.
     * @param hisDecision Opponent's decision.
     * @param myDecision My decision.
     */
    public void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision) {}


}
