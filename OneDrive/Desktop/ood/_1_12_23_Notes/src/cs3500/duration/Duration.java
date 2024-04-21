package cs3500.duration;

import java.io.*;
import java.util.*;
public interface Duration extends Comparable<Duration>{
  long inSeconds();

  String asHms();

  Duration plus(Duration other);

  int compareTo(Duration o);
}
