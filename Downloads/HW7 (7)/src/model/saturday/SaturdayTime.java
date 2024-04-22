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

  // iTime could be a saturday or a sunday time
  // if iTime is a saturday time, then can
  // assuming that if using saturday model, will always be comparing saturday times
  // just change the day index for each
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

    if (currDayIdx < otherDayIdx) {
      return -1;
    }
    else if (currDayIdx > otherDayIdx) {
      return 1;
    }
    else { // same day
      if (this.getHours() < iTime.getHours()) {
        return -1;
      }
      else if (this.getHours() > iTime.getHours()) {
        return 1;
      }
      else { // same day + hour, so comparing minutes
        return Integer.compare(this.getMinutes(), iTime.getMinutes());
      }
    }

  }

  @Override
  public int minutesSinceMidnight() {
    return super.minutesSinceMidnight();
  }

}
