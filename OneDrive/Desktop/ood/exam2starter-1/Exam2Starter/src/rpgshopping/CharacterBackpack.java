package rpgshopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The backpack for some entity in the game.
 * Stores gold separately from the items.
 * The internal representation uses a list of custom
 * entries to allow for easy searching of the contents.
 *
 * While a Map is more efficient, the list allows for
 * more readable code if an implementer decides to
 * search.
 */
public class CharacterBackpack implements Backpack {
  private double currencyHeld;

  /**
   * An entry within the list of contents.
   * Wrapper around an item and how many of it exist
   * in the backpack.
   */
  private class ContentEntry {
    private Item item;
    private int amount;

    /**
     * Creates a new ContentEntry.
     * Assumes the item is non-null and every method in
     * this class prevents null items from being given.
     * @param item The item in the backpack
     * @param amt The amount of that item in the backpack
     * @throws IllegalArgumentException if amt is negative or item is null
     */
    ContentEntry(Item item, Integer amt) {
      if(item == null) {
        throw new IllegalArgumentException("item cannot be null");
      }
      this.item = item;
      if(amt < 0) {
        throw new IllegalArgumentException("Amount cannot be negative");
      }
      this.amount = amt;
    }
  }

  protected List<ContentEntry> contents;

  /**
   * Creates a backpack. This backpack has no
   * gold and no contents.
   */
  public CharacterBackpack() {
    this.contents = new ArrayList<>();
    this.currencyHeld = 0.0;
  }

  @Override
  public void addItemToInventory(Item item, int amt) throws IllegalArgumentException {
    if(item == null) {
      throw new IllegalArgumentException("item cannot be null");
    }

    for(ContentEntry entry : contents) {
      if(entry.item.equals(item)) {
        if(entry.amount + amt < 0) {
          throw new IllegalArgumentException("amt to remove cannot exceed the amount in backpack");
        }
        entry.amount += amt;
        return;
      }
    }

    if(amt < 0) {
      throw new IllegalArgumentException("amount cannot be negative if none of this item is in backpack");
    }
    contents.add(new ContentEntry(item, amt));
  }

  @Override
  public void addCurrency(double currency) {
    if(currency < 0 && this.currencyHeld < Math.abs(currency)) {
      throw new IllegalArgumentException("Cannot remove more currency than is in the backpack");
    }
    this.currencyHeld += currency;
  }

  @Override
  public double backpackWorth() {
    double worth = 0;
    for(ContentEntry entry : contents) {
      worth += (entry.item.price() * entry.amount);
    }
    return worth + currencyHeld;
  }

  @Override
  public Map<Item, Integer> contents() {
    Map<Item, Integer> result = new HashMap<>();
    for(ContentEntry entry : contents) {
      result.put(entry.item, entry.amount);
    }
    return result;
  }
}
