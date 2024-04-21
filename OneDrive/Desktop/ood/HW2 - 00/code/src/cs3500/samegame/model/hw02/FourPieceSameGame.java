package cs3500.samegame.model.hw02;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;


/**
 * Describes the FourPieceSameGame back-end model.
 */

public class FourPieceSameGame implements SameGameModel<Piece> {
  private List<List<Piece>> board;
  private int rows;
  private int cols;
  private int swaps;
  private int score;
  private boolean hasSwapped;
  private boolean hasRemoved;
  private boolean gameStarted;

  /**
   * Convenience constructor for FourPieceSameGame.
   */
  public FourPieceSameGame() {
    //allows the user to start with an empty game;
  }

  /**
   * Correctly initializes the game with relevant parameters.
   * @param board a list of pieces the board the player plays on
   * @param rows the number of rows the board is
   * @param cols the number of columns the board is
   * @param swaps the number of swaps granted to the player
   * @param score the score the player currently has
   */


  public FourPieceSameGame(List<List<Piece>> board, int rows,
                           int cols, int swaps, int score) {
    this.board = board;
    if (rows <= 0) {
      throw new IllegalArgumentException("Number of rows cannot be negative");
    }
    if (cols <= 0) {
      throw new IllegalArgumentException("Number of columns cannot be negative");
    }
    if (swaps < 0) {
      throw new IllegalArgumentException("Number of swaps cannot be negative");
    }
    if (score < 0) {
      throw new IllegalArgumentException("Score cannot be negative");
    }
    this.rows = rows;
    this.cols = cols;
    this.swaps = swaps;
    this.score = score;
    this.hasSwapped = false;
    this.hasRemoved = false;
    this.gameStarted = false;
  }


  /**
   * Allows the player to swap 2 pieces.
   * @param fromRow the row of the first piece (0-based index)
   * @param fromCol the col of the first piece (0-based index)
   * @param toRow the row of the first piece (0-based index)
   * @param toCol the col of the first piece (0-based index)
   */

  @Override
  public void swap(int fromRow, int fromCol, int toRow, int toCol) {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    if (this.gameOver()) {
      throw new IllegalStateException("Game is over.");
    }
    if (fromCol > this.length() || (fromCol < 0)) {
      throw new IllegalArgumentException("Column out of bounds");
    }
    if (toCol > this.length() || (toCol < 0)) {
      throw new IllegalArgumentException("Column out of bounds");
    }
    if (fromRow > this.width() || (fromRow < 0)) {
      throw new IllegalArgumentException("Row out of bounds");
    }
    if (toRow > this.width() || (toRow < 0)) {
      throw new IllegalArgumentException("Row out of bounds");
    }
    if ((this.pieceAt(fromRow, fromCol) == null) && (this.pieceAt(toRow, toCol) == null)) {
      throw new IllegalStateException("No such piece on the board.");
    }
    if ((fromRow != toRow) && (fromCol != toCol)) {
      throw new IllegalStateException("Move not allowed. Pieces must be located "
              + "on same row or column");
    }
    if ((fromRow == toRow) && (fromCol == toCol)) {
      throw new IllegalStateException("Switching the same piece is not allowed");
    }
    if (this.swaps <= 0) {
      throw new IllegalStateException("No more swaps left!");
    }

    this.hasSwapped = true;
    PieceType fromColor = this.board.get(fromRow).get(fromCol).color();
    PieceType toColor = this.board.get(toRow).get(toCol).color();

    if (fromRow == toRow) {
      this.board.get(fromRow).set(fromCol, new Piece(toColor, fromRow, fromCol));
      this.board.get(toRow).set(toCol, new Piece(fromColor, toRow, toCol));
    }
    if (fromCol == toCol) {
      this.board.get(fromRow).set(fromCol, new Piece(toColor, fromRow, fromCol));
      this.board.get(toRow).set(toCol, new Piece(fromColor, toRow, toCol));
    }
    this.swaps--;

  }

