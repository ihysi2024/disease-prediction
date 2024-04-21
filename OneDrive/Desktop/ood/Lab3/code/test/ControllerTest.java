import org.junit.Assert;
import org.junit.Test;


import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import spreadsheet.MockSpreadsheet;
import spreadsheet.NewSpreadSheet;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetController;

/**
 * Used to test the controllers.
 */
public class ControllerTest {

  /**
   * Tests that the correct messages are being returned.
   */
  @Test
  public void testMessage() {
    Appendable ap = new StringBuilder();
    Readable rd = new StringReader(ap.toString());
    SpreadSheet model = new SparseSpreadSheet();
    SpreadSheetController controller = new SpreadSheetController(model, rd, ap);
    controller.control();

    // take out last line where program quits immediately
    String origStr = ap.toString();
    String[] arrOfStr = origStr.split("\n");
    String newStr = "";
    //System.out.println(arrOfStr.length);
    for (int idx = 0; idx < (arrOfStr.length - 1); idx++) {
      newStr = newStr + arrOfStr[idx] + "\n";
    }

    newStr = newStr.substring(0, 264);

    String farewellMsg = arrOfStr[(arrOfStr.length - 1)];

    Assert.assertEquals("Welcome to the spreadsheet program!\n"
            + "Supported user instructions are: \n"
            + "assign-value row-num col-num value (set a cell to a value)\n"
            + "print-value row-num col-num (print the value at a given cell)\n"
            + "menu (Print supported instruction list)\n"
            + "q or quit (quit the program)", newStr);

    Assert.assertEquals("Thank you for using this program!", farewellMsg);
  }

  /**
   * Tests that the correct inputs are being used.
   * @throws IOException idk why.
   */
  @Test
  public void testInputs() throws IOException {
    StringBuilder log = new StringBuilder("assign-value a 3 4 0.0");

    Readable rd = new StringReader(log.toString());
    Scanner sc = new Scanner(rd);

    StringBuilder dontCareOutput = new StringBuilder();
    SpreadSheet ss = new MockSpreadsheet(log);
    SpreadSheetController controller1 = new SpreadSheetController(ss, rd, dontCareOutput);
    controller1.control();
    Assert.assertEquals("assign-value a 3 4 0.0 num1 = 0, num2 = 2\n", log.toString());
  }

  /**
   * Tests that the correct input is being used.
   * @throws IOException idk why.
   */
  @Test
  public void testBetterSpreadsheet() throws IOException {
    StringBuilder log = new StringBuilder("bulk-assign A 1 B 4 100");

    Readable rd = new StringReader(log.toString());
    StringBuilder dontCareOutput = new StringBuilder();

    SpreadSheet ss = new NewSpreadSheet(log);

    SpreadSheetController controller1 = new SpreadSheetController(ss, rd, dontCareOutput);
    controller1.control();
    Assert.assertEquals("changing cells in the range (0,1), (1,4) to 100", log.toString());
  }


}
