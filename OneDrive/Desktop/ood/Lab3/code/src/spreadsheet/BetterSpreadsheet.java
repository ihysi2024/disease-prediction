package spreadsheet;


/**
 * Represents a better spreadsheet interface.
 */
public interface BetterSpreadsheet extends SpreadSheet {


  /**
   * sets all the cells in a given region to the specified value.
   * @param fromRow starting region row
   * @param toRow ending region row
   * @param fromCol starting region column
   * @param toCol ending region column
   * @param value specified value to set all cells to
   */
  public void rectangular(int fromRow, int toRow, int fromCol, int toCol, int value);


}
