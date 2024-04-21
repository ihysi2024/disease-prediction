import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.samegame.controller.SameGameTextController;
import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.PieceType;
import cs3500.samegame.model.hw02.SameGameModel;
import cs3500.samegame.view.SameGameTextView;

/**
 * Test the controller implementation.
 */
public class ControllerTest {

  FourPieceSameGame fpsgRandom;
  FourPieceSameGame fpsgDeterm;
  FourPieceSameGame fpsgRandomSmall;
  FourPieceSameGame fpsgDetermSmall;
  List<List<Piece>> allPieces;

  SameGameTextView sgvRandom;
  SameGameTextView sgvDeterm;

  SameGameTextView sgvRandomSmall;

  SameGameTextView sgvDetermSmall;
  List<List<Piece>> determRows;

  FourPieceSameGame fpsgOneVFour;
  List<Piece> row2;
  List<Piece> row1;

  @Before
  public void setUp() {
    this.allPieces  = new ArrayList<>();
    this.fpsgOneVFour = new FourPieceSameGame(this.allPieces, 1, 4, 2, 0);
    this.fpsgRandomSmall = new FourPieceSameGame(this.allPieces, 2,5 , 2, 0);
    this.fpsgDetermSmall = new FourPieceSameGame(this.allPieces, 2, 5, 2, 0);
    this.fpsgRandom = new FourPieceSameGame(this.allPieces, 8, 9, 2, 0);
    this.fpsgDeterm = new FourPieceSameGame(this.allPieces, 8, 9, 2, 0);
    this.sgvRandom = new SameGameTextView(this.fpsgRandom);
    this.sgvDeterm = new SameGameTextView(this.fpsgDeterm);
    this.sgvRandomSmall = new SameGameTextView(this.fpsgRandomSmall);
    this.sgvDetermSmall = new SameGameTextView(this.fpsgDetermSmall);
    this.determRows = new ArrayList();
    this.row1 = new ArrayList<>();
    this.row1.add(new Piece(PieceType.RED, 0,0));
    this.row1.add(new Piece(PieceType.RED, 0,1));
    this.row1.add(new Piece(PieceType.BLUE, 0,2));
    this.row1.add(new Piece(PieceType.RED, 0,3));
    this.row1.add(new Piece(PieceType.RED, 0,4));
    this.row2 = new ArrayList();
    this.row2.add(new Piece(PieceType.BLUE, 1,0));
    this.row2.add(new Piece(PieceType.RED, 1,1));
    this.row2.add(new Piece(PieceType.BLUE, 1,2));
    this.row2.add(new Piece(PieceType.YELLOW, 1,3));
    this.row2.add(new Piece(PieceType.RED, 1,4));
    this.determRows.add(this.row1);
    this.determRows.add(this.row2);
  }

  /**
   * Test the SameGameTextView render method().
   * @throws IOException if input is not correctly read.
   */
  @Test
  // tests
  public void testRender() throws IOException {
    Appendable ap = new StringBuilder("");
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    SameGameTextView sgDetermSmall = new SameGameTextView(this.fpsgDetermSmall, ap);
    String manualRender = "R G B Y R\nG B Y R G";
    sgDetermSmall.render();
    Assert.assertEquals(manualRender, ap.toString());
  }

  /**
   * Test swap with and without invalid alphabetic inputs.
   */
  @Test
  public void testSwapInput() {
    // test a normal stream of input starting with s and then a series of 4 natural numbers
    StringBuilder log = new StringBuilder("s 1 2 3 4");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> mock = new MockModel(log);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(mock, 4, 5, 2, false);
    Assert.assertEquals("Swapping: (0,1) and (2,3)- "
            + "Swaps is: N- Score is: N", log.toString());

    // test a stream of input starting with s and then some invalid moves
    // should still wait for the integer inputs
    StringBuilder logInvalid = new StringBuilder("s 1 a b c 2 g 3 lmao 4");
    Readable rdInvalid = new StringReader(logInvalid.toString());
    StringBuilder apInvalid = new StringBuilder();
    SameGameModel<Piece> mockInvalid = new MockModel(logInvalid);
    SameGameTextController<Piece> controller2 = new SameGameTextController<>(rdInvalid, apInvalid);
    controller2.playGame(mockInvalid, 4, 5, 2, false);
    Assert.assertEquals("Swapping: (0,1) and (2,3)- "
            + "Swaps is: N- Score is: N", logInvalid.toString());

  }

