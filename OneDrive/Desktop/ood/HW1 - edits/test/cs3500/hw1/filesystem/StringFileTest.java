package cs3500.hw1.filesystem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StringFileTest {

  @Test
  public void contents() {
  }

  @Test
  public void name() {
  }

  @Test
  public void size() {
  }

  @Test
  public void search() {
    // check search method on simple directory with 1 file
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    StringFile sf2 = new StringFile("TikTok", "Reposts and Reports");
    assertFalse(sf1.search("Social Media"));
    assertTrue(sf1.search("Instagram"));
    assertTrue(sf1.search("Likes"));
    assertTrue(sf1.search(" "));
    assertFalse(sf1.search("."));
  }

  @Test
  public void totalSize() {
  }

  @Test
  public void testToString() {
  }
}