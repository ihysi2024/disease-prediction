package cs3500.tictactoe;

import java.awt.*;

import javax.swing.*;

public class TTTPanel extends JPanel {

  private final ReadonlyTTTModel model;

  public TTTPanel(ReadonlyTTTModel model) {
    super();
    this.model = model;
  }

  @Override
  public void paintComponents(Graphics g) {
    Graphics2D g2d = (Graphics2D)g;

    g2d.drawLine(this.getWidth()/3, 0, this.getWidth()/3, this.getHeight());
  }
}