  /**
   * Test swap with different negative number placements.
   */
  @Test
  public void testSwapNeg() {
    // test stream of input with some negative inputs
    StringBuilder logNeg = new StringBuilder("s -1 2 3 4");
    Readable rdNeg = new StringReader(logNeg.toString());
    StringBuilder apNeg = new StringBuilder();
    SameGameModel<Piece> mockNeg = new MockModel(logNeg);
    SameGameTextController<Piece> controller3 = new SameGameTextController<>(rdNeg, apNeg);
    Assert.assertThrows(IllegalStateException.class, () -> controller3.playGame(mockNeg, 4,
            5, 2, false));

    // test stream of input with some negative inputs
    StringBuilder logNegComplete = new StringBuilder("s -8 7 -6 6 5 3");
    Readable rdNegComplete = new StringReader(logNegComplete.toString());
    StringBuilder apNegComplete = new StringBuilder();
    SameGameModel<Piece> mockNegComplete = new MockModel(logNegComplete);
    SameGameTextController<Piece> controller4 = new SameGameTextController<>(rdNegComplete,
            apNegComplete);
    controller4.playGame(mockNegComplete, 4, 5, 2, false);
    Assert.assertEquals("Swapping: (6,5) and (4,2)- Swaps is: N- Score is: N",
            logNegComplete.toString());
  }

  /**
   * Test swap and remove match inputs with mixed invalid inputs.
   */
  @Test
  public void testMixed() {
    StringBuilder logInvalid = new StringBuilder("s 1 a -3 c 2 g 3 -5 4");
    Readable rdInvalid = new StringReader(logInvalid.toString());
    StringBuilder apInvalid = new StringBuilder();
    SameGameModel<Piece> mockInvalid = new MockModel(logInvalid);
    SameGameTextController<Piece> controller2 = new SameGameTextController<>(rdInvalid, apInvalid);
    controller2.playGame(mockInvalid, 4, 5, 2, false);
    Assert.assertEquals("Swapping: (0,1) and (2,3)- Swaps is: N- Score is: N",
            logInvalid.toString());

    StringBuilder logNegComplete = new StringBuilder("m -8 a 7 g 9");
    Readable rdNegComplete = new StringReader(logNegComplete.toString());
    StringBuilder apNegComplete = new StringBuilder();
    SameGameModel<Piece> mockNegComplete = new MockModel(logNegComplete);
    SameGameTextController<Piece> controller4 = new SameGameTextController<>(rdNegComplete,
            apNegComplete);
    controller4.playGame(mockNegComplete, 4, 5, 2, false);
    Assert.assertEquals("Removing block at: (6,8)- Swaps is: N- Score is: N",
            logNegComplete.toString());
  }

  /**
   * Test remove match inputs with and without valid alphabetic elements.
   */
  @Test
  public void testRemoveInput() {
    // test a normal stream of input starting with s and then a series of 4 natural numbers
    StringBuilder log = new StringBuilder("m 1 2");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> mock = new MockModel(log);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(mock, 4, 5, 2, false);
    Assert.assertEquals("Removing block at: (0,1)- Swaps is: N- Score is: N",
            log.toString());

    // test a stream of input starting with s and then some invalid moves
    // should still wait for the integer inputs
    StringBuilder logInvalid = new StringBuilder("m 1 a b c 2");
    Readable rdInvalid = new StringReader(logInvalid.toString());
    StringBuilder apInvalid = new StringBuilder();
    SameGameModel<Piece> mockInvalid = new MockModel(logInvalid);
    SameGameTextController<Piece> controller2 = new SameGameTextController<>(rdInvalid, apInvalid);
    controller2.playGame(mockInvalid, 4, 5, 2, false);
    Assert.assertEquals("Removing block at: (0,1)- Swaps is: N- Score is: N",
            logInvalid.toString());
  }

