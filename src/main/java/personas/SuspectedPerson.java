package personas;

import ethnicity.EthnicGroup;
import ethnicity.Ethnicity;

/**
 * Created by Matěj Kareš on 02.01.2018.
 */
public abstract class SuspectedPerson implements Person {

    private static int lastID = 0;
    private final int id;

    private int score = 0;
    protected EthnicGroup ethnicGroup;

    public SuspectedPerson() {
        id = lastID++;
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
     * Sets ethnic group.
     * @param ethnicGroup
     */
    public void setEthnicGroup(EthnicGroup ethnicGroup) {
        if(ethnicGroup == null) {
            ethnicGroup = new EthnicGroup(this.getClass().getSimpleName() + "_" + id);
        }
        this.ethnicGroup = ethnicGroup;
    }

    /**
     * Gets ethnic group. Creates a default one if ethnicGroup is null.
     */
    public EthnicGroup getEthnicGroup() {
        if(ethnicGroup == null) setEthnicGroup(null);
        return ethnicGroup;
    }

    /**
     * Gets ethnicity.
     */
    @Override
    public Ethnicity getEthnicity() {
        return ethnicGroup;
    }


}
