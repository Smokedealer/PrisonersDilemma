import ethnicity.EthnicGroup;
import personas.SuspectedPerson;

import java.util.*;
import java.util.function.Supplier;

/**
 * Created by Matěj Kareš on 02.01.2018.
 */
public class Game {

    private static final int SCORE_COOPERATION = 6;
    private static final int SCORE_DEFECTION = 2;
    private static final int SCORE_TRIAL_WIN = 10;
    private static final int SCORE_TRIAL_LOST = -10;

    private Map<EthnicGroup, Integer> groups = new HashMap<>();
    private List<SuspectedPerson> community = new ArrayList<>();

    private int iterations;

    public Game(int iterations) {
        this.iterations = iterations;
    }


    public void play() {
        if (community.size() < 2) {
            System.err.println("Not enough people in community.");
            return;
        }

        int totalScore = 0;

        for (int i = 0; i < iterations; i++) {
            Collections.shuffle(community);

            for (int j = 0; j <= community.size() / 2; j += 2) {
                totalScore += matchPeople(community.get(j), community.get(j + 1));
            }
        }

        System.out.println("Total community score: " + totalScore);
    }

    private int matchPeople(SuspectedPerson person1, SuspectedPerson person2) {
        boolean decision1 = person1.decide(person2);
        boolean decision2 = person2.decide(person1);

        int result1;
        int result2;

        if (decision1 && decision2) { // Cooperation
            result1 = result2 = SCORE_COOPERATION;
        } else if (decision1 && !decision2) { // SuspectedPerson 1 was betrayed
            result1 = SCORE_TRIAL_LOST;
            result2 = SCORE_TRIAL_WIN;
        } else if (!decision1 && decision2) { // SuspectedPerson 2 was betrayed
            result1 = SCORE_TRIAL_WIN;
            result2 = SCORE_TRIAL_LOST;
        } else { // Defection
            result1 = result2 = SCORE_DEFECTION;
        }

        person1.onPostTrial(person2, decision2, decision1);
        person1.addScore(result1);

        person2.onPostTrial(person1, decision1, decision2);
        person2.addScore(result2);

        return result1 + result2;
    }


    public void printCommunityResult() {
        for (SuspectedPerson person : community) {
            groups.putIfAbsent(person.getEthnicGroup(), 0);
            groups.compute(person.getEthnicGroup(), (k, v) -> v + person.getScore());
        }

        System.out.println("\nIndividual ethnicity scores:");

        for (Map.Entry<EthnicGroup, Integer> group : groups.entrySet()) {
            System.out.printf("%-20.20s %-20.20s%n", group.getKey().getName(), group.getValue());
        }

    }


    public void addPeopleToCommunity(int peopleCount, Supplier<SuspectedPerson> personSupplier) {
        addPeopleToCommunity(peopleCount, personSupplier, null);
    }

    public void addPeopleToCommunity(int peopleCount, Supplier<SuspectedPerson> personSupplier, EthnicGroup ethnicGroup) {
        for (int i = 0; i < peopleCount; i++) {

            SuspectedPerson person = personSupplier.get();

            if(ethnicGroup == null) {
                ethnicGroup = person.getEthnicGroup(); // gets default one of first person in group
            }

            person.setEthnicGroup(ethnicGroup);

            community.add(person);
        }
    }
}
