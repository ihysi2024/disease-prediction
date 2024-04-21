/**
 * Utilizes the decorator pattern for the pill counter to run it in batch.
 */
public class LazyAddPill extends PillCounterDecorator {

  private NewDecorator cumulativeDel;

  /**
   * Takes in a Pill Counter delegate.
   * @param delegate PillCounter to rely on.
   */
  public LazyAddPill(PillCounter delegate) {
    super(delegate);
    this.cumulativeDel = new NewDecorator(delegate);
  }

  /**
   * Only adds pills when getPillCount is called.
   * @return the number of pills in the bottle.
   */

  @Override
  public int getPillCount() {
    super.addPill(cumulativeDel.getTotalCount().get(cumulativeDel.getTotalCount().size()));
    super.reset();
    return cumulativeDel.getTotalCount().get(cumulativeDel.getTotalCount().size());
  }
}
