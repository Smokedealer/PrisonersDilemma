import personas.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Supplier;

/**
 * Created by Matěj Kareš on 02.01.2018.
 */
public class Game {

    static final int SCORE_COOPERATION = 1;
    static final int SCORE_DEFECTION = 2;
    static final int SCORE_TRIAL_WIN = 0;
    static final int SCORE_TRIAL_LOST = 3;


    private ArrayList<Person> community;

    private int iterations;


    public Game(int iterations) {
        this.community = new ArrayList<>();
        this.iterations = iterations;
    }

    public void play() {

        int totalYearsInPrison = 0;

        for (int i = 0; i < iterations; i++) {
            Collections.shuffle(community);

            for (int j = 0; j <= community.size() / 2; j += 2) {
                totalYearsInPrison += matchPeople(community.get(j), community.get(j + 1));
            }
        }

        System.out.println("Total years spent in prison: \t" + totalYearsInPrison);
    }

    private int matchPeople(Person person1, Person person2) {
        boolean decision1 = person1.decide();
        boolean decision2 = person2.decide();

        person1.onPostTrial(person2, decision2, decision1);
        person2.onPostTrial(person1, decision1, decision2);

        if (decision1 && decision2) { //Cooperation
            return SCORE_COOPERATION * 2;
        } else if (decision1 && !decision2) { //Person 1 was betrayed
            return SCORE_TRIAL_LOST + SCORE_TRIAL_WIN;
        } else if (!decision1 && decision2) { //Person 2 was betrayed
            return SCORE_TRIAL_WIN + SCORE_TRIAL_LOST;
        } else { //Defection
            return SCORE_DEFECTION * 2;
        }
    }

    public ArrayList<Person> addPeopleToCommunity(int peopleCount, Supplier<Person> personSupplier) {
        for (int i = 0; i < peopleCount; i++) {
            Person person = personSupplier.get();
            community.add(person);
        }

        return community;
    }

}
