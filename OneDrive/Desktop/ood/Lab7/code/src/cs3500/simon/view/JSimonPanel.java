package cs3500.simon.view;

import cs3500.simon.model.ReadOnlySimon;
import cs3500.simon.model.ColorGuess;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;


/**
 * A JSimonPanel will draw all the colors, allow users to click on them,
 * and play the game.
 */
public class JSimonPanel extends JPanel {
  /**
   * Our view will need to display a model, so it needs to get the current sequence from the model.
   */
  private final ReadOnlySimon model;
  /**
   * We'll allow an arbitrary number of listeners for our events...even if
   * we happen right now to only expect a single listener.
   */
  private final List<ViewFeatures> featuresListeners;

  /**
   * INVARIANT: currentRoundOfColorGuesses is never empty.
   */
  private final Stack<ColorGuess> currentRoundOfColorGuesses;

  private boolean mouseIsDown;
  private ColorGuess activeColorGuess;

  private static final Map<ColorGuess, Point2D> CIRCLE_CENTERS = Map.of(
          ColorGuess.Red, new Point2D.Double(10, 0),
          ColorGuess.Yellow, new Point2D.Double(0, 10),
          ColorGuess.Green, new Point2D.Double(-10, 0),
          ColorGuess.Blue, new Point2D.Double(0, -10)
  );
  private static final Map<ColorGuess, Color> CIRCLE_COLORS = Map.of(
          ColorGuess.Red, Color.CYAN,
          ColorGuess.Blue, Color.MAGENTA,
          ColorGuess.Yellow, Color.ORANGE,
          ColorGuess.Green, Color.PINK
  );

  private static final double CIRCLE_RADIUS = 5;

  /**
   * Represents the Simon Game Panel view.
   * @param model model to operate the game on
   */

  public JSimonPanel(ReadOnlySimon model) {
    this.model = Objects.requireNonNull(model);
    this.featuresListeners = new ArrayList<>();
    this.currentRoundOfColorGuesses = new Stack<>();
    this.currentRoundOfColorGuesses.addAll(this.model.getCurrentSequence());
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);
  }

  /**
   * This method tells Swing what the "natural" size should be
   * for this panel.  Here, we set it to 400x400 pixels.
   * @return  Our preferred *physical* size.
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(350, 350);
  }

  /**
   * Conceptually, we can choose a different coordinate system
   * and pretend that our panel is 40x40 "cells" big. You can choose
   * any dimension you want here, including the same as your physical
   * size (in which case each logical pixel will be the same size as a physical
   * pixel, but perhaps your calculations to position things might be trickier)
   * @return Our preferred *logical* size.
   */
  private Dimension getPreferredLogicalSize() {
    return new Dimension(40, 40);
  }

  public void addFeaturesListener(ViewFeatures features) {
    this.featuresListeners.add(Objects.requireNonNull(features));
  }

  /**
   * Advances the next round of color guesses.
   */

  public void advance() {
    System.err.println("Yay!");
    this.currentRoundOfColorGuesses.pop();
    if (this.currentRoundOfColorGuesses.isEmpty()) {
      this.currentRoundOfColorGuesses.addAll(this.model.getCurrentSequence());
    }
    this.repaint();
  }

  /**
   * Represents an error in the color guesses.
   */

  public void error() {
    System.err.println("OOPS!");
    this.currentRoundOfColorGuesses.clear();
    this.currentRoundOfColorGuesses.addAll(this.model.getCurrentSequence());
    this.repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    Rectangle bounds = this.getBounds();
    for (ColorGuess c : ColorGuess.values()) {
      this.drawCircles(g2d,
              (int) CIRCLE_CENTERS.get(c).getX(),
              (int) CIRCLE_CENTERS.get(c).getY(),
              CIRCLE_COLORS.get(c),
              true);
    }
    this.drawCircles(g2d, 5, 4, Color.RED, true);
    g2d.setColor(Color.RED);
    g2d.drawLine(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
    g2d.setColor(Color.BLUE);
    g2d.drawLine(bounds.x + bounds.width, bounds.y, bounds.x, bounds.y + bounds.height);
    g2d.transform(transformLogicalToPhysical());
    //g2d.translate(350, 350);
    //g2d.scale(350, 350);
    // Draw your calibration pattern here
  }

  private void drawCircles(Graphics2D g2d, int x, int y, Color color, boolean fill) {
    AffineTransform oldTransform = g2d.getTransform();
    Rectangle bounds = this.getBounds();
    g2d.translate(x, y);
    Shape circle = new Ellipse2D.Double(
            -CIRCLE_RADIUS,     // left
            -CIRCLE_RADIUS,     // top
            2 * CIRCLE_RADIUS,  // width
            2 * CIRCLE_RADIUS); // height
    if (fill) {
      g2d.fill(circle);
    }
    else {
      g2d.draw(circle);
    }
    g2d.setTransform(oldTransform);

  }


  /**
   * Computes the transformation that converts board coordinates
   * (with (0,0) in center, width and height our logical size)
   * into screen coordinates (with (0,0) in upper-left,
   * width and height in pixels).
   * @return The necessary transformation
   */
  private AffineTransform transformLogicalToPhysical() {
    AffineTransform ret = new AffineTransform();
    Dimension preferred = getPreferredLogicalSize();
    ret.translate(getWidth() / 2., getHeight() / 2.);
    ret.scale(getWidth() / preferred.getWidth(), getHeight() / preferred.getHeight());
    ret.scale(1, -1);
    return ret;
  }

  /**
   * Computes the transformation that converts screen coordinates
   * (with (0,0) in upper-left, width and height in pixels)
   * into board coordinates (with (0,0) in center, width and height
   * our logical size).
   *
   * @return The necessary transformation
   */
  private AffineTransform transformPhysicalToLogical() {
    AffineTransform ret = new AffineTransform();
    Dimension preferred = getPreferredLogicalSize();
    ret.scale(1, -1);
    ret.scale(preferred.getWidth() / getWidth(), preferred.getHeight() / getHeight());
    ret.translate(-getWidth() / 2., -getHeight() / 2.);
    return ret;
  }

  private class MouseEventsListener extends MouseInputAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      JSimonPanel.this.mouseIsDown = true;
      this.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      JSimonPanel.this.mouseIsDown = false;
      if (JSimonPanel.this.activeColorGuess != null) {
        for (ViewFeatures listener : JSimonPanel.this.featuresListeners) {
          listener.selectedColor(JSimonPanel.this.activeColorGuess);
        }
      }
      JSimonPanel.this.activeColorGuess = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      // This point is measured in actual physical pixels
      Point physicalP = e.getPoint();
      // For us to figure out which circle it belongs to, we need to transform it
      // into logical coordinates
      Point2D logicalP = transformPhysicalToLogical().transform(physicalP, null);
      for (ColorGuess c : ColorGuess.values()) {
        if ((logicalP.getX() >= ((int) CIRCLE_CENTERS.get(c).getX() - CIRCLE_RADIUS))
            && (logicalP.getY() <= ((int) CIRCLE_CENTERS.get(c).getX() + CIRCLE_RADIUS))
            && ((logicalP.getY() >= ((int) CIRCLE_CENTERS.get(c).getY() - CIRCLE_RADIUS))
                  && (logicalP.getY() <= ((int) CIRCLE_CENTERS.get(c).getY() + CIRCLE_RADIUS)))) {
          activeColorGuess = c;
        }
        else {
          activeColorGuess = null;
        }
      }
      JSimonPanel.this.repaint();
      // TODO: Figure out whether this location is inside a circle, and if so, which one
    }
  }
}
