package view;

import java.awt.*;

import javax.swing.*;

import model.interfaces.IEvent;
import model.interfaces.ITime;

public interface IDrawEvent {

  int[] timeToPaintLoc(ITime time, JPanel host);

  void paintFullDay(Graphics g, Color color, int dayWidth, ITime startDay, ITime endDay, JPanel host);

  void paintEvent(Graphics g, IEvent event, JPanel host, Color color);
}
