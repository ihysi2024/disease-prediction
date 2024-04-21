package cs3500.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents the TicTacToe game.
 */
public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  private Player[][] board;
  private Player currentPlayer;
  private Player winner;

  /**
   * Represents the TicTacToe initialization.
   */

  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.currentPlayer = Player.X;
    this.winner = null;
  }

  /**
   * Converts the player's moves into strings on the board.
   * @return a string version of the board.
   */

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
      row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
          .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(Player[] row : getBoard()) {
    //   List<String> rowStrings = new ArrayList<>();
    //   for(Player p : row) {
    //     if(p == null) {
    //       rowStrings.add(" ");
    //     } else {
    //       rowStrings.add(p.toString());
    //     }
    //   }
    //   rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }

  /**
   * Allows a player to move to a specified spot on the board.
   * @param r the row of the intended move
   * @param c the column of the intended move
   */
  @Override
  public void move(int r, int c) {
    ensureValidCoordinates(r, c);
    //TODO: check if anyone has played in r, c

    this.board[r][c] = this.currentPlayer;
    if (currentPlayer == Player.X) {
      currentPlayer = Player.O;
    }
    else {
      currentPlayer = Player.X;
    }


  }

  /**
   * Determines which player's turn it is.
   * @return the player currently playing.
   */
  @Override
  public Player getTurn() {
    return this.currentPlayer;
  }

  /**
   * Determines if the game is over.
   * @return a boolean representing if the game is over.
   */

  @Override
  public boolean isGameOver() {
    return this.haveThreeInARow();
  }

  /**
   * Represents the winner of the game.
   * @return the player that won.
   */
  @Override
  public Player getWinner() {
    return winner;
  }

  private boolean haveThreeInARow() {
    for (Player[] row: board) {
      if (isThreeInThisRow(row)) {
        return true;
      }
    }
    return false;
  }

  private boolean isThreeInThisRow(Player[] row) {
    return row[0] == row[1] && row[1] == row[2];
  }

  /**
   * Displays the full board.
   * @return a list of lists that represents the board.
   */
  @Override
  public Player[][] getBoard() {
    Player[][] copy = new Player[3][3];
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        copy[row][col] = board[row][col];
      }
    }
    return copy;
  }

  /**
   * Displays the player at a given spot on the board.
   * @param r the row
   * @param c the column
   * @return a player.
   */

  @Override
  public Player getMarkAt(int r, int c) {
    ensureValidCoordinates(r, c);
    return this.board[r][c];
  }

  private void ensureValidCoordinates(int r, int c) {
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
      throw new IllegalArgumentException("bad row, col: " + r + "," + c);
    }
  }
}
