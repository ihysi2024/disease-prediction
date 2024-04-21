import java.util.List;

import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.SameGameModel;

public class MockModel implements SameGameModel<Piece> {

  final StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = log;
  }


  @Override
  public void swap(int fromRow, int fromCol, int toRow, int toCol) {
    log.delete(0, log.length());
    log.append("Swapping: (" + fromRow + "," + fromCol + ") and (" + toRow + "," + toCol + ")");
  }

  @Override
  public void removeMatch(int row, int col) {
    log.delete(0, log.length());
    log.append("Removing block at: (" + row + "," + col + ")");
  }

  @Override
  public int width() {
    return 0;
  }

  @Override
  public int length() {
    return 0;
  }

  @Override
  public Piece pieceAt(int row, int col) {
    return null;
  }

  @Override
  public int score() {
    log.append("- Score is: N");
    return 0;
  }

  @Override
  public int remainingSwaps() {
    log.append("- Swaps is: N");
    return 0;
  }

  @Override
  public boolean gameOver() {
    return false;
  }

  @Override
  public void startGame(int rows, int cols, int swaps, boolean random) {

  }

  @Override
  public Piece[] createListOfPieces() {
    return new Piece[0];
  }

  @Override
  public void startGame(List board, int swaps) {

  }
}
