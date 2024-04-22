package view.saturday;

import java.awt.*;

import javax.swing.*;

import model.allInterfaces.IEvent;
import model.allInterfaces.ITime;
import model.sunday.Time;
import view.allInterfaces.IDrawEvent;
import view.sunday.DrawEvent;

import static model.sunday.Time.indexToTime;

public class SaturdayDrawEvent extends DrawEvent implements IDrawEvent {

  @Override
  public void paintEvent(Graphics g, IEvent event, JPanel host, Color color) {
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
        endTime = new Time(Time.Day.FRIDAY, 23, 59);
        int[] sunday2359 = this.timeToPaintLoc(endTime, host);
        eventEndCoords[0] = sunday2359[0];
        eventEndCoords[1] = sunday2359[1];
      }

      int endOfFirstDay =
              (int) Math.round(this.minLoc(new Time(Time.Day.FRIDAY, 23, 59))
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

  public void paintFullDay(Graphics g, Color color, int dayWidth, ITime startDay, ITime endDay,
                           JPanel host) {
    super.paintFullDay(g, color, dayWidth, startDay, endDay, host);
  }

  @Override
  public int[] timeToPaintLoc(ITime time, JPanel host) {
    return super.timeToPaintLoc(time, host);
  }

  public double minLoc(ITime time) {
    return super.minLoc(time);
  }

}
