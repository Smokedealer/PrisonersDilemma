package personas;

/**
 * Created by Matěj Kareš on 02.01.2018.
 */
public abstract class Person implements Personality {

    static int lastID = 0;

    private int id;
    private int score;

    public Person() {
        this.id = lastID++;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    /**
     * Make a decision for trial based on the personality of opponent
     * True denotes 'being good' - cooperation with other player.
     * False denotes 'being bad' - rat the other player out/doublecross.
     *
     * @return Decision.
     */
    public abstract boolean decide(Personality opponent);

    public void onPostTrial(Personality opponent, boolean hisDecision, boolean myDecision) {}

    public void addScore(int score){
        setScore(getScore() + score);
    }

}
