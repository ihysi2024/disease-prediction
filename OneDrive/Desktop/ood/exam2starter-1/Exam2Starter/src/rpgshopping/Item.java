package rpgshopping;

import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;

/**
 * Wrapper for a pair of name and double. Represents
 * an item in a classic role-playing game.
 * Note this class assumes the price of an item
 * does not change.
 *
 * NOTE: The value of price is unrestricted, meaning
 * it can be 0 or less.
 */
public final class Item {
  private final String name;
  private final double price;

  /**
   * Creates an immutable Item object of the specified name and price
   * @param name the name of the item
   * @param pricePerItem the price of a single one of this item. Price can be negative
   * @throws IllegalArgumentException name is null
   */
  public Item(String name, double pricePerItem) throws IllegalArgumentException {
    if(name == null) {
      throw new IllegalArgumentException("name cannot be null");
    }
    this.name = name;
    this.price = pricePerItem;
  }

  /**
   * Returns the name of the item when it was created
   * @return the name of this object
   */
  public String name() {
    return this.name;
  }

  /**
   * Return the price of a single count of this item
   * @return the price of one of this item
   */
  public double price() {
    HashMap<String, Integer> mapping = new HashMap<>();
    mapping.put("ira", 5);
    mapping.put("ira", mapping.get("ira")+1);
    System.out.println(mapping.get("ira"));
    return this.price;
  }

  /**
   * Returns true if and only if 2 objects are equal to each other.
   * Two items are equal if they have the same name and the same price.
   *
   * Due to double comparisons, prices are considered equal if they
   * are within some epsilon. This can cause false positive for unlikely
   * scenarios.
   *
   * @param other the other item to compare to
   * @return true if and only if other has the same name and price as this item
   */
  @Override
  public boolean equals(Object other) {
    if(other == null || !(other instanceof Item)) {
      return false;
    }

    Item otherItem = (Item)other;
    double delta = this.price - otherItem.price();
    return this.name.equals(otherItem.name()) &&
        delta <= 0.001;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.price);
  }
}