import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.PieceType;
import cs3500.samegame.view.SameGameTextView;


/**
 * Represents the tests for FourPieceSameGame.
 */
public class FourPieceSameGameTest {
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

  /**
   * Allows a standard set of models to be generated freshly for each test.
   */
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
   * Tests swap() with a large random board.
   */
  @Test
  public void testLargeSwap() {
    // large random example - same row
    this.fpsgRandom.startGame(8, 9, 2, true);
    this.fpsgRandom.swap(0, 0, 0, 1);
    PieceType fromColor = this.fpsgRandom.pieceAt(0, 6).color();
    PieceType toColor = this.fpsgRandom.pieceAt(0, 4).color();
    SameGameTextView sgRandom = new SameGameTextView(this.fpsgRandom);
    this.fpsgRandom.swap(0, 6, 0, 4);
    Assert.assertEquals(fromColor, this.fpsgRandom.pieceAt(0, 4).color());
    Assert.assertEquals(toColor, this.fpsgRandom.pieceAt(0, 6).color());
  }

  /**
   * Tests swap() with a small deterministic board.
   */

  @Test
  public void testSmallSwap() {
    // small determ example - same col
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    this.fpsgDetermSmall.swap(0, 0, 0, 1);
    PieceType fromColorD = this.fpsgDetermSmall.pieceAt(1, 3).color();
    PieceType toColorD = this.fpsgDetermSmall.pieceAt(0, 3).color();
    PieceType fromColorD2 = this.fpsgDetermSmall.pieceAt(1, 4).color();
    PieceType toColorD2 = this.fpsgDetermSmall.pieceAt(0, 4).color();
    SameGameTextView sgDetermSmall = new SameGameTextView(this.fpsgDetermSmall);
    this.fpsgDetermSmall.swap(1, 3, 0, 3);
    Assert.assertEquals(fromColorD, this.fpsgDetermSmall.pieceAt(0, 3).color());
    Assert.assertEquals(toColorD, this.fpsgDetermSmall.pieceAt(1, 3).color());
  }

