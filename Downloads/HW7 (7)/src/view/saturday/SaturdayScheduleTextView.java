package view.saturday;

import model.allInterfaces.IEvent;
import model.allInterfaces.ISchedule;
import model.allInterfaces.ITime;
import model.allInterfaces.IUser;
import model.allInterfaces.PlannerSystem;
import view.allInterfaces.IScheduleTextView;
import view.sunday.ScheduleTextView;

public class SaturdayScheduleTextView extends ScheduleTextView implements IScheduleTextView {
  /**
   * Produce a view for this planner system for all users' schedules to be seen.
   *
   * @param plannerSystem planner system to be viewed
   * @param appendable    output view to show the planner system text view on
   */
  public SaturdayScheduleTextView(PlannerSystem plannerSystem, Appendable appendable) {
    super(plannerSystem, appendable);
  }

  @Override
  public String timeToString(ITime iTime) {
    return super.timeToString(iTime);
  }

  @Override
  public String scheduleToString(ISchedule iSchedule) {
    return super.scheduleToString(iSchedule);
  }

  @Override
  public String userToString(IUser iUser) {
    return super.userToString(iUser);
  }

  @Override
  public String eventToString(IEvent iEvent) {
    return super.eventToString(iEvent);
  }
}
