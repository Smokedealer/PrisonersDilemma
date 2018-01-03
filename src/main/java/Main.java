import personas.*;

/**
 * Created by Matěj Kareš on 11.12.2017.
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game(1);

        game.addPeopleToCommunity(1, Kavka::new);
        game.addPeopleToCommunity(1, Podrazak::new);
        game.addPeopleToCommunity(1, TitForTat::new);
        game.addPeopleToCommunity(1, TitForTwoTat::new);
        game.addPeopleToCommunity(1, Rozmar::new);

        game.play();
    }
}
