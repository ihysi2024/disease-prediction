package model.saturday;

import model.ITime;
import model.Time;

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
  public model.Time.Day getDate() {
    return super.getDate();
  }

  @Override
  public int compareTimes(ITime iTime) {
    return super.compareTimes(iTime);
  }

  @Override
  public int minutesSinceMidnight() {
    return super.minutesSinceMidnight();
  }

}
