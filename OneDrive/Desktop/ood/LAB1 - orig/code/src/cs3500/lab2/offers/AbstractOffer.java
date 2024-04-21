package cs3500.lab2.offers;

import java.util.List;
import java.util.Objects;

import cs3500.lab2.skills.Skill;

/**
 * Represents an job offer.
 */
abstract public class AbstractOffer implements Offer {
  /**
   * Represents a job offer.
   * @param jobDescription describes the offer
   * @param requirements describes the list of skills required
   */
  protected String jobDescription;
  protected List<Skill> requirements;

  AbstractOffer(String jobDescription, List<Skill> requirements) {
    this.jobDescription = Objects.requireNonNull(jobDescription);
    this.requirements = Objects.requireNonNull(requirements);
  }

  /**
   * Calculates the job's salary.
   * @return int
   */
  public abstract int calculateSalary();

  /**
   * SatisfiesRequirements.
   * @param application the set of skills
   * @return whether skill requirement is met
   */
  public boolean satisfiesRequirements(List<Skill> application) {

    for (Skill req : requirements) {
      if (!application.stream().anyMatch((appSkill) -> appSkill.satisfiesReq(req))) {
        return false;
      }
    }
    return true;
  }
}