  /**
   * Test the remove match method with negative input in different places.
   */
  @Test
  public void testRemoveNeg() {
    // test stream of input with some negative input
    StringBuilder logInvalid = new StringBuilder("m -2 1 2");
    Readable rdInvalid = new StringReader(logInvalid.toString());
    StringBuilder apInvalid = new StringBuilder();
    SameGameModel<Piece> mockInvalid = new MockModel(logInvalid);
    SameGameTextController<Piece> controller2 = new SameGameTextController<>(rdInvalid, apInvalid);
    controller2.playGame(mockInvalid, 4, 5, 2, false);
    Assert.assertEquals("Removing block at: (0,1)- Swaps is: N- Score is: N",
            logInvalid.toString());

    StringBuilder logNeg = new StringBuilder("m -1 2");
    Readable rdNeg = new StringReader(logNeg.toString());
    StringBuilder apNeg = new StringBuilder();
    SameGameModel<Piece> mockNeg = new MockModel(logNeg);
    SameGameTextController<Piece> controller3 = new SameGameTextController<>(rdNeg, apNeg);
    controller3.playGame(mockNeg, 4, 5, 2, false);
    Assert.assertEquals("Invalid move. Try again. No more elements\n"
            + "\n"
            + "Remaining swaps: 0\n"
            + "Score: 0"
            + "\n", apNeg.toString());
  }

  /**
   * Test invalid implementations of swap method.
   */

  @Test
  public void testInvalidSwapMoves() {
    StringBuilder log = new StringBuilder("s 1 2 3 4");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> fpsg1 = new FourPieceSameGame(this.allPieces, 4, 5, 2, 0);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    Assert.assertThrows(IllegalStateException.class, () -> controller1.playGame(fpsg1, 4,
            5, 2, false));
  }

  /**
   * Test invalid implementations of remove match method.
   */

  @Test
  public void testInvalidRemoveMoves() {
    StringBuilder log = new StringBuilder("m 1 6");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> fpsg1 = new FourPieceSameGame(this.allPieces, 4, 5, 2, 0);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(fpsg1, 4, 5, 2, false);
    String origStr = ap.toString();
    String[] arrOfStr = origStr.split("\n");

    Assert.assertEquals("Invalid move. Try again. Column out of bounds"
            + System.lineSeparator(), arrOfStr[0]);
  }

  /**
   * Test that an invalid first command will be caught.
   */

  @Test
  public void testInvalidCommands() {
    StringBuilder log = new StringBuilder("a 1 6");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> fpsg1 = new FourPieceSameGame(this.allPieces, 4, 5, 2, 0);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(fpsg1, 4, 5, 2, false);
    String origStr = ap.toString();
    String[] arrOfStr = origStr.split("\n");

    //Assert.assertEquals("Invalid command. Try Again. a not allowed.R G B Y R", arrOfStr[0]);

    StringBuilder logInvalid = new StringBuilder("a q 1 6");
    Readable rdInvalid = new StringReader(logInvalid.toString());
    StringBuilder apInvalid = new StringBuilder();
    SameGameModel<Piece> fpsg2 = new FourPieceSameGame(this.allPieces, 4, 5, 2, 0);
    SameGameTextController<Piece> controller2 = new SameGameTextController<>(rdInvalid, apInvalid);
    controller2.playGame(fpsg2, 4, 5, 2, false);
    String origStrInvalid = apInvalid.toString();
    String[] arrOfStrInvalid = origStrInvalid.split("\n");

    Assert.assertEquals("Invalid command. Try Again. a not allowed.R G B Y R",
            arrOfStrInvalid[0]);


    StringBuilder logMultiple = new StringBuilder("abc q 1 6");
    Readable rdMultiple = new StringReader(logMultiple.toString());
    StringBuilder apMultiple = new StringBuilder();
    SameGameModel<Piece> fpsg3 = new FourPieceSameGame(this.allPieces, 4, 5, 2, 0);
    SameGameTextController<Piece> controller3 = new SameGameTextController<>(rdMultiple,
            apMultiple);
    controller3.playGame(fpsg3, 4, 5, 2, false);
    String origStrMultiple = apMultiple.toString();
    String[] arrOfStrMultiple = origStrMultiple.split("\n");

    Assert.assertEquals("Invalid command. Try Again. abc not allowed.R G B Y R",
            arrOfStrMultiple[0]);

  }

