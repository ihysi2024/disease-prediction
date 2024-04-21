package cs3500.simon.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents the Simon Says game model.
 */
public class SimonSays implements Simon {
  private final List<ColorGuess> colorGuesses;
  private final Random random;
  /**
   * INVARIANT: 0 <= currentColorIndex < Colors.size()
   * INTERPRETATION: the current progress the player has made in guessing the color sequence
   */
  private int currentColorIndex;

  /**
   * Represents the builder for the Simon Says Game.
   */
  public static class Builder {
    Random random;
    List<ColorGuess> initialSequence;


    /**
     * Represents the builder constructor for the game.
     */
    public Builder() {
      this.random = new Random();
      this.initialSequence = new ArrayList<>();
      setInitialLength(1);
    }

    public Builder setRandom(Random random) {
      this.random = Objects.requireNonNull(random);
      return this;
    }

    /**
     * Set the initial length of the builder sequence.
     * @param initialLength initial length of the sequence.
     * @return a new sequence
     */

    public Builder setInitialLength(int initialLength) {
      if (initialLength < 1) {
        throw new IllegalArgumentException("Length must be positive");
      }
      this.initialSequence.clear();
      for (int i = 0; i < initialLength; i++) {
        this.initialSequence.add(SimonSays.getRandomColor(this.random));
      }
      return this;
    }

    /**
     * Sets the initial sequence in the Simon Says builder.
     * @param colorGuesses sequence of color guesses in the game
     * @return a new colorguess sequence
     */

    public Builder setInitialSequence(ColorGuess... colorGuesses) {
      for (ColorGuess s : colorGuesses) {
        Objects.requireNonNull(s);
      }
      this.initialSequence.clear();
      this.initialSequence.addAll(List.of(colorGuesses));
      return this;
    }

    public SimonSays build() {
      return new SimonSays(this.random, this.initialSequence);
    }
  }

  private SimonSays(Random random, List<ColorGuess> initialSequence) {
    this.random = random;
    this.colorGuesses = new ArrayList<>(initialSequence);
  }

  private void addNewColor() {
    this.colorGuesses.add(getRandomColor(this.random));
    this.currentColorIndex = 0;
  }

  private static ColorGuess getRandomColor(Random random) {
    return ColorGuess.values()[random.nextInt(ColorGuess.values().length)];
  }

  @Override
  public List<ColorGuess> getCurrentSequence() {
    return Collections.unmodifiableList(this.colorGuesses);
  }

  @Override
  public boolean enterNextColor(ColorGuess guess) {
    if (guess == this.colorGuesses.get(this.currentColorIndex)) {
      this.currentColorIndex++;
      if (this.currentColorIndex == this.colorGuesses.size()) {
        addNewColor();
      }
      return true;
    } else {
      this.currentColorIndex = 0;
      return false;
    }
  }
}