  /**
   * Allows the user to remove a set of matching pieces.
   * @param row the row of the piece to remove (0-based index)
   * @param col the col of the piece to remove (0-based index)
   */
  @Override
  public void removeMatch(int row, int col) {
    if (this.gameOver()) {
      throw new IllegalStateException("Game is over.");
    }
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    if (col > this.length() || (col < 0)) {
      throw new IllegalArgumentException("Column out of bounds");
    }
    if (row > this.width() || (row < 0)) {
      throw new IllegalArgumentException("Row out of bounds");
    }
    if (this.pieceAt(row, col) == null) {
      throw new IllegalStateException("No such piece on the board.");
    }
    List<Piece> connectedPiece = boardSearch(row, col);
    this.hasRemoved = true;
    makeEmpty(connectedPiece);
  }

  private List<Piece> boardSearch(int row, int col) {
    PieceType color = this.board.get(row).get(col).color();
    List<Piece> connectedPiece = new ArrayList();
    Stack<Piece> alreadySeen = new Stack<>();
    connectedPiece.add(new Piece(color, row, col));
    alreadySeen.push(new Piece(color, row, col));
    // make sure you stop searching the grid when there are no more connecting pieces
    // of the same color
    while (!alreadySeen.isEmpty()) {
      Piece p = alreadySeen.get(0);
      System.out.println("Starting on (" + p.row() + ", " + p.col() + ")");
      if (p.row() - 1 >= 0) {
        searchAndAdd(p.col(), p.row() - 1, color, connectedPiece, alreadySeen);
      }
      if (p.row() + 1 < this.width()) {
        searchAndAdd(p.col(), p.row() + 1, color, connectedPiece, alreadySeen);
      }
      if (p.col() - 1 >= 0) {
        searchAndAdd(p.col() - 1, p.row(), color, connectedPiece, alreadySeen);
      }
      if (p.col() + 1 < this.length()) {
        searchAndAdd(p.col() + 1, p.row() , color, connectedPiece, alreadySeen);
      }
      alreadySeen.remove(0);
    }
    return connectedPiece;
  }

  // Check if the given piece is the right color, and add it to the corresponding lists
  private void searchAndAdd(int col, int rowNum,
                            PieceType color, List<Piece> connectedPiece, Stack<Piece> alreadySeen) {

    List contained = new ArrayList();
    System.out.println("(" + rowNum + ", " + col + ")");
    if (this.board.get(rowNum).get(col).color().equals(color)) {
      for (Piece piece: connectedPiece) {
        if ((piece.row() == rowNum) && (piece.col() == col)) {
          contained.add(piece);
        }
      }
      if (contained.isEmpty()) {
        connectedPiece.add(new Piece(color, rowNum, col));
        alreadySeen.add(new Piece(color, rowNum, col));
      }
    }
    System.out.println(connectedPiece.size());
  }
  // Because border tiles only have THREE adjacent tiles, check the 3 adjacent tiles
  // I.E if the tile is on the top or bottom, only check the second row or the second-to-last row
  // but check the columns normally
  // if the tile is on the far left or right, only check the right-adjacent or left-adjacent tile
  // but check the rows normally
  private void boardEdges(int firstEdge, int secondEdge, int firstBound, int secondBound,
                          Piece p, PieceType color, List<Piece> connectedPiece,
                          Stack<Piece> alreadySeen) {
    System.out.println("firstedge " + firstEdge);
    System.out.println("secondedge " + secondEdge);
    System.out.println("firstbound " + firstBound);
    System.out.println("secondbound " + secondBound);
    System.out.println("ON BORDER");
    // top and bottom rows
    // if column is not far left or far right
    if ((firstEdge != 0) && (firstEdge != firstBound - 1)) {
      System.out.println("TOP/BOTTOM");
      // if top row, check row 1 & corresponding column (i.e. (1,1))
      if ((secondEdge == 0)) {
        //System.out.println(firstEdge);
        searchAndAdd(firstEdge, 1, color, connectedPiece, alreadySeen);
      }
      // if bottom row, check second to last row & corresponding column
      if ((secondEdge == secondBound - 1)) {
        searchAndAdd(firstEdge, secondBound - 2, color, connectedPiece, alreadySeen);
      }
      //
      for (int colNum = firstEdge - 1; colNum < firstEdge + 2; colNum++) {
        searchAndAdd(colNum, secondEdge, color, connectedPiece, alreadySeen);
      }
    }
    // left or right columns
    // if not top or bottom rows
    if ((secondEdge != 0) && (secondEdge != secondBound - 1)) {
      System.out.println("LEFT/RIGHT");
      if ((firstEdge == 0)) {
        searchAndAdd(1, secondEdge, color, connectedPiece, alreadySeen);
      }
      if ((firstEdge == firstBound - 1)) {
        searchAndAdd(firstBound - 2, secondEdge, color, connectedPiece, alreadySeen);
      }
      for (int colNum = secondEdge - 1; colNum < secondEdge + 2; colNum++) {
        searchAndAdd(colNum, secondEdge, color, connectedPiece, alreadySeen);
      }
    }
  }


