

public class HMSDuration implements Duration{
  private int hours;
  private int minutes;
  private int seconds;

  public HMSDuration(int hours, int minutes, int seconds) {
    if (hours<0 || minutes<0 || seconds <0) {
      throw new IllegalArgumentException("Cannot be negative");
    }
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public HMSDuration(long seconds) {
    if (seconds < 0){
      throw new IllegalArgumentException()
    }
    this.hours = (int)(seconds/(60*60));
    this.minutes = (int)((seconds % (60*60))/60);
    this.seconds = (int)(seconds%60);
  }

  public Duration plus(Duration other){

    return new HMSDuration(this.inSeconds()+other.inSeconds());
  }



  public void testInvalidDurationExamples() {
    duration d1 = new HMS(0, -1, 0);
  }


}
