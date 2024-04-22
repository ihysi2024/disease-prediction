package view.saturday;

import model.allInterfaces.ReadOnlyPlanner;
import view.allInterfaces.IScheduleView;
import view.sunday.SchedulePanel;

public class SaturdaySchedulePanel extends SchedulePanel implements IScheduleView {
  /**
   * Creates a panel that will house the input labels, buttons, and text fields for the user to
   * * create/modify/remove an event.
   *
   * @param model desired model to represent calendar system
   */
  public SaturdaySchedulePanel(ReadOnlyPlanner model) {
    super(model);
  }
}
