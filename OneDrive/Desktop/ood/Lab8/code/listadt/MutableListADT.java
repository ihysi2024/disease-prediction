package listadt;

/**
 * Represents a mutable list.
 * @param <T> a parameter of type T.
 */
public interface MutableListADT<T> extends ListADT<T> {

  /**
   * Gets the immutable list.
   * @return an immutable list.
   */
  ImmutableListADT<T> getImmutableList();
}
