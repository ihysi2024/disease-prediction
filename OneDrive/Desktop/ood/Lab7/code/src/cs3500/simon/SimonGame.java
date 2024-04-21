package cs3500.simon;

import cs3500.simon.controller.Controller;
import cs3500.simon.model.Simon;
import cs3500.simon.model.SimonSays;
import cs3500.simon.view.SimonView;
import cs3500.simon.view.SimpleSimonView;

/**
 * Represents the SimonGame.
 */
public class SimonGame {
  /**
   * Allows the user to play the SimonGame.
   * @param args the arguments needed to play the game.
   */
  public static void main(String[] args) {
    Simon model = new SimonSays.Builder().build(); // Feel free to customize this as desired
    SimonView view = new SimpleSimonView(model);
    Controller controller = new Controller(model, view);
    controller.goDisplay();
  }
}
