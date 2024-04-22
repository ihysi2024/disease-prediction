package view.saturday;

import model.allInterfaces.IEvent;
import model.allInterfaces.ITime;
import model.allInterfaces.ReadOnlyPlanner;
import view.allInterfaces.IPlannerView;
import view.sunday.PlannerView;

public class SaturdayPlannerView extends PlannerView implements IPlannerView {
  /**
   * Creates a view of the planner system view.
   *
   * @param model desired model to represent a planner system
   */
  public SaturdayPlannerView(ReadOnlyPlanner model) {
    super(model);
  }

  @Override
  public IEvent findEventAtTime(ITime iTime) {
    return super.findEventAtTime(iTime);
  }
}
