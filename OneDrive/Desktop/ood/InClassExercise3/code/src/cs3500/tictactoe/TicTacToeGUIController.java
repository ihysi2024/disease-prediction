package cs3500.tictactoe;

public class TicTacToeGUIController implements TicTacToeController {
   private final TTTView view;
   private TicTacToe model;

   public TicTacToeGUIController(TTTView view) {
     this.view = view;
   }

   public void playGame(TicTacToe m) {
     this.model = m;
     this.view.addClickListener(this);
     this.view.makeVisible();
   }

   public void handleCellClick(int row, int col) {

   }
}
