import java.util.ArrayList;
import java.util.List;

/**
 * Represents the decorator pattern.
 */
public class NewDecorator extends PillCounterDecorator {

  List<Integer> totalCount;

  /**
   * Takes in a PillCounter to delegate its functionality to.
   * @param delegate PillCounter to delegate to.
   */
  public NewDecorator(PillCounter delegate) {
    super(delegate);
    this.totalCount = new ArrayList<>();
  }

  /**
   * Resets the counter.
   */

  @Override
  public void reset() {
    totalCount.add(super.getPillCount());
    super.reset();
  }

  /**
   * Gets the total number of times addCount has been called.
   * @return the total number of times called.
   */
  public List<Integer> getTotalCount() {
    List<Integer> temp = new ArrayList<>();
    temp.addAll(totalCount);
    return this.totalCount;
  }

}
