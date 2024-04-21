/**
 * Represents a Pill Counter decorator.
 */
public class PillCounterDecorator implements PillCounter {

  PillCounter delegate;

  /**
   * Takes in a delegate for all functionality.
   * @param delegate Pill Counter to delegate to.
   */
  public PillCounterDecorator(PillCounter delegate) {
    this.delegate = delegate;
  }

  /**
   * Adds a pill to the counter.
   * @param count number of pills to add.
   */
  @Override
  public void addPill(int count) {
    delegate.addPill(count);
  }

  /**
   * Removes a pill from the bottle.
   */

  @Override
  public void removePill() {
    delegate.removePill();
  }

  /**
   * Resets the number of pills in the bottle.
   */

  @Override
  public void reset() {
    delegate.reset();
  }

  /**
   * Observes the number of pills in the bottle.
   * @return the number of pills.
   */

  @Override
  public int getPillCount() {
    return delegate.getPillCount();
  }
}
