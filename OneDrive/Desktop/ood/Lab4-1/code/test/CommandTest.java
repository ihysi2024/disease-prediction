import java.io.StringReader;


import spreadsheet.NewSSController;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetBulk;
import spreadsheet.SpreadSheetCommand;

/**
 * Tests the command design.
 */
public class CommandTest {

  /**
   * Tests the commands in the command design.
   */
  public void testCommand() {
    SpreadSheet ss = new SparseSpreadSheet();
    SpreadSheetCommand cmds = new SpreadSheetBulk(2, 2, 2);

    Appendable ap = new StringBuilder();
    Readable rd = new StringReader(ap.toString());
    NewSSController controller1 = new NewSSController(ss,rd,ap);
    controller1.control();

  }
}
