package cs3500.hw1.filesystem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleDirectoryTest {

  @Test
  public void name() {
  }

  @Test
  public void search() {
    // check search method on simple directory with 1 file
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    StringFile sf2 = new StringFile("TikTok", "Reposts and Reports");
    List l1 = new ArrayList();
    List l2 = new ArrayList();
    SimpleDirectory sd1 = new SimpleDirectory("Social Media", l1, l2);
    assertTrue(sd1.search("Social Media"));
    assertFalse(sd1.search("Instagram"));
    assertFalse(sd1.search("Likes"));

    // check search method on simple diretory with 2 files
    StringFile sf11 = new StringFile("Twitter", "Likes and Comments");
    StringFile sf22 = new StringFile("Youtube", "Reposts and Reports");
    List l11 = new ArrayList();
    List l22 = new ArrayList();
    l22.add(sf11);
    l22.add(sf22);
    SimpleDirectory sd2 = new SimpleDirectory("Social Media", l11, l22);
    assertTrue(sd2.search("Twitter"));
    assertTrue(sd2.search("Reposts"));
    assertFalse(sd2.search("Instagram"));
    assertTrue(sd2.search("Social Media"));
  }

  @Test
  public void size() {

  }

  @Test
  public void contents() {
  }

  @Test
  public void totalSize() {
  }

  @Test
  public void testToString() {
  }
}