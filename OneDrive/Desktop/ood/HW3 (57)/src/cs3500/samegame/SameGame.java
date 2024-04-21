package cs3500.samegame;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.controller.SameGameTextController;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw04.SameGameCreator;
import cs3500.samegame.model.hw02.SameGameModel;

/**
 * Allows the user to run the game.
 */
public final class SameGame {
  /**
   * Takes in user instruction on what game to play.
   * @param args user instructions.
   */
  public static void main(String[] args) {
    SameGameCreator.GameType type = SameGameCreator.GameType.FOURPIECE;
    int row = 10;
    int cols = 10;
    int swaps = 10;
    boolean random = false;
    List<Integer> inputs = new ArrayList<>();
    inputs.add(row);
    inputs.add(cols);
    inputs.add(swaps);

    if (args.length != 0) {
      if (args[0].equals("gravity")) {
        type = SameGameCreator.GameType.GRAVITY;
      } else if (args[0].equals("automatch")) {
        type = SameGameCreator.GameType.AUTOMATCH;
      }
      else if (args[0].equals("fourpiece")) {
        type = SameGameCreator.GameType.FOURPIECE;
      }
      else {
        throw new IllegalArgumentException("Invalid Type of Game");
      }

      if (args.length > 1) {
        for (int idx = 1; idx < args.length; idx++) {
          try {
            inputs.set((idx - 1), Integer.parseInt(args[idx]));
          } catch (IllegalStateException | IllegalArgumentException e) {
            continue;
          }
        }
      }

      SameGameModel<Piece> game = SameGameCreator.createGame(type);
      Readable rd = new InputStreamReader(System.in);
      Appendable ap = System.out;
      SameGameTextController<Piece> controller = new SameGameTextController<>(rd, ap);

      try {
        controller.playGame(game, inputs.get(0), inputs.get(1), inputs.get(2), random);
      }
      catch (IllegalArgumentException e) {
        // do nothing
      }

    } else {
      throw new IllegalArgumentException("User has not provided any instructions");
    }
  }
}
