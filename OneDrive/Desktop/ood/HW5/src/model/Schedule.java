package model;

public class Schedule {
  private final User scheduleID;
  private final List<Event> events;


  public Schedule(User scheduleID, List<Event> events) {
    this.scheduleID = Objects.requireNonNull(scheduleID);
    this.events = Objects.requireNonNull(events);
  }

  /**
   * Add a new event to the events field.
   * @throws IllegalArgumentException if event overlaps another event
   * @param event - the event to be added
   */
  public void addEvent(Event event) {
    //
  }

  /**
   * Remove event to the events field.
   * @throws IllegalArgumentException if event doesn't exist
   * @param event - the event to be added
   */

  public void removeEvent(Event event) {
    //
  }

  public User getScheduleID() {
    return this.scheduleID;
  }

  public List<Event> events() {
    return this.events;
  }



}
