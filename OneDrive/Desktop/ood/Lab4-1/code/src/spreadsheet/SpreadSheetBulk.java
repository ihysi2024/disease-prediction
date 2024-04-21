package spreadsheet;

import java.util.HashMap;

/**
 * Sets up the command design.
 */
public class SpreadSheetBulk implements SpreadSheetCommand {
  int row;
  int col;
  double value;

  /**
   * Sets up the command design.
   * @param row the row of the spreadsheet
   * @param col the column of the spreadsheet
   * @param value the new values of the spreadsheet
   */
  public SpreadSheetBulk(int row, int col, double value) {
    if ((row < 0 ) || (col < 0)) {
      throw new IllegalArgumentException("Cells out of range");
    }
    this.row = row;
    this.col = col;
    this.value = value;
  }

  /**
   * The overarching method for the command design.
   * @param ss the spreadsheet to change.
   */
  @Override
  public void edit(SpreadSheet ss) {
    SpreadSheetCommand get = new Get(row, col);
    SpreadSheetCommand set = new Add(row, col, value);
    HashMap<String, SpreadSheetCommand> cmds = new HashMap<>();
    cmds.put("get", get);
    cmds.put("set", set);
  }
}
