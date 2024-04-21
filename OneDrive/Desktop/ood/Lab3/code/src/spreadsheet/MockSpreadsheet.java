package spreadsheet;

import java.util.Objects;

/**
 * Represents a mock spreadsheet to check correct input translation via controller.
 */
public class MockSpreadsheet implements SpreadSheet {

  final StringBuilder log;

  /**
   * Determines if the correct values have been taken in by the controller.
   * @param log values to pass as input to the controller.
   */
  public MockSpreadsheet(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  /**
   * Dummy function, not relevant for the purpose of this assignment.
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return a double.
   * @throws IllegalArgumentException if less than 0.
   */
  @Override
  public double get(int row, int col) throws IllegalArgumentException {
    return 0.0;
  }

  /**
   * Used to track inputs and if they are correctly used.
   * @param row   the row number of the cell, starting with 0
   * @param col   the column number of the cell, starting at 0
   * @param value the value that this cell must be set to
   * @throws IllegalArgumentException if less than 0.
   */
  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    log.append(String.format(" num1 = %d, num2 = %d\n", row, col));
  }

  /**
   * Dummy function.
   * @param row the row number of the cell, starting with 0
   * @param col the column number of the cell, starting with 0
   * @return a boolean.
   * @throws IllegalArgumentException if less than 0.
   */
  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    return false;
  }

  /**
   * Dummy function.
   * @return an int.
   */
  @Override
  public int getWidth() {
    return 0;
  }

  /**
   * Dummy function.
   * @return an int.
   */

  @Override
  public int getHeight() {
    return 0;
  }

  /**
   * Changes the value of all cells in a rectangular region to the specified value.
   * @param fromRow region of interest starting row
   * @param toRow region of interest ending row
   * @param fromCol region of interest starting column
   * @param toCol region of interest ending column
   * @param value2 value to change cells to.
   */
  @Override
  public void rectangular(int fromRow, int toRow, int fromCol, int toCol, int value2) {
    // not necessary to fill out.

  }
}
