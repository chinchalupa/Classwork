package problem.question7;

/**
 * Created by wrightjt on 12/1/2015.
 */
public class GameStarter {

    public void startGame(Game game) {

        Thread c = new Clock();

        c.start(); // Runs the clock asynchronously

        game.beginPlay();
    }

}
