import ethnicity.EthnicGroup;
import personas.TrustComplexPerson;

/**
 * Created by Matěj Kareš on 11.12.2017.
 */
public class Main {
    public static void main(String[] args) {

        Game game = new Game(30);

        //game.addPeopleToCommunity(1, Kavka::new);
        //game.addPeopleToCommunity(1, Podrazak::new);
        //game.addPeopleToCommunity(1, TitForTat::new);
        //game.addPeopleToCommunity(1, TitForTat::new);
        //game.addPeopleToCommunity(1, TitForTwoTats::new);
        //game.addPeopleToCommunity(1, Rozmar::new);
        //game.addPeopleToCommunity(1, Optimalni::new);
        //game.addPeopleToCommunity(100, () -> new TrustRobotPerson(0.0, 1.0, 1.0), new EthnicGroup("RobotPodrazak")); // act as tit for tat, begin with faul
        //game.addPeopleToCommunity(100, () -> new TrustRobotPerson(1.0, 1.0, 1.0), new EthnicGroup("RobotKavka")); // act as tit for tat, begin with cooperation


        EthnicGroup groupBlue = new EthnicGroup("Blue");
        EthnicGroup groupRed = new EthnicGroup("Red");

        groupBlue.setBiasTowards(groupRed, 1.0); // blue are good to red at the beginning
        groupRed.setBiasTowards(groupBlue, 0.0); // red hates blue

        game.addPeopleToCommunity(1, () -> new TrustComplexPerson(0.5, 0.01, 0.05), groupBlue);
        game.addPeopleToCommunity(1, () -> new TrustComplexPerson(0.5, 0.01, 0.05), groupRed);


        game.play();

        game.printCommunityResult();
        game.printHistory();
        game.printEthnicGroupsTrustLevels();

    }
}
