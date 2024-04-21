package spreadsheet;

/**
 * Gets rows and columns in the sheet to a given value.
 */
public class Get implements SpreadSheetCommand {

  int row;
  int col;

  /**
   * Sets the value of the sheet rows and columns to a specified number.
   * @param row row of sheet.
   * @param col column of sheet.
   */

  public Get(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Calls the set method for a spreadsheet.
   * @param ss the spreadsheet to change
   */

  @Override

  public void edit(SpreadSheet ss) {

    ss.get(this.row, this.col);
  }
}
