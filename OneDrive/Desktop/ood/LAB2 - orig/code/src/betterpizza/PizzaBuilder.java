package betterpizza;

import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Represents a pizza builder.
 */
abstract public class PizzaBuilder {
  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Represents a pizza builder.
   * @param crust the crust of the pizza
   * @param size the size of the pizza
   * @param toppings the toppings on the pizza
   */
  public PizzaBuilder(Crust crust, Size size, Map<ToppingName, ToppingPortion> toppings) {
    this.crust = crust;
    this.size = size;
    this.toppings = toppings;
  }

  /**
   * Changes the crust value.
   * @param crust the crust value to change to
   * @return a pizza builder.
   */
  public PizzaBuilder crust(Crust crust) {

    this.crust = crust;
    return this;
  }

  /**
   * Changes the size value.
   * @param size the size value to change to
   * @return a pizza builder.
   */
  public PizzaBuilder size(Size size) {

    this.size = size;
    return this;
  }

  /**
   * Changes the toppings on the pizza.
   * @param tn the topping name
   * @param tp the topping portion
   * @return a pizza builder.
   */
  public PizzaBuilder addTopping(ToppingName tn, ToppingPortion tp) {

    this.toppings.put(tn, tp);
    return this;
  }

  /**
   * Builds the current pizza.
   * @return an Observable Pizza
   */
  abstract public ObservablePizza build();

}
