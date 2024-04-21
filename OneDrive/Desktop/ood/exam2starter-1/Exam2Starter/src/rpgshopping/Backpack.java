package rpgshopping;

import java.util.Map;

/**
 * Behaviors for a backpack for any entity/character
 * in a role-playing game. A backpack is a container
 * of items and gold.
 *
 * As a currency, gold is subdivided into silver and copper.
 * As a unit, silver is a tenth of gold and copper is a hundredth of gold.
 * For example, 2.25 gold is 2 gold, 2 silver, 5 copper.
 *
 * This interface allows someone to add
 * items and gold separately rather than adding
 * gold as an item.
 */
public interface Backpack {

  /**
   * Adds some amount of an item to the inventory
   * @param item the item to add in question
   * @param amt the amount to be given. This amount can be negative.
   * @throws IllegalArgumentException item is null or if the value of amt would result in
   *     a negative number of items in the backpack
   */
  void addItemToInventory(Item item, int amt) throws IllegalArgumentException;

  /**
   * Adds some amount of gold to the backpack.
   * @param currency amount to add in terms of gold
   * @throws IllegalArgumentException if the amount of currency added results in negative gold
   */
  void addCurrency(double currency) throws IllegalArgumentException;

  /**
   * Returns the worth of the backpack in gold. This worth
   * includes all items in the backpack as well as the currency
   * stored within.
   * @return number representing the amount this backpack is worth in gold
   */
  double backpackWorth();

  /**
   * Returns the contents of this backpack as a map.
   * Modifying this map has no effect on the backpack itself.
   * @return map pairing an item in the backpack with its amount
   */
  Map<Item, Integer> contents();

}
