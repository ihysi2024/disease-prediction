import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.lab2.skills.Ability;
import cs3500.lab2.skills.Proficiency;
import cs3500.lab2.skills.ProficientAbility;
import cs3500.lab2.skills.Skill;
import cs3500.lab2.skills.Years;

/**
 * Represents the tests for all skill methods.
 */
public class SkillsTest {

  /**
   * Produces tests for all methods related to skill.
   */


  @Test
  public void testYearRequirements() {
    Skill experienced = new Years(4);
    Skill inexperienced = new Years(0);
    Skill exactExperience = new Years(4);
    Skill requiredExperience = new Years(3);

    Assert.assertFalse(inexperienced.satisfiesReq(requiredExperience));
    Assert.assertTrue(experienced.satisfiesReq(requiredExperience));
    Assert.assertTrue(exactExperience.satisfiesReq(requiredExperience));
  }

  @Test
  public void testAbilityRequirements() {
    Skill program = new Ability("program");
    Skill teach = new Ability("teach");
    Skill programReq = new Ability("program");

    Assert.assertTrue(program.satisfiesReq(program));
    Assert.assertFalse(teach.satisfiesReq(program));
    Assert.assertTrue(program.satisfiesReq(programReq));
  }

  @Test
  public void testDifferentSkillRequirements() {
    Skill requiredExperience = new Years(3);
    Skill program = new Ability("program");
    Assert.assertFalse(program.satisfiesReq(requiredExperience));
    Assert.assertFalse(requiredExperience.satisfiesReq(program));
  }

  @Test
  public void testSkillEquality() {
    Skill programAbility = new Ability("program");
    Skill oodAbility = new Ability("OOD");
    Skill oneYear = new Years(1);
    Skill anotherOneYear = new Years(1);
    Skill twoYears = new Years(2);

    Assert.assertTrue(programAbility.equals(programAbility));
    Assert.assertTrue(oneYear.equals(anotherOneYear));

    Assert.assertFalse(programAbility.equals(oodAbility));
    Assert.assertFalse(oodAbility.equals(programAbility));
    Assert.assertFalse(oneYear.equals(twoYears));
    Assert.assertFalse(twoYears.equals(oneYear));

    Assert.assertFalse(programAbility.equals(oneYear));
    Assert.assertFalse(oneYear.equals(programAbility));
  }

  @Test
  public void testProficientAbility() {
    ProficientAbility pa1 = new ProficientAbility("communication", Proficiency.BEGINNER);
    ProficientAbility pa2 = new ProficientAbility("communication", Proficiency.EXPERT);

    Assert.assertTrue(pa1.satisfiesReq(pa1));
    Assert.assertFalse(pa1.satisfiesReq(pa2));

  }
}
