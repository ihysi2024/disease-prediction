package cs3500.samegame.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs3500.samegame.model.hw02.SameGameModel;
import cs3500.samegame.view.SameGameTextView;

public class SameGameTextController<T> implements SameGameController<T> {
  private Readable readable;
  private Appendable appendable;

  private boolean quit;
  public SameGameTextController(Readable rd, Appendable ap) {
    if ((rd == null) || (ap == null)) {
      throw new IllegalArgumentException("Input and Output are empty");
    }
    this.readable = rd;
    this.appendable = ap;

  }

  @Override
  public void playGame(SameGameModel<T> model, int rows, int cols, int swaps, boolean isRandom)
          throws IllegalStateException {



    this.quit = false;
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    // start the game
    try {
      model.startGame(rows, cols, swaps, isRandom);
    }
    catch (Exception startE) {
      writeMessage("Error: " + startE.getMessage() + System.lineSeparator());
    }

    // generate a view of the game with the current appendable
    SameGameTextView view = new SameGameTextView(model, this.appendable);

    // collect the user input through a scanner
    Scanner sc = new Scanner(this.readable);

    // responding to user instructions
    respondToUserMoves(model, sc, view);

    // visualizing the board if the game is over
    visualizeGameOver(model, view);

    // visualizing the board if the user has quit
    visualizeQuit(model, view);

  }

  private void visualizeQuit(SameGameModel<T> model, SameGameTextView view) {
    if (this.quit) {
      writeMessage("Game quit!" + System.lineSeparator());
      writeMessage("State of game when quit:" + System.lineSeparator());
      visualizeBoard(model, view);
      return;
    }
  }

  private void respondToUserMoves(SameGameModel<T> model, Scanner sc, SameGameTextView view) {
    int removeRow;
    int swapFromRow;
    int swapFromCol;
    int swapToRow;
    int removeCol;
    int swapToCol;
    // while the user has not quit and the game is not over
    while (!this.quit && !model.gameOver() && sc.hasNext()) {
      // collect the kind of move the user wants to make
       // checks if the next element is a letter
      String userMove = sc.next();
      System.out.println(userMove);
      switch (userMove) { // switches between different allowable letters
        case "quit":
        case "q":
          this.quit = true;
          break;
        case "m":  // if the user wants to try removing a match
          try {

            // collect the indices of the piece to try removing

            removeRow = this.nextInt(sc) - 1;
            removeCol = this.nextInt(sc) - 1;
            model.removeMatch(removeRow, removeCol);
          } catch (Exception matchE) {
            writeMessage("Invalid move. Try again. " + matchE.getMessage() + System.lineSeparator());
          }
          break;
        case "s": // if the user wants to swap
          try {
            swapFromRow = this.nextInt(sc) - 1;
            swapFromCol = this.nextInt(sc) - 1;
            swapToRow = this.nextInt(sc) - 1;
            swapToCol = this.nextInt(sc) - 1;

            model.swap(swapFromRow, swapFromCol, swapToRow, swapToCol);
          } catch (Exception swapE) {
            writeMessage("Invalid move. Try again. " + swapE.getMessage() + System.lineSeparator());
          }
          break;
        default:
          System.out.println("default case");
          writeMessage("Invalid command. Try Again. " + userMove + " not allowed.");
      }
      // try attaching the board view to the appendable
      visualizeBoard(model, view);
    }
  }


  private int nextInt(Scanner sc) {

    while (!sc.hasNextInt()) {
      String itemQuit = sc.next();
      if ((itemQuit.equals("q")) || (itemQuit.equals("quit"))) {
        this.quit = true;
        break;
      }
    }

    return Integer.parseInt(sc.next());
  }

  private void visualizeBoard(SameGameModel<T> model, SameGameTextView view) {
    try {
      view.render();
    } catch (Exception e) {
      throw new IllegalStateException();
    }
    writeMessage("Remaining swaps: " + model.remainingSwaps() + System.lineSeparator());
    writeMessage("Score: " + model.score() + System.lineSeparator());
  }


  @Override
  public void playGame(SameGameModel<T> model, List<List<T>> board, int swaps) throws IllegalStateException {
    boolean quit = false;
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    // start the game
    Scanner sc = new Scanner(this.readable);
    try {
      model.startGame(board, swaps);
    }
    catch (Exception e) {
      throw new IllegalStateException("Game cannot be started");
    }
    // generate a view of the game with the current appendable
    SameGameTextView view = new SameGameTextView(model, this.appendable);

    // controller responding to different user instructions
    respondToUserMoves(model, sc, view);

    // visualizing the board if the game is over
    visualizeGameOver(model, view);

    // visualizing the board if the user has quit
    visualizeQuit(model, view);

  }

  private void visualizeGameOver(SameGameModel<T> model, SameGameTextView view) {
    if (model.gameOver()) {
      writeMessage("Game Over" + System.lineSeparator());
      visualizeBoard(model, view);
    }
  }

  protected void writeMessage(String message) throws IllegalArgumentException {
    try {
      appendable.append(message);

    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}