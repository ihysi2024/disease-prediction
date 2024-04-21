package model.allInterfaces;

import java.util.List;


/**
 * Represents the observational interface for the calendar system that
 * the user interacts with through the view and controller.
 */
public interface ReadOnlyPlanner {

  /**
   * return event in a user's schedule at a given time.
   *
   * @param user      the user to examine
   * @param givenTime the time to look at event within
   * @return an event. return null if no events at that time
   * @throws IllegalArgumentException if user doesn't exist or doesn't have a schedule
   */
  model.allInterfaces.IEvent retrieveUserScheduleAtTime(IUser user, ITime givenTime);

  /**
   * Retrieves the list of users in the planner system.
   */
  List<IUser> getUsers();

  /**
   * Returns the current host whose planner is open.
   * @return the current host whose planner is open
   */
  String getHost();

  /**
   * Retrieves the events in this user's schedule.
   *
   * @param user desired user for whom to retrieve the schedule
   * @return a list of this user's events
   */
  List<IEvent> retrieveUserEvents(IUser user);

}
