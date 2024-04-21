package spreadsheet;

import java.util.Objects;

/**
 * Used to test the implementation of BetterSpreadsheet.
 */
public class NewSpreadSheet implements BetterSpreadsheet {
  StringBuilder log;

  /**
   * Used to track user input to determine if the new method is used properly.
   * @param log user input.
   */
  public NewSpreadSheet(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  /**
   * Dummy function.
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return a double.
   * @throws IllegalArgumentException if less than 0.
   */
  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    return 0;
  }

  /**
   * Dummy function.
   * @param row   the row number of the cell, starting with 0
   * @param col   the column number of the cell, starting at 0
   * @param value the value that this cell must be set to
   * @throws IllegalArgumentException if less than 0.
   */
  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    //nothing here.
  }

  /**
   * Dummy function.
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return a boolean
   * @throws IllegalArgumentException if less than 0.
   */
  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    return false;
  }

  /**
   * Dummy function.
   * @return an integer
   */
  @Override
  public int getWidth() {
    return 0;
  }

  /**
   * Dummy function.
   * @return an integer.
   */
  @Override
  public int getHeight() {
    return 0;
  }

  /**
   * Used to display that the user has input information correctly.
   * @param fromRow starting region row
   * @param toRow ending region row
   * @param fromCol starting region column
   * @param toCol ending region column
   * @param value specified value to set all cells to
   */
  @Override
  public void rectangular(int fromRow, int toRow, int fromCol, int toCol, int value) {
    this.log.delete(0, this.log.length());
    this.log.append("changing cells in the range " + "(" + fromRow + "," + fromCol + "), "
            + "(" + toRow + "," + toCol + ") to " + value);
  }
}
