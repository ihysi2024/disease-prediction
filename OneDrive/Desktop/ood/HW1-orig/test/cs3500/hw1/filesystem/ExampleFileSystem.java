package cs3500.hw1.filesystem;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Represents tests for file system.
 */
public class ExampleFileSystem {

  @org.junit.Test
  public void testPath() {
    StringFile nestedSF1 = new StringFile("Twitter", "Likes and Comments");
    StringFile nestedSF2 = new StringFile("Youtube", "Reposts and Reports");
    StringFile nestedSF3 = new StringFile("Instagram", "Reels and Posts");

    List sdList = new ArrayList();
    List sfList = new ArrayList();
    List nestedL1 = new ArrayList();
    List nestedL2 = new ArrayList();
    List nestedL3 = new ArrayList();

    nestedL2.add(nestedSF1);
    nestedL2.add(nestedSF2);

    // add first two files to one directory
    SimpleDirectory sd3 = new SimpleDirectory("Social Media", nestedL1, nestedL2);
    sdList.add(sd3);
    sfList.add(nestedSF3);
    // add previous directory and the third file to this directory
    SimpleDirectory sd4 = new SimpleDirectory("All Social Data", sdList, sfList);
    ReadOnlyFileSystem rofs2 = new ReadOnlyFileSystem(10000, sd4);


    assertEquals("All Social Data", rofs2.path(sd4));
    assertEquals("All Social Data/Social Media", rofs2.path(sd3));
    assertEquals("All Social Data/Social Media", sd4.path(sd3));
    assertEquals("All Social Data/Instagram", rofs2.path(nestedSF3));
    assertEquals("All Social Data/Instagram", sd4.path(nestedSF3));
    assertEquals("Social Media/Twitter", sd3.path(nestedSF1));
    assertEquals("All Social Data/Social Media/Twitter", rofs2.path(nestedSF1));

  }



  @org.junit.Test
  public void searchRofsTest() {
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    StringFile sf2 = new StringFile("TikTok", "Reposts and Reports");
    List l1 = new ArrayList();
    List l2 = new ArrayList();
    l2.add(sf1);
    l2.add(sf2);
    SimpleDirectory sd1 = new SimpleDirectory("Social Media", l1, l2);
    ReadOnlyFileSystem rofs1 = new ReadOnlyFileSystem(10000, sd1);

    assertTrue(rofs1.search("Instagram"));
    assertTrue(rofs1.search("Reports"));
    assertTrue(rofs1.search("Social"));
    assertFalse(rofs1.search("Videos"));

  }

  @org.junit.Test
  public void searchTestDirectory() {
    // check search method on simple directory with no file

    // make file
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    List l1 = new ArrayList();
    List l2 = new ArrayList();
    // add empty lists of files to the directory
    SimpleDirectory sd1 = new SimpleDirectory("Social Media", l1, l2);
    // check if files exist
    assertTrue(sd1.search("Social Media"));
    assertFalse(sd1.search("Instagram"));
    assertFalse(sd1.search("Likes"));

    // check search method on simple directory with 2 files
    // make two new files
    StringFile sf21 = new StringFile("Twitter", "Likes and Comments");
    StringFile sf22 = new StringFile("Youtube", "Reposts and Reports");
    List l11 = new ArrayList();
    List l22 = new ArrayList();
    l22.add(sf21);
    l22.add(sf22);
    // add files to the directory
    SimpleDirectory sd2 = new SimpleDirectory("Social Media", l11, l22);
    // check if files exist
    assertTrue(sd2.search("Twitter"));
    assertTrue(sd2.search("Reposts"));
    assertFalse(sd2.search("Instagram"));
    assertTrue(sd2.search("Social Media"));

    // check search method on directory with nested directory and 3 files
    // make three different files, 2 for one directory, one for another
    StringFile nestedSF1 = new StringFile("Twitter", "Likes and Comments");
    StringFile nestedSF2 = new StringFile("Youtube", "Reposts and Reports");
    StringFile nestedSF3 = new StringFile("Instagram", "Reels and Posts");

    List sdList = new ArrayList();
    List sfList = new ArrayList();
    List nestedL1 = new ArrayList();
    List nestedL2 = new ArrayList();
    List nestedL3 = new ArrayList();

    nestedL2.add(nestedSF1);
    nestedL2.add(nestedSF2);

    // add first two files to one directory
    SimpleDirectory sd3 = new SimpleDirectory("Social Media", nestedL1, nestedL2);
    sdList.add(sd3);
    sfList.add(nestedSF3);
    // add previous directory and the third file to this directory
    SimpleDirectory sd4 = new SimpleDirectory("All Social Data", sdList, sfList);
    // check if all files are present
    assertTrue(sd4.search("Twitter"));
    assertTrue(sd4.search("Reposts"));
    assertTrue(sd4.search("Instagram"));
    assertTrue(sd4.search("Social Media"));
    assertTrue(sd4.search("All Social Data"));
    assertFalse(sd4.search("Tiktok"));
  }

