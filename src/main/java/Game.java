import personas.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by Matěj Kareš on 02.01.2018.
 */
public class Game {

    static final int SCORE_COOPERATION = 6;
    static final int SCORE_DEFECTION = 2;
    static final int SCORE_TRIAL_WIN = 10;
    static final int SCORE_TRIAL_LOST = -10;


    private ArrayList<Person> community;

    private int iterations;


    public Game(int iterations) {
        this.community = new ArrayList<>();
        this.iterations = iterations;
    }

    public void play() {

        int totalScore = 0;

        for (int i = 0; i < iterations; i++) {
            Collections.shuffle(community);

            for (int j = 0; j <= community.size() / 2; j += 2) {
                totalScore += matchPeople(community.get(j), community.get(j + 1));
            }
        }

        System.out.println("Total community score: " + totalScore);
    }

    private int matchPeople(Person person1, Person person2) {
        boolean decision1 = person1.decide();
        boolean decision2 = person2.decide();

        int result1;
        int result2;

        if (decision1 && decision2) { //Cooperation
            result1 = result2 = SCORE_COOPERATION;
        } else if (decision1 && !decision2) { //Person 1 was betrayed
            result1 = SCORE_TRIAL_LOST;
            result2 = SCORE_TRIAL_WIN;
        } else if (!decision1 && decision2) { //Person 2 was betrayed
            result1 = SCORE_TRIAL_WIN;
            result2 = SCORE_TRIAL_LOST;
        } else { //Defection
            result1 = result2 = SCORE_DEFECTION;
        }

        person1.onPostTrial(person2, decision2, decision1);
        person1.addScore(result1);

        person2.onPostTrial(person1, decision1, decision2);
        person2.addScore(result2);

        return result1 + result2;
    }

    public void printCommunityResult() {
        Map<Class<? extends Person>, Integer> ethnicities = new HashMap<>();

        for (Person person : community) {
            ethnicities.putIfAbsent(person.getClass(), 0);
            ethnicities.compute(person.getClass(), (k, v) -> v + person.getScore());
        }


        System.out.println("\nIndividual ethnicity scores:");

        for (Map.Entry<Class<? extends Person>, Integer> ethnicity : ethnicities.entrySet()) {
            System.out.printf("%-20.20s %-20.20s%n", ethnicity.getKey().getSimpleName(), ethnicity.getValue());
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