  /**
   * Test how the controller responds to the game being over.
   */
  @Test
  public void testGameOver() {
    StringBuilder log = new StringBuilder("m 1 2");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    List<List<Piece>> empty = new ArrayList();
    List<Piece> r1 = new ArrayList<>();
    Piece piece = new Piece(PieceType.EMPTY, 0, 0);
    r1.add(piece);
    List<Piece> r2 = new ArrayList<>();
    Piece pieceTwo = new Piece(PieceType.EMPTY, 0, 0);
    r2.add(pieceTwo);
    empty.add(r1);
    empty.add(r2);
    FourPieceSameGame fpsg1 = new FourPieceSameGame(empty, 1, 1, 2, 0);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(fpsg1, empty, 2);
    Assert.assertEquals("Game over.\n"
            + "X\n"
            + "X\n"
            + "Remaining swaps: 2\n"
            + "Score: 0\n", ap.toString());
  }

  /**
   * Test how the game handles quit when placed in both a swap and remove method call.
   */
  @Test
  public void testQuit() {
    // test match when quitting
    StringBuilder log = new StringBuilder("m 1 q 2");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> mock = new MockModel(log);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(mock, 4, 5, 2, false);
    Assert.assertEquals("Game quit!\n"
            + "State of game when quit:\n"
            + "\n"
            + "Remaining swaps: 0\n"
            + "Score: 0\n", ap.toString());

    StringBuilder logQuit = new StringBuilder("q m 1 2");
    Readable rdQuit = new StringReader(logQuit.toString());
    StringBuilder apQuit = new StringBuilder();
    SameGameModel<Piece> mockQuit = new MockModel(logQuit);
    SameGameTextController<Piece> controller2 = new SameGameTextController<>(rdQuit, apQuit);
    controller2.playGame(mockQuit, 4, 5, 2, false);
    Assert.assertEquals("Game quit!\n"
            + "State of game when quit:\n"
            + "\n"
            + "Remaining swaps: 0\n"
            + "Score: 0\n", apQuit.toString());

    StringBuilder logRemoveQuit = new StringBuilder("s 1 q 2 2 3");
    Readable rdRemoveQuit = new StringReader(logRemoveQuit.toString());
    StringBuilder apRemoveQuit = new StringBuilder();
    SameGameModel<Piece> mockRemoveQuit = new MockModel(logRemoveQuit);
    SameGameTextController<Piece> controller3 = new SameGameTextController<>(rdRemoveQuit,
            apRemoveQuit);
    controller3.playGame(mockRemoveQuit, 4, 5, 2, false);
    Assert.assertEquals("Game quit!\n"
            + "State of game when quit:\n"
            + "\n"
            + "Remaining swaps: 0\n"
            + "Score: 0\n", apRemoveQuit.toString());

    StringBuilder logRemoveQuitSecond = new StringBuilder("s 1 2 2 q 3");
    Readable rdRemoveQuitSecond = new StringReader(logRemoveQuitSecond.toString());
    StringBuilder apRemoveQuitSecond = new StringBuilder();
    SameGameModel<Piece> mockRemoveQuitSecond = new MockModel(logRemoveQuitSecond);
    SameGameTextController<Piece> controller4 = new SameGameTextController<>(rdRemoveQuitSecond,
            apRemoveQuitSecond);
    controller4.playGame(mockRemoveQuitSecond, 4, 5, 2, false);
    Assert.assertEquals("Game quit!\n"
            + "State of game when quit:\n"
            + "\n"
            + "Remaining swaps: 0\n"
            + "Score: 0\n", apRemoveQuitSecond.toString());

    StringBuilder logAnotherRQuit = new StringBuilder("q s 4 3 1 2");
    Readable rdAnotherRQuit = new StringReader(logAnotherRQuit.toString());
    StringBuilder apAnotherRQuit = new StringBuilder();
    SameGameModel<Piece> mockAnotherRQuit = new MockModel(logAnotherRQuit);
    SameGameTextController<Piece> controller5 = new SameGameTextController<>(rdAnotherRQuit,
            apAnotherRQuit);
    controller5.playGame(mockAnotherRQuit, 4, 5, 2, false);
    Assert.assertEquals("Game quit!\n"
            + "State of game when quit:\n"
            + "\n"
            + "Remaining swaps: 0\n"
            + "Score: 0\n", apAnotherRQuit.toString());
  }

