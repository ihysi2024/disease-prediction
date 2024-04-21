package listadt;

/**
 * Represents an immutable list.
 */
public interface ImmutableListADT<T> extends CommonListADT<T> {

  /**
   * Get mutable list.
   * @return a mutable list.
   */
  public MutableListADT<T> getMutableList();

}
