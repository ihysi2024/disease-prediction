package cs3500.simon.view;

import cs3500.simon.model.ReadOnlySimon;

import javax.swing.JFrame;

/**
 * Represents the class implementation of the Simon Says Game view interface.
 */
public class SimpleSimonView extends JFrame implements SimonView {
  private final JSimonPanel panel;

  /**
   * Represents the view constructor.
   * @param model the model to operate the view through
   */
  public SimpleSimonView(ReadOnlySimon model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new JSimonPanel(model);
    this.add(panel);
    this.pack();
  }

  @Override
  public void addFeatureListener(ViewFeatures features) {
    this.panel.addFeaturesListener(features);
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }

  @Override
  public void advance() {
    this.panel.advance();
  }

  @Override
  public void error() {
    this.panel.error();
  }
}
