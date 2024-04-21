package cs3500.hw1.filesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReadOnlyFileSystemTest {

  @Test
  public void search() {
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    StringFile sf2 = new StringFile("TikTok", "Reposts and Reports");
    List l1 = new ArrayList();
    List l2 = new ArrayList();
    SimpleDirectory sd1 = new SimpleDirectory("Social Media", l1, l2);
    ReadOnlyFileSystem rofs1 = new ReadOnlyFileSystem(10000, sd1);

    assertTrue(rofs1.search("Instagram"));
    assertTrue(rofs1.search("Reports"));
    assertTrue(rofs1.search("Social"));
    assertFalse(rofs1.search("Videos"));
  }