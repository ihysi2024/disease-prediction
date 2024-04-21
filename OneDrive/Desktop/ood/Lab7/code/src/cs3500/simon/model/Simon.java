package cs3500.simon.model;

/**
 * Class implementation of the Read Only Simon interface.
 */
public interface Simon extends ReadOnlySimon {
  /**
   * A player guesses one color at a time, trying to mimic the current contents of the sequence.
   * When the player guesses the entire sequence correctly, the model adds a new color to
   * the current sequence.
   * @param guess The current color the player is guessing
   * @return Whether the player guessed correctly
   */
  boolean enterNextColor(ColorGuess guess);
}
