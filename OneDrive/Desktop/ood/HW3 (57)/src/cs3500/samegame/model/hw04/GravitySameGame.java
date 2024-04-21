package cs3500.samegame.model.hw04;

import java.util.List;

import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.PieceType;

/**
 * Represents a GravitySameGame version of FourPieceSameGame.
 */
public class GravitySameGame extends FourPieceSameGame {

  /**
   * Represents the GravitySameGame version of FourPieceSameGame.
   */

  public GravitySameGame() {
    super();
  }

  /**
   * Swapping two pieces with gravity applied.
   * @param fromRow the row of the first piece (0-based index)
   * @param fromCol the col of the first piece (0-based index)
   * @param toRow the row of the first piece (0-based index)
   * @param toCol the col of the first piece (0-based index)
   */
  @Override
  public void swap(int fromRow, int fromCol, int toRow, int toCol) {
    super.swap(fromRow, fromCol, toRow, toCol);
    gravity();
  }

  /**
   * Removing a block of pieces with gravity applied.
   * @param row the row of the piece to remove (0-based index)
   * @param col the col of the piece to remove (0-based index)
   */
  @Override
  public void removeMatch(int row, int col) {
    super.removeMatch(row, col);
    gravity();
  }

  private void gravity() {
    boolean leftAbove = true;

    while (leftAbove) {
      leftAbove = false;
      for (int rowNum = 0; rowNum < this.rows - 1; rowNum++) {
        for (int colNum = 0; colNum < this.cols; colNum++) {
          if ((super.board.get(rowNum).get(colNum).color() != PieceType.EMPTY)
                  && (super.board.get(rowNum + 1).get(colNum).color() == PieceType.EMPTY)) {
            leftAbove = true ;
            PieceType color = super.board.get(rowNum).get(colNum).color();
            super.board.get(rowNum + 1).set(colNum,
                    new Piece(color, (rowNum + 1), colNum));
            super.board.get(rowNum).set(colNum,
                    new Piece(PieceType.EMPTY, rowNum, colNum));
          }
        }
      }
    }
  }

  /**
   * Represents the AutoMatch board width.
   * @return int representing the board width.
   */
  @Override
  public int width() {
    return super.width();
  }

  /**
   * Represents the AutoMatch board length.
   * @return int representing the board length.
   */
  @Override
  public int length() {
    return super.length();
  }

  /**
   * Represents the piece at a given coordinate.
   * @param row the row in the board (0-based index)
   * @param col the col in the board (0-based index)
   * @return a piece at that coordinate.
   */
  @Override
  public Piece pieceAt(int row, int col) {
    return super.pieceAt(row, col);
  }

  /**
   * Represents the score of the AutoMatch game.
   * @return the int representing the game score.
   */
  @Override
  public int score() {
    return super.score();
  }

  /**
   * Represents the number of remaining swaps in a game.
   * @return the int representing the remaining swaps.
   */
  @Override
  public int remainingSwaps() {
    return super.remainingSwaps();
  }

  /**
   * Determines whether the game is over.
   * @return a boolean of if the game is over.
   */
  @Override
  public boolean gameOver() {
    return super.gameOver();
  }

  /**
   * Starts the game and initializes the board with gravity.
   * @param rows the number of rows in the board
   * @param cols the number of columns in the board
   * @param swaps the number of swaps allowed in the game
   * @param random true if the board should be setup randomly
   */

  @Override
  public void startGame(int rows, int cols, int swaps, boolean random) {
    super.startGame(rows, cols, swaps, random);
    gravity();
  }

  /**
   * Starts the game and initializes the board with gravity.
   * @param board the board to start the game with
   * @param swaps the number of swaps
   */

  @Override
  public void startGame(List<List<Piece>> board, int swaps) {
    super.startGame(board, swaps);
    gravity();
  }

  /**
   * Generate a list of pieces in a specific order.
   * @return a list of pieces.
   */

  @Override
  public Piece[] createListOfPieces() {
    return super.createListOfPieces();
  }

}
