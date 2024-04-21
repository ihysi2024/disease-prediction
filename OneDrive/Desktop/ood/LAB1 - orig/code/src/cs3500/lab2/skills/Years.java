package cs3500.lab2.skills;

/**
 * Represents the number of years of experience.
 * As a requirement, it is satisfied if another Year
 * meets or exceeds the number of years in this skill.
 */
public class Years extends AbstractSkill {

  public int numYears;

  /**
   * Represents years as a skill.
   * @param y represents number of years
   */
  public Years(int y) {
    if (y < 0) {
      throw new IllegalArgumentException("Years cannot be negative");
    }
    this.numYears = y;
  }

  /**
   * Determines if the year requirement is met.
   * @param that number of years
   * @return boolean
   */
  public boolean isSatisfiedBy(Years that) {
    return that.numYears >= this.numYears;
  }

  /**
   * Determines if the year requirement is met.
   * @param application the skill to meet or exceed
   * @return boolean
   */
  public boolean satisfiesReq(Skill application) {
    return super.satisfiesReq(application);
  }

  /**
   * Determines if this is the sameYear.
   * @param other represents a year
   * @return boolean
   */
  protected boolean sameYear(Years other) {
    return (this.equals(other));
  }
}
