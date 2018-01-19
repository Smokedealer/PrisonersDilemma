import ethnicity.EthnicGroup;
import personas.SuspectedPerson;
import java.util.*;
import java.util.function.Supplier;


/**
 * Class represents prisoners dilemma simulation.
 * Manages population, history and the simulation itself (trials of pairs).
 *
 * @author Matěj Kareš.
 */
public class Game {

    private static final int SCORE_COOPERATION = 6;
    private static final int SCORE_DEFECTION = 2;
    private static final int SCORE_TRIAL_WIN = 10;
    private static final int SCORE_TRIAL_LOST = -10;

    /**
     * Score/wealth of whole population.
     */
    private int totalScore = 0;

    /**
     * {@code true} - each iteration consist of n/2 trials.
     * Pairs are combined randomly, but everyone in
     * population has 1 trial.
     *
     * <p>
     * {@code false} - each iteration consist of n^2 trials.
     * Each person has trial with everyone else in population
     * (ie. n trials).
     *
     * <p>
     * n denotes size of population.
     */
    private boolean randomPairs = true;

    /**
     * Number of iterations per single "game play"/"simulation run".
     */
    private int iterations;

    /**
     * Set of existing ethnic groups.
     */
    private Set<EthnicGroup> groups = new HashSet<>();

    /**
     * List of all persons in population.
     */
    private List<SuspectedPerson> community = new ArrayList<>();

    /**
     * Game history recorder.
     */
    private GameHistory history = new GameHistory();


    /**
     * Represents prisoners dilemma simulation.
     * Manages population, history and the simulation itself (trials of pairs).
     *
     * @param iterations Number of iterations per single "game play"/"simulation run".
     */
    public Game(int iterations) {
        this.iterations = iterations;
    }


    /**
     * Runs the simulation/"game".
     */
    public void play() {
        if (community.size() < 2) {
            System.err.println("Not enough people in community.");
            return;
        }

        logCurrentState();

        if(randomPairs) {
            // in pairs, everyone has 1 trial
            for (int i = 0; i < iterations; i++) {
                Collections.shuffle(community);

                for (int j = 0; j <= community.size() / 2; j += 2) {
                    totalScore += doTrial(community.get(j), community.get(j + 1));
                }

                logCurrentState();
            }
        }
        else {
            // "each with each other"
            for (int i = 0; i < iterations; i++) {

                for (int j = 0; j < community.size(); j++) {
                    for (int k = 0; k < community.size(); k++) {
                        if(j != k) {
                            totalScore += doTrial(community.get(j), community.get(k));
                        }
                    }
                }

                logCurrentState();
            }
        }
    }

    /**
     * Logs current community state (wealthiness, trustfulness).
     */
    private void logCurrentState(){
        history.recordCommunityWealth(totalScore);

        for (EthnicGroup group : groups) {
            history.recordEthnicGroup(group);
        }
    }

    /**
     * Does trial between two chosen people.
     * @param person1 First person.
     * @param person2 Second person.
     * @return Gain. Outcome of trial in terms of wealth.
     */
    private int doTrial(SuspectedPerson person1, SuspectedPerson person2) {
        boolean decision1 = person1.decide(person2);
        boolean decision2 = person2.decide(person1);

        int result1;
        int result2;

        if (decision1 && decision2) { // Cooperation
            result1 = result2 = SCORE_COOPERATION;
        } else if (decision1 && !decision2) { // Person 1 was betrayed
            result1 = SCORE_TRIAL_LOST;
            result2 = SCORE_TRIAL_WIN;
        } else if (!decision1 && decision2) { // Person 2 was betrayed
            result1 = SCORE_TRIAL_WIN;
            result2 = SCORE_TRIAL_LOST;
        } else { // Defection
            result1 = result2 = SCORE_DEFECTION;
        }

        person1.onPostTrial(person2, decision2, decision1);
        person1.addScore(result1);

        person2.onPostTrial(person1, decision1, decision2);
        person2.addScore(result2);

        person1.getEthnicGroup().addScore(result1);
        person2.getEthnicGroup().addScore(result2);

        return result1 + result2;
    }

    /**
     * Prints score of whole community and individual ethnic groups.
     */
    public void printCommunityResult() {
        System.out.println("Total community score: " + totalScore);
        System.out.println();
        System.out.println("Individual ethnicity scores:");

        for (EthnicGroup group : groups) {
            System.out.printf("%-21.21s: %d%n", group.getName(), group.getScore());
        }
    }

    /**
     * Prints history - development of wealthiness and trustfulness (currently uses a JSON string).
     */
    public void printHistory() {
        System.out.println();
        System.out.println("History:");
        System.out.println(history.getHistoryJSON());
    }

    /**
     * Prints trustfulness (trust levels) between ethnic groups.
     */
    public void printEthnicGroupsTrustLevels() {
        for (EthnicGroup group : groups) {
            group.getTrustLevels().forEach((ethnicity, trust) -> {
                System.out.println(group.getName() + " -> " + ethnicity.getName() + ": " + trust);
            });
        }
    }

    /**
     * Show history chart in GUI window.
     */
    public void showHistoryChart() {
        GameHistoryVisualizer.visualize(history);
    }

    /**
     * Adds new group of people (sharing same ethnic group) into community.
     * Ethnic group is generated automatically for each all of this method.
     * @param peopleCount Number of people to be added into community.
     * @param personSupplier Supplier of new people.
     */
    public void addPeopleToCommunity(int peopleCount, Supplier<SuspectedPerson> personSupplier) {
        addPeopleToCommunity(peopleCount, personSupplier, null);
    }

    /**
     * Adds new group of people (sharing same ethnic group) into community.
     * @param peopleCount Number of people to be added into community.
     * @param personSupplier Supplier of new people.
     */
    public void addPeopleToCommunity(int peopleCount, Supplier<SuspectedPerson> personSupplier, EthnicGroup ethnicGroup) {
        for (int i = 0; i < peopleCount; i++) {

            SuspectedPerson person = personSupplier.get();

            if(ethnicGroup == null) {
                ethnicGroup = person.getEthnicGroup(); // gets default one of first person in group
            }

            person.setEthnicGroup(ethnicGroup);

            community.add(person);
        }

        groups.add(ethnicGroup);
        history.addEthnicGroup(ethnicGroup);
    }

    /**
     * Sets number of iterations per single "game play"/"simulation run".
     * @param iterations Number of iterations per single "game play"/"simulation run".
     */
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