  /**
   * Tests invalid ways of swapping pieces.
   */
  @Test
  public void testInvalidSwap() {
    FourPieceSameGame fpsgNoSwaps = new FourPieceSameGame(this.allPieces, 2,5 , 2, 0);
    fpsgNoSwaps.startGame(2, 5, 2, false);

    // invalid method - swap same tile
    Assert.assertThrows(IllegalArgumentException.class, () -> fpsgNoSwaps.swap(0, 0, 0, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> fpsgNoSwaps.swap(0, 0, 1, 1));
  }

  /**
   * Tests removing a row and some column matching pieces.
   */

  @Test
  public void illegalSwaps() {
    this.fpsgDetermSmall.startGame(this.determRows, 3);
    Assert.assertThrows(IllegalArgumentException.class, () -> this.fpsgDetermSmall.swap(0,
            1, 1, 3));
    this.fpsgDetermSmall.swap(0, 0, 0, 1);
    for (int row = 0; row < this.fpsgDetermSmall.width(); row++) {
      for (int col = 0; col < this.fpsgDetermSmall.length(); col++) {
        this.fpsgDetermSmall.pieceAt(row, col).changeColor(PieceType.EMPTY);
      }
    }
    Assert.assertThrows(IllegalStateException.class, () -> this.fpsgDetermSmall.swap(0,
            1, 1, 3));
    this.fpsgDetermSmall.swap(0, 0, 0, 1);
    Assert.assertThrows(IllegalStateException.class, () -> this.fpsgDetermSmall.swap(0,
            1,0 , 3));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.fpsgDetermSmall.swap(6,
            9,6, 8));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.fpsgDetermSmall.swap(6,
            9,6, 8));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.fpsgDetermSmall.swap(0,
            0,1, 3));
    List nullList = new ArrayList();
    nullList.add(null);
    nullList.add(null);
    FourPieceSameGame fpsg1 = new FourPieceSameGame(nullList, 1, 2, 2, 1);
    Assert.assertThrows(IllegalArgumentException.class, () -> fpsg1.swap(0,
            0,0, 1));
  }

  @Test
  public void testRemoveMatchandScore() {
    //initialize a board with some clear matching tiles
    
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    SameGameTextView sgDetermSmall = new SameGameTextView(this.fpsgDetermSmall);
    this.fpsgDetermSmall.swap(0, 0, 0, 1);
    // what the board looks like now:
    String determ = " R R B R R" + "\n" + " B R B Y R";
    Assert.assertEquals(determ, sgDetermSmall.toString());
    // what the board should look like:
    String determ2 = " R R B X X" + "\n" + " B R B Y X";
    this.fpsgDetermSmall.removeMatch(0, 4);
    Assert.assertEquals(determ2, sgDetermSmall.toString());
    Assert.assertEquals(1, fpsgDetermSmall.score());
    String determ3 = " X X X X X" + "\n" + " B X B Y X";
  }

  /**
   * Tests removing just a whole row of same pieces.
   */

  @Test
  public void testRemoveWholeRow() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    SameGameTextView sgDetermSmall = new SameGameTextView(this.fpsgDetermSmall);
    String determ3 = " X X X X X" + "\n" + " B X B Y X";
    // what if the whole first row was red but only some of the second row?
    this.fpsgDetermSmall.pieceAt(0, 2).changeColor(PieceType.RED);
    this.fpsgDetermSmall.pieceAt(0, 3).changeColor(PieceType.RED);
    this.fpsgDetermSmall.pieceAt(0, 4).changeColor(PieceType.RED);
    this.fpsgDetermSmall.removeMatch(0, 4);
    Assert.assertEquals(determ3, sgDetermSmall.toString());
    Assert.assertEquals(5, fpsgDetermSmall.score());
  }

  /**
   * Tests removing a corner of the board.
   */
  @Test
  public void testRemoveCorner() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    SameGameTextView sgDetermSmall = new SameGameTextView(this.fpsgDetermSmall);
    // what if another set of two tiles in the corner were the same color?
    String determ4 = " R R B X X" + "\n" + " B Y B Y X";
    this.fpsgDetermSmall.pieceAt(1, 0).changeColor(PieceType.BLUE);
    this.fpsgDetermSmall.pieceAt(1, 1).changeColor(PieceType.YELLOW);
    System.out.println(sgDetermSmall.toString());
    this.fpsgDetermSmall.removeMatch(0, 4);
    Assert.assertEquals(determ4, sgDetermSmall.toString());
    Assert.assertEquals(1, fpsgDetermSmall.score());
  }

  /**
   * Tests removing a single piece.
   */
  @Test
  public void testInvalidRemove() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    SameGameTextView sgDetermSmall = new SameGameTextView(this.fpsgDetermSmall);
    // what if we tried to change the last piece?
    // it should remain the same color - no matches left!
    Assert.assertThrows(IllegalArgumentException.class, () -> this.fpsgDetermSmall.removeMatch(1,
            3));

    Assert.assertEquals(0, fpsgDetermSmall.score());
  }

  /**
   * Tests that width() returns the correct number of columns.
   */
  @Test
  public void testWidth() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    this.fpsgRandomSmall.startGame(this.determRows, 2);
    Assert.assertEquals(2, this.fpsgDetermSmall.width());
    Assert.assertEquals(2, this.fpsgRandomSmall.width());
  }

  /**
   * Tests that length() returns the correct number of rows.
   */
  @Test
  public void testLength() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    this.fpsgRandomSmall.startGame(this.determRows, 2);
    Assert.assertEquals(5, this.fpsgDetermSmall.length());
    Assert.assertEquals(5, this.fpsgRandomSmall.length());
  }

  /**
   * Tests that pieceAt() returns the correct piece at the given coordinate.
   */
  @Test
  public void testPieceAt() {
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    // Check correct return of piece at specified position
    Assert.assertEquals(PieceType.RED, this.fpsgDetermSmall.pieceAt(0,0).color());
    Assert.assertEquals(PieceType.RED, this.fpsgDetermSmall.pieceAt(0,4).color());
    Assert.assertEquals(PieceType.GREEN, this.fpsgDetermSmall.pieceAt(0,1).color());
    Assert.assertEquals(PieceType.GREEN, this.fpsgDetermSmall.pieceAt(1,0).color());
    // check if an error will be thrown for a piece out of bounds
    Assert.assertThrows(IllegalArgumentException.class, () -> this.fpsgDetermSmall.pieceAt(6, 3));
    FourPieceSameGame fpsg1 = new FourPieceSameGame(this.allPieces, 2, 5, 2, 0);
    List<List<Piece>> l1 = new ArrayList<>();
    List<Piece> r1 = new ArrayList<>();
    r1.add(null);
    r1.add(null);
    r1.add(null);
    r1.add(null);
    r1.add(null);
    List<Piece> r2 = new ArrayList<>();
    r2.add(null);
    r2.add(null);
    r2.add(null);
    r2.add(null);
    r2.add(null);
    l1.add(r1);
    l1.add(r2);
    // check an error will be thrown when the game hasn't started
    Assert.assertThrows(IllegalStateException.class, () -> fpsg1.pieceAt(0, 0));
    fpsg1.startGame(l1, 2);
    // check null returns for a null piece
    Assert.assertEquals(null, fpsg1.pieceAt(0,0));
  }

  /**
   * Tests that the remaining number of swaps after a swap has occurred is correct.
   */

  @Test
  public void testRemainingSwaps() {
    this.fpsgRandom.startGame(8, 9, 2, true);
    Assert.assertEquals(2, this.fpsgRandom.remainingSwaps());
    this.fpsgRandom.swap(0, 1, 0, 2);
    Assert.assertEquals(1, this.fpsgRandom.remainingSwaps());
    this.fpsgRandom.swap(0, 3, 0, 4);
    Assert.assertEquals(0, this.fpsgRandom.remainingSwaps());
  }

  /**
   * Tests whether the game is over if the board is empty.
   */
  @Test
  public void testBoardEmpty() {
    this.fpsgDetermSmall.startGame(this.determRows, 3);
    for (int row = 0; row < this.fpsgDetermSmall.width(); row++) {
      for (int col = 0; col < this.fpsgDetermSmall.length(); col++) {
        this.fpsgDetermSmall.pieceAt(row, col).changeColor(PieceType.EMPTY);
      }
    }
    Assert.assertTrue(this.fpsgDetermSmall.gameOver());
  }


  /**
   * Tests if the game is over if there are remaining swaps.
   */
  @Test
  public void testRemainingMoves() {
    // if there is a way in which a swap could be made where the user could get a match
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    this.fpsgDetermSmall.swap(0, 0, 0, 1);
    this.fpsgDetermSmall.pieceAt(0, 1).changeColor(PieceType.GREEN);
    this.fpsgDetermSmall.pieceAt(0, 2).changeColor(PieceType.EMPTY);
    this.fpsgDetermSmall.pieceAt(0, 3).changeColor(PieceType.EMPTY);
    this.fpsgDetermSmall.pieceAt(0,4).changeColor(PieceType.RED);
    this.fpsgDetermSmall.pieceAt(1,2).changeColor(PieceType.YELLOW);
    this.fpsgDetermSmall.pieceAt(1,3).changeColor(PieceType.GREEN);
    this.fpsgDetermSmall.pieceAt(1,4).changeColor(PieceType.EMPTY);
    SameGameTextView sgd = new SameGameTextView(this.fpsgDetermSmall);
    Assert.assertFalse(this.fpsgDetermSmall.gameOver());
  }

  /**
   * Tests if the game is over if there is no way the user could score.
   */
  @Test
  public void testNoRemainingMoves() {
    this.fpsgDetermSmall.startGame(this.determRows, 2);
    // no possible way, with or without a swap left, that the user could score
    this.fpsgDetermSmall.pieceAt(0,4).changeColor(PieceType.EMPTY);
    this.fpsgDetermSmall.pieceAt(1,2).changeColor(PieceType.EMPTY);
    this.fpsgDetermSmall.pieceAt(1,1).changeColor(PieceType.EMPTY);
    this.fpsgDetermSmall.pieceAt(1,3).changeColor(PieceType.EMPTY);
    this.fpsgDetermSmall.pieceAt(0,3).changeColor(PieceType.EMPTY);
    this.fpsgDetermSmall.pieceAt(0,0).changeColor(PieceType.EMPTY);
    Assert.assertTrue(this.fpsgDetermSmall.gameOver());
  }

  /**
   * Tests if a list of piece is generated in the correct order.
   */

  @Test
  public void testCreateListPieces() {

    Assert.assertEquals(4, this.fpsgRandom.createListOfPieces().length);
    Assert.assertEquals("R", this.fpsgRandom.createListOfPieces()[0].toString());
    Assert.assertEquals("G", this.fpsgRandom.createListOfPieces()[1].toString());
    Assert.assertEquals("B",this.fpsgRandom.createListOfPieces()[2].toString());
    Assert.assertEquals("Y", this.fpsgRandom.createListOfPieces()[3].toString());
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
    Assert.assertEquals(r, this.fpsgRandomSmall.pieceAt(0, 0));
    Assert.assertEquals(2, this.fpsgDetermSmall.width());
    Assert.assertEquals(5, this.fpsgDetermSmall.length());

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
    //Assert.assertEquals(r, this.fpsgDetermSmall.pieceAt(0, 0));
    Assert.assertEquals(2, this.fpsgDetermSmall.width());
    Assert.assertEquals(5, this.fpsgDetermSmall.length());
  }


  /**
   * Tests if the view displays the pieces correctly.
   */
  @Test
  public void testtoString() {
    String determ = " R G B Y R" + "\n" + " G B Y R G";
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    SameGameTextView sg1 = new SameGameTextView(this.fpsgDetermSmall);
    Assert.assertEquals(determ, sg1.toString());
    SameGameTextView sg2 = new SameGameTextView(this.fpsgRandomSmall);
    Assert.assertEquals(21, sg1.toString().length());
    Assert.assertTrue(sg1.toString().contains("\n"));
  }

  /**
   * Test correct deterministic board is returned.
   */

  @Test
  public void testDetermBoard() {
    this.fpsgDetermSmall.startGame(2, 5, 2, false);
    Assert.assertEquals(new Piece(PieceType.RED, 0, 0), this.fpsgDetermSmall.pieceAt(0,0));
    Assert.assertEquals(new Piece(PieceType.GREEN, 0, 1), this.fpsgDetermSmall.pieceAt(0,1));
    Assert.assertEquals(new Piece(PieceType.BLUE, 0, 2), this.fpsgDetermSmall.pieceAt(0,2));
    Assert.assertEquals(new Piece(PieceType.YELLOW, 0, 3), this.fpsgDetermSmall.pieceAt(0,3));
    Assert.assertEquals(new Piece(PieceType.RED, 0, 4), this.fpsgDetermSmall.pieceAt(0,4));
    Assert.assertEquals(new Piece(PieceType.GREEN, 1, 0), this.fpsgDetermSmall.pieceAt(1,0));
    Assert.assertEquals(new Piece(PieceType.BLUE, 1, 1), this.fpsgDetermSmall.pieceAt(1,1));
    Assert.assertEquals(new Piece(PieceType.YELLOW, 1, 2), this.fpsgDetermSmall.pieceAt(1,2));
    Assert.assertEquals(new Piece(PieceType.RED, 1, 3), this.fpsgDetermSmall.pieceAt(1,3));
    Assert.assertEquals(new Piece(PieceType.GREEN, 1, 4), this.fpsgDetermSmall.pieceAt(1,4));
  }

  /**
   * Tests that correct random board is returned.
   */
  @Test
  public void testRandomBoard() {
    this.fpsgRandomSmall.startGame(2, 5, 2, true);
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(0,0));
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(0,1));
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(0,2));
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(0,3));
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(0,4));
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(1,0));
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(1,1));
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(1,2));
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(1,3));
    Assert.assertNotEquals(null, this.fpsgRandomSmall.pieceAt(1,4));
    Assert.assertEquals(2, this.fpsgRandomSmall.width());
    Assert.assertEquals(5, this.fpsgRandomSmall.length());
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
    // check if an error wil be thrown if the game has already been started
    Assert.assertThrows(IllegalStateException.class, () -> this.fpsgDetermSmall.startGame(allP,
            2));
  }

}
