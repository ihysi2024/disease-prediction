import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.samegame.model.hw02.Piece;
import cs3500.samegame.model.hw02.PieceType;

/**
 * Represents the tests pertaining to the pieces.
 */
public class PieceTest {

  Piece red;
  Piece green;
  Piece blue;
  Piece yellow;
  Piece empty;

  /**
   * Generates a list of pieces to freshly reset for each test.
   */
  @Before
  public void setUp() {
    this.red = new Piece(PieceType.RED, 1, 9);
    this.green = new Piece(PieceType.GREEN, 3, 8);
    this.blue = new Piece(PieceType.BLUE, 7, 0);
    this.yellow = new Piece(PieceType.YELLOW, 3, 3);
    this.empty = new Piece(PieceType.EMPTY, 3, 3);
  }

  /**
   * Tests that the correct string represents each piece.
   */
  @Test
  public void testToString() {

    Assert.assertEquals("R", red.toString());
    Assert.assertEquals("G", green.toString());
    Assert.assertEquals("B", blue.toString());
    Assert.assertEquals("Y", yellow.toString());
    Assert.assertEquals("X", empty.toString());
  }

  /**
   * Tests invalid examples of the constructor.
   */
  @Test
  public void testInvalidConstructor() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new Piece(PieceType.RED,
            -1, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> new Piece(PieceType.RED,
            9, -1));
  }

  /**
   * Tests that the correct color is given.
   */
  @Test
  public void testColor() {
    Assert.assertEquals(PieceType.RED, this.red.color());
    Assert.assertEquals(PieceType.GREEN, this.green.color());
  }

  /**
   * Tests that two pieces are correctly equal to each other.
   */
  @Test
  public void testEquals() {
    Assert.assertTrue(this.red.equals(this.red));
    Assert.assertFalse(this.red.equals(this.green));
  }

  /**
   * Tests that two piece's attributes are correctly equal to each other.
   */
  @Test
  public void testHashCode() {
    Assert.assertTrue(this.red.color().equals(this.red.color()));
    Assert.assertTrue(this.green.color().equals(this.green.color()));
  }

}