package cs3500.samegame.model.hw02;


/**
 * Represents the types of colors a piece can be.
 */
public enum PieceType {
  RED,
  GREEN,
  BLUE,
  YELLOW,

  EMPTY;

  /**
   * Represents the types of colors a piece can be as a string.
   * @return the colors as strings.
   */

  public String toString() {
    switch (this) {
      case RED:
        return "R";
      case GREEN:
        return "G";
      case BLUE:
        return "B";
      case YELLOW:
        return "Y";
      case EMPTY:
        return "X";
      default:
        return "";
    }
  }



}
