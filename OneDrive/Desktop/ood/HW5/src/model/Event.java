package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event {
  User host;
  String eventName;
  Time startTime;
  Time endTime;
  boolean online;
  String location;
  List<User> users;

  public Event(String name, Time startTime, Time endTime, boolean online, String location,
               List<User> users, User host) {
    this.eventName = Objects.requireNonNull(name);
    this.startTime = Objects.requireNonNull(startTime);
    this.endTime = Objects.requireNonNull(endTime);
    this.online = online;
    if (!online) {
      this.location = Objects.requireNonNull(location);
    }
    else {
      this.location = null;
    }
    this.users = Objects.requireNonNull(users);
    this.host = Objects.requireNonNull(host);

  }


  public static EventBuilder builder(){
    return new EventBuilder();
  }

  static class EventBuilder {
    private User host;
    private String eventName;
    private Time startTime;
    private Time endTime;
    private boolean online;
    private String location;
    private List<User> users;

    public EventBuilder setHost(User newHost) {
      this.host = newHost;
      return this;
    }
    public EventBuilder setName(String newEventName) {
      this.eventName = newEventName;
      return this;
    }
    public EventBuilder setstartTime(Time newStartTime) {
      this.startTime = new Time(newStartTime.getHours(), newStartTime.getMinutes(), newStartTime.getDate());
      return this;
    }
    public EventBuilder setHost(Time newEndTime) {
      this.endTime = new Time(newEndTime.getHours(), newEndTime.getMinutes(), newEndTime.getDate());
      return this;
    }
    public EventBuilder setHost(boolean newOnline) {
      this.online = newOnline;
      return this;
    }
    public EventBuilder setLocation(String newLocation) {
      this.location = newLocation;
      return this;
    }
    public EventBuilder setUsers(List<User> newUsers) {
      // check not an alias
      List<User> newListUsers = new ArrayList<>();
      for (User user: newUsers) {
        newListUsers.add(new User(user.getName(), user.getSchedule()));
      }
      return this;
    }

    public User getHost() {
      return new User(this.host.getName(), this.host.getSchedule());
    }

    public String eventName() {
      return this.eventName;
    }

    public Time getStartTime() {
      return new Time(this.startTime.getHours(), this.startTime.getMinutes(),
              this.startTime.getDate());
    }

    public Time getEndTime() {
      return new Time(this.endTime.getHours(), this.endTime.getMinutes(),
              this.endTime.getDate());
    }

    public boolean getOnline() {
      return this.online;
    }

    public String getLocation() {
      return this.location;
    }

    public List<User> getListUsers() {
      List<User> newListUsers = new ArrayList<>();
      for (User user: this.users) {
        newListUsers.add(new User(user.getName(), user.getSchedule()));
      }
      return newListUsers;
    }

  }
}
