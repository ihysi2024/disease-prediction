package cs3500.lab2.offers;

import java.util.List;
import cs3500.lab2.skills.Skill;

/**
 * Represents a coop job offer.
 */
public class Coop extends AbstractOffer {


  private int hourlyRate;
  private int maxHours;

  /**
   * Describes a coop offer.
   * @param description name of coop offer
   * @param rate name of hourly rate
   * @param hours number of max hours that can be worked
   * @param reqs skill requirement
   */
  public Coop(String description, int rate, int hours, List<Skill> reqs) {
    super(description, reqs);
    if (rate < 0) {
      throw new IllegalArgumentException("Hourly rate cannot be negative.");
    }
    if (hours < 0) {
      throw new IllegalArgumentException("Max hours cannot be negative");
    }
    this.hourlyRate = rate;
    this.maxHours = hours;
  }

  /**
   * Determines if a skill requirement has been met.
   * @param application the set of skills
   * @return boolean
   */
  public boolean satisfiesRequirements(List<Skill> application) {

    return super.satisfiesRequirements(application);
  }

  /**
   * Calculates the salary.
   * @return int
   */
  @Override
  public int calculateSalary() {
    return this.hourlyRate * this.maxHours * 52;
  }

}
