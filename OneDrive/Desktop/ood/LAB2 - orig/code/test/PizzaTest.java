import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pizza.AlaCartePizza;
import pizza.CheesePizza;
import pizza.Pizza;
import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;
import betterpizza.ObservablePizza;

import static org.junit.Assert.assertEquals;

/**
 * The test class for pizzas.
 */
public class PizzaTest {
  private Pizza alacarte;
  private Pizza cheese;
  private Pizza halfCheese;


  @Before
  public void setup() {
    alacarte = new AlaCartePizza(Size.Medium,Crust.Classic);
    alacarte.addTopping(ToppingName.Cheese, ToppingPortion.Full);
    alacarte.addTopping(ToppingName.Sauce,ToppingPortion.Full);
    alacarte.addTopping(ToppingName.GreenPepper,ToppingPortion.Full);
    alacarte.addTopping(ToppingName.Onion,ToppingPortion.Full);
    alacarte.addTopping(ToppingName.Jalapeno,ToppingPortion.LeftHalf);

    cheese = new CheesePizza(Size.Large,Crust.Thin);

    halfCheese = new CheesePizza(Size.Large,Crust.Thin);
    //put cheese only on left half
    halfCheese.addTopping(ToppingName.Cheese,ToppingPortion.LeftHalf);
  }

  @Test
  public void testCost() {
    assertEquals(8.25,alacarte.cost(),0.01);
    assertEquals(9,cheese.cost(),0.01);
    assertEquals(8.5,halfCheese.cost(),0.01);

  }

  @Test
  public void testBuilder() {
    ObservablePizza alacarte = new betterpizza.AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce,ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper,ToppingPortion.Full)
            .addTopping(ToppingName.Onion,ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno,ToppingPortion.LeftHalf)
            .build();

    ObservablePizza cheese = new betterpizza.CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    Assert.assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Onion));
    Assert.assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Cheese));
  }



}