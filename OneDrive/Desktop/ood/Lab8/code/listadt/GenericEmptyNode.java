package listadt;

import java.util.function.Function;

/**
 * This represents an empty node of the generic list implementation.
 */
public class GenericEmptyNode<T> implements GenericListADTNode<T> {

  /**
   * Count the number of nodes.
   * @return int of nodes
   */

  @Override
  public int count() {
    return 0;
  }

  /**
   * Add an element to the front.
   * @param object the object to be added
   * @return a new generic list
   */


  @Override
  public GenericListADTNode<T> addFront(T object) {
    return new GenericElementNode(object,
            this);
  }

  /**
   * Add an element to the back.
   * @param object the object to be added
   * @return a new generic list
   */

  @Override
  public GenericListADTNode<T> addBack(T object) {
    return addFront(object);
  }

  /**
   * Add an element to the list.
   * @param index the position to be occupied by this object, starting at 0
   * @param object     the object to be added
   * @return a new generic list
   */

  @Override
  public GenericListADTNode<T> add(int index, T object) throws
          IllegalArgumentException {
    if (index == 0) {
      return addFront(object);
    }
    throw new IllegalArgumentException("Invalid index to add an element");
  }

  /**
   * Remove an element from the list.
   * @param object the object to be removed
   * @return a generic list
   */

  @Override
  public GenericListADTNode<T> remove(T object) {
    return this; //cannot remove from nothing!
  }

  /**
   * Return an element from a certain index in the list.
   * @param index the specified index
   * @return an element
   * @throws IllegalArgumentException if the index is not present.
   */

  @Override
  public T get(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("Wrong index");
  }

  /**
   * Starting from this list of T, the resulting list of type R is an
   * element that contains this data converted to T, followed by the rest of
   * the converted list.
   * @param converter the function needed to convert T into R
   * @return a map
   * @param <R> a type R
   */

  @Override
  public <R> GenericListADTNode<R> map(Function<T, R> converter) {
    return new GenericEmptyNode();
  }

  /**
   * Convert the list to a string.
   * @return a string
   */

  @Override
  public String toString() {
    return "";
  }
}
