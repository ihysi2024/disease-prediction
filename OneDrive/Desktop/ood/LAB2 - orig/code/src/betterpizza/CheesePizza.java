package betterpizza;

import java.util.HashMap;

import pizza.AlaCartePizza;
import pizza.Crust;
import pizza.Size;

/**
 * Represents a cheese pizza.
 */
public class CheesePizza extends AlaCartePizza {

  /**
   * Create a pizza given its crust type, size and toppings.
   *
   * @param size represents a pizza size.
   * @param crust represents the pizza crust.
   */
  public CheesePizza(Size size, Crust crust) {
    super(size, crust);
  }

  /**
   * Represents the builder for the CheesePizza.
   */

  public static class CheesePizzaBuilder extends PizzaBuilder {

    public CheesePizzaBuilder() {
      super(null, null, new HashMap<>());
    }

    public ObservablePizza build() {
      return new CheesePizza(this.size, this.crust);
    }
  }
}
