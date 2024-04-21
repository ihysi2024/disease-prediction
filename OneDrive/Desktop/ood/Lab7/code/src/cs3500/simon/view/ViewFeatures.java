package cs3500.simon.view;

import cs3500.simon.model.ColorGuess;

/**
 * Represents the features to be displayed on the screen.
 */
public interface ViewFeatures {

  void selectedColor(ColorGuess s);

  void quit();

}
