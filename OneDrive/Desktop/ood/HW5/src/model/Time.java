package model;

public class Time {

  private final int hours;
  private final int minutes;
  private final Day date;

  public enum Day {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);
    private final int dayIdx;

    Day(int dayIdx) {
      this.dayIdx = dayIdx;
    }
  }

  public Time (int hours, int minutes, Day date) {
    if (hours < 0 || hours > 24) {
      throw new IllegalArgumentException("Invalid time");
    }
    if (minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Invalid time");
    }
    this.hours = hours;
    this.minutes = minutes;
    this.date = date;
  }

  public int getHours() {
    return this.hours;
  }

  public int getMinutes() {
    return this.minutes;
  }

  public Day getDate() {
    return this.date;
  }

  /***
   * compare two times
   * return 0 if they are the same time
   * return -1 if this time comes before that time
   * return 1 if this time comes after that time
   * @param refTime
   * @return
   */

  public int compareTimes(Time refTime) {
    if (this.date.dayIdx < refTime.getDate().dayIdx) {
      return -1;
    }
    else if (this.date.dayIdx > refTime.getDate().dayIdx) {
      return 1;
    }
    else {
      if (this.hours < refTime.getHours()) {
        return -1;
      }
      else if (this.hours > refTime.getHours()) {
        return 1;
      }
      else {
        if (this.minutes < refTime.getMinutes()) {
          return -1;
        }
        else if (this.minutes > refTime.getMinutes()) {
          return 1;
        }
        else {
          return 0;
        }
      }
    }
  }


}
