package view.saturday;

import model.allInterfaces.IEvent;
import model.allInterfaces.IUser;
import model.allInterfaces.ReadOnlyPlanner;
import view.allInterfaces.IEventView;
import view.sunday.EventPanel;

public class SaturdayEventPanel extends EventPanel implements IEventView {
  /**
   * Creates a panel that will house the input labels, buttons, and text fields for the user to
   * * create/modify/remove an event.
   *
   * @param model desired model to represent Simon game
   */
  public SaturdayEventPanel(ReadOnlyPlanner model) {
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
