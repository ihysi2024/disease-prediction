package spreadsheet;

/**
 * Sets rows and columns in the sheet to a given value.
 */
public class Add implements SpreadSheetCommand {

  int row;
  int col;
  double value;

  /**
   * Sets the value of the sheet rows and columns to a specified number.
   * @param row row of sheet.
   * @param col column of sheet.
   * @param value value to set the row and column to.
   */

  public Add(int row, int col, double value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }

  /**
   * Calls the set method for a spreadsheet.
   * @param ss the spreadsheet to change
   */
  @Override
  public void edit(SpreadSheet ss) {
    ss.set(this.row, this.col, this.value);
  }
}
