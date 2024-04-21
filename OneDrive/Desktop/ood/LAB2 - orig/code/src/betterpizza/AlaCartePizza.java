package betterpizza;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents an ala carte pizza (i.e. a pizza that can
 * have an arbitrary number of ingredients.
 */
public class AlaCartePizza implements ObservablePizza {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its crust type, size and toppings.
   */
  protected AlaCartePizza(Crust crust, Size size, Map<ToppingName, ToppingPortion> toppings) {
    try {
      this.crust = Objects.requireNonNull(crust);
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Crust cannot be null");
    }
    try {
      this.size = Objects.requireNonNull(size);
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Size cannot be null");
    }
    try {
      this.toppings = Objects.requireNonNull(toppings);
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Toppings cannot be null");
    }
  }


  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  /**
   * Builder class for the AlaCartePizza class.
   */
  public static class AlaCartePizzaBuilder extends PizzaBuilder {

    public AlaCartePizzaBuilder() {
      super(null, null, new HashMap<>());
    }

    public ObservablePizza build() {
      return new AlaCartePizza(this.crust, this.size, this.toppings);
    }
  }
}
