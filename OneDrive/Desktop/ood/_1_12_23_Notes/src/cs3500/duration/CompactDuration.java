package cs3500.duration;

public class CompactDuration {
  private long seconds;
  public CompactDuration(long seconds){
    this.seconds = seconds;
  }

  public long inSeconds(){
    return 0;
  }
  public String asHms(){
    return null;
  }

  public Duration plus(Duration other){
    return new CompactDuration(this.inSeconds() + other.inSeconds());
  }

  public int compareTo(Duration o){
    return Long.compare(this.inSeconds(), o.inSeconds());
  }
}
