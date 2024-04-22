package model.saturday;

import java.util.List;

import model.allInterfaces.IEvent;
import model.allInterfaces.ITime;
import model.sunday.Event;

public class SaturdayEvent extends Event implements IEvent {

  public SaturdayEvent(String eventName, model.allInterfaces.ITime startTime,
                       model.allInterfaces.ITime endTime, boolean online, String location,
                       List<String> users) {
    super(eventName, startTime, endTime, online, location, users);
  }

  // ADAPT OVERLAPPING
  @Override
  public boolean overlappingEvents(IEvent otherEvent) {

    /*
    // do the events occur at the same time? if so, they overlap
    if ((this.startTime.compareTimes(otherEvent.getEndTime()) == 0)
            || (this.endTime.compareTimes(otherEvent.getStartTime()) == 0)) {
      return false;
    }
    // do the events start and end times overlap? if so, they overlap
    else {
      return (this.startTime.compareTimes(otherEvent.getEndTime()) <= 0)
              && (this.endTime.compareTimes(otherEvent.getStartTime()) >= 0);
    }
     */
    return false;
  }

}
