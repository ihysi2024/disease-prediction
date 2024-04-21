package cs3500.lab2.offers;

import java.util.List;


import cs3500.lab2.skills.Skill;

/**
 * Represents a full time job offer.
 */
public class FullTimeJob extends AbstractOffer {

  private int yearlySalary;

  /**
   * Describes a job offer.
   * @param description describes the job
   * @param yearlySalary represents the job's salary
   * @param reqs represents the job's skill requirement
   */
  public FullTimeJob(String description, int yearlySalary, List<Skill> reqs) {
    super(description, reqs);
    if (yearlySalary < 0) {
      throw new IllegalArgumentException("Salary cannot be negative");
    }
    this.yearlySalary = yearlySalary;
  }

  /**
   * SatisfiesRequirements determines whether the skill requirements have been met.
   * @param application the set of skills
   * @return boolean
   */
  public boolean satisfiesRequirements(List<Skill> application) {
    return super.satisfiesRequirements(application);
  }

  /**
   * calculateSalary calculates the job salary.
   * @return int
   */
  @Override
  public int calculateSalary() {
    return this.yearlySalary;
  }

}
