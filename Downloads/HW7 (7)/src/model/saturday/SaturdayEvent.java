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

  @Override
  public List<String> getUsers() {
    return super.getUsers();
  }


  @Override
  public ITime getStartTime() {
    return super.getStartTime();
  }

  @Override
  public ITime getEndTime() {
    return super.getEndTime();
  }

  // ADAPT DURATION
  @Override
  public int eventDuration() {
    return 0;
  }



  // ADAPT OVERLAPPING
  @Override
  public boolean overlappingEvents(IEvent otherEvent) {
    
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
  }

  @Override
  public boolean equals(Object other) {
    return super.equals(other);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String eventToXMLFormat() {
    return super.eventToXMLFormat();
  }

  @Override
  public Boolean getOnline() {
    return super.getOnline();
  }

  @Override
  public String getEventName() {
    return super.getEventName();
  }

  @Override
  public String getLocation() {
    return super.getLocation();
  }

  @Override
  public void removeUserFromList(String userName) {
    super.removeUserFromList(userName);
  }
}
