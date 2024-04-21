package cs3500.samegame.model.hw04;

import java.util.List;

import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;

/**
 * Represents a version of the game with AutoMatch implemented.
 */
public class AutoMatchSameGame extends FourPieceSameGame {

  /**
   * Represents a subclass of the FourPieceSameGame model with automatch capabilities.
   */
  public AutoMatchSameGame() {
    super();
  }

  /**
   * Swap two pieces with automatic match.
   *
   * @param fromRow the row of the first piece (0-based index)
   * @param fromCol the col of the first piece (0-based index)
   * @param toRow   the row of the first piece (0-based index)
   * @param toCol   the col of the first piece (0-based index)
   */

  @Override
  public void swap(int fromRow, int fromCol, int toRow, int toCol) {
    super.swap(fromRow, fromCol, toRow, toCol);
    this.autoMatch();
  }

  /**
   * Remove a block of pieces with automatic match.
   *
   * @param row the row of the piece to remove (0-based index)
   * @param col the col of the piece to remove (0-based index)
   */
  @Override
  public void removeMatch(int row, int col) {
    super.removeMatch(row, col);
    this.autoMatch();
  }


  private void autoMatch() {
    for (int rowNum = 0; rowNum < this.rows; rowNum++) {
      for (int colNum = 0; colNum < this.cols; colNum++) {
        try {
          super.removeMatch(rowNum, colNum);
        } catch (IllegalArgumentException | IllegalStateException s) {
          continue;
        }
      }
    }
  }

  /**
   * AutoMatch board width.
   *
   * @return an int as a width.
   */
  @Override
  public int width() {
    return super.width();
  }

  /**
   * AutoMatch board length.
   *
   * @return an int as a length
   */
  @Override
  public int length() {
    return super.length();
  }

  /**
   * Find a piece at a given coordinate.
   *
   * @param row the row in the board (0-based index)
   * @param col the col in the board (0-based index)
   * @return a piece at the given coordinate.
   */
  @Override
  public Piece pieceAt(int row, int col) {
    return super.pieceAt(row, col);
  }

  /**
   * Determines the score of an AutoMatch game.
   *
   * @return an int representing the model's score.
   */
  @Override
  public int score() {
    return super.score();
  }

  /**
   * Determines the remaining swaps in a game.
   *
   * @return int as a swap number.
   */
  @Override
  public int remainingSwaps() {
    return super.remainingSwaps();
  }

  /**
   * Determines if the automatch game is over.
   *
   * @return boolean representing game over.
   */
  @Override
  public boolean gameOver() {
    return super.gameOver();
  }

  /**
   * Starts the game and initializes the board.
   *
   * @param rows   the number of rows in the board
   * @param cols   the number of columns in the board
   * @param swaps  the number of swaps allowed in the game
   * @param random true if the board should be setup randomly
   */

  @Override
  public void startGame(int rows, int cols, int swaps, boolean random) {
    super.startGame(rows, cols, swaps, random);
  }

  /**
   * Starts the game and initializes the board.
   *
   * @param board the board to start the game with
   * @param swaps the number of swaps
   */
  @Override
  public void startGame(List<List<Piece>> board, int swaps) {
    super.startGame(board, swaps);
  }

  /**
   * Generates a list of pieces in a specific order.
   *
   * @return a list of pieces.
   */
  @Override
  public Piece[] createListOfPieces() {
    return super.createListOfPieces();
  }


}