  @Test
  public void testPrettyPrint() {
    StringFile compressed = new StringFile("compressed.zip", "Likes and Comments");
    StringFile expName = new StringFile("exp-name.log", "Reposts and Reports");
    StringFile data = new StringFile("data.log", "Reels and Posts");
    StringFile coolThing = new StringFile("cool-thing.txt", "Reels and Posts");

    List expDirList = new ArrayList();
    List experimentFileList = new ArrayList();
    List libDirList = new ArrayList();
    List libFileList = new ArrayList();
    List dataDirList = new ArrayList();
    List dataFileList = new ArrayList();
    List fileSysDirList = new ArrayList();
    List fileSysFileList = new ArrayList();
    List rootDirList = new ArrayList();
    List rootFileList = new ArrayList();

    fileSysFileList.add(data);

    experimentFileList.add(compressed);
    experimentFileList.add(expName);
    libFileList.add(coolThing);

    SimpleDirectory sdFileSys = new SimpleDirectory("filesystem", fileSysDirList, fileSysFileList);
    expDirList.add(sdFileSys);
    SimpleDirectory sdExp = new SimpleDirectory("experiment", expDirList, experimentFileList);
    dataDirList.add(sdExp);
    SimpleDirectory sdLib = new SimpleDirectory("lib", libDirList, libFileList);
    SimpleDirectory sdData = new SimpleDirectory("data", dataDirList, dataFileList);
    rootDirList.add(sdData);
    rootDirList.add(sdLib);
    SimpleDirectory rootDir = new SimpleDirectory("root", rootDirList, rootFileList);
    ReadOnlyFileSystem rofsExample = new ReadOnlyFileSystem(1000000, rootDir);

    String fullVisual = "+-root/" + "\n" +
            "| +-data/" + "\n" + "| | +-experiment/" + "\n"
            + "| | | +-compressed.zip" + "\n" +
            "| | | +-exp-name.log" + "\n" +
            "| | | +-filesystem/" + "\n" +
            "| | | | +-data.log" + "\n" +
            "| +-lib/" + "\n" + "| | +-cool-thing.txt" + "\n";


    //System.out.println(sdExp.contents());
    assertEquals(fullVisual, rofsExample.prettyPrint());
  }


  @Test
  public void testPrefix() {
    String a = "+-filessystem/\n| +-data.log";
    String correct = "| +-filessystem/\n| | +-data.log\n";
    StringFile sf1 = new StringFile("data.log", "blah");
    List l1 = List.of(sf1);
    SimpleDirectory sd1 = new SimpleDirectory("filesystem", new ArrayList(), l1);
    assertEquals(correct, sd1.prefix(a));
  }

  @org.junit.Test
  public void searchTestFile() {
    // check search method on simple directory with 1 file
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    StringFile sf2 = new StringFile("TikTok", "Reposts and Reports");
    assertFalse(sf1.search("Social Media"));
    assertTrue(sf1.search("Instagram"));
    assertTrue(sf1.search("Likes"));
    assertTrue(sf1.search(" "));
    assertFalse(sf1.search("."));
  }

  @org.junit.Test
  public void totalSizeTestRofs() {
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    StringFile sf2 = new StringFile("TikTok", "Reposts and Reports");
    List l1 = new ArrayList();
    List l2 = new ArrayList();
    l2.add(sf1);
    l2.add(sf2);
    SimpleDirectory sd1 = new SimpleDirectory("Social Media", l1, l2);
    ReadOnlyFileSystem rofs1 = new ReadOnlyFileSystem(10000, sd1);

    assertEquals(4096, rofs1.used());
  }

