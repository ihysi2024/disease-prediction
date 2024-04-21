
public class DurationExamples {
  public void testValidDurationExamples(){
    Duration d1 = new HMSDuration(0, 0, 1);
    assertEquals("0:00:01", d1.asHms());
    assertEquals(1, d1.inSeconds());

    d1 = new HMSDuration(0, 0, 90);
    assertEquals(90, d1.inSeconds());
    assertEquals("0:01:30", d1.asHms());
  }

  public void testInvalidDurationExamples() {
    try {
      Duration d1 = new HMSDuration(0, -1, 0);
      fail("Expected exception")
    }
    catch (IllegalArgumentException ignored){
      //do nothing because this is expected.
    }
  }
}
