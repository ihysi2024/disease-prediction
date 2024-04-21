package cs3500.lab2.skills;

/**
 * Describes a level of proficiency.
 */
public enum Proficiency implements Skill {
  BEGINNER,
  INTERMEDIATE,
  EXPERT;

  /**
   * Describes the level of proficiency of a skill.
   */

  @Override
  public boolean satisfiesReq(Skill requirement) {
    switch (this) {
      case BEGINNER:
        return false;
      case INTERMEDIATE:
        return true;
      case EXPERT:
        return true;
      default:
        return false;
    }
  }
}
