import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.samegame.controller.SameGameTextController;
import cs3500.samegame.model.hw02.FourPieceSameGame;
import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.PieceType;
import cs3500.samegame.model.hw02.SameGameModel;
import cs3500.samegame.view.SameGameTextView;

public class SGTest {

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


  @Test
  public void testSwapInput() {
    // test a normal stream of input starting with s and then a series of 4 natural numbers
    StringBuilder log = new StringBuilder("s 1 2 3 4");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> mock = new MockModel(log);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(mock, 4, 5, 2, false);
    Assert.assertEquals("Swapping: (0,1) and (2,3)- Swaps is: N- Score is: N", log.toString());

    // test a stream of input starting with s and then some invalid moves
    // should still wait for the integer inputs
    StringBuilder logInvalid = new StringBuilder("s 1 a b c 2 g 3 lmao 4");
    Readable rdInvalid = new StringReader(logInvalid.toString());
    StringBuilder apInvalid = new StringBuilder();
    SameGameModel<Piece> mockInvalid = new MockModel(logInvalid);
    SameGameTextController<Piece> controller2 = new SameGameTextController<>(rdInvalid, apInvalid);
    controller2.playGame(mockInvalid, 4, 5, 2, false);
    Assert.assertEquals("Swapping: (0,1) and (2,3)- Swaps is: N- Score is: N", logInvalid.toString());

    // test stream of input with some negative inputs
    StringBuilder logNeg = new StringBuilder("s -1 2 3 4");
    Readable rdNeg = new StringReader(logInvalid.toString());
    StringBuilder apNeg = new StringBuilder();
    SameGameModel<Piece> mockNeg = new MockModel(logInvalid);
    SameGameTextController<Piece> controller3 = new SameGameTextController<>(rdNeg, apNeg);
    controller3.playGame(mockNeg, 4, 5, 2, false);
    Assert.assertEquals("s -1 2 3 4", logNeg.toString());
  }

  @Test
  public void testRemoveInput() {
    // test a normal stream of input starting with s and then a series of 4 natural numbers
    StringBuilder log = new StringBuilder("m 1 2");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> mock = new MockModel(log);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(mock, 4, 5, 2, false);
    Assert.assertEquals("Removing block at: (0,1)- Swaps is: N- Score is: N", log.toString());

    // test a stream of input starting with s and then some invalid moves
    // should still wait for the integer inputs
    StringBuilder logInvalid = new StringBuilder("m 1 a b c 2");
    Readable rdInvalid = new StringReader(logInvalid.toString());
    StringBuilder apInvalid = new StringBuilder();
    SameGameModel<Piece> mockInvalid = new MockModel(logInvalid);
    SameGameTextController<Piece> controller2 = new SameGameTextController<>(rdInvalid, apInvalid);
    controller2.playGame(mockInvalid, 4, 5, 2, false);
    Assert.assertEquals("Removing block at: (0,1)- Swaps is: N- Score is: N", logInvalid.toString());

    // test stream of input with some negative inputs
    StringBuilder logNeg = new StringBuilder("s -1 2");
    Readable rdNeg = new StringReader(logInvalid.toString());
    StringBuilder apNeg = new StringBuilder();
    SameGameModel<Piece> mockNeg = new MockModel(logInvalid);
    SameGameTextController<Piece> controller3 = new SameGameTextController<>(rdNeg, apNeg);
    controller3.playGame(mockNeg, 4, 5, 2, false);
    Assert.assertEquals("s -1 2", logNeg.toString());
  }


  @Test
  public void testInvalidSwapMoves() {
    StringBuilder log = new StringBuilder("m 1 2 3 4");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> fpsg1 = new FourPieceSameGame(this.allPieces, 4, 5, 2, 0);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(fpsg1, 4, 5, 2, false);
    String origStr = ap.toString();
    String[] arrOfStr = origStr.split("\n");

    Assert.assertEquals("Invalid move. Try again. Not enough blocks to make a match" + System.lineSeparator(), arrOfStr[0]);
  }


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

    Assert.assertEquals("Invalid move. Try again. Column out of bounds" + System.lineSeparator(), arrOfStr[0]);
  }

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

    Assert.assertEquals("Invalid command. Try again. a not allowed.R G B Y R" + System.lineSeparator(), arrOfStr[0]);

  }

  @Test
  public void testQuit() {
    // test match when quitting
    StringBuilder log = new StringBuilder("m 1 q 2");
    Readable rd = new StringReader(log.toString());
    StringBuilder ap = new StringBuilder();
    SameGameModel<Piece> mock = new MockModel(log);
    SameGameTextController<Piece> controller1 = new SameGameTextController<>(rd, ap);
    controller1.playGame(mock, 4, 5, 2, false);
    Assert.assertEquals("m 1 q 2", log.toString());
    Assert.assertEquals("Game quit!\nState of game when quit:\n", ap.toString());
  }

  @Test
  public void removeMatch() {
    List<List<Piece>> pieces = new ArrayList<>();
    FourPieceSameGame fpsg1 = new FourPieceSameGame(pieces, 3, 4, 0, 2);
    List<Piece> row1 = new ArrayList();
    row1.add(new Piece(PieceType.EMPTY, 0, 0));
    row1.add(new Piece(PieceType.EMPTY, 0, 1));
    row1.add(new Piece(PieceType.EMPTY, 0, 2));
    row1.add(new Piece(PieceType.EMPTY, 0, 3));
    List<Piece> row2 = new ArrayList();
    row2.add(new Piece(PieceType.RED, 1, 0));
    row2.add(new Piece(PieceType.EMPTY, 1, 1));
    row2.add(new Piece(PieceType.YELLOW, 1, 2));
    row2.add(new Piece(PieceType.BLUE, 1, 3));
    List<Piece> row3 = new ArrayList();
    row3.add(new Piece(PieceType.GREEN, 2, 0));
    row3.add(new Piece(PieceType.BLUE, 2, 1));
    row3.add(new Piece(PieceType.BLUE, 2, 2));
    row3.add(new Piece(PieceType.GREEN, 2, 3));
    /**
     List<Piece> row4 = new ArrayList();
     row4.add(new Piece(PieceType.YELLOW, 3, 0));
     row4.add(new Piece(PieceType.EMPTY, 3, 1));
     row4.add(new Piece(PieceType.EMPTY, 3, 2));
     row4.add(new Piece(PieceType.RED, 3, 3));
     row4.add(new Piece(PieceType.YELLOW, 3, 4));
     List<Piece> row5 = new ArrayList();
     row5.add(new Piece(PieceType.RED, 4, 0));
     row5.add(new Piece(PieceType.EMPTY, 4, 1));
     row5.add(new Piece(PieceType.EMPTY, 4, 2));
     row5.add(new Piece(PieceType.YELLOW, 4, 3));
     row5.add(new Piece(PieceType.RED, 4, 4));
     **/
    pieces.add(row1);
    pieces.add(row2);
    pieces.add(row3);
    //pieces.add(row4);
    //pieces.add(row5);
    fpsg1.startGame(pieces, 0);
    SameGameTextView sg1 = new SameGameTextView(fpsg1);
    //System.out.println(sg1.toString());
    // fpsg1.removeMatch(3, 1);
    System.out.println(sg1.toString());
    Assert.assertTrue(fpsg1.gameOver());

    //Assert.assertFalse(fpsg1.gameOver());
  }
}
