package cs3500.simon.view;

/**
 * Represents the view interface for the game.
 */
public interface SimonView {
  void addFeatureListener(ViewFeatures features);

  void display(boolean show);

  /**
   * Display the next color in the sequence.
   */
  void advance();

  /**
   * Tell the user that they guessed wrong.
   */
  void error();
}
