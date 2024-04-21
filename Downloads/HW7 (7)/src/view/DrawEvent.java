package view;

import java.awt.*;

import javax.swing.*;

import model.allInterfaces.IEvent;
import model.allInterfaces.ITime;
import model.sunday.Time;

import static model.sunday.Time.indexToTime;

public class DrawEvent implements IDrawEvent {


  /**
   * Paints given event onto panel. If event goes in to following week, ends painting at
   * Saturday @23:59.
   *
   * @param g     Graphics to use
   * @param event IEvent to be painted
   */
  public void paintEvent(Graphics g, IEvent event, JPanel host, Color color) {
    System.out.println("PAINTING EVENT");
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setColor(color);

    ITime startTime = event.getStartTime();
    ITime endTime = event.getEndTime();

    int dayWidth = (int) Math.round(host.getWidth() / 7.0); // constant, width of one day

    int[] eventStartCoords = this.timeToPaintLoc(startTime, host);
    int[] eventEndCoords = this.timeToPaintLoc(endTime, host);

    int rectHeight = eventEndCoords[1] - eventStartCoords[1];  // length of one-day event

    if (eventStartCoords[0] == eventEndCoords[0]) { // event starts + ends on same day
      g2d.fillRect(eventStartCoords[0], eventStartCoords[1], dayWidth, rectHeight);
    } else {
      // event goes to next week, changing end time to Sunday @23:59
      if (eventEndCoords[0] < eventStartCoords[0]) {
        endTime = new Time(Time.Day.SATURDAY, 23, 59);
        int[] sunday2359 = this.timeToPaintLoc(endTime, host);
        eventEndCoords[0] = sunday2359[0];
        eventEndCoords[1] = sunday2359[1];
      }

      int endOfFirstDay =
              (int) Math.round(this.minLoc(new Time(Time.Day.SATURDAY, 23, 59))
                      * host.getHeight()); // day doesn't matter, only time
      int rectHeightFirstDay = endOfFirstDay - eventStartCoords[1];
      g2d.fillRect(eventStartCoords[0], eventStartCoords[1], dayWidth, rectHeightFirstDay);

      int endDayIndex = endTime.getDate().getDayIdx();

      this.paintFullDay(g, color, dayWidth, startTime, endTime, host);

      // draw the last day. starts at 00:00, ends at actual end of the event
      int[] endDayCoords = this.timeToPaintLoc(indexToTime(endDayIndex, 0), host);

      g2d.fillRect(endDayCoords[0], endDayCoords[1], dayWidth, eventEndCoords[1]);

    }
  }

  /**
   * Paints an entire day on the schedule starting from 00:00 to 23:59.
   *
   * @param g        Graphics
   * @param color    color of event to be painted
   * @param dayWidth width of day, constant
   * @param startDay start day to be painted
   * @param endDay   end day to be painted
   */
  public void paintFullDay(Graphics g, Color color, int dayWidth, ITime startDay, ITime endDay,
                           JPanel host) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setColor(color);
    int startDayIndex = startDay.getDate().getDayIdx();
    int endDayIndex = endDay.getDate().getDayIdx();
    // drawing each full day
    for (int indexFullDay = startDayIndex + 1; indexFullDay < endDayIndex; indexFullDay++) {
      // full day starts at time 00:00
      int[] currDayCoords = this.timeToPaintLoc(indexToTime(indexFullDay, 0), host);

      g2d.fillRect(currDayCoords[0], currDayCoords[1], dayWidth, host.getHeight());
    }

  }

  /**
   * Calculates the top left coordinate of the given time. The x coordinate corresponds
   * to the day, the y coordinate corresponds to the time to minute granularity.
   * Changes based on the size of the board.
   *
   * @param time given time
   * @return 2-value int array containing top left coordinate of given time
   */
  public int[] timeToPaintLoc(ITime time, JPanel host) {
    int[] x_y_coords = new int[2];

    int weekColXCoord = (int) Math.round((time.getDate().getDayIdx() / 7.0) * host.getWidth());
    int weekColYCoord = (int) Math.round(this.minLoc(time) * host.getHeight());

    x_y_coords[0] = weekColXCoord;
    x_y_coords[1] = weekColYCoord;
    return x_y_coords;
  }

  /**
   * Calculates the time position from [0, 1) that the given time falls, with 0 representing
   * midnight.
   *
   * @param time Time to use for calculations
   * @return the decimal value representing the time's position
   */
  private double minLoc(ITime time) {
    int timePos = time.minutesSinceMidnight();
    return timePos / (60.0 * 24.0);
  }

}