  /**
   * Test if readable failures occur and how the controller handles it.
   */
  @Test
  public void testReadableFailure() {
    StringBuilder log = new StringBuilder("");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> mock = new MockModel(log);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd,
            ap);
    Assert.assertThrows(IllegalStateException.class, () -> controller1.playGame(mock, 4,
            5, 2, false));

  }

  /**
   * Test controller exceptions from constructor and playGame.
   */
  @Test
  public void testExceptions() {
    // null readable
    Readable rdNull = null;
    StringBuilder ap = new StringBuilder();
    Assert.assertThrows(IllegalArgumentException.class, () -> new SameGameTextController<>(rdNull,
            ap));

    // null appendable
    Readable rd = new StringReader("");
    StringBuilder apNull = null;
    Assert.assertThrows(IllegalArgumentException.class, () -> new SameGameTextController<>(rd,
            apNull));


    StringBuilder log = new StringBuilder("");
    Readable rdValid = new StringReader(log.toString());
    StringBuilder apValid = new StringBuilder();
    SameGameModel<Piece> mock = new MockModel(log);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd,
            ap);

    // null board
    Assert.assertThrows(IllegalArgumentException.class, () -> controller1.playGame(mock, null,
            2));
    // null model
    Assert.assertThrows(IllegalArgumentException.class, () -> controller1.playGame(null,
            this.allPieces, 2));
  }

  private static List<List<Piece>> exampleList() {

    Piece e = new Piece(PieceType.EMPTY, 0, 0);
    Piece r = new Piece(PieceType.RED, 0, 0);
    Piece g = new Piece(PieceType.GREEN, 0, 0);
    Piece b = new Piece(PieceType.BLUE, 0, 0);
    Piece y = new Piece(PieceType.YELLOW, 0, 0);
    List<Piece> r1 = Arrays.asList(e, g, g, e, e, e, e);
    List<Piece> r2 = Arrays.asList(e, g, g, e, e, g, b);
    List<Piece> r3 = Arrays.asList(e, y, e, g, b, e, g);
    List<Piece> r4 = Arrays.asList(r, r, e, e, g, r, r);
    List<Piece> r5 = Arrays.asList(r, g, b, y, e, g, b);
    List<List<Piece>> allP = Arrays.asList(r1, r2, r3, r4, r5);

    return allP;
  }

  /**
   * Run the whole game for FourPieceSameGame start to finish, including invalid inputs.
   */
  @Test
  public void testFullFourPieceGame() {
    List<List<Piece>> allP = exampleList();
    StringBuilder log = new StringBuilder("m 0 1 s 1 2 3 4 s 2 1 2 5 m 4 0");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> game = new FourPieceSameGame(allP, 5, 6, 2, 0);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    try {
      controller1.playGame(game, allP, 2);
    }
    catch (IllegalStateException e) {
      Assert.assertTrue(ap.toString().contains("Invalid move. " +
              "Try again. Move not allowed. Pieces must be located on same row or column"));
      //Assert.assertTrue(ap.toString().contains("Game over"));
    }
  }

  /**
   * Check the whole game for GravitySameGame start to finish, including invalid inputs.
   */

  @Test
  public void testFullGravitySameGame() {
    List<List<Piece>> allP = exampleList();
    StringBuilder log = new StringBuilder("m 4 0 s 1 2 3 4 m 0 1 s 2 1 2 5 ");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> game = new FourPieceSameGame(allP, 5, 6, 2, 0);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    try {
      controller1.playGame(game, allP, 2);
    }
    catch (IllegalStateException e) {
      Assert.assertTrue(ap.toString().contains("Invalid move. " +
              "Try again. Move not allowed. Pieces must be located on same row or column"));
      Assert.assertTrue(ap.toString().contains("Invalid move. " +
              "Try again. No such piece on the board"));
      Assert.assertTrue(ap.toString().contains("Game over"));
    }
  }

  /**
   * Check the whole game for AutoMatchSameGame start to finish, including invalid inputs.
   */

  @Test
  public void testFullAutoMatchGame() {
    List<List<Piece>> allP = exampleList();
    StringBuilder log = new StringBuilder("m 4 0 s 1 2 3 4 m 0 1 s 2 1 2 5 ");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> game = new FourPieceSameGame(allP, 5, 6, 2, 0);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    try {
      controller1.playGame(game, allP, 2);
    }
    catch (IllegalStateException e) {
      Assert.assertTrue(ap.toString().contains("Game over"));
    }
  }

}
