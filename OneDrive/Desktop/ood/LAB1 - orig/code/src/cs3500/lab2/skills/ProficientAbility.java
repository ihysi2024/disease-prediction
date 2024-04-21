package cs3500.lab2.skills;

import java.util.Objects;

/**
 * Represents a proficient ability.
 */
public class ProficientAbility extends AbstractSkill {
  private final String ability;

  private final Proficiency proficiency;

  /**
   * Describes an ability and its corresponding level of proficiency.
   * @param ability represents an ability name
   * @param proficiency represents a level of proficiency
   */
  public ProficientAbility(String ability, Proficiency proficiency) {
    Objects.requireNonNull(ability);
    this.ability = ability;
    Objects.requireNonNull(proficiency);
    this.proficiency = proficiency;
  }

  /**
   * Determines if a skill meets the requirement.
   * @param application the skill to meet or exceed
   * @return boolean
   */
  public boolean satisfiesReq(Skill application) {
    return super.satisfiesReq(application);
  }

  /**
   * Determines if a skill meets the requirement.
   * @param other represents a proficient ability
   * @return boolean
   */
  protected boolean isSatisfiedBy(ProficientAbility other) {
    return other.ability.equals(this.ability) && other.proficiency.equals(this.proficiency);
  }



}
