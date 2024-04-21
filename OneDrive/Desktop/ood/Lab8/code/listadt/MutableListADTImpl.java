package listadt;

import java.util.function.Function;

/**
 * Represents a mutable list.
 * @param <T> a parameter of type T
 */
public class MutableListADTImpl<T> extends ListADTImpl<T> implements MutableListADT<T> {

  /**
   * Represents a mutable list.
   * @param converter the function that converts T into R
   * @return a mutable list.
   */
  @Override
  public MutableListADTImpl map(Function converter) {
    return null;
  }

  /**
   * Get the size of the list.
   * @return an integer representing the size.
   */

  @Override
  public int getSize() {
    return 0;
  }

  /**
   * Get an element from a list at a certain index.
   * @param index the index of the object to be returned
   * @return an element.
   * @throws IllegalArgumentException if the index is wrong.
   */

  @Override
  public T get(int index) throws IllegalArgumentException {
    return null;
  }

  /**
   * Add an element to the front of the list.
   * @param b the object to be added to the front of this list
   */

  @Override
  public void addFront(Object b) {
    // dummy function
  }

  /**
   * Add an element to the back of the list.
   * @param b the object to be added to teh back of this list
   */

  @Override
  public void addBack(Object b) {
    // dummy function
  }

  /**
   * Add an element to the list.
   * @param index the index to be occupied by this object, beginning at 0
   * @param b the object to be added to the list
   */

  @Override
  public void add(int index, Object b) {
    // dummy function
  }


  /**
   * Remove an element from the list.
   * @param b the object to be removed
   */


  @Override
  public void remove(Object b) {
    // dummy function
  }

  /**
   * Gets an immutable list.
   * @return an immutable list.
   */

  @Override
  public ImmutableListADT<T> getImmutableList() {
    return null;
  }
}
