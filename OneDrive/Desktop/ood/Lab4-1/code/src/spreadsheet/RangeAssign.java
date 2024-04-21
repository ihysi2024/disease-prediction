package spreadsheet;

/**
 * Sets a row or column of cells to a range of values.
 * starting at the given value and advancing by the given increment
 */
public class RangeAssign {
  int row;
  int col;
  double value;

  /**
   * Setting the row, column and values for the spreadsheet.
   * @param row the row of the sheet
   * @param col the col of the sheet
   * @param value the value to set the cells to
   */
  public RangeAssign(int row, int col, double value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }

  /**
   * The response to the user move.
   * @param ss the spreadsheet to change
   */
  public void edit(SpreadSheet ss) {
    //
  }
}
