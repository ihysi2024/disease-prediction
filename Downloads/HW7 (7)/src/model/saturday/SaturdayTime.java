package model.saturday;

import model.sunday.Time;
import model.allInterfaces.ITime;

// time can stay the same as regular time
public class SaturdayTime extends Time implements ITime {

  public SaturdayTime(Time.Day date, int hours, int minutes) {
    super(date, hours, minutes);
  }

  @Override
  public int getHours() {
    return super.getHours();
  }

  @Override
  public int getMinutes() {
    return super.getMinutes();
  }

  @Override
  public model.sunday.Time.Day getDate() {
    return super.getDate();
  }

  /**
   * compare two times and determine what their relation is to each other.
   *
   * @param refTime other time to compare to
   * @return 0 if they are the same time
   * -1 if this time comes before that time
   * 1 if this time comes after that time
   */
  @Override
  public int compareTimes(ITime iTime) {
    int currDayIdx = this.getDate().getDayIdx();
    if (currDayIdx == 6) { // saturday
      currDayIdx = 0;
    }
    else {
      currDayIdx++;
    }

    int otherDayIdx = iTime.getDate().getDayIdx();
    if (otherDayIdx == 6) { // saturday
      otherDayIdx = 0;
    }
    else {
      otherDayIdx++;
    }
    ITime newThisTime = indexToTime(currDayIdx, this.minutesSinceMidnight());
    ITime newOtherTime = indexToTime(otherDayIdx, iTime.minutesSinceMidnight());
    return newThisTime.compareTimes(newOtherTime);
  }

  @Override
  public int minutesSinceMidnight() {
    return super.minutesSinceMidnight();
  }

}
