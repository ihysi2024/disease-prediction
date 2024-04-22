package view.saturday;

import model.allInterfaces.IEvent;
import model.allInterfaces.ITime;
import model.allInterfaces.ReadOnlyPlanner;
import view.allInterfaces.IPlannerView;
import view.sunday.PlannerPanel;

public class SaturdayPlannerPanel extends PlannerPanel implements IPlannerView {
  /**
   * Creates a panel that will house the view representation of the Simon game
   * with clicking capabilities.
   *
   * @param model desired model to represent Simon game
   */
  public SaturdayPlannerPanel(ReadOnlyPlanner model) {
    super(model);
  }

  @Override
  public IEvent findEventAtTime(ITime iTime) {
    return super.findEventAtTime(iTime);
  }
}
