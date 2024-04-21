package cs3500.simon.controller;

import cs3500.simon.model.Simon;
import cs3500.simon.model.ColorGuess;
import cs3500.simon.view.SimonView;
import cs3500.simon.view.ViewFeatures;

/**
 * Represents the controller for the game.
 */
public class Controller implements ViewFeatures {
  private final Simon model;
  private final SimonView view;

  /**
   * Represents the controller constructor.
   * @param model the model that the controller operates on
   * @param view where the controller outputs to
   */
  public Controller(Simon model, SimonView view) {
    this.model = model;
    this.view = view;
    this.view.addFeatureListener(this);
  }

  /**
   * Allows the model to be displayed.
   */
  public void goDisplay() {
    this.view.display(true);
  }

  @Override
  public void selectedColor(ColorGuess s) {
    boolean success = this.model.enterNextColor(s);
    if (success) {
      this.view.advance();
    } else {
      this.view.error();
    }
  }

  @Override
  public void quit() {
    System.exit(0);
  }
}
