import java.util.List;

import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;

/**
 * Represents a mock model for testing purposes.
 */
public class MockModel implements SameGameModel<Piece> {

  final StringBuilder log;

  /**
   * Represents a mock model for testing purposes.
   * @param log input to be tested.
   */
  public MockModel(StringBuilder log) {
    this.log = log;
  }

  /**
   * Tests if swap is called correctly.
   * @param fromRow the row of the first piece (0-based index)
   * @param fromCol the col of the first piece (0-based index)
   * @param toRow   the row of the first piece (0-based index)
   * @param toCol   the col of the first piece (0-based index)
   */
  @Override
  public void swap(int fromRow, int fromCol, int toRow, int toCol) {
    log.delete(0, log.length());
    log.append("Swapping: (" + fromRow + "," + fromCol + ") and (" + toRow + "," + toCol + ")");
  }

  /**
   * Tests if removeMatch is called correctly.
   * @param row the row of the piece to remove (0-based index)
   * @param col the col of the piece to remove (0-based index)
   */
  @Override
  public void removeMatch(int row, int col) {
    log.delete(0, log.length());
    log.append("Removing block at: (" + row + "," + col + ")");
  }

  /**
   * Not relevant for testing.
   * @return dummy output
   */
  @Override
  public int width() {
    return 0;
  }

  /**
   * Not relevant for testing.
   * @return dummy output
   */
  @Override
  public int length() {
    return 0;
  }

  /**
   * Not relevant for testing.
   * @param row the row in the board (0-based index)
   * @param col the col in the board (0-based index)
   * @return dummy output
   */
  @Override
  public Piece pieceAt(int row, int col) {
    return null;
  }

  /**
   * Test if score is called correctly.
   * @return dummy output.
   */
  @Override
  public int score() {
    log.append("- Score is: N");
    return 0;
  }

  /**
   * Test if remainingSwaps is called correctly.
   * @return dummy output
   */
  @Override
  public int remainingSwaps() {
    log.append("- Swaps is: N");
    return 0;
  }

  /**
   * Not relevant for testing.
   * @return dummy output
   */
  @Override
  public boolean gameOver() {
    return false;
  }

  /**
   * Not relevant for testing.
   * @return dummy output
   */
  @Override
  public Piece[] createListOfPieces() {
    return new Piece[0];
  }

  /**
   * Not relevant for testing.
   * @param board the board to start the game with
   * @param swaps the number of swaps
   */
  public void startGame(List<List<Piece>> board, int swaps) {
  // dummy output
  }

  /**
   * Not relevant for testing.
   * @param rows   the number of rows in the board
   * @param cols   the number of columns in the board
   * @param swaps  the number of swaps allowed in the game
   * @param random true if the board should be setup randomly
   */

  public void startGame(int rows, int cols, int swaps, boolean random) {
    // dummy output
  }

}