  // Because corner pieces only have TWO adjacent tiles, check those 2 Adjacent tiles
  private void cornerPieces(int col, Piece p, PieceType color, List<Piece> connectedPiece,
                            Stack<Piece> alreadySeen) {
    // top-left corner
    if ((p.row() == 0) && (p.col() == 0)) {
      System.out.println("on top left corner");
      searchAndAdd(0, 1, color, connectedPiece, alreadySeen);
      searchAndAdd(1, 0, color, connectedPiece, alreadySeen);
    }
    // top-right corner
    if ((p.row() == 0) && (p.col() == this.length() - 1)) {
      searchAndAdd(this.length() - 1, 1, color, connectedPiece, alreadySeen);
      searchAndAdd(this.length() - 2, 0, color, connectedPiece, alreadySeen);
    }
    // bottom-left corner
    if ((p.row() == this.width() - 1) && (p.col() == 0)) {
      searchAndAdd(col, this.width() - 2, color, connectedPiece, alreadySeen);
      searchAndAdd(1, this.width() - 1, color, connectedPiece, alreadySeen);
    }
    // bottom-right corner
    if ((p.row() == this.width() - 1) && (p.col() == this.length() - 1)) {
      searchAndAdd(this.length() - 1, this.width() - 2, color, connectedPiece,
              alreadySeen);
      searchAndAdd(this.length() - 2, this.width() - 1, color, connectedPiece,
              alreadySeen);
    }
  }

  // Once you have a list of all connecting pieces of the same color, make them 'X's and raise
  // the score ONLY if there are more than 3 connecting pieces
  private void makeEmpty(List<Piece> connectedPiece) {
    if (connectedPiece.size() > 2) {
      for (Piece p: connectedPiece) {
        this.board.get(p.row()).set(p.col(), new Piece(PieceType.EMPTY, p.row(), p.col()));
        //p.changeColor(PieceType.EMPTY);
        this.score++;
      }
      this.score = this.score - 2;
    }
    else {
      throw new IllegalStateException("Not enough blocks to make a match");
    }
  }



