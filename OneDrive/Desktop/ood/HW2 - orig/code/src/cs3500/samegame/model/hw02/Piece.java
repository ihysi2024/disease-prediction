package cs3500.samegame.model.hw02;

import java.util.Objects;

/**
 * Represents a game piece in the game model.
 */
public class Piece {

  private PieceType color;
  private int row;
  private int col;

  /**
   * Represents a game piece in the game model.
   * @param color represents the type of piece
   * @param row represents the row where the piece is located
   * @param col represents the column where the piece is located
   */

  public Piece(PieceType color, int row, int col) {
    this.color = Objects.requireNonNull(color);
    if ((row < 0) || (col < 0)) {
      throw new IllegalArgumentException("Out of Bounds");
    }
    this.row = row;
    this.col = col;
  }

  /**
   * Implements the piece as a string in the game model.
   * @return piece as a string
   */

  public String toString() {
    return color.toString();
  }

  /**
   * Determines if a piece is equal to another piece.
   * @param other another object
   * @return boolean.
   */
  public boolean equals(Object other) {
    if (other instanceof Piece) {
      return (this == other);
    }
    return false;
  }

  /**
   * Determines if the fields of an object can be hashed.
   * @return boolean.
   */
  public int hashCode() {
    return Objects.hash(this.row, this.col, this.color);
  }

  /**
   * Observes the piece color.
   * @return a PieceType
   */
  public PieceType color() {
    return this.color;
  }

  /**
   * Changes the current piece's color to the given color.
   * @param color to change the piece to
   */
  public void changeColor(PieceType color) {
    this.color = color;
  }

  /**
   * Changes the current piece's row to the given row.
   * @param row to change the piece to
   */

  public void changeRow(int row) {
    this.row = row;
  }

  /**
   * Changes the current piece's column to the given column.
   * @param col to change the piece to
   */

  public void changeCol(int col) {
    this.col = col;
  }

  /**
   * Observes the piece row.
   * @return an integer
   */

  public int row() {
    return this.row;
  }

  /**
   * Observes the piece column.
   * @return a PieceType
   */
  public int col() {
    return this.col;
  }

}
