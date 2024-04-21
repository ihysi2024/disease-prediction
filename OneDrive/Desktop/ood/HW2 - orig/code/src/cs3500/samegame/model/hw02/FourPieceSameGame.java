package cs3500.samegame.model.hw02;



import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
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
    if (this.gameOver()) {
      throw new IllegalStateException("Game is over.");
    }
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not started");
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
      throw new IllegalArgumentException("Move not allowed. Pieces must be located "
              + "on same row or column");
    }
    if ((fromRow == toRow) && (fromCol == toCol)) {
      throw new IllegalArgumentException("Switching the same piece is not allowed");
    }
    if (this.swaps <= 0) {
      throw new IllegalStateException("No more swaps left!");
    }

    this.hasSwapped = true;
    Piece fromPiece = this.board.get(fromRow).get(toRow);
    Piece toPiece = this.board.get(fromRow).get(toRow);
    PieceType fromColor = this.board.get(fromRow).get(fromCol).color();
    PieceType toColor = this.board.get(toRow).get(toCol).color();
    int initCol = this.board.get(fromRow).get(fromCol).col();
    int endCol = this.board.get(toRow).get(toCol).col();
    int initRow = this.board.get(fromRow).get(fromCol).row();
    int endRow = this.board.get(toRow).get(toCol).row();

    if (fromRow == toRow) {
      this.board.get(fromRow).get(fromCol).changeColor(toColor);
      this.board.get(toRow).get(toCol).changeColor(fromColor);
    }
    if (fromCol == toCol) {
      this.board.get(fromRow).get(fromCol).changeColor(toColor);
      this.board.get(toRow).get(toCol).changeColor(fromColor);
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
    PieceType color = this.board.get(row).get(col).color();
    List<Piece> connectedPiece = new ArrayList();
    Stack<Piece> alreadySeen = new Stack<>();
    connectedPiece.add(this.board.get(row).get(col));
    alreadySeen.push(this.board.get(row).get(col));
    if (this.gameOver()) {
      throw new IllegalStateException("Game is over.");
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

    // make sure you stop searching the grid when there are no more connecting pieces
    // of the same color
    while (!alreadySeen.isEmpty()) {
      Piece p = connectedPiece.get(connectedPiece.size() - 1);
      // FOCUSING ON PIECES IN THE CENTER
      if ((p.row() > 0) && (p.col() > 0)
              && (p.row() < this.width() - 2) && (p.col() < this.length() - 2)) {
        // CHECK IF ANY PIECES ADJACENT TO THE ROW HAVE THE SAME COLOR
        for (int rowNum = p.row() - 1; rowNum < p.row() + 2; rowNum++) {
          searchAndAdd(p, p.col(), rowNum, color, connectedPiece, alreadySeen);
        }
        // CHECK IF ANY PIECES ADJACENT TO THE COLUM HAVE THE SAME COLOR
        for (int colNum = p.col() - 1; colNum < p.col() + 2; colNum++) {
          searchAndAdd(p, colNum, p.row(), color, connectedPiece, alreadySeen);
        }
      }
      else {
        // FOCUSING ON TOP AND BOTTOM COLUMNS
        boardEdges(p.col(), p.row(), this.length(), this.width(),
                p, color, connectedPiece, alreadySeen);
        // FOCUSING ON LEFT AND RIGHT COLUMNS
        boardEdges(p.row(), p.col(), this.width(), this.length(),
                p, color, connectedPiece, alreadySeen);

        cornerPieces(col, p, color, connectedPiece, alreadySeen);
      }
      alreadySeen.pop();
    }
    this.hasRemoved = true;
    makeEmpty(connectedPiece);
  }

  // Check if the given piece is the right color, and add it to the corresponding lists
  private void searchAndAdd(Piece piece, int col, int rowNum,
                            PieceType color, List<Piece> connectedPiece, Stack<Piece> alreadySeen) {
    if (this.board.get(rowNum).get(col).color().equals(color)) {
      if (!connectedPiece.contains(this.board.get(rowNum).get(col))) {
        connectedPiece.add(this.board.get(rowNum).get(col));
        alreadySeen.push(this.board.get(rowNum).get(col));
      }
    }
  }
  // Because border tiles only have THREE adjacent tiles, check the 3 adjacent tiles
  // I.E if the tile is on the top or bottom, only check the second row or the second-to-last row
  // but check the columns normally
  // if the tile is on the far left or right, only check the right-adjacent or left-adjacent tile
  // but check the rows normally
  private void boardEdges(int firstEdge, int secondEdge, int firstBound, int secondBound,
                          Piece p, PieceType color, List<Piece> connectedPiece,
                          Stack<Piece> alreadySeen) {

    if ((firstEdge != 0) && (firstEdge != firstBound - 1)) {
      if ((secondEdge == 0)) {
        searchAndAdd(p, firstEdge, 1, color, connectedPiece, alreadySeen);
      }
      if ((secondEdge == secondBound - 1)) {
        searchAndAdd(p, firstEdge, secondBound - 2, color, connectedPiece, alreadySeen);
      }
      for (int rowNum = firstEdge - 1; rowNum < firstEdge + 2; rowNum++) {
        searchAndAdd(p, rowNum, secondEdge, color, connectedPiece, alreadySeen);
      }
    }
    if ((secondEdge != 0) && (secondEdge != secondBound - 1)) {
      if ((firstEdge == 0)) {
        searchAndAdd(p, secondEdge, 1, color, connectedPiece, alreadySeen);
      }
      if ((firstEdge == firstBound - 1)) {
        searchAndAdd(p, secondEdge, firstBound - 2, color, connectedPiece, alreadySeen);
      }
      for (int colNum = secondEdge - 1; colNum < secondEdge + 2; colNum++) {
        searchAndAdd(p, colNum, firstEdge, color, connectedPiece, alreadySeen);
      }
    }
  }


  // Because corner pieces only have TWO adjacent tiles, check those 2 Adjacent tiles
  private void cornerPieces(int col, Piece p, PieceType color, List<Piece> connectedPiece,
                            Stack<Piece> alreadySeen) {
    // top-left corner
    if ((p.row() == 0) && (p.col() == 0)) {
      searchAndAdd(p, 0, 1, color, connectedPiece, alreadySeen);
      searchAndAdd(p, 1, 0, color, connectedPiece, alreadySeen);
    }
    // top-right corner
    if ((p.row() == 0) && (p.col() == this.length() - 1)) {
      searchAndAdd(p, this.length() - 1, 1, color, connectedPiece, alreadySeen);
      searchAndAdd(p, this.length() - 2, 0, color, connectedPiece, alreadySeen);
    }
    // bottom-left corner
    if ((p.row() == this.width() - 1) && (p.col() == 0)) {
      searchAndAdd(p, col, this.length() - 2, color, connectedPiece, alreadySeen);
      searchAndAdd(p, 1, this.length() - 1, color, connectedPiece, alreadySeen);
    }
    // bottom-right corner
    if ((p.row() == this.width() - 1) && (p.col() == this.length() - 1)) {
      searchAndAdd(p, this.length() - 1, this.width() - 2, color, connectedPiece,
              alreadySeen);
      searchAndAdd(p, this.length() - 2, this.width() - 1, color, connectedPiece,
              alreadySeen);
    }
  }

  // Once you have a list of all connecting pieces of the same color, make them 'X's and raise
  // the score ONLY if there are more than 3 connecting pieces
  private void makeEmpty(List<Piece> connectedPiece) {
    if (connectedPiece.size() > 2) {
      for (Piece p: connectedPiece) {
        p.changeColor(PieceType.EMPTY);
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
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    if (col > this.length() || (col < 0)) {
      throw new IllegalArgumentException("Column out of bounds");
    }
    if (row > this.width() || (row < 0)) {
      throw new IllegalArgumentException("Row out of bounds");
    }
    if (this.board.get(row).get(col) == null) {
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
    List matchLeft = new ArrayList();
    while (matchLeft.isEmpty()) {
      for (PieceType color: this.colorMapping().keySet()) {
        if (this.colorMapping().get(color).size() >= 3) {
          if (this.swaps > 0) {
            matchLeft.add(color);
          }
        }
      }
    }
    return (!matchLeft.isEmpty());
  }

  // track where every color appears on the board
  private HashMap<PieceType, List<Piece>> colorMapping() {
    HashMap<PieceType, List<Piece>> colorMap = new HashMap();

    List<Piece> redList = new ArrayList();
    List<Piece> greenList = new ArrayList();
    List<Piece> blueList = new ArrayList();
    List<Piece> yellList = new ArrayList();

    for (int row = 0; row < this.width(); row++) {
      for (int col = 0; col < this.length(); col++) {
        Piece piece = this.board.get(row).get(col);
        if (piece.color().equals(PieceType.RED)) {
          redList.add(piece);
        }
        if (piece.color().equals(PieceType.GREEN)) {
          greenList.add(piece);
        }
        if (piece.color().equals(PieceType.BLUE)) {
          blueList.add(piece);
        }
        if (piece.color().equals(PieceType.YELLOW)) {
          yellList.add(piece);
        }
      }
      // list of where color -> row and color -> col
    }
    colorMap.put(PieceType.RED, redList);
    colorMap.put(PieceType.GREEN, greenList);
    colorMap.put(PieceType.BLUE, blueList);
    colorMap.put(PieceType.YELLOW, yellList);
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
    if (random) {
      this.randomBoard();
    }
    else {
      this.determBoard();
    }
    this.rows = rows;
    this.cols = cols;
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
    if (listCols.size() != this.width()) {
      throw new IllegalArgumentException("Board has uneven number of rows");
    }
    if ((col / listCols.size()) != this.length()) {
      throw new IllegalArgumentException("Board has uneven number of columns");
    }
    if (swaps < 0) {
      throw new IllegalArgumentException("No remaining swaps");
    }

    // TODO: add new pieces to board
    List<List<Piece>> newBoard = new ArrayList();
    for (List<Piece> l1: board) {
      List newRow = new ArrayList();
      for (Piece piece: l1) {
        newRow.add(piece);
      }
      newBoard.add(newRow);
    }

    this.board = newBoard;
    this.swaps = swaps;
    this.rows = board.size();
    this.cols = board.get(0).size();

    this.gameStarted = true;
  }

}
