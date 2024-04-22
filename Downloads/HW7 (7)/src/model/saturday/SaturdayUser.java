package model.saturday;

import model.allInterfaces.IEvent;
import model.allInterfaces.IUser;
import model.allInterfaces.ISchedule;

public class SaturdayUser extends model.sunday.User implements model.allInterfaces.IUser {
  public SaturdayUser(String name, model.allInterfaces.ISchedule schedule) {
    super(name, schedule);
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
