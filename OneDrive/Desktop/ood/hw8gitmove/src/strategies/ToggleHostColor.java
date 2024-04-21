package strategies;

import java.awt.*;


import model.interfaces.IEvent;
import model.interfaces.IUser;
import view.DrawEvent;
import view.IDrawEvent;
import view.IPlannerView;

public class ToggleHostColor implements IToggleStrategy {

  private Color hostColor;
  private Color inviteeColor;

  private IUser host;

  private IPlannerView planner;
  IDrawEvent dEvent;

  public ToggleHostColor(Color hostColor, Color inviteeColor, IUser host, IPlannerView planner) {
    this.hostColor = hostColor;
    this.inviteeColor = inviteeColor;
    this.host = host;
    this.planner = planner;
    this.dEvent = new DrawEvent();
  }

  public void toggleColors(Graphics g) {
    planner.getPanel().resetPanel();
    for (IEvent event: host.getSchedule().getEvents()) {
      if (event.getUsers().get(0).equals(host.getName())) {
        dEvent.paintEvent(planner.getPanel().getGraphics(), event, planner.getPanel(), hostColor);
        planner.getPanel().addMenus();
      }
      else {
        dEvent.paintEvent(planner.getPanel().getGraphics(), event, planner.getPanel(), inviteeColor);
        planner.getPanel().addMenus();
      }
    }
  }
}
