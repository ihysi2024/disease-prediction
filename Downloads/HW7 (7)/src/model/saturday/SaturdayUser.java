package model.saturday;

import model.allInterfaces.IEvent;
import model.allInterfaces.ISchedule;
import model.allInterfaces.IUser;

public class SaturdayUser implements IUser {
  @Override
  public String getName() {
    return null;
  }

  @Override
  public ISchedule getSchedule() {
    return null;
  }

  @Override
  public void userSchedToXML(String filePathToSave) {

  }

  @Override
  public void addEventForUser(IEvent event) {

  }

  @Override
  public void removeEventForUser(IEvent event) {

  }
}
