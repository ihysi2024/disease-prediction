package cs3500.lab2.skills;

import java.util.Objects;

/**
 * Represents an ability as a type of skill.
 */
public class Ability extends AbstractSkill {

  final String ability;

  /**
   * Describes an ability.
   * @param ability describes an ability
   */
  public Ability(String ability) {
    Objects.requireNonNull(ability);
    this.ability = ability;
  }

  /**
   * SatisfiesReq determines if it satisfies the skill requirement.
   * @param application the skill to meet or exceed
   * @return boolean
   */
  public boolean satisfiesReq(Skill application) {

    return super.satisfiesReq(application);
  }

  /**
   * IsSatisfiedBy determines if the ability satisfies the requirement.
   * @param other represents an ability
   * @return boolean
   */
  @Override
  protected boolean isSatisfiedBy(Ability other) {
    return other.ability.equals(this.ability);
  }

  /**
   * SameYear determines if this is the same year.
   * @param other represents an ability
   * @return boolean
   */

  protected boolean sameYear(Ability other) {
    return (this.equals(other));
  }

}
