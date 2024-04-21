package cs3500.lab2.offers;

import java.util.List;

import cs3500.lab2.skills.Skill;

/**
 * Represents a volunteer job offer.
 */
public class Volunteer extends AbstractOffer {
  public Volunteer(String description, List<Skill> reqs) {
    super(description, reqs);
  }

  /**
   * CalculateSalary calculates the salary of the job.
   * @return int
   */
  @Override
  public int calculateSalary() {
    return 0;
  }

  /**
   * SatisfiesRequirements determines whether the job has met the
   * requirements.
   * @param application the set of skills
   * @return boolean
   */
  public boolean satisfiesRequirements(List<Skill> application) {
    return super.satisfiesRequirements(application);
  }

}
