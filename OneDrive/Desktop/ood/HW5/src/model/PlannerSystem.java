package model;

import java.util.List;

public interface PlannerSystem {

  /**
   * Parse XML file and update a schedule
   * If user in given XML file already has a schedule -> update with XML information
   * If user has an empty or null schedule -> fill with XML.
   * Would require helper to update schedule
   */
  public void parseXMLUpdateSchedule(String filePath);

  // private helper for updating


  /**
   * Export schedule as an XML file
   */

  public void exportScheduleAsXML(Schedule schedule);

  /**
   * return events in a user's schedule at a given time
   * @param user the user to examine
   * @param givenTime the time to look at event within
   * @return a list of events
   */
  public List<Event> retrieveUserSchedule(User user, Time givenTime);




}
