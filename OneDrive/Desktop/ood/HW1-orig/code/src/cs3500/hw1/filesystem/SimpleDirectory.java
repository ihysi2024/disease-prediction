package cs3500.hw1.filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of a directory as a container of files.
 * Of note is the directory is also part of an undirected graph,
 * meaning it contains references to its parent node.
 */
public class SimpleDirectory implements ExtDirectory {

  private List<ExtFile> dirContents;
  private String name;

  private List<ContentFile> files;

  private List<ExtDirectory> dirs;
  /**
   * Creates a directory of the given name with the contents given.
   * Note the contents given must NOT include the . and .. directories.
   * If the parent is null, this directory is assumed to be a root directory.
   *
   * @param name     the name of the directory
   * @param dirs     the directories contained in the directory
   * @param contents the content files contained in the directory
   * @throws NullPointerException     if name or contents are null
   * @throws IllegalArgumentException if name is empty (0 characters)
   * @throws IllegalArgumentException if contents contains this directory
   */

  public SimpleDirectory(String name, List<ExtDirectory> dirs, List<ContentFile> contents) {

    this.dirs = dirs;
    this.files = contents;
    this.name = Objects.requireNonNull(name);
    if (name.isEmpty()) {
      throw new IllegalArgumentException("name of file cannot be empty");
    }
    this.dirContents = new ArrayList<>(dirs);
    if (contents.contains(this)) {
      throw new IllegalArgumentException("contents cannot contain self or parent on construction");
    }
    this.dirContents.addAll(contents);
    Collections.sort(this.dirContents, Comparator.comparing(ExtFile::name));
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public long size() {
    return 4 * Size.KILOBYTE.inBytes;
  }

  @Override
  public boolean search(String phrase) {
    //1. Streams allow you to use list abstraction methods / list comprehensions
    //2. <class/interface-name>::<method-name> gives you a free anonymous function for that method
    //3. Hover over the methods for more information about what they do
    return (this.name.contains(phrase) || !this.dirContents.stream()
            .filter(f -> f.search(phrase))
            .collect(Collectors.toList())
            .isEmpty());
  }

  @Override
  public List<ExtFile> contents() {
    return new ArrayList<>(this.dirContents);
  }

  @Override
  public long totalSize() {
    return this.dirContents.stream()
        .map(ExtFile::totalSize)
        .reduce(this.size(), Long::sum);
  }

  /**
   * Produces the path of a directory from source to given file.
   * @param file given ExtFile to find
   * @return String of file's path
   */
  public String path(ExtFile file) {
    String starter = "";
    if (this.name().equals(file.name())) {
      starter = starter + file.name();
    } else if (this.files.contains(file)) {
      starter = starter + this.name() + "/" + file.name();
    } else {
      while (!starter.contains(file.name())) {
        for (ExtDirectory ed : this.dirs) {
          if (ed.search(file.name())) {
            starter = starter + this.name() + "/" + ed.path(file);
          }
        }
      }
    }
    return starter;
  }

  /**
   * Produces a visual representation of the directory's structure.
   * @return String of all contained directories and files.
   */
  public String prettyPrint() {
    String fullVisual = "\n";

    if (!this.dirs.isEmpty()) {

      for (int i = 0; i < this.dirs.size(); i++) {
        ExtDirectory ed = this.dirs.get(i);
        if ((i == this.dirs.size() - 1) && (this.files.isEmpty())) {
          fullVisual = fullVisual + "| " + "+-" + ed.name() + "/";
        } else {
          fullVisual = fullVisual + "| " + "+-" + ed.name() + "/" + "\n";
          fullVisual = fullVisual + prefix(ed.prettyPrint());
        }
      }
    }

    if (!this.files.isEmpty()) {
      for (int i = 0; i < this.files.size(); i++) {
        ExtFile ef = this.files.get(i);
        if ((i == this.files.size() - 1) && (this.dirs.isEmpty())) {
          fullVisual = fullVisual + "| " + ef.prettyPrint();
        } else {
          fullVisual = fullVisual + "| " + ef.prettyPrint() + "\n";
        }

      }
    }
    return fullVisual;
  }

  /**
   * Adds a prefix to each line of prettyPrint.
   * @param a String to add prefix to.
   * @return String with necessary adjustments.
   */
  public String prefix(String a) {
    String newStr = "";
    for (String s: a.split("\n")) {
      if (s.startsWith("+") || s.startsWith("| ")) {
        newStr = newStr + "| " + s + "\n";
      }
    }
    return newStr;
  }


  public String toString() {
    return this.name + " with " + this.dirContents.size() + " files";
  }
}
