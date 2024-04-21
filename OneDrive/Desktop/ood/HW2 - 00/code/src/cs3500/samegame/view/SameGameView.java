package cs3500.samegame.view;

import java.io.IOException;

public interface SameGameView<T> {
  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;

}