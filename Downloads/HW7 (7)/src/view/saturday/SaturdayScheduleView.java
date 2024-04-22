package view.saturday;

import model.allInterfaces.ReadOnlyPlanner;
import view.allInterfaces.IScheduleView;
import view.sunday.ScheduleView;

public class SaturdayScheduleView extends ScheduleView implements IScheduleView {
  /**
   * Creates a view of the Simon game.
   *
   * @param model desired model to represent Simon game
   */
  public SaturdayScheduleView(ReadOnlyPlanner model) {
    super(model);
  }
}
