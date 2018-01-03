/**
 * Created by Matěj Kareš on 11.12.2017.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game(100, 50, Strategy.kavka);

        //Všichni kavka - 2600 let
        //Všichni podrazák - 5200 let
        //Všichni rozmar - 3500 let +-
        
        game.play();
    }
}
