package listadt;

import java.util.function.Function;

/**
 * This is a non-empty node in a generic list. It contains the object data
 * and the rest of the list
 */
public class GenericElementNode<T> implements GenericListADTNode<T> {
  private T object;
  private GenericListADTNode<T> rest;

  /**
   * Represents the constructor for a generic element node.
   * @param p type P
   * @param rest rest of generic element node.
   */
  public GenericElementNode(T p, GenericListADTNode<T> rest) {
    this.object = p;
    this.rest = rest;
  }

  /**
   * Count the number of nodes.
   * @return int of nodes
   */
  @Override
  public int count() {
    return 1 + this.rest.count();
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
    this.rest = this.rest.addBack(object);
    return this;
  }

  /**
   * Add an element to the list.
   * @param index the position to be occupied by this object, starting at 0
   * @param object     the object to be added
   * @return a new generic list
   */
  @Override
  public GenericListADTNode<T> add(int index, T object) {
    if (index == 0) {
      return addFront(object);
    } else {
      this.rest = this.rest.add(index - 1, object);
      return this;
    }
  }

  /**
   * Remove an element from the list.
   * @param object the object to be removed
   * @return a generic list
   */
  @Override
  public GenericListADTNode<T> remove(T object) {
    if (this.object.equals(object)) {
      return this.rest;
    } else {
      this.rest = this.rest.remove(object);
      return this;
    }
  }

  /**
   * Return an element from a certain index in the list.
   * @param index the specified index
   * @return an element
   * @throws IllegalArgumentException if given a wrong index.
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.object;
    }
    return this.rest.get(index - 1);
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
    /* Starting from this list of T, the resulting list of type R is an
    element that contains this data converted to T, followed by the rest of
    the converted list
     */
    return new GenericElementNode(
            converter.apply(this.object),
            this.rest.map(converter));
  }

  /**
   * Convert the list to a string.
   * @return a string
   */

  @Override
  public String toString() {
    String objstring = this.object.toString();
    String rest = this.rest.toString();
    if (rest.length() > 0) {
      return objstring + " " + rest;
    }
    else {
      return objstring;
    }
  }
}
