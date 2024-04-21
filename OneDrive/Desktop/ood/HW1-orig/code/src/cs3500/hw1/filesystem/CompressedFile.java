package cs3500.hw1.filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Represents a content file as a Compressed File.
 */
public class CompressedFile implements ContentFile {

  private String name;
  private List<ExtFile> allFiles;

  /**
   * Represents a Compressed File fields.
   * @param name name of the compressed file
   * @param files name of the files being compressed
   */
  public CompressedFile(String name, List<ExtFile> files) {

    this.name = Objects.requireNonNull(name);
    this.allFiles = new ArrayList();
    if (name.isEmpty()) {
      throw new IllegalArgumentException("name of file cannot be empty");
    }
    if (files.contains(this)) {
      throw new IllegalArgumentException("contents cannot contain self or parent on construction");
    }
    this.allFiles.addAll(files);
    Collections.sort(this.allFiles, Comparator.comparing(ExtFile::name));
  }

  /**
   * Converts the file into a string.
   * @return String of file name and size
   */
  public String toString() {
    return this.name + "(" + this.size() + " b)";
  }

  /**
   * Generates a list of the compressed file's content names.
   * @return String of the file contents
   */
  @Override
  public String contents() {
    String inside = "";
    if (!this.allFiles.isEmpty()) {
      for (ExtFile ef: this.allFiles) {
        inside = inside + ef.name() + "\n";
      }
    }
    return inside;
  }

  /**
   * Observes the compressed file name.
   * @return String of file name
   */
  @Override
  public String name() {
    return this.name;
  }

  /**
   * Produces the size of the compressed file.
   * @return Long of file size
   */
  @Override
  public long size() {
    long s = 0;
    for (ExtFile ef: this.allFiles) {
      s = ef.totalSize() + s;
    }
    return ((s - (s % 8)) / 8);

  }

  /**
   * Produces the size of the compressed file.
   * @return Long of file size
   */

  @Override
  public long totalSize() {
    return this.size();
  }

  /**
   * Determines if a given file name is contained in the file contents.
   * @param phrase the phrase to find in the file
   * @return true if and only if the name contains the phrase
   */
  @Override
  public boolean search(String phrase) {
    return this.name().contains(phrase);
  }

  /**
   * Produces a visual of the compressed file path.
   * @return String of the path in a visual format
   */

  @Override
  public String prettyPrint() {
    return "+-" + this.name();
  }
}
