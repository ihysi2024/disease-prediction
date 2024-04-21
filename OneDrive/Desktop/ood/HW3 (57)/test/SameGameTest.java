import org.junit.Assert;
import org.junit.Test;

import cs3500.samegame.SameGame;

/**
 * Tests main exceptions.
 */
public class SameGameTest {

  /**
   * Tests main exceptions.
   */
  @Test
  public void testMainExceptions() {
    Assert.assertThrows(IllegalArgumentException.class, () -> SameGame.main(new String[]{"game"}));
    Assert.assertThrows(IllegalArgumentException.class, () -> SameGame.main(new String[]{}));
  }

}
