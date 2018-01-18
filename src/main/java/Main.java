import ethnicity.EthnicGroup;
import personas.*;

/**
 * Created by Matěj Kareš on 11.12.2017.
 */
public class Main {
    public static void main(String[] args) {

        Game game = new Game(30);

        int mode = 0;

        if(mode == 0) {

            //game.addPeopleToCommunity(1, Kavka::new);
            //game.addPeopleToCommunity(1, Podrazak::new);
            //game.addPeopleToCommunity(1, TitForTat::new);
            //game.addPeopleToCommunity(1, TitForTat::new);
            //game.addPeopleToCommunity(1, TitForTwoTats::new);
            //game.addPeopleToCommunity(1, Optimalni::new);
            //game.addPeopleToCommunity(1, Rozmar::new);
            //game.addPeopleToCommunity(1, () -> new TrustRobotPerson(0.0, 1.0, 1.0), new EthnicGroup("RobotTFT_0")); // act as tit for tat, begin with faul
            //game.addPeopleToCommunity(1, () -> new TrustRobotPerson(1.0, 1.0, 1.0), new EthnicGroup("RobotTFT_1")); // act as tit for tat, begin with cooperation

            EthnicGroup groupBlue = new EthnicGroup("Blue (naive)");
            EthnicGroup groupRed = new EthnicGroup("Red");

            groupBlue.setBiasTowards(groupRed, 1.0); // blue are good to red at the beginning
            groupRed.setBiasTowards(groupBlue, 0.0); // red hates blue

            game.addPeopleToCommunity(1, () -> new TrustComplexPerson(0.5, 0.01, 0.05), groupBlue);
            game.addPeopleToCommunity(1, () -> new TrustComplexPerson(0.5, 0.01, 0.05), groupRed);
        }
        else if(mode == 1) { // porovnani klasickych alg.
            game.addPeopleToCommunity(1, Kavka::new);
            game.addPeopleToCommunity(1, Podrazak::new);
            game.addPeopleToCommunity(1, TitForTat::new);
            game.addPeopleToCommunity(1, TitForTat::new);
            game.addPeopleToCommunity(1, TitForTwoTats::new);
            game.addPeopleToCommunity(1, Optimalni::new);
            game.addPeopleToCommunity(1, Rozmar::new);
        }
        else if(mode == 2) { // 2x TFT pomoci primitivni duvery, kazdy zacina opacne
            game.addPeopleToCommunity(1, () -> new TrustRobotPerson(0.0, 1.0, 1.0), new EthnicGroup("RobotTFT_0")); // act as tit for tat, begin with faul
            game.addPeopleToCommunity(1, () -> new TrustRobotPerson(1.0, 1.0, 1.0), new EthnicGroup("RobotTFT_1")); // act as tit for tat, begin with cooperation
        }



        game.play();

        game.printCommunityResult();
        game.printHistory();
        //game.printEthnicGroupsTrustLevels();
        game.showHistoryGraph();

    }
}