  @org.junit.Test
  public void sizeTestDirectory() {
    // check search method on simple directory with no file

    // make file
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    List l1 = new ArrayList();
    List l2 = new ArrayList();
    // add empty lists of files to the directory
    SimpleDirectory sd1 = new SimpleDirectory("Social Media", l1, l2);
    // check if files exist
    assertTrue(sd1.search("Social Media"));
    assertFalse(sd1.search("Instagram"));
    assertFalse(sd1.search("Likes"));

    // check search method on simple directory with 2 files
    // make two new files
    StringFile sf21 = new StringFile("Twitter", "Likes and Comments");
    StringFile sf22 = new StringFile("Youtube", "Reposts and Reports");
    List l11 = new ArrayList();
    List l22 = new ArrayList();
    l22.add(sf21);
    l22.add(sf22);
    // add files to the directory
    SimpleDirectory sd2 = new SimpleDirectory("Social Media", l11, l22);
    // check if files exist
    assertTrue(sd2.search("Twitter"));
    assertTrue(sd2.search("Reposts"));
    assertFalse(sd2.search("Instagram"));
    assertTrue(sd2.search("Social Media"));

    // check search method on directory with nested directory and 3 files
    // make three different files, 2 for one directory, one for another
    StringFile nestedSF1 = new StringFile("Twitter", "Likes and Comments");
    StringFile nestedSF2 = new StringFile("Youtube", "Reposts and Reports");
    StringFile nestedSF3 = new StringFile("Instagram", "Reels and Posts");

    List sdList = new ArrayList();
    List sfList = new ArrayList();
    List nestedL1 = new ArrayList();
    List nestedL2 = new ArrayList();
    List nestedL3 = new ArrayList();

    nestedL2.add(nestedSF1);
    nestedL2.add(nestedSF2);

    // add first two files to one directory
    SimpleDirectory sd3 = new SimpleDirectory("Social Media", nestedL1, nestedL2);
    sdList.add(sd3);
    sfList.add(nestedSF3);
    // add previous directory and the third file to this directory
    SimpleDirectory sd4 = new SimpleDirectory("All Social Data", sdList, sfList);
    // check if all files are present

    assertEquals(4096, sd1.size());
    assertEquals(4096, sd2.size());
    assertEquals(4096, sd3.size());
    assertEquals(4096, sd4.size());

  }

  @org.junit.Test
  public void sizeTestFile() {
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments   ");
    StringFile sf2 = new StringFile("TikTok", "Reposts and Reports");
    assertEquals(21, sf1.size());
    assertEquals(19, sf2.size());
  }

  @org.junit.Test
  public void totalSizeTestDirectory() {
    // check search method on simple directory with no file

    // make file
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    List l1 = new ArrayList();
    List l2 = new ArrayList();
    // add empty lists of files to the directory
    SimpleDirectory sd1 = new SimpleDirectory("Social Media", l1, l2);
    // check if files exist
    assertTrue(sd1.search("Social Media"));
    assertFalse(sd1.search("Instagram"));
    assertFalse(sd1.search("Likes"));

    // check search method on simple directory with 2 files
    // make two new files
    StringFile sf21 = new StringFile("Twitter", "Likes and Comments");
    StringFile sf22 = new StringFile("Youtube", "Reposts and Reports");
    List l11 = new ArrayList();
    List l22 = new ArrayList();
    l22.add(sf21);
    l22.add(sf22);
    // add files to the directory
    SimpleDirectory sd2 = new SimpleDirectory("Social Media", l11, l22);
    // check if files exist
    assertTrue(sd2.search("Twitter"));
    assertTrue(sd2.search("Reposts"));
    assertFalse(sd2.search("Instagram"));
    assertTrue(sd2.search("Social Media"));

    // check search method on directory with nested directory and 3 files
    // make three different files, 2 for one directory, one for another
    StringFile nestedSF1 = new StringFile("Twitter", "Likes and Comments");
    StringFile nestedSF2 = new StringFile("Youtube", "Reposts and Reports");
    StringFile nestedSF3 = new StringFile("Instagram", "Reels and Posts");

    List sdList = new ArrayList();
    List sfList = new ArrayList();
    List nestedL1 = new ArrayList();
    List nestedL2 = new ArrayList();
    List nestedL3 = new ArrayList();

    nestedL2.add(nestedSF1);
    nestedL2.add(nestedSF2);

    // add first two files to one directory
    SimpleDirectory sd3 = new SimpleDirectory("Social Media", nestedL1, nestedL2);
    sdList.add(sd3);
    sfList.add(nestedSF3);
    // add previous directory and the third file to this directory
    SimpleDirectory sd4 = new SimpleDirectory("All Social Data", sdList, sfList);
    // check if all files are present

    assertEquals(4096, sd1.totalSize());
    assertEquals(4133, sd2.totalSize());
    assertEquals(4133, sd3.totalSize());
    assertEquals(8244, sd4.totalSize());
  }

  @org.junit.Test
  public void totalSizeTestFile() {
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    StringFile sf2 = new StringFile("TikTok", "Reposts and Reports    ");
    assertEquals(18, sf1.totalSize());
    assertEquals(23, sf2.totalSize());
  }

