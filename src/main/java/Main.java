import ethnicity.EthnicGroup;
import personas.*;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Prisoners dilemma simulation.<br><br>
 * Semestral work - KIV/VSS, 2017/18, KIV/ZČU
 *
 * @author Matěj Kareš
 * @author Vojtěch Kinkor
 */
public class Main {

    /**
     * Prisoners dilemma simulation. Entry point.
     * Console application with chart in GUI window.
     */
    public static void main(String[] args) {
        Game game = new Game(50);

        String scriptFilename = null;
        if(args.length > 0 && args[0].endsWith(".js")) scriptFilename = args[0];

        //scriptFilename = "script.basic.js";
        //scriptFilename = "script.tft-robots.js";
        //scriptFilename = "script.trust.js";


        // try running JS script
        if(scriptFilename != null) {
            try {
                // manual: https://www.n-k.de/riding-the-nashorn/

                ScriptEngineManager factory = new ScriptEngineManager();
                ScriptEngine engine = factory.getEngineByName("JavaScript");

                ScriptingInterface scriptingInterface = new ScriptingInterface(game);

                Bindings params = new SimpleBindings();
                params.put("game", scriptingInterface.gameManager);
                params.put("factory", scriptingInterface.factory);

                engine.setBindings(params, ScriptContext.ENGINE_SCOPE);

                engine.eval(new FileReader(scriptFilename));

                System.out.println("Running " + scriptFilename + ":");

            } catch (ScriptException | FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        else {
            System.out.println("Unknown scenario. Please add script filename (.js) as first argument. Running predefined code.");

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


        game.play(); // run simulation

        // print stats to console
        game.printCommunityResult();
        game.printHistory();
        //game.printEthnicGroupsTrustLevels();

        // show window with history chart
        game.showHistoryChart();

    }
}
