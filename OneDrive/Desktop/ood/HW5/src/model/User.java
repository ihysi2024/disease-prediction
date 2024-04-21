package model;

import java.util.Objects;

public class User {
  private final String name;

  private Schedule schedule;
  User(String name, Schedule schedule) {
    this.name = Objects.requireNonNull(name);
    this.schedule = schedule;
  }

  public String getName() {
    return this.name;
  }

  public Schedule getSchedule() {
    return this.schedule;
  }
}
