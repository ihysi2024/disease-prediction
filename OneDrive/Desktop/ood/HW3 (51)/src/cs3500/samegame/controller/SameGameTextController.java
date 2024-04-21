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
  public SameGameTextController(Readable rd, Appendable ap) throws IllegalStateException{
    if ((rd == null) || (ap == null)) {
      throw new IllegalArgumentException("Input and Output are empty");
    }
    this.readable = rd;
    this.appendable = ap;

  }
  //TODO Figure out negative inputs
  //TODO Invalid Command & Invalid Move warnings
  //TODO Wrong Exception thrown for invalid # of columns
  //TODO Check appendable & readable failure behavior


  @Override
  public void playGame(SameGameModel<T> model, int rows, int cols, int swaps, boolean isRandom)
          throws IllegalStateException {

    int removeRow;
    int removeCol;
    int swapFromRow;
    int swapFromCol;
    int swapToRow;
    int swapToCol;

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

    // while the user has not quit and the game is not over
    respondToUserMove(model, sc, view);

    visualizeBoard(model, view);

    visualizeGameOver(model, view);

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

  private void visualizeGameOver(SameGameModel<T> model, SameGameTextView view) {
    if (model.gameOver()) {
      writeMessage("Game over." + System.lineSeparator());
      visualizeBoard(model, view);
    }
  }

  private void respondToUserMove(SameGameModel<T> model, Scanner sc, SameGameTextView view) {
    int swapFromCol;
    int removeCol;
    int removeRow;
    int swapFromRow;
    int swapToRow;
    int swapToCol;
    while (!this.quit && !model.gameOver() && sc.hasNext()) {
      // collect the kind of move the user wants to make
       // checks if the next element is a letter
      String userMove = sc.next();
      //System.out.println(userMove);
      switch (userMove) { // switches between different allowable letters
        case "quit":
        case "q":
          this.quit = true;
          break;
        case "m":  // if the user wants to try removing a match
          try {

            // collect the indices of the piece to try removing
            // s -1 2 3 4
            removeRow = (int) this.nextInt(sc) - 1;
            removeCol = (int) this.nextInt(sc) - 1;
            model.removeMatch(removeRow, removeCol);
            // try and remove
          } catch (Exception matchE) {
            writeMessage("Invalid move. Try again. " + matchE.getMessage() + System.lineSeparator());
          }
          break;
        case "s": // if the user wants to swap
          System.out.println("here");
          try {
            // collect the indices of the two pieces to swap
            // s 1 a d d 2 3 4
            swapFromRow = (int) this.nextInt(sc) - 1; //1
           // System.out.println(sc);
            // 1
          //  System.out.println(swapFromRow);
            swapFromCol = (int) this.nextInt(sc) - 1;//2
            //   System.out.println(sc.hasNextInt());
            // 1
            swapToRow = (int) this.nextInt(sc) - 1;//3
           // System.out.println(sc.hasNextInt());
            swapToCol = (int) this.nextInt(sc) - 1;//4
            //   System.out.println(sc.hasNextInt());
            System.out.println("( " + swapFromRow + ", " + swapFromCol + ", "+ swapToRow + ", "+ swapToCol);

            model.swap(swapFromRow, swapFromCol, swapToRow, swapToCol);
          } catch (Exception swapE) {
            writeMessage("Invalid move. Try again. " + swapE.getMessage() + System.lineSeparator());
          }
          break;
        default:
          writeMessage("Invalid command. Try Again. " + userMove + " not allowed.");
      }
      visualizeGameOver(model, view);
      visualizeQuit(model, view);
      visualizeBoard(model, view);
    }
  }


  private Object nextInt(Scanner sc) {

    while (sc.hasNext()) {
      String item = sc.next();
      if ((item.equals("q")) || (item.equals("quit"))) {
        this.quit = true;
        break;
      }
      else {
        try {
          int itemInt = Integer.parseInt(item);
          if (itemInt >= 1) {
            return itemInt;
          }
          else {
            continue;
          }
        }
        catch (NumberFormatException e) {
          writeMessage("Try Again");
        }
      }
    }
    throw new IllegalStateException("No more input");
  }

  private void visualizeBoard(SameGameModel<T> model, SameGameTextView view) {
    if (!model.gameOver() && !this.quit) {
      try {
        view.render();
      } catch (Exception e) {
        throw new IllegalStateException();
      }
      writeMessage("Remaining swaps: " + model.remainingSwaps() + System.lineSeparator());
      writeMessage("Score: " + model.score() + System.lineSeparator());
    }
  }


  @Override
  public void playGame(SameGameModel<T> model, List<List<T>> board, int swaps) throws IllegalStateException {
    int fromRow;
    int toRow;
    int fromCol;
    int toCol;
    boolean startedGame = false;
    boolean quit = false;
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    // start the game
    Scanner sc = new Scanner(this.readable);
    try {
      model.startGame(board, swaps);
      startedGame = true;
    }
    catch (Exception e) {
      throw new IllegalStateException("Game cannot be started");
    }
    // generate a view of the game with the current appendable
    SameGameTextView view = new SameGameTextView(model, this.appendable);


    // while the user has not quit and the game is not over
    respondToUserMove(model, sc, view);

    visualizeGameOver(model, view);

    visualizeQuit(model, view);

  }

  protected void writeMessage(String message) throws IllegalArgumentException {
    try {
      appendable.append(message);

    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}