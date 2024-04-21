package spreadsheet;

import java.util.Scanner;

/**
 * Controller that uses the command design.
 */
public class NewSSController extends SpreadSheetController {
  /**
   * Create a controller to work with the specified sheet (model),
   * readable (to take inputs) and appendable (to transmit output).
   *
   * @param sheet      the sheet to work with (the model)
   * @param readable   the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public NewSSController(SpreadSheet sheet, Readable readable, Appendable appendable) {
    super(sheet, readable, appendable);
  }

  protected void processCommand(Scanner sc, SpreadSheet ss) {
    String userMove = sc.next();
    if (userMove.equals("bulk-assign-value")) {
      int row = sc.nextInt();
      int col = sc.nextInt();
      int value = sc.nextInt();
      SpreadSheetCommand bulk = new SpreadSheetBulk(row, col, value);
      bulk.edit(ss);
    }
  }
}
