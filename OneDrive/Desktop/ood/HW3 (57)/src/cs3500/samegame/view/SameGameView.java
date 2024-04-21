package cs3500.samegame.view;

import java.io.IOException;

/**
 * New view interface to pass to the user.
 * @param <T> represents what the game is based in.
 */
public interface SameGameView<T> {
  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   * @throws IOException if the rendering fails for some reason
   */
  void render() throws IOException;

}