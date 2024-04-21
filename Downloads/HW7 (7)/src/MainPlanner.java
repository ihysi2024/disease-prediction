import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.Controller;
import model.sunday.Event;
import model.interfaces.IEvent;
import model.sunday.NUPlanner;
import model.interfaces.PlannerSystem;
import model.sunday.Schedule;
import model.sunday.Time;
import model.sunday.User;
import view.EventView;
import view.IEventView;
import strategies.IScheduleStrategy;
import view.IScheduleTextView;
import view.IPlannerView;
import view.IScheduleView;
import view.ScheduleTextView;
import view.PlannerView;
import view.ScheduleView;
import strategies.ScheduleAnyTime;
import strategies.ScheduleWorkHours;

/**
 * Represents the class that allows the user to run the calendar system end to end
 * through the graphical user interface.
 */
public class MainPlanner {
  /**
   * Main method to play an instance of Simon Game.
   * @param args arguments to start an instance of Simon game
   */
  public static void main(String[] args) {
    Controller controller;

    PlannerSystem model = new NUPlanner("None"); // Feel free to customize this as desired

    IEvent morningSnack = new Event("snack",
            new Time(Time.Day.TUESDAY, 10, 30),
            new Time(Time.Day.TUESDAY, 11, 45),
            false,
            "Churchill Hall 101",
            Arrays.asList("Prof. Lucia"));

    IEvent officeHours = new Event("office hours",
            new Time(Time.Day.MONDAY, 12, 10),
            new Time(Time.Day.MONDAY, 15, 30),
            false,
            "Churchill Hall 101",
            Arrays.asList("Prof. Lucia", "Me"));

    IEvent sleep = new Event("sleep",
            new Time(Time.Day.FRIDAY, 12, 10),
            new Time(Time.Day.SUNDAY, 15, 30),
            false,
            "home",
            Arrays.asList("Prof. Lucia"));

    IEvent movie = new Event("movie",
            new Time(Time.Day.THURSDAY, 20, 10),
            new Time(Time.Day.THURSDAY, 22, 30),
            false,
            "home",
            Arrays.asList("Me", "Prof. Lucia"));

    model.addUser(new User("Prof. Lucia",
            new Schedule(new ArrayList<>(List.of(morningSnack, officeHours, sleep, movie)))));
    model.addUser(new User("Me", new Schedule(new ArrayList<>(List.of(officeHours, movie)))));

    if (args[0].equals("anytime")) {
      IScheduleStrategy anyTime = new ScheduleAnyTime();
      controller = new Controller(model, anyTime);
    } else if (args[0].equals("workhours")) {
      IScheduleStrategy workHours = new ScheduleWorkHours();
      controller = new Controller(model, workHours);
    } else {
      throw new IllegalArgumentException("Not a valid scheduling strategy");
    }
    IPlannerView plannerView = new PlannerView(model);

    IEventView eView = new EventView(model);
    IScheduleView schedView = new ScheduleView(model);
    IScheduleTextView tView = new ScheduleTextView(model, System.out);

    controller.setPlannerView(plannerView);
    controller.setEventView(eView);
    controller.setTextView(tView);
    controller.setScheduleView(schedView);
    controller.goLaunchPlanner();
  }



}
