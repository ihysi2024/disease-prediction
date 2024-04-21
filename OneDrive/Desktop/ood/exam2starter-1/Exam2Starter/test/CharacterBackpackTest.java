import org.junit.Test;

import java.util.Map;

import rpgshopping.Backpack;
import rpgshopping.CharacterBackpack;
import rpgshopping.Item;

import static org.junit.Assert.assertEquals;

public class CharacterBackpackTest {

  @Test
  public void testEquals() {
    Item sapphire = new Item("sapphire", 75);

    sapphire.price();
  }



  @Test
  public void test1() {
    Backpack pc = new CharacterBackpack();
    double itemWorth = 0.0;
    Item sapphire = new Item("sapphire", 75);
    pc.addItemToInventory(sapphire, 5);
    itemWorth += 5 * 75;

    Item ruby = new Item("ruby", 25);
    pc.addItemToInventory(ruby, 10);
    itemWorth += 10 * 25;

    pc.addCurrency(175.35);
    itemWorth += 175.35;

    assertEquals(itemWorth, pc.backpackWorth(), 0.001);
  }

  @Test
  public void test2() {
    Backpack pc = new CharacterBackpack();
    double itemWorth = 0.0;
    Item sapphire = new Item("sapphire", 75);
    pc.addItemToInventory(sapphire, 5);
    itemWorth += 5 * 75;

    Item ruby = new Item("ruby", 25);
    pc.addItemToInventory(ruby, 10);
    itemWorth += 10 * 25;

    pc.addCurrency(175);

    Map<Item, Integer> contents = pc.contents();
    assertEquals(2, contents.size());

    double worth = 0.0;
    for(Item key : contents.keySet()) {
      int amt = contents.get(key);
      worth += (amt * key.price());
    }

    assertEquals(itemWorth, worth, 0.001);
  }

  @Test
  public void test3() {
    Backpack pc = new CharacterBackpack();
    double itemWorth = 0.0;
    Item sapphire = new Item("sapphire", 75);
    pc.addItemToInventory(sapphire, 5);
    itemWorth += 5 * 75;

    Item ruby = new Item("negative-ruby", -25.5);
    pc.addItemToInventory(ruby, 3);
    itemWorth += 3 * -25.5;

    assertEquals(itemWorth, pc.backpackWorth(), 0.001);
  }

  @Test
  public void test4() {
    Backpack pc = new CharacterBackpack();
    double itemWorth = 0.0;
    Item sapphire = new Item("sapphire", 75);
    pc.addItemToInventory(sapphire, 5);
    itemWorth += 5 * 75;

    pc.addItemToInventory(sapphire, -4);
    itemWorth += -4 * 75;

    assertEquals(itemWorth, pc.backpackWorth(), 0.001);
  }
}
