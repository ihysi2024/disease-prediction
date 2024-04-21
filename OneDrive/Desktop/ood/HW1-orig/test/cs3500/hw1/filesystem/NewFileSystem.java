package cs3500.hw1.filesystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents tests for Compressed Files.
 */
public class NewFileSystem {
  ExtFile ef1;
  ExtFile ef2;
  ExtFile ef3;
  ExtFile ef4;
  ExtFile ef5;
  ExtFile ef6;
  ExtFile ef7;

  List allClasses;
  List allDirs;
  List dirsClasses;
  List allResources;
  List allDirs1;
  List dirsClasses1;
  SimpleDirectory sd1;
  SimpleDirectory sd2;
  CompressedFile cf1;
  CompressedFile cf2;

  @Before
  public void setUp() {
    this.ef1 = new StringFile("ood.txt", "lectures and labs");
    this.ef2 = new StringFile("database.log", "problems, office hours");
    this.ef3 = new StringFile("fundies.py", "homework");
    this.allClasses = new ArrayList();
    this.allDirs = new ArrayList();
    this.dirsClasses = new ArrayList();
    this.sd1 = new SimpleDirectory("cs class", this.allDirs, this.dirsClasses);
    this.allClasses.add(this.ef1);
    this.allClasses.add(this.ef2);
    this.allClasses.add(this.ef3);
    this.allClasses.add(this.sd1);

    this.cf1 = new CompressedFile("code.zip", this.allClasses);

    this.ef4 = new StringFile("tutorials", "youtube, khan academy");
    this.ef5 = new StringFile("notes.app", " GoodNotes   One Note, Notes App, Google Docs");
    this.ef6 = new StringFile("canvas", "dashboard, courses, calendar");
    this.ef7 = new StringFile("registration", " student hub, northeastern   ");


    this.allResources = new ArrayList();
    this.allDirs1 = new ArrayList();
    this.dirsClasses1 = new ArrayList();
    this.dirsClasses1.add(this.ef4);
    this.sd2 = new SimpleDirectory("some stuff", this.allDirs1, this.dirsClasses1);
    this.allResources.add(this.ef5);
    this.allResources.add(this.ef6);
    this.allResources.add(this.ef7);
    this.allResources.add(this.sd2);

    this.cf2 = new CompressedFile("resources", this.allResources);

  }

  @Test
  public void testValidFileConstruction() {
    Assert.assertEquals("code.zip", cf1.name());
    Assert.assertEquals("resources", cf2.name());
  }

  @Test
  public void testInvalidFileConstruction() {
    List resources = new ArrayList();
    resources.add(cf1);
    CompressedFile cf1 = new CompressedFile("resources", resources);
    Assert.assertThrows(IllegalArgumentException.class,
            () -> new CompressedFile("", this.allClasses));
  }

  @Test
  public void testCFToString() {
    Assert.assertEquals("code.zip(517 b)", this.cf1.toString());
    Assert.assertEquals("resources(527 b)", this.cf2.toString());
  }

  @Test
  public void testCFContents() {
    String allContents = "canvas" + "\n" + "notes.app" + "\n" +
            "registration" + "\n" + "some stuff" + "\n";
    Assert.assertEquals(allContents, this.cf2.contents());

    String allContents1 = "cs classes" + "\n" + "database.log" +
            "\n" + "fundies.py" + "\n" + "ood.txt" + "\n";
    Assert.assertEquals(allContents1, this.cf1.contents());
  }

  @Test
  public void testCFName() {
    Assert.assertEquals("resources", this.cf2.name());
    Assert.assertEquals("code.zip", this.cf1.name());
  }

  @Test
  public void testCFSize() {
    Assert.assertEquals(527, this.cf2.size());
    Assert.assertEquals(517, this.cf1.size());
  }

  @Test
  public void testCFTotalSize() {
    Assert.assertEquals(527, this.cf2.totalSize());
    Assert.assertEquals(517, this.cf1.totalSize());
  }

  @Test
  public void testCFSearch() {
    Assert.assertTrue(cf1.search("code"));
    Assert.assertFalse(cf1.search("tutorials"));
    Assert.assertTrue(cf2.search("resources"));
    Assert.assertFalse(cf2.search("notes.app"));
  }

  @Test
  public void testCFPrettyPrint() {
    Assert.assertEquals("+-code.zip", cf1.prettyPrint());
    Assert.assertEquals("+-resources", cf2.prettyPrint());
  }

}
