package spreadsheet;

/**
 * Averages rows and columns in the sheet.
 */
public class Avg {
  int row;
  int col;
  double value;

  /**
   * Averages the rows and columns.
   * @param row row of sheet.
   * @param col column of sheet.
   * @param value value to set the row and column to.
   */
  public Avg(int row, int col, double value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }

  /**
   * Calls the average method for a spreadsheet.
   * @param ss the spreadsheet to change
   */
  public void edit(SpreadSheet ss) {
    // average
  }
}
