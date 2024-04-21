package listadt;

import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements
 * the listadt.ListADT interface
 */
public class ListADTImpl<T> implements ListADT<T> {
  private GenericListADTNode<T> head;

  /**
   * Represents the constructor for a list.
   */
  public ListADTImpl() {
    head = new GenericEmptyNode();
  }

  //a private constructor that is used internally (see map)
  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * Add an element to the front of the list.
   * @param b the object to be added to the front of this list
   */
  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  /**
   * Add an element to the back of the list.
   * @param b the object to be added to teh back of this list
   */
  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  /**
   * Add an element to the list.
   * @param index the index to be occupied by this object, beginning at 0
   * @param b the object to be added to the list
   */
  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  /**
   * Get the size of the list.
   * @return an integer representing the size.
   */
  @Override
  public int getSize() {
    return head.count();
  }

  /**
   * Remove an element from the list.
   * @param b the object to be removed
   */

  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  /**
   * Get an element from a list at a certain index.
   * @param index the index of the object to be returned
   * @return an element.
   * @throws IllegalArgumentException get an index of a list
   */

  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    }
    throw new IllegalArgumentException("Invalid index");

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
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ListADTImpl(head.map(converter));
  }

  /**
   * Convert to a string.
   * @return a string.
   */

  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}
