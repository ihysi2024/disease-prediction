package cs3500.samegame.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.samegame.model.hw02.SameGameModel;
import cs3500.samegame.view.SameGameTextView;

/**
 * Represents the SameGame Controller. Used to interact with the model
 * and translate user instructions to the model view.
 * @param <Piece> what the MVC relies on for the game to work.
 */

public class SameGameTextController<Piece> implements SameGameController<Piece> {
  private Readable readable;
  private Appendable appendable;

  private boolean quit;

  /**
   * Represents the SameGame controller that reads user input and transmits a corresponding output.
   * @param rd user input in the form of a readable.
   * @param ap model output in the form of an appendable.
   * @throws IllegalStateException if the controller cannot read input or transmit output.
   */
  public SameGameTextController(Readable rd, Appendable ap) throws IllegalStateException {
    if ((rd == null) || (ap == null)) {
      throw new IllegalArgumentException("Input and Output are empty");
    }
    this.readable = rd;
    this.appendable = ap;

  }


  // the call to render if the user quits
  private void visualizeQuit(SameGameModel<Piece> model, SameGameTextView<Piece> view) {
    if (this.quit) {
      writeMessage("Game quit!");
      writeMessage("State of game when quit:");
      visualizeBoard(model, view);
      return;
    }
  }

  // the call to render if the game is over
  private void visualizeGameOver(SameGameModel<Piece> model, SameGameTextView<Piece> view) {
    if (model.gameOver()) {
      writeMessage("Game over.");
      visualizeBoard(model, view);
    }
  }

  // how the controller responds to user moves
  private void respondToUserMove(SameGameModel<Piece> model, Scanner sc,
                                 SameGameTextView<Piece> view) {
    String userMove = "";
    int swapFromCol;
    int removeCol;
    int removeRow;
    int swapFromRow;
    int swapToRow;
    int swapToCol;

    while (!this.quit && !model.gameOver()) {
      try {
        userMove = sc.next();
      }
      catch (NoSuchElementException e) {
        throw new IllegalStateException("Readable failure");
      }
      switch (userMove) {
        case "q":
        case "Q":
          this.quit = true;
          break;
        case "m":
          try {
            removeRow = this.nextInt(sc) - 1;
            if (this.quit) {
              continue;
            }
            removeCol = this.nextInt(sc) - 1;
            if (this.quit) {
              continue;
            }
            model.removeMatch(removeRow, removeCol);
          } catch (IllegalStateException | IllegalArgumentException matchE) {
            writeMessage("Invalid move. Try again. "
                    + matchE.getMessage());
          }
          break;
        case "s":
          swapFromRow = this.nextInt(sc) - 1;
          if (this.quit) {
            continue;
          }
          swapFromCol = this.nextInt(sc) - 1;
          if (this.quit) {
            continue;
          }
          swapToRow = this.nextInt(sc) - 1;
          if (this.quit) {
            continue;
          }
          swapToCol = this.nextInt(sc) - 1;
          if (this.quit) {
            continue;
          }
          try {
            model.swap(swapFromRow, swapFromCol, swapToRow, swapToCol);
          }
          catch (IllegalStateException | IllegalArgumentException swapE) {
            writeMessage("Invalid move. Try again. " + swapE.getMessage());
          }
          break;
        default:
          writeMessage("Invalid command. Try again. " + userMove + " not allowed.");
      }
      visualizeBoard(model, view);
    }
  }

  // iterate through the scanner until a valid positive integer appears or user has quit
  private int nextInt(Scanner sc) {
    String item = "";
    int itemInt = -1;
    try {
      item = sc.next();
    }
    catch (NoSuchElementException e) {
      throw new IllegalStateException("No more elements");
    }
    while (!item.equalsIgnoreCase("q")) {
      try {
        itemInt = Integer.parseInt(item);
      }
      catch (NumberFormatException e) {
        // do nothing
      }
      if (itemInt >= 0) {
        return itemInt;
      } else {
        item = sc.next();
      }
    }
    this.quit = true;
    return -1;
  }

  // the call to render for every user move
  private void visualizeBoard(SameGameModel<Piece> model, SameGameTextView<Piece> view) {
    try {
      view.render();
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    writeMessage("Remaining swaps: " + model.remainingSwaps());
    writeMessage("Score: " + model.score());
  }

  /**
   * Allows the user to start and play the game.
   * @param model model of the current game
   * @param rows number of rows in the board
   * @param cols number of cols in the board
   * @param swaps number of swaps allowed in the game
   * @param isRandom true if the board should be setup randomly
   * @throws IllegalStateException if the user instructions cannot be read.
   */

  @Override
  public void playGame(SameGameModel<Piece> model, int rows, int cols, int swaps, boolean isRandom)
          throws IllegalStateException {
    this.quit = false;
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    Scanner sc = new Scanner(this.readable);
    try {
      model.startGame(rows, cols, swaps, isRandom);
    }
    catch (IllegalStateException | IllegalArgumentException startE) {
      throw new IllegalArgumentException("Game cannot be started");
    }

    visualization(model, sc);

  }

  /**
   * Starts and plays the game through user feedback passed into the controller.
   * @param model model of the current game
   * @param board the board to start the game with
   * @param swaps  the number of swaps allowed in the game
   * @throws IllegalStateException if user input or output can't be transmitted
   */

  // start and play the game with a given board
  public void playGame(SameGameModel<Piece> model, List<List<Piece>> board,
                       int swaps) throws IllegalStateException {

    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    Scanner sc = new Scanner(this.readable);
    try {
      model.startGame(board, swaps);
    }
    catch (IllegalStateException | IllegalArgumentException e) {
      throw new IllegalArgumentException("Game cannot be started");
    }
    visualization(model, sc);

  }

  private void visualization(SameGameModel<Piece> model, Scanner sc) {
    SameGameTextView<Piece> view = new SameGameTextView<Piece>(model, this.appendable);

    respondToUserMove(model, sc, view);

    visualizeGameOver(model, view);

    visualizeQuit(model, view);
  }

  // helper to append messages to the appendable for the view
  protected void writeMessage(String message) throws IllegalArgumentException {
    try {
      appendable.append(message + System.lineSeparator());

    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}