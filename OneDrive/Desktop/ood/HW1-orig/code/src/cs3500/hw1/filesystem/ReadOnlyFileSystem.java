package cs3500.hw1.filesystem;

import java.util.Objects;

/**
 * A basic read-only file system. A file system is a container of files.
 * The system always contains a directory called the root directory
 * that can then contain multiple files or other directories.
 **
 * This file system keeps track of the capacity of the hard disk
 * it is normally placed (technically "mounted") upon.
 */
public class ReadOnlyFileSystem {

  private ExtDirectory root;
  private long capacity;

  /**
   * Creates a read only file system with the given capacity and
   * containing the files in the given root directory.
   * @param capacity the amount of bytes this file system can hold
   * @param root the top-most directory of the system
   * @throws IllegalArgumentException if capacity is negative
   * @throws NullPointerException if root is null
   * @throws IllegalArgumentException if root's total size is greater than the capacity
   */
  public ReadOnlyFileSystem(long capacity, ExtDirectory root) {
    if (capacity < 0) {
      throw new IllegalArgumentException("capacity must be non-negative");
    }
    this.capacity = capacity;
    this.root = Objects.requireNonNull(root);
    if (root.totalSize() > this.capacity) {
      throw new IllegalArgumentException("files cannot exceed the capacity of the system");
    }
  }

  /**
   * Returns the size of a file system, defined as the size of all files
   * contained within it.
   * @return the size of the files in the system in bytes
   */
  public long used() {
    return root.totalSize();
  }

  /**
   * Returns the amount of bytes the file system can handle.
   * @return the capacity of the system in bytes
   */
  public long capacity() {
    return this.capacity;
  }

  /**
   * Returns true if and only if the phrase is found in the name
   * of a file or a file's contents.
   * @param phrase the phrase to look for
   * @return true if and only if that phrase exists in the system
   */
  public boolean search(String phrase) {
    return this.root.search(phrase);
  }

  /**
   * Produces the file system's capacity and root directory name and file size.
   * @return a string representing the file system
   */
  public String toString() {
    return "capacity=" + this.capacity + "b with root directory " + this.root.toString();
  }

  /**
   * Produces the absolute path of a file system.
   * @param file whose path must be found
   * @return String of the file's path
   */
  public String path(ExtFile file) {
    try {
      this.root.search(file.name());
    } catch (Exception e) {
      throw new IllegalArgumentException("No Such File Found in File System");
    }

    return this.root.path(file);

  }

  /**
   * Produces a visual of the file system's path.
   * @return String of the file system's root path
   */
  public String prettyPrint() {
    return "+-" + this.root.name() + "/" + this.root.prettyPrint() ;

  }

}
