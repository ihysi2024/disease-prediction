package cs3500.samegame.view;

import java.io.IOException;

import cs3500.samegame.model.hw02.SameGameModel;

/**
 * Represents the visual display of the game model.
 */
public class SameGameTextView<T> implements SameGameView<T> {
  private final SameGameModel<T> model;

  private Appendable appendable;
  // ... any other fields you need

  /**
   * Represents the visual display of the game model.
   * @param model SameGameModel
   */
  public SameGameTextView(SameGameModel<T> model) {
    this.model = model;
  }

  public SameGameTextView(SameGameModel<T> model, Appendable ap) {
    this.model = model;
    this.appendable = ap;
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
        if (colNum != (this.model.length() - 1)) {
          if (this.model.pieceAt(rowNum, colNum) == null) {
            board = board + "X" + " ";
          } else {
            board = board + this.model.pieceAt(rowNum, colNum).toString() + " ";
          }
        } else {
          if (this.model.pieceAt(rowNum, colNum) == null) {
            board = board + "X";
          } else {
            board = board + this.model.pieceAt(rowNum, colNum).toString();
          }
        }
      }
      if (rowNum != (this.model.width() - 1)) {
        board = board + "\n";
      }
    }
    return board;
  }


  @Override
  public void render() throws IOException {
    this.appendable.append(this.toString());
    this.appendable.append(System.lineSeparator());
  }
}