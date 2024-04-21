package model.saturday;

import java.util.function.UnaryOperator;

import model.interfaces.IEvent;
import model.interfaces.ISchedule;
import model.interfaces.IUser;

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
