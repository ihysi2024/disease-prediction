package betterpizza;

import java.util.HashMap;

import pizza.AlaCartePizza;
import pizza.Crust;
import pizza.Size;

/**
 * Represents a veggie pizza.
 */
public class VeggiePizza extends AlaCartePizza {

  /**
   * Create a pizza given its crust type, size and toppings.
   *
   * @param size the size of the pizza
   * @param crust the crust of the pizza
   */
  public VeggiePizza(Size size, Crust crust) {
    super(size, crust);
  }

  /**
   * Represents the veggie pizza builder.
   */
  public static class VeggiePizzaBuilder extends PizzaBuilder {

    public VeggiePizzaBuilder() {
      super(null, null, new HashMap<>());
    }

    public VeggiePizza build() {
      return new VeggiePizza(this.size, this.crust);
    }
  }
}