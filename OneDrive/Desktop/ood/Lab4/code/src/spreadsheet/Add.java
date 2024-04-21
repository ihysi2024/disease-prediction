package spreadsheet;
public class Add implements SpreadSheetCommand {
  int row;
  int col;
  int value;
  public Add(int row, int col, int value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }

  public void edit(SpreadSheet ss) {
    ss.set(this.row, this.col, this.value);
  }

}