  /**
   * Represents the number of columns on the board.
   * @return a number of columns.
   */
  @Override
  public int width() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    return this.rows;
  }

  /**
   * Represents the number of rows on the board.
   * @return a number of rows.
   */
  @Override
  public int length() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    return this.cols;
  }

  /**
   * Represents the piece at a given coordinate.
   * @param row the row in the board (0-based index)
   * @param col the col in the board (0-based index)
   * @return
   */
  @Override
  public Piece pieceAt(int row, int col) {

    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    if (col > (this.length() - 1) || (col < 0)) {
      throw new IllegalArgumentException("Column out of bounds");
    }
    if (row > (this.width() - 1) || (row < 0)) {
      throw new IllegalArgumentException("Row out of bounds");
    }
    if (this.board.get(row).get(col).color() == PieceType.EMPTY) {
      return null;
    }
    return this.board.get(row).get(col);
  }

  /**
   * Represents the current score.
   * @return an integer that represents the score.
   */
  @Override
  public int score() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    return this.score;
  }

  /**
   * Represents how many swaps the user has remaining.
   * @return a number of swaps.
   */
  @Override
  public int remainingSwaps() {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    return this.swaps;
  }

  /**
   * Determines whether the game is over depending on the board is clear
   * or the user can still make a valid move.
   * @return a boolean that determines whether the game is over
   */
  @Override
  public boolean gameOver() {
    boolean gameOver = false;
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    else {
      if (!this.remainingMoves()) {
        gameOver = true;
      } else if (this.emptyBoard()) {
        gameOver = true;
      }
    }
    return gameOver;
  }

  // Check if the board is empty
  private boolean emptyBoard() {
    //System.out.println("True");
    List<Piece> anyLeft = new ArrayList();
    for (List<Piece> listList : this.board) {
      for (Piece noPieceLeft : listList) {
        if (!noPieceLeft.color().equals(PieceType.EMPTY)) {
          anyLeft.add(noPieceLeft);
        }
      }
    }
    return (anyLeft.isEmpty());
  }

  // Check if there are any colors on the board present more than twice and if there are enough
  // swaps left. If there aren't any, then there are no more moves that the user can make
  private boolean remainingMoves() {

    List<PieceType> matchLeft = new ArrayList();
    for (PieceType color: this.colorMapping().keySet()) {
      //List<Piece> blockLeftRow = new ArrayList();
      //List<Piece> blockLeftCol = new ArrayList<>();
      if (this.colorMapping().get(color).size() > 2) {
        if (this.swaps > 0) {
          matchLeft.add(color);
        } else {
          for (Piece piece : this.colorMapping().get(color)) {
            List piecesLeft = boardSearch(piece.row(), piece.col());
            if (piecesLeft.size() > 2) {
              matchLeft.add(color);
            }
          }
        }
      }
    }
    return (!matchLeft.isEmpty());
  }

  // track where every color appears on the board
  private HashMap<PieceType, List<Piece>> colorMapping() {
    HashMap<PieceType, List<Piece>> colorMap = new HashMap();

    List redPieces = new ArrayList<>();
    List greenPieces = new ArrayList<>();
    List bluePieces = new ArrayList<>();
    List yellPieces = new ArrayList<>();

    for (int row = 0; row < this.width(); row++) {
      for (int col = 0; col < this.length(); col++) {
        Piece piece = this.board.get(row).get(col);
        if (piece.color().equals(PieceType.RED)) {
          redPieces.add(new Piece(PieceType.RED, piece.row(), piece.col()));
        }
        if (piece.color().equals(PieceType.GREEN)) {
          greenPieces.add(new Piece(PieceType.GREEN, piece.row(), piece.col()));
        }
        if (piece.color().equals(PieceType.BLUE)) {
          bluePieces.add(new Piece(PieceType.BLUE, piece.row(), piece.col()));
        }
        if (piece.color().equals(PieceType.YELLOW)) {
          yellPieces.add(new Piece(PieceType.YELLOW, piece.row(), piece.col()));
        }
      }
      // list of where color -> row and color -> col
    }
    colorMap.put(PieceType.RED, redPieces);
    colorMap.put(PieceType.GREEN, greenPieces);
    colorMap.put(PieceType.BLUE, bluePieces);
    colorMap.put(PieceType.YELLOW, yellPieces);
    return colorMap;
  }

  /**
   * Initializes the board of the game with the correct order of pieces.
   * @param rows the number of rows in the board
   * @param cols the number of columns in the board
   * @param swaps the number of swaps allowed in the game
   * @param random true if the board should be setup randomly
   */
  @Override
  public void startGame(int rows, int cols, int swaps, boolean random) {
    if (this.gameStarted) {
      throw new IllegalStateException("Game already started");
    }
    if ((rows <= 0) || (cols <= 0)) {
      throw new IllegalArgumentException("Out of bounds.");
    }
    if (swaps <= 0) {
      throw new IllegalArgumentException("Cannot have negative swaps");
    }
    this.rows = rows;
    this.cols = cols;
    if (random) {
      this.randomBoard();
    }
    else {
      this.determBoard();
    }
    this.swaps = swaps;

    this.gameStarted = true;
  }

  // Generate a random board
  private void randomBoard() {
    this.board = new ArrayList<>();
    for (int rowNum = 0; rowNum < this.rows; rowNum++) {
      List<Piece> row = new ArrayList();
      for (int colNum = 0; colNum < this.cols; colNum++) {
        int idx = (int) Math.floor(Math.random() * this.createListOfPieces().length);
        row.add(this.createListOfPieces()[idx]);
      }
      this.board.add(row);
    }
  }


  // Generate a deterministic board
  private void determBoard() {
    int counter = 0;
    this.board = new ArrayList<>();
    for (int rowNum = 0; rowNum < rows; rowNum++) {
      List<Piece> row = new ArrayList();
      for (int colNum = 0; colNum < cols; colNum++) {
        PieceType newColor = this.createListOfPieces()[counter].color();
        row.add(new Piece(newColor, rowNum, colNum));
        if (counter < 3) {
          counter++;
        }
        else {
          counter = 0;
        }
      }
      this.board.add(row);
    }
  }

  /**
   * Generates a list of pieces in a particular order based on color.
   * @return a new list of pieces
   */
  @Override
  public Piece[] createListOfPieces() {
    Piece red = new Piece(PieceType.RED, 0, 0);
    Piece green = new Piece(PieceType.GREEN, 0, 1);
    Piece blue = new Piece(PieceType.BLUE, 0, 2);
    Piece yellow = new Piece(PieceType.YELLOW, 0, 3);
    return new Piece[]{red, green, blue, yellow};
  }

  /**
   * Initializes the game with a given board and number of swaps.
   * @param board the board to start the game with
   * @param swaps the number of swaps
   */
  @Override
  public void startGame(List<List<Piece>> board, int swaps) {
    if (gameStarted) {
      throw new IllegalStateException("Game already started");
    }
    if (board == null || board.isEmpty()) {
      throw new IllegalArgumentException("Board is null or empty");
    }
    this.rows = board.size();
    this.cols = board.get(0).size();

    List listCols = new ArrayList();

    int col = 0;
    for (List l1: board) {
      listCols.add(l1.size());
      col = col + l1.size();
    }
    if (listCols.size() != board.size()) {
      throw new IllegalArgumentException("Board has uneven number of rows");
    }
    if ((col / listCols.size()) != board.get(0).size()) {
      throw new IllegalArgumentException("Board has uneven number of columns");
    }
    if (swaps < 0) {
      throw new IllegalArgumentException("No remaining swaps");
    }

    this.board = new ArrayList<>();

    for (int rowNum = 0; rowNum < this.rows; rowNum++) {
      List newRow = new ArrayList();
      for (int colNum = 0; colNum < this.cols; colNum++) {
        if (board.get(rowNum).get(colNum) == null) {
          newRow.add(new Piece(PieceType.EMPTY, rowNum, colNum));
        }
        else {
          newRow.add(new Piece(board.get(rowNum).get(colNum).color(), rowNum, colNum));
        }
      }
      this.board.add(newRow);
    }

    this.swaps = swaps;
    this.rows = board.size();
    this.cols = board.get(0).size();

    this.gameStarted = true;
  }

}
