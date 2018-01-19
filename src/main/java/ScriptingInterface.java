import ethnicity.EthnicGroup;
import personas.*;

import java.util.function.Supplier;

public class ScriptingInterface {

    private Game game;
    public Factory factory;
    public GameManager gameManager;

    public ScriptingInterface(Game game) {
        this.game = game;
        this.factory = new Factory();
        this.gameManager = new GameManager(game);
    }

    public class GameManager {
        private Game game;

        private GameManager(Game game) {
            this.game = game;
        }

        public void addPeopleToCommunity(int count, Supplier<SuspectedPerson> personSupplier) {
            game.addPeopleToCommunity(count, personSupplier);
        }

        public void addPeopleToCommunity(int count, Supplier<SuspectedPerson> personSupplier, EthnicGroup ethnicGroup) {
            game.addPeopleToCommunity(count, personSupplier, ethnicGroup);
        }

        public void setIterations(int iterations) {
            game.setIterations(iterations);
        }
    }


    public class Factory {

        private Factory() {
        }

        public Supplier<SuspectedPerson> Kavka() {
            return Kavka::new;
        }

        public Supplier<SuspectedPerson> Podrazak() {
            return Podrazak::new;
        }

        public Supplier<SuspectedPerson> TitForTat() {
            return TitForTat::new;
        }

        public Supplier<SuspectedPerson> TitForTwoTats() {
            return TitForTwoTats::new;
        }

        public Supplier<SuspectedPerson> Optimalni() {
            return Optimalni::new;
        }

        public Supplier<SuspectedPerson> Rozmar() {
            return Rozmar::new;
        }

        public Supplier<SuspectedPerson> TrustRobotPerson(double defaultTrust) {
            return () -> new TrustRobotPerson(defaultTrust);
        }

        public Supplier<SuspectedPerson> TrustRobotPerson(double defaultTrust, double increaseStep, double decreaseStep) {
            return () -> new TrustRobotPerson(defaultTrust, increaseStep, decreaseStep);
        }

        public Supplier<SuspectedPerson> TrustComplexPerson(double defaultTrust) {
            return () -> new TrustComplexPerson(defaultTrust);
        }

        public Supplier<SuspectedPerson> TrustComplexPerson(double defaultTrust, double increaseStep, double decreaseStep) {
            return () -> new TrustComplexPerson(defaultTrust, increaseStep, decreaseStep);
        }

        public Supplier<SuspectedPerson> TrustComplexPerson(double defaultTrust, double increaseStep, double decreaseStep, double groupIncreaseStep, double groupDecreaseStep) {
            return () -> new TrustComplexPerson(defaultTrust, increaseStep, decreaseStep, groupIncreaseStep, groupDecreaseStep);
        }

        public EthnicGroup EthnicGroup(String name) {
            return new EthnicGroup(name);
        }

    }


}