  @org.junit.Test
  public void testValidRofsConstructor() {
    StringFile compressed = new StringFile("compressed.zip", "Likes and Comments");
    StringFile expName = new StringFile("exp-name.log", "Reposts and Reports");
    StringFile data = new StringFile("data.log", "Reels and Posts");
    StringFile coolThing = new StringFile("cool-thing.txt", "Reels and Posts");

    List expDirList = new ArrayList();
    List experimentFileList = new ArrayList();
    List libDirList = new ArrayList();
    List libFileList = new ArrayList();
    List dataDirList = new ArrayList();
    List dataFileList = new ArrayList();
    List fileSysDirList = new ArrayList();
    List fileSysFileList = new ArrayList();
    List rootDirList = new ArrayList();
    List rootFileList = new ArrayList();

    fileSysFileList.add(data);

    experimentFileList.add(compressed);
    experimentFileList.add(expName);
    libFileList.add(coolThing);

    SimpleDirectory sdFileSys = new SimpleDirectory("filesystem", fileSysDirList, fileSysFileList);
    expDirList.add(sdFileSys);
    SimpleDirectory sdExp = new SimpleDirectory("experiment", expDirList, experimentFileList);
    dataDirList.add(sdExp);
    SimpleDirectory sdLib = new SimpleDirectory("lib", libDirList, libFileList);
    SimpleDirectory sdData = new SimpleDirectory("data", dataDirList, dataFileList);
    rootDirList.add(sdData);
    rootDirList.add(sdLib);
    SimpleDirectory rootDir = new SimpleDirectory("root", rootDirList, rootFileList);
    ReadOnlyFileSystem rofsExample = new ReadOnlyFileSystem(1000000, rootDir);

    assertTrue(rofsExample.capacity() > 0);
    Assert.assertNotNull(rofsExample.capacity());
  }

  @org.junit.Test
  public void testInvalidRofsConstructor() {
    StringFile compressed = new StringFile("compressed.zip", "Likes and Comments");
    StringFile expName = new StringFile("exp-name.log", "Reposts and Reports");
    StringFile data = new StringFile("data.log", "Reels and Posts");
    StringFile coolThing = new StringFile("cool-thing.txt", "Reels and Posts");

    List expDirList = new ArrayList();
    List experimentFileList = new ArrayList();
    List libDirList = new ArrayList();
    List libFileList = new ArrayList();
    List dataDirList = new ArrayList();
    List dataFileList = new ArrayList();
    List fileSysDirList = new ArrayList();
    List fileSysFileList = new ArrayList();
    List rootDirList = new ArrayList();
    List rootFileList = new ArrayList();

    fileSysFileList.add(data);

    experimentFileList.add(compressed);
    experimentFileList.add(expName);
    libFileList.add(coolThing);

    SimpleDirectory sdFileSys = new SimpleDirectory("filesystem", fileSysDirList, fileSysFileList);
    expDirList.add(sdFileSys);
    SimpleDirectory sdExp = new SimpleDirectory("experiment", expDirList, experimentFileList);
    dataDirList.add(sdExp);
    SimpleDirectory sdLib = new SimpleDirectory("lib", libDirList, libFileList);
    SimpleDirectory sdData = new SimpleDirectory("data", dataDirList, dataFileList);
    rootDirList.add(sdData);
    rootDirList.add(sdLib);
    SimpleDirectory rootDir = new SimpleDirectory("root", rootDirList, rootFileList);
    ReadOnlyFileSystem rofsExample = new ReadOnlyFileSystem(1000000, rootDir);

    Assert.assertThrows(IllegalArgumentException.class, () -> new ReadOnlyFileSystem(0, rootDir));

  }

  @org.junit.Test
  public void testValidDirectoryConstructor() {
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    List l1 = new ArrayList();
    List l2 = new ArrayList();
    // add empty lists of files to the directory
    SimpleDirectory sd1 = new SimpleDirectory("Social Media", l1, l2);
    assertEquals("Social Media", sd1.name());
    assertEquals(new ArrayList(), sd1.contents());
  }

  @org.junit.Test
  public void testInvalidDirectoryConstructor() {
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    List l1 = new ArrayList();
    List l2 = new ArrayList();
    Assert.assertThrows(IllegalArgumentException.class, () -> new SimpleDirectory("", l1, l2));
  }

  @org.junit.Test
  public void testValidFileConstructor() {
    StringFile sf1 = new StringFile("Instagram", "Likes and Comments");
    StringFile sf2 = new StringFile("TikTok", "Reposts and Reports    ");
    assertEquals("Instagram", sf1.name());
    assertEquals("TikTok", sf2.name());
  }

  @org.junit.Test
  public void testInvalidFileConstructor() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new StringFile("", "likes"));

  }




}
