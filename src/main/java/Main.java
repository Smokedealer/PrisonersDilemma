import personas.*;

/**
 * Created by Matěj Kareš on 11.12.2017.
 */
public class Main {
    public static void main(String[] args) {

        Game game = new Game(500);

        game.addPeopleToCommunity(1, Kavka::new);
        game.addPeopleToCommunity(1, Podrazak::new);
        game.addPeopleToCommunity(1, TitForTat::new);
        game.addPeopleToCommunity(1, TitForTwoTats::new);
        game.addPeopleToCommunity(1, Rozmar::new);
        game.addPeopleToCommunity(1, Optimalni::new);

        game.play();

        game.printCommunityResult();

    }
}
