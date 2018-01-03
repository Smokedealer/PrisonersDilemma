import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Matěj Kareš on 02.01.2018.
 */
public class Person {

    static int lastID = 0;

    private int id;
    private float cooperationProbability;
    private ArrayList<Boolean> history;

    public Person(float cooperationProbability) {
        this.id = lastID++;
        this.cooperationProbability = cooperationProbability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCooperationProbability() {
        return cooperationProbability;
    }

    public void setCooperationProbability(float cooperationProbability) {
        this.cooperationProbability = cooperationProbability;
    }

    public ArrayList<Boolean> getHistory() {
        return history;
    }

    public boolean decide(Strategy strategy) {
        switch (strategy){
            case kavka:     return true;
            case podrazak:  return false;
            case rozmar:    return new Random().nextDouble() < cooperationProbability;
            default:
                System.err.println("Unsupported strategy. Choosing randomly.");
                return new Random().nextDouble() < cooperationProbability;
        }
    }
}
