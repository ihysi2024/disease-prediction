package cs3500.samegame.view;

import cs3500.samegame.model.hw02.SameGameModel;

/**
 * Represents the visual display of the game model.
 */
public class SameGameTextView<T> implements SameGameView<T> {
  private final SameGameModel<T> model;
  private static boolean random;
  private static int swaps;
  // ... any other fields you need

  /**
   * Represents the visual display of the game model.
   * @param model SameGameModel
   */
  public SameGameTextView(SameGameModel<T> model) {
    this.model = model;
  }

  // your implementation goes here

  /**
   * Represents a visual view of the game board.
   * @return a string.
   */
  public String toString() {
    String board = "";
    for (int rowNum = 0; rowNum < this.model.width(); rowNum++) {
      for (int colNum = 0; colNum < this.model.length(); colNum++) {
        if (this.model.pieceAt(rowNum, colNum) == null) {
          board = board + " " + "X";
        }
        board = board + " " + this.model.pieceAt(rowNum, colNum).toString();
      }
      if (rowNum != (this.model.width() - 1)) {
        board = board + "\n";
      }
    }
    return board;
  }



}