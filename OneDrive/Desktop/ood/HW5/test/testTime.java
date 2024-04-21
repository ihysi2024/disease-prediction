import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Time;

public class testTime {
  private Time satOnePM;

  private Time tues10AM;
  private Time tues10PM;

  private Time thursMidnight;

  private Time thursNoon;

  private Time wedFiveThirtyPM;
  private Time wedFiveThirtyOnePM;
  private Time satOnePMTwo;
  private Time sunThreePM;
  private Time monFiveAM;

  @Before
  public void setUp() {
    this.satOnePM = new Time(13, 0, Time.Day.SATURDAY);
    this.tues10AM = new Time(10, 0, Time.Day.TUESDAY);
    this.tues10PM = new Time(22, 0, Time.Day.TUESDAY);
    this.thursMidnight = new Time(0, 0, Time.Day.THURSDAY);
    this.thursNoon = new Time(12, 0, Time.Day.THURSDAY);
    this.wedFiveThirtyPM = new Time (17, 30, Time.Day.WEDNESDAY);
    this.wedFiveThirtyOnePM = new Time(17, 31, Time.Day.WEDNESDAY);
    this.satOnePMTwo = new Time(13, 0, Time.Day.SATURDAY);
    this.sunThreePM = new Time(15, 0, Time.Day.SUNDAY);
    this.monFiveAM = new Time(5, 0, Time.Day.MONDAY);
  }

  @Test
  public void testCompareTimes() {
    Assert.assertEquals(0, satOnePM.compareTimes(satOnePM));
    Assert.assertEquals(0, satOnePMTwo.compareTimes(satOnePM));
    Assert.assertEquals(-1, tues10AM.compareTimes(tues10PM));
    Assert.assertEquals(-1, tues10AM.compareTimes(thursNoon));
    Assert.assertEquals(1, wedFiveThirtyOnePM.compareTimes(wedFiveThirtyPM));
    Assert.assertEquals(1, monFiveAM.compareTimes(sunThreePM));
  }

  @Test
  public void testTimeDuration() {
    Assert.assertEquals(720, thursMidnight.calculateDuration(thursNoon));
    Assert.assertEquals(1, wedFiveThirtyOnePM.calculateDuration(wedFiveThirtyPM));

  }
}
