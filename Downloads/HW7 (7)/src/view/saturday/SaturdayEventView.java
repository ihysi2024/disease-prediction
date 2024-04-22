package view.saturday;

import model.allInterfaces.IEvent;
import model.allInterfaces.IUser;
import model.allInterfaces.ReadOnlyPlanner;
import view.allInterfaces.IEventView;
import view.sunday.EventView;

public class SaturdayEventView extends EventView implements IEventView {
  /**
   * Creates a view of the Simon game.
   *
   * @param model desired model to represent Simon game
   */
  public SaturdayEventView(ReadOnlyPlanner model) {
    super(model);
  }

  @Override
  public void populateEventContents(IEvent iEvent) {
    super.populateEventContents(iEvent);
  }

  @Override
  public void displayModifyError(IUser iUser) {
    super.displayModifyError(iUser);
  }
}
