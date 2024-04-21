import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw04.GravitySameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.PieceType;
import cs3500.samegame.view.SameGameTextView;

/**
 * Represents tests for GravitySameGame.
 */

public class GravitySameGameTest extends FourPieceSameGameTest {
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
  GravitySameGame gsgDetermSmall;
  GravitySameGame gsgRandomSmall;
  GravitySameGame gsgDeterm;
  GravitySameGame gsgRandom;

  /**
   * Allows a standard set of models to be generated freshly for each test.
   */
  @Before
  public void setUp() {
    this.allPieces  = new ArrayList<>();
    this.fpsgOneVFour = new FourPieceSameGame(this.allPieces, 1, 4, 2, 0);
    this.fpsgRandomSmall = new FourPieceSameGame(this.allPieces, 5,7, 2, 0);
    this.fpsgDetermSmall = new FourPieceSameGame(this.allPieces, 5, 7, 2, 0);
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
    this.gsgDetermSmall = new GravitySameGame();
    this.gsgRandomSmall = new GravitySameGame();
  }

  /**
   * Tests valid gravity functionality at the start of the game.
   */
  @Test
  public void testGravityStartGame() {
    List<List<Piece>> allP = exampleList();

    this.gsgDetermSmall.startGame(allP, 2);

    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(0, 2));
    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(1, 2));
    Assert.assertEquals(PieceType.BLUE, this.gsgDetermSmall.pieceAt(2, 2).color());
    Assert.assertEquals(PieceType.GREEN, this.gsgDetermSmall.pieceAt(3, 2).color());
    Assert.assertEquals(PieceType.GREEN, this.gsgDetermSmall.pieceAt(3, 3).color());
    Assert.assertEquals(PieceType.GREEN, this.gsgDetermSmall.pieceAt(4, 4).color());
    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(2, 3));
  }

  /**
   * Tests valid gravity functionality after removing a block.
   */
  @Test
  public void testGravityRemove() {
    List<List<Piece>> allP = exampleList();

    this.gsgDetermSmall.startGame(allP, 2);

    Assert.assertEquals(PieceType.GREEN, this.gsgDetermSmall.pieceAt(0, 1).color());
    Assert.assertEquals(PieceType.RED, this.gsgDetermSmall.pieceAt(1, 1).color());

    this.gsgDetermSmall.removeMatch(4, 0);

    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(4,0));
    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(0, 1));
    Assert.assertEquals(PieceType.GREEN, this.gsgDetermSmall.pieceAt(1, 1).color());
    Assert.assertEquals(PieceType.RED, this.gsgDetermSmall.pieceAt(2, 1).color());

  }

  /**
   * Tests correct extension of FourPieceSameGame width().
   */

  @Test
  public void testGravityWidth() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    this.gsgDetermSmall.startGame(this.determRows, 2);
    Assert.assertEquals(2, this.gsgDetermSmall.width());
  }

  /**
   * Tests correct extension of FourPieceSameGame length().
   */

  @Test
  public void testGravityLength() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    this.gsgDetermSmall.startGame(this.determRows, 2);
    Assert.assertEquals(5, this.gsgDetermSmall.length());

  }

  /**
   * Tests correct extension of FourPieceSameGame pieceAt().
   */

  @Test
  public void testGravityPieceAt() {
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    this.gsgDetermSmall.startGame(2, 5, 2, false);
    // Check correct return of piece at specified position
    Assert.assertEquals(PieceType.RED, this.gsgDetermSmall.pieceAt(0,0).color());
    Assert.assertEquals(PieceType.RED, this.gsgDetermSmall.pieceAt(0,4).color());
    Assert.assertEquals(PieceType.GREEN, this.gsgDetermSmall.pieceAt(0,1).color());
    Assert.assertEquals(PieceType.GREEN, this.gsgDetermSmall.pieceAt(1,0).color());
    // check if an error will be thrown for a piece out of bounds
    Assert.assertThrows(IllegalArgumentException.class, () -> this.gsgDetermSmall.pieceAt(6, 3));
    FourPieceSameGame fpsg1 = new FourPieceSameGame(this.allPieces, 2, 5, 2, 0);
    GravitySameGame gsg = new GravitySameGame();
    List<List<Piece>> l1 = new ArrayList<>();
    List<Piece> r1 = Arrays.asList(null, null, null, null, null, null, null);
    List<Piece> r2 = Arrays.asList(null, null, null, null, null, null, null);
    l1.add(r1);
    l1.add(r2);
    // check an error will be thrown when the game hasn't started
    Assert.assertThrows(IllegalStateException.class, () -> gsg.pieceAt(0, 0));
    fpsg1.startGame(l1, 2);
    // check null returns for a null piece
    Assert.assertEquals(null, fpsg1.pieceAt(0,0));
  }

  /**
   * Tests correct extension of FourPieceSameGame remainingSwaps().
   */
  @Test
  public void testGravitySwaps() {
    this.fpsgDetermSmall.startGame(8, 9, 2, false);
    this.gsgDetermSmall.startGame(8, 9, 2, false);
    Assert.assertEquals(2, this.gsgDetermSmall.remainingSwaps());
    this.gsgDetermSmall.swap(0, 1, 0, 2);
    Assert.assertEquals(1, this.gsgDetermSmall.remainingSwaps());
    this.gsgDetermSmall.swap(0, 3, 0, 4);
    Assert.assertEquals(0, this.gsgDetermSmall.remainingSwaps());
  }

  /**
   * Tests correct extension of FourPieceSameGame gameOver().
   */
  @Test
  public void testGravityEmpty() {
    this.fpsgDetermSmall.startGame(this.determRows, 3);
    this.gsgDetermSmall.startGame(this.determRows, 3);
    for (int row = 0; row < this.gsgDetermSmall.width(); row++) {
      for (int col = 0; col < this.gsgDetermSmall.length(); col++) {
        this.gsgDetermSmall.pieceAt(row, col).changeColor(PieceType.EMPTY);
      }
    }
    Assert.assertTrue(this.gsgDetermSmall.gameOver());
  }

  /**
   * Tests if the game is over if there is no way the user could score.
   */
  @Test
  public void testRemainingMoves() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    this.gsgDetermSmall.startGame(this.determRows, 2);
    this.gsgDetermSmall.swap(0, 0, 0, 1);
    this.gsgDetermSmall.pieceAt(0, 1).changeColor(PieceType.GREEN);
    this.gsgDetermSmall.pieceAt(0, 2).changeColor(PieceType.EMPTY);
    this.gsgDetermSmall.pieceAt(0, 3).changeColor(PieceType.EMPTY);
    this.gsgDetermSmall.pieceAt(0,4).changeColor(PieceType.RED);
    this.gsgDetermSmall.pieceAt(1,2).changeColor(PieceType.YELLOW);
    this.gsgDetermSmall.pieceAt(1,3).changeColor(PieceType.GREEN);
    this.gsgDetermSmall.pieceAt(1,4).changeColor(PieceType.EMPTY);
    SameGameTextView sgd = new SameGameTextView(this.gsgDetermSmall);
    Assert.assertFalse(this.gsgDetermSmall.gameOver());
  }

  /**
   * Tests if a list of piece is generated in the correct order.
   */

  @Test
  public void testGravityListofPieces() {
    Assert.assertEquals(4, this.gsgDetermSmall.createListOfPieces().length);
    Assert.assertEquals("R", this.gsgDetermSmall.createListOfPieces()[0].toString());
    Assert.assertEquals("G", this.gsgDetermSmall.createListOfPieces()[1].toString());
    Assert.assertEquals("B",this.gsgDetermSmall.createListOfPieces()[2].toString());
    Assert.assertEquals("Y", this.gsgDetermSmall.createListOfPieces()[3].toString());
  }

  /**
   * Tests if the game is over if there is no way the user could score.
   */

  @Test
  public void testNoRemainingMoves() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    this.gsgDetermSmall.startGame(this.determRows, 2);
    // no possible way, with or without a swap left, that the user could score
    this.gsgDetermSmall.pieceAt(0,4).changeColor(PieceType.EMPTY);
    this.gsgDetermSmall.pieceAt(1,2).changeColor(PieceType.EMPTY);
    this.gsgDetermSmall.pieceAt(1,1).changeColor(PieceType.EMPTY);
    this.gsgDetermSmall.pieceAt(1,3).changeColor(PieceType.EMPTY);
    this.gsgDetermSmall.pieceAt(0,3).changeColor(PieceType.EMPTY);
    this.gsgDetermSmall.pieceAt(0,0).changeColor(PieceType.EMPTY);
    Assert.assertTrue(this.gsgDetermSmall.gameOver());
  }
  /**
   * Tests swap() with a small deterministic board.
   */

  @Test
  public void testSmallSwap() {
    // small determ example - same col
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    this.gsgDetermSmall.startGame(2, 5, 2, false);

    this.gsgDetermSmall.swap(0, 0, 0, 1);
    PieceType fromColorD = this.gsgDetermSmall.pieceAt(1, 3).color();
    PieceType toColorD = this.gsgDetermSmall.pieceAt(0, 3).color();
    PieceType fromColorD2 = this.gsgDetermSmall.pieceAt(1, 4).color();
    PieceType toColorD2 = this.gsgDetermSmall.pieceAt(0, 4).color();
    SameGameTextView sgDetermSmall = new SameGameTextView(this.gsgDetermSmall);
    this.gsgDetermSmall.swap(1, 3, 0, 3);
    Assert.assertEquals(fromColorD, this.gsgDetermSmall.pieceAt(0, 3).color());
    Assert.assertEquals(toColorD, this.gsgDetermSmall.pieceAt(1, 3).color());
  }

  /**
   * Tests invalid ways of swapping pieces.
   */
  @Test
  public void testInvalidSwap() {
    FourPieceSameGame fpsgNoSwaps = new FourPieceSameGame(this.allPieces, 2,5 , 2, 0);
    fpsgNoSwaps.startGame(2, 5, 2, false);
    GravitySameGame gsg = new GravitySameGame();
    gsg.startGame(2, 5, 2, false);

    // invalid method - swap same tile
    Assert.assertThrows(IllegalArgumentException.class, () -> gsg.swap(0, 0, 0, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> gsg.swap(0, 0, 1, 1));
  }

  /**
   * Tests removing a row and some column matching pieces.
   */

  @Test
  public void illegalSwaps() {
    this.fpsgDetermSmall.startGame(this.determRows, 3);
    this.gsgDetermSmall.startGame(this.determRows, 3);
    Assert.assertThrows(IllegalArgumentException.class, () -> this.gsgDetermSmall.swap(0,
            1, 1, 3));
    this.gsgDetermSmall.swap(0, 0, 0, 1);
    for (int row = 0; row < this.gsgDetermSmall.width(); row++) {
      for (int col = 0; col < this.gsgDetermSmall.length(); col++) {
        this.gsgDetermSmall.pieceAt(row, col).changeColor(PieceType.EMPTY);
      }
    }
    Assert.assertThrows(IllegalStateException.class, () -> this.gsgDetermSmall.swap(0,
            1, 1, 3));
    this.gsgDetermSmall.swap(0, 0, 0, 1);
    Assert.assertThrows(IllegalStateException.class, () -> this.gsgDetermSmall.swap(0,
            1,0 , 3));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.gsgDetermSmall.swap(6,
            9,6, 8));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.gsgDetermSmall.swap(6,
            9,6, 8));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.gsgDetermSmall.swap(0,
            0,1, 3));
    List nullList = new ArrayList();
    nullList.add(null);
    nullList.add(null);
    FourPieceSameGame fpsg1 = new FourPieceSameGame(nullList, 1, 2, 2, 1);
    GravitySameGame gsg = new GravitySameGame();
    Assert.assertThrows(IllegalArgumentException.class, () -> gsg.swap(0,
            0,0, 1));
  }

  @Test
  public void testRemoveMatchandScore() {
    //initialize a board with some clear matching tiles

    this.fpsgDetermSmall.startGame(this.determRows, 2);
    this.gsgDetermSmall.startGame(this.determRows, 2);
    SameGameTextView sgDetermSmall = new SameGameTextView(this.fpsgDetermSmall);
    this.gsgDetermSmall.swap(0, 0, 0, 1);
    // what the board looks like now:
    String determ = " R R B R R" + "\n" + " B R B Y R";
    Assert.assertEquals(determ, sgDetermSmall.toString());
    // what the board should look like:
    String determ2 = " R R B X X" + "\n" + " B R B Y X";
    this.gsgDetermSmall.removeMatch(0, 4);
    Assert.assertEquals(determ2, sgDetermSmall.toString());
    Assert.assertEquals(1, gsgDetermSmall.score());
    String determ3 = " X X X X X" + "\n" + " B X B Y X";
  }

  /**
   * Tests removing a single piece.
   */
  @Test
  public void testInvalidRemove() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    this.gsgDetermSmall.startGame(this.determRows, 2);
    SameGameTextView sgDetermSmall = new SameGameTextView(this.gsgDetermSmall);
    // what if we tried to change the last piece?
    // it should remain the same color - no matches left!
    Assert.assertThrows(IllegalArgumentException.class, () -> this.gsgDetermSmall.removeMatch(1,
            3));

    Assert.assertEquals(0, gsgDetermSmall.score());
  }


  /**
   * Tests if startGame() initializes correctly.
   */
  @Test
  public void testStartGame() {
    ArrayList allP = new ArrayList();
    Piece r = new Piece(PieceType.RED, 0, 0);
    Piece g = new Piece(PieceType.GREEN, 0, 0);
    Piece b = new Piece(PieceType.BLUE, 0, 0);
    Piece y = new Piece(PieceType.YELLOW, 0, 0);

    ArrayList<Piece> r1 = new ArrayList();
    r1.add(r);
    r1.add(g);
    r1.add(b);
    r1.add(y);
    r1.add(r);
    ArrayList<Piece> r2 = new ArrayList();
    r2.add(g);
    r2.add(b);
    r2.add(y);
    r2.add(r);
    r2.add(g);
    allP.add(r1);
    allP.add(r2);
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    this.gsgDetermSmall.startGame(2, 5, 2, false);
    Assert.assertEquals(r, this.gsgDetermSmall.pieceAt(0, 0));
    Assert.assertEquals(2, this.gsgDetermSmall.width());
    Assert.assertEquals(5, this.gsgDetermSmall.length());

  }

  /**
   * Tests if startGame() initializes correctly.
   */
  @Test
  public void testConvenientStartGame() {
    ArrayList allP = new ArrayList();
    Piece r = new Piece(PieceType.RED, 0, 0);
    Piece g = new Piece(PieceType.GREEN, 0, 0);
    Piece b = new Piece(PieceType.BLUE, 0, 0);
    Piece y = new Piece(PieceType.YELLOW, 0, 0);

    ArrayList<Piece> r1 = new ArrayList();
    r1.add(r);
    r1.add(g);
    r1.add(b);
    r1.add(y);
    r1.add(r);
    ArrayList<Piece> r2 = new ArrayList();
    r2.add(g);
    r2.add(b);
    r2.add(y);
    r2.add(r);
    r2.add(g);
    allP.add(r1);
    allP.add(r2);
    this.fpsgDetermSmall.startGame(allP, 2);
    this.gsgDetermSmall.startGame(allP, 2);

    //Assert.assertEquals(r, this.fpsgDetermSmall.pieceAt(0, 0));
    Assert.assertEquals(2, this.gsgDetermSmall.width());
    Assert.assertEquals(5, this.gsgDetermSmall.length());
  }


  /**
   * Tests if the view displays the pieces correctly.
   */
  @Test
  public void testtoString() {
    String determ = " R G B Y R" + "\n" + " G B Y R G";
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    this.gsgDetermSmall.startGame(2, 5, 2, false);

    SameGameTextView sg1 = new SameGameTextView(this.gsgDetermSmall);
    Assert.assertEquals(determ, sg1.toString());
    Assert.assertEquals(21, sg1.toString().length());
    Assert.assertTrue(sg1.toString().contains("\n"));
  }

  /**
   * Test correct deterministic board is returned.
   */

  @Test
  public void testDetermBoard() {
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    this.gsgDetermSmall.startGame(2, 5, 2, false);

    Assert.assertEquals(new Piece(PieceType.RED, 0, 0), this.gsgDetermSmall.pieceAt(0,0));
    Assert.assertEquals(new Piece(PieceType.GREEN, 0, 1), this.gsgDetermSmall.pieceAt(0,1));
    Assert.assertEquals(new Piece(PieceType.BLUE, 0, 2), this.gsgDetermSmall.pieceAt(0,2));
    Assert.assertEquals(new Piece(PieceType.YELLOW, 0, 3), this.gsgDetermSmall.pieceAt(0,3));
    Assert.assertEquals(new Piece(PieceType.RED, 0, 4), this.gsgDetermSmall.pieceAt(0,4));
    Assert.assertEquals(new Piece(PieceType.GREEN, 1, 0), this.gsgDetermSmall.pieceAt(1,0));
    Assert.assertEquals(new Piece(PieceType.BLUE, 1, 1), this.gsgDetermSmall.pieceAt(1,1));
    Assert.assertEquals(new Piece(PieceType.YELLOW, 1, 2), this.gsgDetermSmall.pieceAt(1,2));
    Assert.assertEquals(new Piece(PieceType.RED, 1, 3), this.gsgDetermSmall.pieceAt(1,3));
    Assert.assertEquals(new Piece(PieceType.GREEN, 1, 4), this.gsgDetermSmall.pieceAt(1,4));
  }

  /**
   * Tests that correct random board is returned.
   */
  @Test
  public void testRandomBoard() {
    this.fpsgRandomSmall.startGame(2, 5, 2, true);
    this.gsgDetermSmall.startGame(2, 5, 2, false);

    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(0,0));
    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(0,1));
    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(0,2));
    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(0,3));
    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(0,4));
    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(1,0));
    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(1,1));
    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(1,2));
    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(1,3));
    Assert.assertNotEquals(null, this.gsgDetermSmall.pieceAt(1,4));
    Assert.assertEquals(2, this.gsgDetermSmall.width());
    Assert.assertEquals(5, this.gsgDetermSmall.length());
  }

  /**
   * Tests invalid examples of boards.
   */
  @Test
  public void testInvalidBoard() {
    List<List<Piece>> allP = new ArrayList();
    // check if an error will be thrown when board is empty
    Assert.assertThrows(IllegalArgumentException.class, () -> this.fpsgDetermSmall.startGame(allP,
            2));
    Piece r = new Piece(PieceType.RED, 0, 0);
    Piece g = new Piece(PieceType.GREEN, 0, 0);
    Piece b = new Piece(PieceType.BLUE, 0, 0);
    Piece y = new Piece(PieceType.YELLOW, 0, 0);

    // making a non-rectangular board
    ArrayList<Piece> r1 = new ArrayList();
    r1.add(r);
    r1.add(g);
    r1.add(b);
    r1.add(y);
    r1.add(r);
    ArrayList<Piece> r2 = new ArrayList();
    r2.add(g);
    r2.add(b);
    r2.add(y);
    r2.add(r);
    allP.add(r1);
    allP.add(r2);
    // check if an error will be thrown with a non-rectangular board
    Assert.assertThrows(IllegalArgumentException.class, () -> this.fpsgDetermSmall.startGame(allP,
            2));
    allP.get(0).remove(0);
    // check if an error will be thrown with a negative number of swaps
    Assert.assertThrows(IllegalArgumentException.class, () -> this.fpsgDetermSmall.startGame(allP,
            -2));
    this.fpsgDetermSmall.startGame(allP, 2);
    this.gsgDetermSmall.startGame(allP, 2);
    // check if an error wil be thrown if the game has already been started
    Assert.assertThrows(IllegalStateException.class, () -> this.gsgDetermSmall.startGame(allP,
            2));
  }

  private static List<List<Piece>> exampleList() {

    Piece e = new Piece(PieceType.EMPTY, 0, 0);
    Piece r = new Piece(PieceType.RED, 0, 0);
    Piece g = new Piece(PieceType.GREEN, 0, 0);
    Piece b = new Piece(PieceType.BLUE, 0, 0);
    Piece y = new Piece(PieceType.YELLOW, 0, 0);
    List<Piece> r1 = Arrays.asList(e, g, b, e, e, e, e);
    List<Piece> r2 = Arrays.asList(e, r, g, e, e, g, b);
    List<Piece> r3 = Arrays.asList(e, y, e, g, b, y, g);
    List<Piece> r4 = Arrays.asList(r, r, e, e, g, b, y);
    List<Piece> r5 = Arrays.asList(r, g, b, y, e, g, b);
    List<List<Piece>> allP = Arrays.asList(r1, r2, r3, r4, r5);

    return allP;
  }

  /**
   * Tests full end-to-end game session of GravitySameGame.
   */
  @Test
  public void testPlayGravityGame() {
    List<List<Piece>> allP = exampleList();

    SameGameTextView<Piece> view = new SameGameTextView<Piece>(this.gsgDetermSmall,
            new StringBuilder());
    this.gsgDetermSmall.startGame(allP, 2);

    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(0, 2));
    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(1, 2));
    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(2, 3));

    this.gsgDetermSmall.removeMatch(4, 0);
    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(4, 0));
    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(3, 0));
    Assert.assertEquals(PieceType.YELLOW, this.gsgDetermSmall.pieceAt(3, 1).color());

    this.gsgDetermSmall.swap(4, 4, 3, 4);
    this.gsgDetermSmall.swap(4, 5, 3, 5);
    this.gsgDetermSmall.removeMatch(3, 2);

    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(3, 3));
    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(3, 4));
    Assert.assertEquals(PieceType.BLUE, this.gsgDetermSmall.pieceAt(3, 2).color());
    Assert.assertEquals(PieceType.YELLOW, this.gsgDetermSmall.pieceAt(3, 5).color());

    Assert.assertThrows(NullPointerException.class, () -> this.gsgDeterm.swap(0, 0, 0, 3));

    Assert.assertEquals(3, this.gsgDetermSmall.score());
    Assert.assertEquals(0, this.gsgDetermSmall.remainingSwaps());

    Assert.assertFalse(this.gsgDetermSmall.gameOver());
    this.gsgDetermSmall.removeMatch(4, 4);
    Assert.assertEquals(null, this.gsgDetermSmall.pieceAt(4, 4));
    Assert.assertEquals(PieceType.YELLOW, this.gsgDetermSmall.pieceAt(4, 5).color());
    Assert.assertEquals(PieceType.YELLOW, this.gsgDetermSmall.pieceAt(4, 6).color());

    Assert.assertTrue(this.gsgDetermSmall.gameOver());
  }
}

