package cs3500.lab2.skills;

/**
 * Represents a generic skill.
 */
abstract class AbstractSkill implements Skill {

  protected boolean isSatisfiedBy(Years other) {
    return false;
  }

  protected boolean isSatisfiedBy(Ability other) {
    return false;
  }

  protected boolean isSatisfiedBy(ProficientAbility other) {
    return false;
  }

  /**
   * Determines if two objects are equivalent.
   * @param other represents an object
   * @return boolean
   */

  @Override
  public boolean equals(Object other) {
    if ((other instanceof Years)) {
      Years that = (Years)other;
      return this.sameYear(that);
    }

    if (other instanceof Ability) {
      Ability that = (Ability)other;
      return this.sameAbility(that);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  /**
   * Determines if a skill requirement is reached.
   * @param requirement the skill to meet or exceed
   * @return boolean
   */

  public boolean satisfiesReq(Skill requirement) {
    if (requirement instanceof Years) {
      return this.isSatisfiedBy((Years) requirement);
    }
    else if (requirement instanceof Ability) {
      return this.isSatisfiedBy((Ability) requirement);
    }
    else {
      return this.isSatisfiedBy((ProficientAbility) requirement);
    }
  }

  /**
   * Determines if it is the same year as given.
   * @param other represents a year
   * @return boolean
   */
  protected boolean sameYear(Years other) {
    return false;
  }

  /**
   * Determines if it is the same ability as given.
   * @param other represents an ability
   * @return boolean
   */
  protected boolean sameAbility(Ability other) {
    return false;
  }

}
