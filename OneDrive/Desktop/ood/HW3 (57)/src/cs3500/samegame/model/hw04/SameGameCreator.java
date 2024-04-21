package cs3500.samegame.model.hw04;

import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;

/**
 * Factory for the Model-View-Controller interface.
 */

public class SameGameCreator {

  /**
   * Represents the type of game the user can play.
   */
  public enum GameType {
    FOURPIECE,
    GRAVITY,
    AUTOMATCH
  }

  /**
   * Represents an instance of a game that the user wants to play.
   * @param gametype the type of game that the user wants to play
   * @return an instance of SameGameModel
   */
  public static SameGameModel<Piece> createGame(GameType gametype) {
    switch (gametype) {
      case AUTOMATCH:
        return new AutoMatchSameGame();
      case GRAVITY:
        return new GravitySameGame();
      case FOURPIECE:
        return new FourPieceSameGame();
      default:
        return new FourPieceSameGame();
    }
  }
}
