package personas;

/**
 * Created by Matěj Kareš on 02.01.2018.
 */
public abstract class Person {

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

    public abstract boolean decide();

    public abstract void onPostTrial(Person opponent, boolean hisDecision, boolean myDecision);

    public void addScore(int score){
        setScore(getScore() + score);
    }

}
