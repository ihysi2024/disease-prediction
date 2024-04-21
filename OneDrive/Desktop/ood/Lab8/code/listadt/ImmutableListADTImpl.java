package listadt;

import java.util.function.Function;

/**
 * Represents an immutable list.
 * @param <T> a parameter of type T.
 */
public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {
  ListADTImpl<T> listRef;

  /**
   * Represents an immutable list that has a generic list reference.
   * @param listRef generic list
   */
  public ImmutableListADTImpl(ListADTImpl listRef) {
    this.listRef = listRef;
  }

  /**
   * A general map higher order function on nodes. This returns a list of
   * identical structure, but each data item of type T converted into R using
   * the provided converter method.
   *
   * @param converter the function needed to convert T into R
   * @param <R>       the type of the data in the returned list
   * @return the head of a list that is structurally identical to this list,
   *          but contains data of type R
   */

  @Override
  public <R> CommonListADT<R> map(Function<T, R> converter) {
    return null;
  }

  /**
   * Return the number of objects in this list.
   *
   * @return the size of this list
   */

  @Override
  public int getSize() {
    return 0;
  }

  /**
   * Get the object at the specified index, with 0 meaning the first object in
   * this list.
   *
   * @param index the specified index
   * @return the object at the specified index
   * @throws IllegalArgumentException if an invalid index is passed
   */

  @Override
  public T get(int index) throws IllegalArgumentException {
    return null;
  }

  /**
   * Get a generic list of nodes.
   * @return a list of nodes
   */

  @Override
  public MutableListADT<T> getMutableList() {
    return null;
  }

  private void addBack() {
    // dummy function
  }

  /**
   * Builder for an immutable list.
   */
  public class ImmutableBuilder {

    ListADTImpl<T> listRef;

    /**
     * Add an element to the back of a list.
     * @param b parameter type
     * @return a builder
     */
    public ImmutableBuilder addBack(T b) {
      this.listRef.addBack(b);
      return this;
    }

    /**
     * Immutable list build.
     * @return an immutable list.
     */
    public ImmutableListADTImpl<T> build() {
      return new ImmutableListADTImpl<>(listRef);
    }

  }
}
