import personas.Kavka;
import personas.Podrazak;

/**
 * Created by Matěj Kareš on 11.12.2017.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game(50);

        game.addPeopleToCommunity(50, new Kavka());
        game.addPeopleToCommunity(50, new Podrazak());

        //Všichni kavka - 2600 let
        //Všichni podrazák - 5200 let
        //Všichni rozmar - 3500 let +-

        game.play();
    }
}
