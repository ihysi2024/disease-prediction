package model;

import java.util.List;
import java.util.Objects;

public class NUPlanner implements PlannerSystem {

  private final List<User> users;

  public NUPlanner(List<User> users) {
    this.users = Objects.requireNonNull(users);
  }
  @Override
  public void parseXMLUpdateSchedule(String filePath) {

  }

  @Override
  public void exportScheduleAsXML(Schedule schedule) {

  }

  @Override
  public List<Event> retrieveUserSchedule(User user, Time givenTime) {
    return null;
  }
}
